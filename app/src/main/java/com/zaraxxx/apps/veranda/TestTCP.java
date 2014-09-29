package com.zaraxxx.apps.veranda;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Flav on 28/09/2014.
 */
public class TestTCP extends Activity {
    Button btConnect;
    Button btDisconnect;
    Button btSend;
    EditText tbTextToSend;

    //private MyCustomAdapter mAdapter;
    private TCPClient mTcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testtcp_activity);

        btConnect = (Button) findViewById(R.id.btConnect);
        btDisconnect = (Button) findViewById(R.id.btDisconnect);
        btSend = (Button) findViewById(R.id.btSend);
        tbTextToSend = (EditText) findViewById(R.id.tbTextToSend);

        SetOnClickListener();
    }

    void SetOnClickListener() {
        btConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectClick();
            }
        });
        btDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisconnectClick();
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendClick();
            }
        });
    }


    void ConnectClick() {

        // connect to the server
        new connectTask().execute("");
        
        Toast.makeText(this, "Connecting", Toast.LENGTH_SHORT).show();
    }

    void DisconnectClick() {

    }

    void SendClick() {

    }
    public class connectTask extends AsyncTask<String,String,TCPClient> {

        @Override
        protected TCPClient doInBackground(String... message) {

            //we create a TCPClient object and
            mTcpClient = new TCPClient("192.168.0.18",23,new TCPClient.OnMessageReceived() {
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

            tbTextToSend.setText("Received");
            super.onProgressUpdate(values);

            //in the arrayList we add the messaged received from server
            //arrayList.add(values[0]);
            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
            //mAdapter.notifyDataSetChanged();
        }
    }

}