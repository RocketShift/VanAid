package com.example.vanaid.classes;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.support.design.widget.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ErrorMessages {
    private Activity activity;
    private Context context;
    private JSONObject response;

    public ErrorMessages(Context context, Activity activity, JSONObject response){
        this.activity = activity;
        this.context = context;
        this.response = response;
    }

    public void showErrors(){
        try {
            JSONObject errors = response.getJSONObject("errors");
            Iterator<String> keys = errors.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                if (errors.getJSONArray(key) != null) {
                    JSONArray messages = errors.getJSONArray(key);
                    TextInputLayout view = activity.findViewById(context.getResources().getIdentifier(key + "_layout", "id", context.getPackageName()));
                    view.setError(messages.get(0).toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
