package com.example.androidprocess.firstline.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidprocess.R;

public class LoginActivity extends BaseLogoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btLogin = findViewById(R.id.bt_lg_login);
        EditText etAccount = findViewById(R.id.et_lg_account);
        EditText etPassword = findViewById(R.id.et_lg_password);
        btLogin.setOnClickListener(v -> {
            String account = etAccount.getText().toString();
            String password = etPassword.getText().toString();

            if (account.equals("admin") && password.equals("123456")) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}