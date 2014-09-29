package com.zaraxxx.apps.veranda;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
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
public class ModeAvanceFragment extends Fragment {

    int baseDelay = 300;
    public SharedPreferences prefs;

   // private TCPClient mTcpClient;
    UDPFunctions udp;

    Button btOuvrirF1;
    Button btOuvrirF2;
    Button btOuvrirF3;
    Button btOuvrirF4;
    Button btOuvrirF5;
    Button btOuvrirF6;

    Button btFermerF1;
    Button btFermerF2;
    Button btFermerF3;
    Button btFermerF4;
    Button btFermerF5;
    Button btFermerF6;

    Button btStopF1;
    Button btStopF2;
    Button btStopF3;
    Button btStopF4;
    Button btStopF5;
    Button btStopF6;

    Button btToutOuvrir;
    Button btToutFermer;


    @Override
    public void onResume() {
        baseDelay = Integer.parseInt(prefs.getString("pref_DelayBetweenClicks", "300"));
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.modeavance_frag, container, false);
        // ((TextView)ios.findViewById(R.id.textView)).setText("iOS");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        udp = new UDPFunctions(getActivity());

        btOuvrirF1 = (Button) frag.findViewById(R.id.btOuvrirF1);
        btOuvrirF2 = (Button) frag.findViewById(R.id.btOuvrirF2);
        btOuvrirF3 = (Button) frag.findViewById(R.id.btOuvrirF3);
        btOuvrirF4 = (Button) frag.findViewById(R.id.btOuvrirF4);
        btOuvrirF5 = (Button) frag.findViewById(R.id.btOuvrirF5);
        btOuvrirF6 = (Button) frag.findViewById(R.id.btOuvrirF6);

        btStopF1 = (Button) frag.findViewById(R.id.btStopF1);
        btStopF2 = (Button) frag.findViewById(R.id.btStopF2);
        btStopF3 = (Button) frag.findViewById(R.id.btStopF3);
        btStopF4 = (Button) frag.findViewById(R.id.btStopF4);
        btStopF5 = (Button) frag.findViewById(R.id.btStopF5);
        btStopF6 = (Button) frag.findViewById(R.id.btStopF6);

        btFermerF1 = (Button) frag.findViewById(R.id.btFermerF1);
        btFermerF2 = (Button) frag.findViewById(R.id.btFermerF2);
        btFermerF3 = (Button) frag.findViewById(R.id.btFermerF3);
        btFermerF4 = (Button) frag.findViewById(R.id.btFermerF4);
        btFermerF5 = (Button) frag.findViewById(R.id.btFermerF5);
        btFermerF6 = (Button) frag.findViewById(R.id.btFermerF6);

        btToutFermer = (Button) frag.findViewById(R.id.btToutFermer);
        btToutOuvrir = (Button) frag.findViewById(R.id.btToutOuvrir);

        SetButtonsClickListeners();

       //Reconnect();


