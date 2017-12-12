package com.example.shweta.login;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    final Dialog dialog = new Dialog(RegisterActivity.this);

    Button bRegister;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        final EditText userName = (EditText)findViewById(R.id.etUserName);
        final EditText password = (EditText)findViewById(R.id.etPassword);
        final EditText mobileNo = (EditText)findViewById(R.id.etMobileNumber);

        Button register = (Button)findViewById(R.id.bRegister);
        assert register != null;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString();
                String passwd = password.getText().toString();
                String mobNo = mobileNo.getText().toString();

                if(username.equals("")||passwd.equals("")||mobNo.equals("")){
                    Toast.makeText(RegisterActivity.this, "Empty Field Value", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    loginDataBaseAdapter.insertEntry(username,passwd,mobNo);
                    Toast.makeText(RegisterActivity.this, "Sucessfully Registered",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}
