package com.revesion.restoorder.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.revesion.restoorder.Activitys.OrderActivity;
import com.revesion.restoorder.Entities.Resto;
import com.revesion.restoorder.Fragments.MenuFragment;
import com.revesion.restoorder.Fragments.RestoFragment;
import com.revesion.restoorder.Fragments.RestoFragment.OnListFragmentInteractionListener;
import com.revesion.restoorder.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.revesion.restoorder.Entities.Resto} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyRestoRecyclerViewAdapter extends RecyclerView.Adapter<MyRestoRecyclerViewAdapter.ViewHolder> {

    private final List<Resto> mValues;
    private final OnListFragmentInteractionListener mListener;
    TextView title;
    Button zero, showBuy;
    ImageView shop;

    public MyRestoRecyclerViewAdapter(List<Resto> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resto, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.nameResto.setText(mValues.get(position).getName());
        holder.cuisine.setText(mValues.get(position).getCuisine());
        holder.location.setText(mValues.get(position).getAddress());
        holder.min_order.setText(mValues.get(position).getMin_order_price());

        Picasso.get()
                .load(mValues.get(position).getImage())
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageResto);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
                holder.detailsResto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MenuFragment mf = new MenuFragment();

                        Bundle b = new Bundle();
                        b.putInt("idResto", mValues.get(position).getId());

                        mf.setArguments(b);

                        title = ((AppCompatActivity) holder.location.getContext()).findViewById(R.id.title_home);
                        title.setText(mValues.get(position).getName());

                        zero = ((AppCompatActivity) holder.location.getContext()).findViewById(R.id.zero_dollar);
                        zero.setVisibility(View.VISIBLE);

                        shop = ((AppCompatActivity) holder.location.getContext()).findViewById(R.id.shopping_cart);
                        shop.setVisibility(View.VISIBLE);

                        showBuy = ((AppCompatActivity) holder.location.getContext()).findViewById(R.id.btnContinue);
                        showBuy.setVisibility(View.VISIBLE);

                        showBuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(holder.location.getContext(), OrderActivity.class);
                                i.putExtra("nomRes", title.getText().toString());
                                holder.location.getContext().startActivity(i);

                            }
                        });

                        final ImageView retour = ((AppCompatActivity) holder.location.getContext()).findViewById(R.id.btn_return);
                        retour.setVisibility(View.VISIBLE);

                        retour.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RestoFragment rf = new RestoFragment();
                                FragmentTransaction ft = ((AppCompatActivity) holder.location.getContext()).getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, rf);
                                retour.setVisibility(View.INVISIBLE);
                                zero.setVisibility(View.INVISIBLE);
                                shop.setVisibility(View.INVISIBLE);
                                showBuy.setVisibility(View.INVISIBLE);
                                title.setText("Restaurant");
                                ft.commit();
                            }
                        });

                        FragmentTransaction ft = ((AppCompatActivity) holder.location.getContext()).getSupportFragmentManager().beginTransaction();


                        ft.replace(R.id.container, mf);
                        ft.commit();
                    }
                });
            }
        });
    }

    private void goToOrderActivity() {
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameResto;
        public final TextView cuisine;
        public final TextView location;
        public final TextView min_order;
        public final ImageView imageResto;

        public final ImageView detailsResto;


        public Resto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameResto = view.findViewById(R.id.nom_resto);
            cuisine = view.findViewById(R.id.type_food);
            min_order = view.findViewById(R.id.min_order);
            location = view.findViewById(R.id.localisation_resto);
            imageResto = view.findViewById(R.id.img_resto);
            detailsResto = view.findViewById(R.id.details_btn);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameResto.getText() + "'";
        }
    }
}
