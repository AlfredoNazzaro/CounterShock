package com.giorgione.nazzaro.countershock.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.activity.RoadAdapter;
import com.giorgione.nazzaro.countershock.database.DBManager;
import com.giorgione.nazzaro.countershock.share.Percorso;

import java.util.ArrayList;

public class ListRoadFragment extends Fragment {

    private DBManager mydb;

    public ListRoadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_list_feedback, container, false);

        mydb = new DBManager(this.getActivity());
        mydb = mydb.open();

        /*final ArrayList<Feedback> lf = mydb.getFeedbackByRoad();

        FeedbackAdapter adapter = new FeedbackAdapter(lf,getContext());
        // Attach the adapter to a ListView
        ListView lw= (ListView) view.findViewById(R.id.listFeedback);
        lw.setAdapter(adapter);
        */
        final ArrayList<Percorso> lp = mydb.getAllRoad();
        // Create the adapter to convert the array to views
        RoadAdapter adapter = new RoadAdapter(lp,getContext());


        ListView lw= (ListView) view.findViewById(R.id.listFeedback);
        lw.setAdapter(adapter);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Dialog builder = new Dialog (getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setContentView(inflater.inflate(R.layout.dialog, null));

                builder.create();
                Percorso p=lp.get(position);
                final TextView id_p= (TextView) builder.findViewById(R.id.did);
                id_p.setText(" "+Integer.toString(p.getId()));
                final TextView partenza= (TextView) builder.findViewById(R.id.dpart);
                partenza.setText(" "+p.getPartenza());
                final TextView destinazione= (TextView) builder.findViewById(R.id.ddest);
                destinazione.setText(" "+p.getDestinazione());
                final TextView num_fossi= (TextView) builder.findViewById(R.id.dnumfossi);
                num_fossi.setText(" "+Integer.toString(p.getNumero_fossi()));
                final TextView km= (TextView) builder.findViewById(R.id.dkm);
                km.setText(" "+Double.toString(p.getKm()));
                View b = builder.findViewById(R.id.buttonFeedback);
                b.setVisibility(View.GONE);
                View c = builder.findViewById(R.id.buttonReadFeedback);
                c.setVisibility(View.GONE);

                builder.show();
            }
        });

        return view;
    }
}
