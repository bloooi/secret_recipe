package com.cm.secret_recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Intro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_intro);
        toolbar.setTitle("어플 소개");


    }
}
