package com.example.smartgate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartgate.Login.MemberLogin;

public class LoginActivity extends AppCompatActivity {
    Button btn_MemberLoign;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_MemberLoign = findViewById(R.id.btn_MemberLogin);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        btn_MemberLoign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this , MemberLogin.class);
                startActivity(mIntent);
               //finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPrefManager.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}