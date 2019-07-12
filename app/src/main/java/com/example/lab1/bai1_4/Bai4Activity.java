package com.example.lab1.bai1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab1.R;
import com.example.lab1.bai1_4.bai4.AsyncTaskRunner;

public class Bai4Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTime ;
    private Button btnRun  ;
    private TextView tvResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        edtTime = findViewById(R.id.edt)  ;
        tvResult = findViewById(R.id.tv) ;
        btnRun = findViewById(R.id.btn) ;

        btnRun.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(tvResult, edtTime, this);
                String sleepTime = edtTime.getText().toString() ;
                asyncTaskRunner.execute(sleepTime) ;
                break;
        }
    }
}
