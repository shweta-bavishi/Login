package com.example.shweta.login;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class LoginActivity extends AppCompatActivity {

    final Dialog dialog = new Dialog(LoginActivity.this);
    Button login,buttonSignUP;
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        buttonSignUP=(Button)findViewById(R.id.bSignUp);
        buttonSignUP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intentSignUP);
            }
        });
        final EditText userName = (EditText)findViewById(R.id.etUserName);
        final EditText password = (EditText)findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.bLogin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = userName.getText().toString();
                String pswd = password.getText().toString();

                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(uname);
                if(password.equals(storedPassword)){
                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}
