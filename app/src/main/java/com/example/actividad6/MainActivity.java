package com.example.actividad6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.actividad6.model.Message;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button upBtn, downBtn, leftBtn, rightBtn, colorBtn;
    private Socket connection;
    private BufferedWriter bw;
    private BufferedReader bf;
    private Message message;
    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upBtn = findViewById(R.id.upBtn);
        downBtn = findViewById(R.id.downBtn);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        colorBtn = findViewById(R.id.colorBtn);

        //Connection
        initController();

        //GSON
        Gson gson = new Gson();

        //Message
        message = new Message(" ", 0);


        //Buttons
        upBtn.setOnClickListener(
                (v) -> {
                    message.setChange("up");
                    message.setMovementValue(new Random().nextInt(16) + 10);
                    json = gson.toJson(message);
                    sendMessage(json);
                }
        );

        downBtn.setOnClickListener(
                (v) -> {
                    message.setChange("down");
                    message.setMovementValue(new Random().nextInt(16) + 10);
                    json = gson.toJson(message);
                    sendMessage(json);
                }
        );

        leftBtn.setOnClickListener(
                (v) -> {
                    message.setChange("left");
                    message.setMovementValue(new Random().nextInt(16) + 10);
                    json = gson.toJson(message);
                    sendMessage(json);
                }
        );

        rightBtn.setOnClickListener(
                (v) -> {
                    message.setChange("right");
                    message.setMovementValue(new Random().nextInt(16) + 10);
                    json = gson.toJson(message);
                    sendMessage(json);
                }
        );

        colorBtn.setOnClickListener(
                (v) -> {
                    message.setChange("color");
                    json = gson.toJson(message);
                    sendMessage(json);
                }
        );

    }

    public void initController(){
        new Thread(
                ()->{
                    try {
                        System.out.println("Conectandose al servidor...");
                        connection = new Socket("10.0.2.2", 7000);
                        System.out.println("Cliente conectado");

                        //To receive
                        InputStream is = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        bf = new BufferedReader(isr);

                        //To transmit
                        OutputStream os = connection.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bw = new BufferedWriter(osw);

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
    }

    public void sendMessage(String message) {
        new Thread(
                ()->{
                    //Write message
                    try {
                        bw.write(message + "\n");
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
    }
}