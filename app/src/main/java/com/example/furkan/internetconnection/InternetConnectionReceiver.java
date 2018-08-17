package com.example.furkan.internetconnection;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectionReceiver extends BroadcastReceiver {
    static boolean isConnected = false;//I created variable which name is isConnected and type is Boolean

    @Override
    public void onReceive(final Context context, final Intent intent) {
        isNetworkAvailable(context); //I called function of isNetworkAvailable when receiver runs

    }

    private boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE); //the system is listening to the network.(is there Internet?)

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();//Get all internet informations
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {//State of Internet

                        if(!isConnected){ //is there Internet?
                            isConnected = true;
                        }
                        return true;
                    }
                }
            }
        }
        isConnected = false;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);//Alert dialog was created.
        alertDialog.setCancelable(false);
        alertDialog.setTitle("No Internet connection");//The Alert dialog title has been set.
        alertDialog.setMessage("There is no connection with the internet.Please check your internet.");//The Alert dialog message has been set.
        alertDialog.setPositiveButton("Cancel",//I defined name  of the Positive Button which inside of the Alert Dialog and ..
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);//Exit from application.
                    }
                });
        alertDialog.setNegativeButton("Try again",//I defined name of the Negative Button which inside of te Alert Dialog and..
                new DialogInterface.OnClickListener() {
                    ConnectivityManager connectivity = (ConnectivityManager)
                            context.getSystemService(Context.CONNECTIVITY_SERVICE); //the system is listening to the network.(is there Internet?)
                    public void onClick(DialogInterface dialog, int id) {
                        if(isNetworkAvailable(context)==false)
                        {
                            //Toast.makeText(context, "Inthernet connection lost", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            //Toast.makeText(context, "Inthernet connection is available", Toast.LENGTH_LONG).show();
                        }
                    }
                });

        alertDialog.show();//Show alert dialog on the Screen
        return false;
    }
}