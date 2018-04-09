package com.giorgione.nazzaro.countershock.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.giorgione.nazzaro.countershock.activity.MainActivity;

import com.giorgione.nazzaro.countershock.R;
import com.giorgione.nazzaro.countershock.database.DBManager;
import com.giorgione.nazzaro.countershock.share.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private DBManager mydb;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registration, container, false);
        final TextView txt_name = (TextView) view.findViewById(R.id.name);

        mydb = new DBManager(this.getActivity());
        mydb=mydb.open();

        final Button txt_show1 = (Button) view.findViewById(R.id.buttonreset);

        txt_show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView name = (TextView) view.findViewById(R.id.name);
                name.setText("");
                final TextView surname = (TextView) view.findViewById(R.id.surname);
                surname.setText("");
                final TextView email = (TextView) view.findViewById(R.id.email);
                email.setText("");
                final TextView password = (TextView) view.findViewById(R.id.password);
                password.setText("");
            }
        });

        final Button log= (Button) view.findViewById(R.id.buttonlogin);
        log.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                final TextView Temail = (TextView) view.findViewById(R.id.email);
                String email = Temail.getText().toString();
                final TextView Tname = (TextView) view.findViewById(R.id.name);
                String name = Tname.getText().toString();
                final TextView Tsurname = (TextView) view.findViewById(R.id.surname);
                String surname = Tsurname.getText().toString();
                final TextView Tpass = (TextView) view.findViewById(R.id.password);
                String password = Tpass.getText().toString();

                if (mydb.getUserByEmail(email))
                    Toast.makeText(getContext(), "impossibile inserire, l'utente già esiste", Toast.LENGTH_LONG).show();
                else {
                    mydb.insertUser(email, name, surname, password);
                    Toast.makeText(getContext(), "registrazione effettuata", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getActivity(),MainActivity.class);
                    startActivity(i);
                }
            }
        });
        return view;


    }
}