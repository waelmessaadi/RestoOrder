package com.revesion.restoorder.Adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.revesion.restoorder.Entities.Menu;
import com.revesion.restoorder.Fragments.ItemFragment.OnListFragmentInteractionListener;
import com.revesion.restoorder.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Menu} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyOrderRecyclerViewAdapter extends RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder> {

    private final List<Menu> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyOrderRecyclerViewAdapter(List<Menu> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_order, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nameMenu.setText(mValues.get(position).getNameMenu());
        holder.priceMenu.setText("$" + mValues.get(position).getPrice());

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
        TextView totale = ((AppCompatActivity) holder.priceMenu.getContext()).findViewById(R.id.montant);
        //totale.setText();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameMenu;
        public final TextView priceMenu;
        public Menu mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameMenu = view.findViewById(R.id.name);
            priceMenu = view.findViewById(R.id.price);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameMenu.getText() + "'";
        }
    }
}
