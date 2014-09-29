package com.zaraxxx.apps.veranda;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.prefs.Preferences;

/**
 * Created by Zaraxxx on 10/09/2014.
 */
public class UserSettingActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener  {


//    //TCPFunctions tcp;
 //   UDPFunctions udp;
  //  private TCPClient mTcpClient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        if (USE_UDP){
//            udp = new UDPFunctions(this);
//        }
//        if (USE_TCP){
//            //tcp = new TCPFunctions(this);
//        }

        addPreferencesFromResource(R.xml.settings);
       // PreferenceManager.setDefaultValues(this, R.xml.settings, false);

        initSummary(getPreferenceScreen());


      //  Reconnect();



    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference pref = findPreference(key);

        updatePrefSummary(pref);
       //Toast.makeText(this, key , Toast.LENGTH_SHORT).show();

        // Enable OA
        if (key.equals("OA1_enabled")){
            if (sharedPreferences.getBoolean(key, false)){
                sendMessage("Set_OA1_E");
            } else {
                sendMessage("Set_OA1_D");
            }
        } else if (key.equals("OA2_enabled")){
            if (sharedPreferences.getBoolean(key, false)){
                sendMessage("Set_OA2_E");
            } else {
                sendMessage("Set_OA2_D");
            }
        } else if (key.equals("OA3_enabled")){
            if (sharedPreferences.getBoolean(key, false)){
                sendMessage("Set_OA3_E");
            } else {
                sendMessage("Set_OA3_D");
            }
        } else if (key.equals("FA1_enabled")){
            if (sharedPreferences.getBoolean(key, false)){
                sendMessage("Set_FA1_E");
            } else {
                sendMessage("Set_FA1_D");
            }
        } else if (key.equals("FA2_enabled")){
            if (sharedPreferences.getBoolean(key, false)){
                sendMessage("Set_FA2_E");
            } else {
                sendMessage("Set_FA2_D");
            }
        } else if (key.equals("FA3_enabled")){
            if (sharedPreferences.getBoolean(key, false)){
                sendMessage("Set_FA3_E");
            } else {
                sendMessage("Set_FA3_D");
            }

        // Values OA
        } else if (key.equals("OA1_value")){
            TimePreference tp = (TimePreference) pref;
            sendMessage("Set_OA1_H(" + tp.getHour() + ")");
            sendMessage("Set_OA1_M(" + tp.getMinute() + ")");
        } else if (key.equals("OA2_value")){
            TimePreference tp = (TimePreference) pref;
            sendMessage("Set_OA2_H(" + tp.getHour() + ")");
            sendMessage("Set_OA2_M(" + tp.getMinute() + ")");
        } else if (key.equals("OA3_value")){
            TimePreference tp = (TimePreference) pref;
            sendMessage("Set_OA3_H(" + tp.getHour() + ")");
            sendMessage("Set_OA3_M(" + tp.getMinute() + ")");
        } else if (key.equals("FA1_value")){
            TimePreference tp = (TimePreference) pref;
            sendMessage("Set_FA1_H(" + tp.getHour() + ")");
            sendMessage("Set_FA1_M(" + tp.getMinute() + ")");
        } else if (key.equals("FA2_value")){
            TimePreference tp = (TimePreference) pref;
            sendMessage("Set_FA2_H(" + tp.getHour() + ")");
            sendMessage("Set_FA2_M(" + tp.getMinute() + ")");
        } else if (key.equals("FA3_value")){
            TimePreference tp = (TimePreference) pref;
            sendMessage("Set_FA3_H(" + tp.getHour() + ")");
            sendMessage("Set_FA3_M(" + tp.getMinute() + ")");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);


        //sendMessage("Get_All_Config");

        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
       // Reconnect();

    }
    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    private void initSummary(Preference p) {
        if (p instanceof PreferenceGroup) {
            PreferenceGroup pGrp = (PreferenceGroup) p;
            for (int i = 0; i < pGrp.getPreferenceCount(); i++) {
                initSummary(pGrp.getPreference(i));
            }
        } else {
            updatePrefSummary(p);
        }
    }

    private void updatePrefSummary(Preference p) {

//        if (p instanceof SwitchPreference) {
//            SwitchPreference switchPref = (SwitchPreference) p;
//Toast.makeText(this, p.getKey(),Toast.LENGTH_SHORT).show();
//
//            if (p.getTitle() == "Heure 1") {
//                if (switchPref.isChecked()) {
//                    udp.sendUDPmessage("Set_OA1_E");
//                } else {
//                    udp.sendUDPmessage("Set_OA1_D");
//                }
//            } else if (p.getKey() == "OA2_enabled") {
//                if (switchPref.isChecked()){
//                    udp.sendUDPmessage("Set_OA2_E");
//                } else {
//                    udp.sendUDPmessage("Set_OA2_D");
//                }
//            } else if (p.getKey() == "OA3_enabled") {
//                if (switchPref.isChecked()){
//                    udp.sendUDPmessage("Set_OA3_E");
//                } else {
//                    udp.sendUDPmessage("Set_OA3_D");
//                }
//            } else if (p.getKey() == "FA1_enabled") {
//                if (switchPref.isChecked()){
//                    udp.sendUDPmessage("Set_FA1_E");
//                } else {
//                    udp.sendUDPmessage("Set_FA1_D");
//                }
//            } else if (p.getKey() == "FA2_enabled") {
//                if (switchPref.isChecked()){
//                    udp.sendUDPmessage("Set_FA2_E");
//                } else {
//                    udp.sendUDPmessage("Set_FA2_D");
//                }
//            } else if (p.getKey() == "FA3_enabled") {
//                if (switchPref.isChecked()){
//                    udp.sendUDPmessage("Set_FA3_E");
//                } else {
//                    udp.sendUDPmessage("Set_FA3_D");
//                }
//            }
//        }

        if (p instanceof TimePreference) {
            TimePreference timePref = (TimePreference) p;
            p.setTitle(timePref.getTime());
        }

        if (p instanceof ListPreference) {
            ListPreference listPref = (ListPreference) p;
            p.setSummary(listPref.getEntry());
        }
        if (p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference) p;
            //p.setSummary(editTextPref.getText());

            if (p.getTitle() == getString(R.string.pref_DelayBetweenClicks_title)) {
                p.setSummary(editTextPref.getText() + " ms");
            } else {
                p.setSummary(editTextPref.getText());
            }
        }
        if (p instanceof MultiSelectListPreference) {
            EditTextPreference editTextPref = (EditTextPreference) p;
            p.setSummary(editTextPref.getText());
        }


       /*
       // for named preferences

        if (p.getKey() == "OA1_enabled"){
         //SwitchPreference sp = (SwitchPreference) p;

         PreferenceManager pm = getPreferenceManager();

         Preference value_perf = pm.findPreference("OA1_value");

         value_perf.setEnabled(false);

        // pm.getSharedPreferences().getBoolean("OA1_enabled", false);


        }
        */
    }


    void sendMessage(String msg){

//        if (mTcpClient != null) {
//            mTcpClient.sendMessage(msg);
//        } else {
//            Toast.makeText(this, "Reconnecting",Toast.LENGTH_SHORT).show();
//            //Reconnect();
//        }
    }

}
