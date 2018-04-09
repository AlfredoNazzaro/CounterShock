package com.giorgione.nazzaro.countershock.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.share.Percorso;

import java.util.ArrayList;

public class RoadAdapter extends BaseAdapter{

    ArrayList<Percorso> listaPercorsi;
    Context ctx;
    LayoutInflater layoutInflater;

    public RoadAdapter(ArrayList<Percorso> listaPercorsi, Context ctx){
        this.listaPercorsi=listaPercorsi;
        this.ctx=ctx;
        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listaPercorsi.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPercorsi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.list_road_row,parent,false);
        }
        Percorso p= (Percorso) getItem(position);
        // Lookup view for data population
        TextView partenza = (TextView) convertView.findViewById(R.id.part);
        TextView destinazione = (TextView) convertView.findViewById(R.id.dest);
        // Populate the data into the template view using the data object
        partenza.setText(p.getPartenza());
        destinazione.setText(p.getDestinazione());
        // Return the completed view to render on screen
        return convertView;
    }
}