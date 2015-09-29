package com.cm.secret_recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class notification extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_notification);
        toolbar.setTitle("공지사항");
    }
}
