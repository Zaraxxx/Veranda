package com.zaraxxx.apps.veranda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Zaraxxx on 11/09/2014.
 */
public class ModeSimpleFragment extends Fragment {
//
    //private TCPClient mTcpClient;

    //UDPFunctions udp;
    //TCPPFunctions udp;

    Button btFenetre1;
    Button btFenetre2;
    Button btFenetre3;
    Button btFenetre4;
    Button btFenetre5;
    Button btFenetre6;
    Button btOuvrir;
    Button btStop;
    Button btFermer;

    String readString;


    @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.modesimple_frag, container, false);
        // ((TextView)ios.findViewById(R.id.textView)).setText("iOS");

        //udp = new UDPFunctions(getActivity());

        btFenetre1 = (Button) frag.findViewById(R.id.btF1);
        btFenetre2 = (Button) frag.findViewById(R.id.btF2);
        btFenetre3 = (Button) frag.findViewById(R.id.btF3);
        btFenetre4 = (Button) frag.findViewById(R.id.btF4);
        btFenetre5 = (Button) frag.findViewById(R.id.btF5);
        btFenetre6 = (Button) frag.findViewById(R.id.btF6);
        btOuvrir = (Button) frag.findViewById(R.id.btOuvrir);
        btStop = (Button) frag.findViewById(R.id.btStop);
        btFermer = (Button) frag.findViewById(R.id.btFermer);

        SetButtonsClickListeners();

        CheckConnection();
        return frag;
    }

    void CheckConnection(){
        boolean isConnected = false;
        isConnected = ((MainActivity)getActivity()).isConnected();
        SetButtonsEnabled(isConnected);
    }

    void SetButtonsEnabled(boolean enabled){
        btFenetre1.setEnabled(enabled);
        btFenetre2.setEnabled(enabled);
        btFenetre3.setEnabled(enabled);
        btFenetre4.setEnabled(enabled);
        btFenetre5.setEnabled(enabled);
        btFenetre6.setEnabled(enabled);
        btOuvrir.setEnabled(enabled);
        btStop.setEnabled(enabled);
        btFermer.setEnabled(enabled);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CheckConnection();
    }

    void SetButtonsClickListeners(){
      btFenetre1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Fenetre1");
          }
      });

      btFenetre2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Fenetre2");
          }
      });

      btFenetre3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Fenetre3");
          }
      });

      btFenetre4.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Fenetre4");
          }
      });

      btFenetre5.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Fenetre5");
          }
      });

      btFenetre6.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Fenetre6");
          }
      });

      btOuvrir.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Ouvrir");
          }
      });
      btStop.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Stop");
          }
      });
      btFermer.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendMessage("Fermer");
          }
      });
  }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CheckConnection();
    }

    @Override
    public void onResume() {
        super.onResume();
        CheckConnection();
    }

    public void sendMessage(String msg) {
        ((MainActivity)getActivity()).SendMessage(msg);
    }
    public boolean isConnected() {
        return ((MainActivity)getActivity()).isConnected();
    }
/*
    void Reconnect(){
        // connect to the server
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String SERVERIP = sharedPrefs.getString("prefIPServeur", getString(R.string.prefIPServeur_defaultValue));
        String strPort = sharedPrefs.getString("prefPortServeur", getString(R.string.prefPortServeur_defaultValue));
        //int SERVERPORT = Integer.parseInt(strPort);
        //new connectTask().execute(SERVERIP,strPort );

    }


    public class connectTask extends AsyncTask<String, String, TCPClient> {

        @Override
        protected TCPClient doInBackground(String... message) {

            String ip = message[0];
            String strport = message[1];
            int port = Integer.parseInt(strport);

            //we create a TCPClient object and
            mTcpClient = new TCPClient(ip, port,new TCPClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            readString = values[0];
            //Toast.makeText(getActivity(), "readString " + readString, Toast.LENGTH_SHORT).show();
            ReadReceivedValues(readString);
            //in the arrayList we add the messaged received from server
            //arrayList.add(values[0]);
            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
            //mAdapter.notifyDataSetChanged();

            super.onProgressUpdate(values);

        }

        void ReadReceivedValues(String msg){
           // Toast.makeText(getActivity(), "msg: " + msg, Toast.LENGTH_SHORT).show();

            if (msg.equals("ConnectionOK")){
               Toast.makeText(getActivity(), "Connecté", Toast.LENGTH_SHORT).show();

               //Button btFenetre1 = (Button) getActivity().findViewById(R.id.btF1);
               btFenetre1.setEnabled(false);



               // btFenetre1.setText("OK");
            }
        }
    }
    */
}
