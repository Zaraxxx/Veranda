package com.zaraxxx.apps.veranda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class TCPClient1Activity extends Activity {


    public static final int BUFFER_SIZE = 2048;
    private Socket sck = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    Button btnSend;
    Button btnConnect;
    Button btnDisconnect;
    EditText textOut;
    TextView textIn;
    String response = "";
    final Handler handler = new Handler();
    final Runnable updateUI = new Runnable() {
        public void run() {
            textIn.setText(response);
        }
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testtcpdeux);

        textOut = (EditText)findViewById(R.id.txtSend);
        textIn = (TextView)findViewById(R.id.txtMSG);
        btnSend = (Button)findViewById(R.id.btnSend);
        btnConnect = (Button)findViewById(R.id.btnConnect);
        btnDisconnect = (Button)findViewById(R.id.btnDisconnect);
    }

    public void ConnectUp(View view){
        OpenConnection();
    }


    public void OpenConnection(){
        new Thread(new Runnable(){
            public void run(){
                try{
                    if (sck == null){
                        sck = new Socket("192.168.0.18",23);
                        sck.setKeepAlive(true);
                        screenConfig();

                        in = new BufferedReader(new InputStreamReader(sck.getInputStream()));
                        out = new PrintWriter(sck.getOutputStream());
                        sendDataWithString("testing 1 2 3");
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
            textOut.setClickable(true);
            btnSend.setClickable(true);
            btnDisconnect.setClickable(true);
            btnConnect.setClickable(false);
        }else{
            textOut.setClickable(false);
            btnSend.setClickable(false);
            btnDisconnect.setClickable(false);
            btnConnect.setClickable(true);
        }
    }

    public void SendMessage(View view){
        sendDataWithString(textOut.getText().toString());
        textOut.setText(null);
    }
    public void DisConnect(View view){
        CloseConnection();
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

}
