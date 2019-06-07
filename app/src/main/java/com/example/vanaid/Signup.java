package com.example.vanaid;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vanaid.classes.Requestor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    private Button submit;
    private TextInputEditText username;
    private TextInputEditText  name;
    private TextInputEditText  address;
    private TextInputEditText  email;
    private TextInputEditText  phone;
    private TextInputEditText  password;
    private TextInputEditText password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);
        password_confirmation = findViewById(R.id.password_confirmation);
        submit = findViewById(R.id.button2);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(submit)){
            Map<String, Object> param =  new LinkedHashMap<>();;
            param.put("username", username.getText());
            param.put("name", name.getText());
            param.put("address", address.getText());
            param.put("email", email.getText());
            param.put("phone", phone.getText());
            param.put("password", password.getText());
            param.put("password_confirmation", password_confirmation.getText());

            Requestor requestor = new Requestor("register", param, this){
                @Override
                public void postExecute(JSONObject result) {
                    try {
                        JSONObject errors = result.getJSONObject("errors");
                        Iterator<String> keys = errors.keys();

                        while(keys.hasNext()) {
                            String key = keys.next();
                            if (errors.getJSONArray(key) != null) {
                                JSONArray messages = errors.getJSONArray(key);
                                TextInputLayout view = findViewById(getApplicationContext().getResources().getIdentifier(key + "_layout", "id", getApplicationContext().getPackageName()));
                                view.setError(messages.get(0).toString());
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            requestor.execute();
        }
    }

//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            // pre-condition
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            listItem.measure(0, 0);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//        listView.requestLayout();
//    }
}
