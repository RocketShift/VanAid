package com.example.vanaid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vanaid.classes.Requestor;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    private Button submit;
    private EditText username;
    private EditText name;
    private EditText address;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText password_confirmation;

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
                    super.postExecute(result);
                }
            };

            requestor.execute();
        }
    }
}