        return frag;
    }

    void sendMessage(String msg){

//        if (mTcpClient != null) {
//            mTcpClient.sendMessage(msg);
//        } else {
//            Reconnect();
//        }

        udp.sendUDPmessage(msg);
    }

    void Reconnect(){
        // connect to the server
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String SERVERIP = sharedPrefs.getString("prefIPServeur", getString(R.string.prefIPServeur_defaultValue));
        String strPort = sharedPrefs.getString("prefPortServeur", getString(R.string.prefPortServeur_defaultValue));
        //int SERVERPORT = Integer.parseInt(strPort);
        new connectTask().execute(SERVERIP,strPort );

    }

    void SetButtonsClickListeners() {

        // OUVRIR
        btOuvrirF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre1");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Ouvrir");
                    }
                }, baseDelay);
            }
        });

        btOuvrirF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre2");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Ouvrir");
                    }
                }, baseDelay);
            }
        });

        btOuvrirF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre3");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Ouvrir");
                    }
                }, baseDelay);
            }
        });

        btOuvrirF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre4");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Ouvrir");
                    }
                }, baseDelay);
            }
        });

        btOuvrirF5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre5");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Ouvrir");
                    }
                }, baseDelay);
            }
        });

        btOuvrirF6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre6");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Ouvrir");
                    }
                },  baseDelay);
            }
        });



        //  STOP
        btStopF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre1");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage( "Stop");
                    }
                }, baseDelay);
            }
        });

        btStopF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre2");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage( "Stop");
                    }
                }, baseDelay);
            }
        });

        btStopF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre3");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage( "Stop");
                    }
                }, baseDelay);
            }
        });

        btStopF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage( "Fenetre4");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Stop");
                    }
                }, baseDelay);
            }
        });

        btStopF5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage( "Fenetre5");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Stop");
                    }
                }, baseDelay);
            }
        });

        btStopF6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre6");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage( "Stop");
                    }
                }, baseDelay);
            }
        });


        //  FERMER

        btFermerF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage( "Fenetre1");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage( "Fermer");
                    }
                },  baseDelay);
            }
        });

        btFermerF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre2");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage( "Fermer");
                    }
                }, baseDelay);
            }
        });


        btFermerF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage( "Fenetre3");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Fermer");
                    }
                },  baseDelay);
            }
        });

        btFermerF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre4");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Fermer");
                    }
                },  baseDelay);
            }
        });

        btFermerF5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre5");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage("Fermer");
                    }
                },  baseDelay);
            }
        });

        btFermerF6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("Fenetre6");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendMessage( "Fermer");
                    }
                },  baseDelay);
            }
        });
        //   TOUT OUVRIR
        btToutOuvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   Tout("Ouvrir");
            }
        });

        //   TOUT FERMER
        btToutFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tout("Fermer");
            }
        });
    }



    void Tout(final String action){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        boolean F1 = prefs.getBoolean("pref_TOUT_Command_F1", true);
        boolean F2 = prefs.getBoolean("pref_TOUT_Command_F2", true);
        boolean F3 = prefs.getBoolean("pref_TOUT_Command_F3", true);
        boolean F4 = prefs.getBoolean("pref_TOUT_Command_F4", true);
        boolean F5 = prefs.getBoolean("pref_TOUT_Command_F5", true);
        boolean F6 = prefs.getBoolean("pref_TOUT_Command_F6", true);

        //int baseDelay = getResources().getInteger(R.integer.delay_between_udp_messages);
        int totalBoutons = 0;


        if (F1){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F1", Toast.LENGTH_SHORT).show();
                    sendMessage( "Fenetre1");
                }
            }, baseDelay * totalBoutons );
        }

        if (F2){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F2", Toast.LENGTH_SHORT).show();
                    sendMessage( "Fenetre2");
                }
            }, baseDelay * totalBoutons );
        }

        if (F3){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F3", Toast.LENGTH_SHORT).show();
                    sendMessage("Fenetre3");
                }
            }, baseDelay * totalBoutons );
        }

        if (F4){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F4", Toast.LENGTH_SHORT).show();
                    sendMessage("Fenetre4");
                }
            }, baseDelay * totalBoutons );
        }

        if (F5){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F5", Toast.LENGTH_SHORT).show();
                    sendMessage("Fenetre5");
                }
            }, baseDelay * totalBoutons );
        }

        if (F6){
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), "F6", Toast.LENGTH_SHORT).show();
                    sendMessage( "Fenetre6");
                }
            }, baseDelay * totalBoutons );
        }

        if (totalBoutons > 0) {
            totalBoutons += 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(getActivity(), action , Toast.LENGTH_SHORT).show();
                    sendMessage( action);
                }
            }, baseDelay * totalBoutons );
        } else {
            Toast.makeText(getActivity(), "Aucune fenètres n'est paramètrée pour ce bouton", Toast.LENGTH_LONG).show();
        }
    }


    public class connectTask extends AsyncTask<String, String, TCPClient> {

        @Override
        protected TCPClient doInBackground(String... message) {

//            String ip = message[0];
//            String strport = message[1];
//            int port = Integer.parseInt(strport);
//
//            //we create a TCPClient object and
//            mTcpClient = new TCPClient(ip, port,new TCPClient.OnMessageReceived() {
//                @Override
//                //here the messageReceived method is implemented
//                public void messageReceived(String message) {
//                    //this method calls the onProgressUpdate
//                    publishProgress(message);
//                }
//            });
            //mTcpClient.run();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {


            //Toast.makeText(getActivity(), "readString " + readString, Toast.LENGTH_SHORT).show();
            ReadReceivedValues(values[0]);
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
                //btFenetre1.setEnabled(false);



                // btFenetre1.setText("OK");
            }
        }
    }

 }
