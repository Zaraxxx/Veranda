package com.zaraxxx.apps.veranda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends FragmentActivity  {

    public static final int BUFFER_SIZE = 2048;
    private Socket sck = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    String response = "";
    SharedPreferences sharedPrefs;


    //TextView textIn;
    final Handler handler = new Handler();
    final Runnable updateUI = new Runnable() {
        public void run() {
            // receive TCP string
            //Toast.makeText(getParent(), "ttt", Toast.LENGTH_SHORT).show();
            //textIn.setText(response);
            ReadIncommingString();
        }
    };

    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar actionBar;

 //   private TCPClient mTcpClient;

    //interface myinterface(String str);
    void ReadIncommingString(){
        if (response.contains("[OA1]")){


        } else {
            sharedPrefs.edit().putString("pref", "Unknown command: " + response).apply();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

        //OpenConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();

       // CloseConnection();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        Tab = (ViewPager)findViewById(R.id.pager);


        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar = getActionBar();
                        if (actionBar != null) {
                            actionBar.setSelectedNavigationItem(position);
                        }
                    }
                });
        Tab.setAdapter(TabAdapter);
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
                Tab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

            }
        };
        //Add New Tab
        actionBar.addTab(actionBar.newTab().setText(getString(R.string.lblPage1)).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText(getString(R.string.lblPage2)).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Mode Checkbox").setTabListener(tabListener));


        // connect to the server

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String SERVERIP = sharedPrefs.getString("prefIPServeur", getString(R.string.prefIPServeur_defaultValue));
        String strPort = sharedPrefs.getString("prefPortServeur", getString(R.string.prefPortServeur_defaultValue));
        int SERVERPORT = Integer.parseInt(strPort);

       // new connectTask().execute(SERVERIP,strPort );

       // OpenConnection(SERVERIP, SERVERPORT);

    }



    public void OpenConnection(){


        new Thread(new Runnable(){
            public void run(){
                try{
                    if (sck == null){
                        //sck = new Socket(ip, port);
                        sck = new Socket("192.168.0.18", 23);
                        sck.setKeepAlive(true);
                        screenConfig();

                        in = new BufferedReader(new InputStreamReader(sck.getInputStream()));
                        out = new PrintWriter(sck.getOutputStream());

                        sendDataWithString("Connected");

                        int charsRead = 0;
                        char[] buffer = new char[BUFFER_SIZE];

                        while ((charsRead = in.read(buffer)) != -1) {
                            response += new String(buffer).substring(0, charsRead);
                            handler.post( updateUI );
                        }

                        screenConfig();
                    }
                }
                catch (IOException e) {
                    System.out.print(e+"");
                }
                catch (Exception e) {
                    System.out.print(e+"");
                }
            }
        }).start();
    }
    public void screenConfig(){
        if (sck.isClosed() == false){
//            textOut.setClickable(true);
//            btnSend.setClickable(true);
//            btnDisconnect.setClickable(true);
//            btnConnect.setClickable(false);
        }else{
//            textOut.setClickable(false);
//            btnSend.setClickable(false);
//            btnDisconnect.setClickable(false);
//            btnConnect.setClickable(true);
        }
    }

    public void SendMessage(String msg){
        sendDataWithString(msg);
    }


    public void CloseConnection(){
        if (sck != null) {
            try {
                sck.close();
                screenConfig();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                sck = null;
            }
        }
    }

    public void sendDataWithString(String message) {
        if (message != null && sck != null) {
            out.write(message);
            out.flush();
        }
    }

boolean isConnected(){
    boolean result = false;
    if (sck != null) {
       result = sck.isConnected();
    }
    return result;
}


    //     ----------------------------------------------------



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //case RESULT_SETTINGS:
            case 1:
                //showUserSettings();
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_settings:
                Intent i = new Intent(this, UserSettingActivity.class);
                //startActivityForResult(i, RESULT_SETTINGS);
                startActivityForResult(i, 1);
                break;
            case R.id.menu_testTCP:
                Intent i2 = new Intent(this, TCPClient1Activity.class);
                //startActivityForResult(i2, RESULT_SECONDACTIVITY);
                startActivityForResult(i2, 2);
                break;
        }
        return true;

    }

//    public class connectTask extends AsyncTask<String, String, TCPClient> {
//
//        @Override
//        protected TCPClient doInBackground(String... message) {
//
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
//            mTcpClient.run();
//
//            //Toast.makeText(this ,"Connecté",Toast.LENGTH_SHORT).show();
//
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//
//            //in the arrayList we add the messaged received from server
//            //arrayList.add(values[0]);
//            // notify the adapter that the data set has changed. This means that new message received
//            // from server was added to the list
//            //mAdapter.notifyDataSetChanged();
//        }
//    }

}
