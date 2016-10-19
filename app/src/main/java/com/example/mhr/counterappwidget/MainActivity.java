package com.example.mhr.counterappwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mCurrentNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCurrentNumber = (TextView) findViewById(R.id.text_number);
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateNumberView();
        //Register a receiver to receive updates when the service finishes
        IntentFilter filter = new IntentFilter(RandomService.ACTION_RANDOM_NUMBER);
        registerReceiver(mReceiver, filter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Unregister our receiver
        unregisterReceiver(mReceiver);
    }
    public void onRandomClick(View v) {
        Log.d("test == ","clicked");
        //Call the service to update the number data
        startService(new Intent(this, RandomService.class));
    }

    private void updateNumberView() {
        //Update the view with the latest number
        mCurrentNumber.setText(String.valueOf(RandomService.getsRandomNumber()));
    }
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            updateNumberView();
        } };
}
