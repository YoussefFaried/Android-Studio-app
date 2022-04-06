package com.example.milestone0;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button connect=(Button) findViewById(R.id.connectBtn);
        Button send=(Button) findViewById(R.id.sendBtn);
        send.setEnabled(false);
        EditText message=(EditText) findViewById(R.id.textToBeSended);
        message.setEnabled(false);
        TextView reply=(TextView) findViewById(R.id.textView);
        int c=0;

        final Socket[]s={null};
        final DataOutputStream[]dOut={null};
        final DataInputStream[]dIn ={null};
        final String[] str = {null};



        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               Thread t=new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           //change Ip address according to the server
                           s[0]=new Socket("10.3.0.94",7777);
                           connect.setText("Connected");

                       }catch(UnknownHostException e){
                           e.printStackTrace();
                       }
                       catch (IOException e) {
                           e.printStackTrace();
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }

                   }
               });


               t.start();
                message.setEnabled(true);
                send.setEnabled(true);
                connect.setEnabled(false);
            }

        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread t2=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FilterOutputStream os = new FilterOutputStream(s[0].getOutputStream()) {
                                @Override
                                public void close() throws IOException {
                                    //don't close socket!
                                }
                            };
                            dOut[0]=new DataOutputStream(os);
                            dOut[0].writeUTF(message.getText().toString());
                            dOut[0].flush();
                            dOut[0].close();

                            dIn[0]=new DataInputStream(s[0].getInputStream());
                            reply.setText(dIn[0].readUTF().toString());

                        }
                        catch(UnknownHostException e){
                            e.printStackTrace();
                            reply.setText("Server down");
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                            s[0]=null;
                            reply.setText("Server down");
                        }
                        catch (Exception e){


                        }
                    }
                });
                t2.start();
            }
        });











    }


}
