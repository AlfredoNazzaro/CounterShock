package com.giorgione.nazzaro.countershock.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.share.Feedback;

import java.util.ArrayList;

public class FeedbackAdapter extends BaseAdapter{

    ArrayList<Feedback> listaFeedback;
    Context ctx;
    LayoutInflater layoutInflater;

    public FeedbackAdapter(ArrayList<Feedback> listaFeedback, Context ctx){
        this.listaFeedback=listaFeedback;
        this.ctx=ctx;
        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listaFeedback.size();
    }

    @Override
    public Object getItem(int position) {
        return listaFeedback.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.feedback_row,parent,false);
        }
        Feedback f= (Feedback) getItem(position);
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.feedTitle);
        TextView email = (TextView) convertView.findViewById(R.id.feedEmail);
        // Populate the data into the template view using the data object
        email.setText(f.getEmail());
        title.setText(f.getTitle());
        // Return the completed view to render on screen
        return convertView;
    }
}
