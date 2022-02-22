package com.mxtech.skin.demo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.mxtech.demo.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zhanfei","MainActivity.onCreate.....");
        setContentView(R.layout.activity_main);
        findViewById(R.id.pop_dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popDialog();
            }
        });
    }

    private void popDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_referral, null);

        alertDialog.setView(view);
        alertDialog.show();
    }
}