package com.giorgione.nazzaro.countershock.share;

import java.util.ArrayList;

/**
 * Created by 91juv on 01/10/2016.
 */
public class Feedback {

    private ArrayList<Feedback> listaFeedback=new ArrayList<Feedback>();
    private String email,title,body;
    private int road,id;
    private float voto;

    public Feedback(int id,String email,
                    int road,String title, String body ,float voto){
        this.id=id;
        this.email=email;
        this.road=road;
        this.title=title;
        this.body=body;
        this.voto=voto;
    }
    public int getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String getBody(){
        return body;
    }
    public int getRoad(){
        return road;
    }
    public float getVoto(){
        return voto;
    }
    public String getTitle(){
        return title;
    }
    public ArrayList<Feedback> getListafeedback(){
        return listaFeedback;
    }
}

