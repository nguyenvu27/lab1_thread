package com.example.lab1.bai1_4.bai3;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.lab1.bai1_4.Bai3Activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
    private Listener mListener ;
    private ProgressDialog progressDialog ;
    public LoadImageTask(Bai3Activity listener , Context context ){
        mListener = listener  ;
        progressDialog = new ProgressDialog(context) ;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Download image...");
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try{
            return BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent()) ;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if(progressDialog.isShowing()){
            progressDialog.dismiss();

        }if(result != null){
            mListener.onImageLoaded(result);

        }else {
            mListener.onError();
        }
    }
}
