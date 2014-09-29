package com.zaraxxx.apps.veranda;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Zaraxxx on 16/09/2014.
 */
public class UDPFunctions {

    Activity currentactivity;

    void sendUDPmessage(String message) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(currentactivity);
        String IP = sharedPrefs.getString("prefIPServeur", currentactivity.getString(R.string.prefIPServeur_defaultValue));
        String strPort = sharedPrefs.getString("prefPortServeur", currentactivity.getString(R.string.prefPortServeur_defaultValue));
        new nwcomm().execute(message, IP, strPort);
    }

    public UDPFunctions(Activity activity) {
        currentactivity = activity;
    }

    private class nwcomm extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {

            String msg = msgs[0].toString();
            String ipaddress = msgs[1].toString();
            int port = Integer.parseInt(msgs[2].toString());

            // TODO Auto-generated method stub


            InetAddress to = null;
            try {
                to = InetAddress.getByName(ipaddress);
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            DatagramSocket soc = null;
            try {
                soc = new DatagramSocket();
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            byte[] data = msg.getBytes();
            DatagramPacket pac = new DatagramPacket(data, data.length, to, port);
            try {
                soc.send(pac);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

    }
}
