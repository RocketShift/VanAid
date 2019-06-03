package com.example.vanaid.classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Requestor {
    private Boolean isRunning = false;
    private Map<String, Object> param;
    private Boolean asynchronus = false;
    private Integer PAGE = null;
    private String url = "http://vanaid.dresdain.com/api/";
    private Context context;

    public Requestor(String url, Map<String, Object> param, Context context){
        this.url = url + url;
        this.param = param;
        this.context = context;
    }

    public void execute(){
        if(PAGE != null){
            param.put("page", PAGE);
        }

        Boolean network = isNetworkAvailable();
        if (network == true) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            String macAddress = wInfo.getMacAddress();
            if(macAddress.equals("02:00:00:00:00:00")){
                macAddress = getWifiMacAddress();
            }
            param.put("macaddress", macAddress);
            param.put("type", "driver");
            if (isRunning == true && asynchronus == true) {
//                new Ajaxer().execute();
            }else{
//                new Ajaxer().execute();
            }
        } else {
            onNetworkError();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getWifiMacAddress() {
        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (!intf.getName().equalsIgnoreCase(interfaceName)){
                    continue;
                }

                byte[] mac = intf.getHardwareAddress();
                if (mac==null){
                    return "";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02X:", aMac));
                }
                if (buf.length()>0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }

    public void onNetworkError(){
        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_LONG).show();
    }
}
