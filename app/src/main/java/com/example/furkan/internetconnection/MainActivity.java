package com.example.furkan.internetconnection;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private InternetConnectionReceiver receiver;//I have identified the receiver object listening to the network
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);// Bağlantı aktifliği kontrol ediliyor
        receiver = new InternetConnectionReceiver();//I created an object from the NetworkChangeReceiver class.
        registerReceiver(receiver, filter);//I called the registerReceiver function for Internet control.
    }


    @Override
    protected void onDestroy() { //When activity close recevier will stop.The receiver continues to operate when the application is received in the background
        super.onDestroy();

        unregisterReceiver(receiver);//receiver stops

    }
}
