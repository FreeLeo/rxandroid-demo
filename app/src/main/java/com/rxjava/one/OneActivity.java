package com.rxjava.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rxjava.R;

public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        testBasicSample();
    }

    private void testBasicSample(){
        Basic1.sample();
//        Basic1.map();
//        Basic1.flatmap();
//        Basic1.concatmap();
//        Basic1.zip();
    }
}
