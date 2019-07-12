package com.example.lab1.bai1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.MainActivity;
import com.example.lab1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Bai2Activity extends AppCompatActivity {
    private ImageView imgLoad;
    private Button btnLoad;
    private ProgressDialog progressDialog;
    private String url = "http://pngimg.com/uploads/android_logo/android_logo_PNG12.png";
    private Bitmap bitmap = null;
    private TextView tvMessenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        imgLoad = findViewById(R.id.imagebai2);
        tvMessenge = findViewById(R.id.tvHander);
        btnLoad = findViewById(R.id.loadImage);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();

            }
        });


    }

    private void show() {
        progressDialog = ProgressDialog.show(Bai2Activity.this, "", "Downloading...");
        Runnable aRunnable = new Runnable() {
            @Override
            public void run() {
                bitmap = downLoadBitmap(url);
                Message msg = messageHandler.obtainMessage();
                Bundle bundle = new Bundle();
                String threadMessage = "Image downloaded";
                bundle.putString("message", threadMessage);
                msg.setData(bundle);
                messageHandler.sendMessage(msg);
            }
        };
        Thread aThread = new Thread(aRunnable);
        aThread.start();
    }

    private Bitmap downLoadBitmap(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessenge.setText(message);
            imgLoad.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };
}
