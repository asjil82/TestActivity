package com.testactivity.testactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by DongJin on 2018-02-26.
 */

public class TestActivity2 extends Activity {

    public static TestActivity2 testActivity = null;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testActivity = this;
        MainActivity.AAA += 1;
        Toast.makeText(getApplicationContext(), " 2 - "+MainActivity.AAA, Toast.LENGTH_LONG).show();
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.testActivity.aaa();
            }
        });

    }
    public void start() {
        Intent intent = new Intent(TestActivity2.this, TestActivity.class);
        startActivityForResult(intent, MainActivity.AAA);
    }
    @Override
    public void startActivityForResult(@RequiresPermission Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }

    public void aaa() {
        Toast.makeText(getApplicationContext(), " 2 - "+MainActivity.AAA, Toast.LENGTH_LONG).show();
    }
}
