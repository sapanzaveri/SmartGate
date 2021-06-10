package com.example.smartgate.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartgate.MainActivity;
import com.example.smartgate.Model.LoginResponse;
import com.example.smartgate.R;
import com.example.smartgate.RetrofitClient;
import com.example.smartgate.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberLogin extends AppCompatActivity {
    EditText ed_UserName, ed_Password;
    Button btn_MLogin;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        init();
        memberlogin();
    }

    private void init() {
        ed_UserName = findViewById(R.id.ed_UserName);
        ed_Password = findViewById(R.id.ed_Password);
        btn_MLogin = findViewById(R.id.btn_MLogin);

    }

    private void memberlogin() {
        btn_MLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_Name = ed_UserName.getText().toString();
                String PassWord = ed_Password.getText().toString();
                String Flag = "2";
                Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(User_Name, PassWord, Flag);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();

                        if (response.isSuccessful()) {

                            if (loginResponse.getStatus().equalsIgnoreCase("true")) {

                                sharedPrefManager.saveUser(loginResponse.getUser());
                                Intent intent = new Intent(MemberLogin.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                Toast.makeText(MemberLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MemberLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(MemberLogin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isLoggedIn()) {
            Intent intent = new Intent(MemberLogin.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}