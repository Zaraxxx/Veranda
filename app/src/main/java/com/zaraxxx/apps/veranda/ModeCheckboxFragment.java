package com.zaraxxx.apps.veranda;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Zaraxxx on 17/09/2014.
 */
public class ModeCheckboxFragment extends Fragment {

    int baseDelay = 300;

    public SharedPreferences prefs;

    public UDPFunctions udp;

    public CheckBox cb1;
    public CheckBox cb2;
    public CheckBox cb3;
    public CheckBox cb4;
    public CheckBox cb5;
    public CheckBox cb6;
    public Button btOuvrir;
    public Button btStop;
    public Button btFermer;

    @Override
    public void onResume() {
        baseDelay = Integer.parseInt(prefs.getString("pref_DelayBetweenClicks", "300"));
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.modecheckbox_frag, container, false);

        udp = new UDPFunctions(getActivity());
        //prefs = getActivity().getSharedPreferences("com.zaraxxx.apps.veranda", Context.MODE_PRIVATE);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
       //prefsEditor = prefs.edit();


        cb1 = (CheckBox) frag.findViewById(R.id.cbF1);
        cb2 = (CheckBox) frag.findViewById(R.id.cbF2);
        cb3 = (CheckBox) frag.findViewById(R.id.cbF3);
        cb4 = (CheckBox) frag.findViewById(R.id.cbF4);
        cb5 = (CheckBox) frag.findViewById(R.id.cbF5);
        cb6 = (CheckBox) frag.findViewById(R.id.cbF6);

        btOuvrir = (Button) frag.findViewById(R.id.btOuvrir);
        btStop = (Button) frag.findViewById(R.id.btStop);
        btFermer = (Button) frag.findViewById(R.id.btFermer);

        cb1.setChecked(prefs.getBoolean("pref_cb1",true));
        cb2.setChecked(prefs.getBoolean("pref_cb2",true));
        cb3.setChecked(prefs.getBoolean("pref_cb3",true));
        cb4.setChecked(prefs.getBoolean("pref_cb4",true));
        cb5.setChecked(prefs.getBoolean("pref_cb5",false));
        cb6.setChecked(prefs.getBoolean("pref_cb6",false));

        SetClickListener();

        //baseDelay = getResources().getInteger(R.integer.delay_between_udp_messages);

        return frag;
    }

    public void SetClickListener(){

        cb1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View cbView) {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                prefs.edit().putBoolean("pref_cb1", cb1.isChecked()).apply();
            }
        });

        cb2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View cbView) {
                prefs.edit().putBoolean("pref_cb2", cb2.isChecked()).apply();
            }
        });

        cb3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View cbView) {
                prefs.edit().putBoolean("pref_cb3", cb3.isChecked()).apply();
            }
        });

        cb4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View cbView) {
                prefs.edit().putBoolean("pref_cb4", cb4.isChecked()).apply();
            }
        });

        cb5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View cbView) {
                prefs.edit().putBoolean("pref_cb5", cb5.isChecked()).apply();
            }
        });

        cb6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View cbView) {
                prefs.edit().putBoolean("pref_cb6", cb6.isChecked()).apply();
            }
        });



        btOuvrir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                OuvrirClick();
            }
        });

        btStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                StopClick();
            }
        });

        btFermer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FermerClick();
            }
        });

    }



    int sendUDPFenetresChecked(){


        //int baseDelay = getResources().getInteger(R.integer.delay_between_udp_messages);
        int totalBoutons = 0;


        if (cb1.isChecked()){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Fenetre1");
                }
            }, baseDelay * totalBoutons );
        }
        if (cb2.isChecked()){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Fenetre2");
                }
            }, baseDelay * totalBoutons );
        }
        if (cb3.isChecked()){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Fenetre3");
                }
            }, baseDelay * totalBoutons );
        }
        if (cb4.isChecked()){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Fenetre4");
                }
            }, baseDelay * totalBoutons );
        }
        if (cb5.isChecked()){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Fenetre5");
                }
            }, baseDelay * totalBoutons );
        }
        if (cb6.isChecked()){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Fenetre6");
                }
            }, baseDelay * totalBoutons );
        }
        return totalBoutons;
    }

    void OuvrirClick(){
        int totalBouton = sendUDPFenetresChecked();
        if (totalBouton != 0){
            totalBouton += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Ouvrir");
                }
            }, baseDelay * totalBouton );
        } else {
            Toast.makeText(getActivity(), "Il n'y a aucune fenètre de séléctionné", Toast.LENGTH_SHORT).show();
        }
    }

    void StopClick(){
        int totalBouton = sendUDPFenetresChecked();
        if (totalBouton != 0){
            totalBouton += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Stop");
                }
            }, baseDelay * totalBouton );
        } else {
            Toast.makeText(getActivity(), "Il n'y a aucune fenètre de séléctionné", Toast.LENGTH_SHORT).show();
        }
    }

    void FermerClick(){
        int totalBouton = sendUDPFenetresChecked();
        if (totalBouton != 0){
            totalBouton += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    udp.sendUDPmessage("Fermer");
                }
            }, baseDelay * totalBouton );
        } else {
            Toast.makeText(getActivity(), "Il n'y a aucune fenètre de séléctionné", Toast.LENGTH_SHORT).show();

        }

    }
}
