package com.example.lab1.bai1_4;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Bai1Activity extends AppCompatActivity {
    private TextView tv;
    private ImageView image;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        tv = findViewById(R.id.tv);
        image = findViewById(R.id.image);
        button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anhXa();
            }
        });


    }

    private void anhXa() {
        final Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetWork("http://pngimg.com/uploads/android_logo/android_logo_PNG12.png");
                image.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Image Downloaded");
                        image.setImageBitmap(bitmap);
                    }
                });
            }
        });
        myThread.start();
    }

    private Bitmap loadImageFromNetWork(String link) {
        URL url;
        Bitmap bmp = null;
        try {
            url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;


    }


}
