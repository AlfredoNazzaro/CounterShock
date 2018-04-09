package com.giorgione.nazzaro.countershock.share;

import java.util.ArrayList;

/**
 * Created by 91juv on 14/09/2016.
 */
@SuppressWarnings("ALL")
public class Percorso {

    private ArrayList<Percorso> listaPercorsi=new ArrayList<Percorso>();
    private String partenza,destinazione;
    private double km;
    private int numero_fossi,id;

    public Percorso(int id,String partenza,
                   String destinazione,int numero_fossi,double km){
        this.id=id;
        this.partenza=partenza;
        this.destinazione=destinazione;
        this.numero_fossi=numero_fossi;
        this.km=km;
    }
    public int getId(){
        return id;
    }
    public String getPartenza(){
        return partenza;
    }
    public String getDestinazione(){
        return destinazione;
    }
    public int getNumero_fossi(){
        return numero_fossi;
    }
    public double getKm(){
        return km;
    }
    public ArrayList<Percorso> getListaPercorsi(){
        return listaPercorsi;
    }
}
