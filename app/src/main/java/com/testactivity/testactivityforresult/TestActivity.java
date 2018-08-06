package com.testactivity.testactivityforresult;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by DongJin on 2018-02-26.
 */

public class TestActivity extends Activity {

    public static TestActivity testActivity = null;
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testActivity = this;
        MainActivity.AAA += 1;
        index = MainActivity.AAA;
        Toast.makeText(getApplicationContext(), "app 1 - "+MainActivity.AAA, Toast.LENGTH_LONG).show();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter(MainActivity.TTT));

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
                sendMessage(getApplicationContext());
            }
        });

    }

    @Override
    protected  void  onDestroy ()  {
        // 수신기 삭제
        // NSNotificationCenter.defaultCenter () removeObserver (...) 역할
        LocalBroadcastManager . getInstance ( this ) .unregisterReceiver ( mMessageReceiver );
        super . onDestroy ();
    }

    public void start() {
        Intent intent = new Intent(TestActivity.this, TestActivity.class);
        startActivityForResult(intent, MainActivity.AAA);
    }
    @Override
    public void startActivityForResult(@RequiresPermission Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }

    public void aaa() {
        Toast.makeText(getApplicationContext(), " 1 - "+MainActivity.AAA, Toast.LENGTH_LONG).show();
    }

    private  BroadcastReceiver  mMessageReceiver  =  new  BroadcastReceiver()  {
        @Override
        public  void  onReceive (Context context , Intent  intent )  {
            // Extra 값을 얻을
            String  message  =  intent . getStringExtra ( "message" );
            Log. d ( "receiver" ,  "수신 메시지 :"  +  message + "   : "+index);
        }
    };


    public void  sendMessage ( Context  context )  {
        Log . d ( "sender" ,  "메시지 전송 수행" );
        // "MY_EVENT"라는 이름으로 Intent 쓰기
        Intent  intent  =  new  Intent ( MainActivity.TTT );
        // Intent 그래서 Extra의 이용이 가능
        intent . putExtra ( "message" ,  "가루빤 좋은거야   "+index );
        // 전송
        // NSNotificationCenter.defaultCenter () postNotificationName (...) 역할
        LocalBroadcastManager . getInstance ( context ).sendBroadcast( intent );
    }

}
