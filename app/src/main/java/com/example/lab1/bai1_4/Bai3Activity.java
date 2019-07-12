package com.example.lab1.bai1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.R;
import com.example.lab1.bai1_4.bai3.Listener;
import com.example.lab1.bai1_4.bai3.LoadImageTask;

public class Bai3Activity extends AppCompatActivity implements View.OnClickListener, Listener {
    private TextView tvMessage;
    private Button btnLoad;
    private ImageView imgLoad;

    public static final String IMAGE_URL = "http://pluspng.com/img-png/android-png-android-png-photos-399.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        tvMessage = findViewById(R.id.tvBai3_2);
        btnLoad = findViewById(R.id.btnbai3);
        imgLoad = findViewById(R.id.imgBai3);

        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btnbai3:
                new LoadImageTask(this, this).execute(IMAGE_URL);
                break;
        }
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        imgLoad.setImageBitmap(bitmap);
        tvMessage.setText("Image Downloaded");
    }

    @Override
    public void onError() {
        tvMessage.setText("Error download image");
    }
}
