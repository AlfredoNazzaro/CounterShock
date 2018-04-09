package com.giorgione.nazzaro.countershock.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.activity.UserActivity;
import com.giorgione.nazzaro.countershock.database.DBManager;
import com.giorgione.nazzaro.countershock.share.User;

public class LoginFragment extends Fragment {

    private DBManager mydb;
    private SharedPreferences sp;

    public LoginFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_login, container, false);

        mydb = new DBManager(this.getActivity());
        mydb=mydb.open();
        sp=this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);

        final Button buttonlogin = (Button) view.findViewById(R.id.entry);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                final TextView textEmail = (TextView) view.findViewById(R.id.emailLog);
                String email= textEmail.getText().toString();
                final TextView textPass = (TextView) view.findViewById(R.id.passLog);
                String password= textPass.getText().toString();
                User user=mydb.login(email,password);
                if (user!=null) {
                    SharedPreferences.Editor e=sp.edit();
                    e.putString("user",user.getEmail());
                    e.putString("password",user.getPassword());
                    e.commit();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    Toast.makeText(getContext(),"accesso effettuato",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else
                    Toast.makeText(getContext(),"impossibile accedere, email o password errati",Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }

}
