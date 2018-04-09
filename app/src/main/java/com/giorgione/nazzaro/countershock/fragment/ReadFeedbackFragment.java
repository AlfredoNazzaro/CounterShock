package com.giorgione.nazzaro.countershock.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.activity.RoadAdapter;
import com.giorgione.nazzaro.countershock.database.DBManager;
import com.giorgione.nazzaro.countershock.share.*;

import java.util.ArrayList;

public class ReadFeedbackFragment extends Fragment {

    private String title, body;
    private float vote;

    public ReadFeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_read_feedback, container, false);

        Bundle b = getArguments();
        title = b.getString("title");
        body = b.getString("body");
        vote = b.getFloat("vote");

        final TextView TextTitle = (TextView) view.findViewById(R.id.readTitle);
        TextTitle.setText(title);
        final TextView TextBody = (TextView) view.findViewById(R.id.readBody);
        TextBody.setText(body);
        final RatingBar RatingVote = (RatingBar) view.findViewById(R.id.readVote);
        RatingVote.setRating(vote);

        return view;
    }
}
