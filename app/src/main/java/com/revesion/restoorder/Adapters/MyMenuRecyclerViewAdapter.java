package com.revesion.restoorder.Adapters;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.revesion.restoorder.Entities.Menu;
import com.revesion.restoorder.Fragments.MenuFragment.OnListFragmentInteractionListener;
import com.revesion.restoorder.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Menu} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMenuRecyclerViewAdapter extends RecyclerView.Adapter<MyMenuRecyclerViewAdapter.ViewHolder> {

    private final List<Menu> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMenuRecyclerViewAdapter(List<com.revesion.restoorder.Entities.Menu> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        holder.nomMenu.setText(mValues.get(position).getNameMenu());
        holder.descriptioMenu.setText(mValues.get(position).getDescription());
        holder.priceMenu.setText(mValues.get(position).getPrice());

    /*    Picasso.get().load(mValues.get(position).getPhotoMenu())
                .resize(80, 80)
                .centerCrop()
                .into(holder.imgMenu); */

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase liteDB = holder.addBtn.getContext().openOrCreateDatabase("orderDb", MODE_PRIVATE, null);
                    String nameMenu = mValues.get(position).getNameMenu();
                    String priceMenu = mValues.get(position).getPrice();
                    liteDB.execSQL("INSERT INTO commands VALUES(NULL,'" + nameMenu + "','" + priceMenu + "');");
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nomMenu;
        public final TextView descriptioMenu;
        public final TextView priceMenu;
        public final ImageView imgMenu;
        public final ImageView addBtn;


        public Menu mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nomMenu = view.findViewById(R.id.nom_menu);
            descriptioMenu = view.findViewById(R.id.description_menu);
            priceMenu = view.findViewById(R.id.price_menu);
            imgMenu = view.findViewById(R.id.photo_menu);
            addBtn = view.findViewById(R.id.add_btn);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + nomMenu.getText() + "'";
        }
    }
}
