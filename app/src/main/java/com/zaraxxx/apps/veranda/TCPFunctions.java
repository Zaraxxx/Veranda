package com.zaraxxx.apps.veranda;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Zaraxxx on 21/09/2014.
 */
public class TCPFunctions {

    Activity currentactivity;

//    private ListView mList;
//    private ArrayList<String> arrayList;
//    private MyCustomAdapter mAdapter;
    private TCPClient mTcpClient;
    Socket socket;


    void Connect() {

        //SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(currentactivity);
        //String IP = sharedPrefs.getString("prefIPServeur", currentactivity.getString(R.string.prefIPServeur_defaultValue));
        //String strPort = sharedPrefs.getString("prefPortServeur", currentactivity.getString(R.string.prefPortServeur_defaultValue));
        //new connectTask().execute("");

        new Thread(new ClientThread()).start();


    }

    void sendTCPmessage(String message) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(currentactivity);
        String IP = sharedPrefs.getString("prefIPServeur", currentactivity.getString(R.string.prefIPServeur_defaultValue));
        String strPort = sharedPrefs.getString("prefPortServeur", currentactivity.getString(R.string.prefPortServeur_defaultValue));
        //new sendTask().execute(message, IP, strPort);
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(
                          new OutputStreamWriter(socket.getOutputStream())),
            true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println(message);


    }

    public TCPFunctions(Activity activity) {
        currentactivity = activity;
    }

   /* public class connectTask extends AsyncTask<String,String,TCPClient> {

        @Override
        protected TCPClient doInBackground(String... message) {

            //we create a TCPClient object and
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    //publishProgress(message);
                    Toast.makeText(currentactivity, "received: " + message, Toast.LENGTH_SHORT).show();
                }
            });
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

//            //in the arrayList we add the messaged received from server
//            arrayList.add(values[0]);
//            // notify the adapter that the data set has changed. This means that new message received
//            // from server was added to the list
//            mAdapter.notifyDataSetChanged();
        }
    }
*/
    class ClientThread implements Runnable {



        @Override
        public void run() {
            try {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(currentactivity);
                String IP = sharedPrefs.getString("prefIPServeur", currentactivity.getString(R.string.prefIPServeur_defaultValue));
                String strPort = sharedPrefs.getString("prefPortServeur", currentactivity.getString(R.string.prefPortServeur_defaultValue));
                int port = Integer.parseInt(strPort);

                InetAddress serverAddr = InetAddress.getByName(IP);
                socket = new Socket(serverAddr, port);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
