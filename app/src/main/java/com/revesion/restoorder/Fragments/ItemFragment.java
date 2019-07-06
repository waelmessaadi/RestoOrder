package com.revesion.restoorder.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.revesion.restoorder.Activitys.EndActivity;
import com.revesion.restoorder.Activitys.HomeActivity;
import com.revesion.restoorder.Adapters.MyOrderRecyclerViewAdapter;
import com.revesion.restoorder.Entities.Menu;
import com.revesion.restoorder.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    double totaleMontants;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            final SQLiteDatabase liteDB = getActivity().openOrCreateDatabase("orderDb", MODE_PRIVATE, null);

            Cursor crs = liteDB.rawQuery("SELECT * FROM commands;", null);

            ArrayList<Menu> listCommands = new ArrayList<Menu>();

            totaleMontants = 0.0;

            while (crs.moveToNext()) {
                Menu m = new Menu();
                m.setNameMenu(crs.getString(1));
                m.setPrice(String.valueOf(crs.getDouble(2)));
                listCommands.add(m);
                totaleMontants += Double.parseDouble(crs.getString(2).replace("$", ""));
            }
            recyclerView.setAdapter(new MyOrderRecyclerViewAdapter(listCommands, mListener));

            TextView tot = getActivity().findViewById(R.id.montant);
            tot.setText("$ " + totaleMontants);

            Button annuler = getActivity().findViewById(R.id.cancel);
            annuler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liteDB.execSQL("DELETE FROM commands;");
                    totaleMontants = 0.0;
                    Intent i = new Intent(getActivity(), HomeActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            });

            Button terminer = getActivity().findViewById(R.id.terminer);
            terminer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liteDB.execSQL("DELETE FROM commands;");
                    totaleMontants = 0.0;
                    Intent i = new Intent(getActivity(), EndActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Menu item);
    }
}
