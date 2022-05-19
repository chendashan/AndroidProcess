package com.example.androidprocess.firstline.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidprocess.R;

public class LoginActivity extends BaseLogoutActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btLogin = findViewById(R.id.bt_lg_login);
        EditText etAccount = findViewById(R.id.et_lg_account);
        EditText etPassword = findViewById(R.id.et_lg_password);
        checkBox = findViewById(R.id.cb_remember_password);

        sp = getSharedPreferences("remember_password", MODE_PRIVATE);
        boolean isRemember = sp.getBoolean("is_remember", false);
        if (isRemember) {
            String account = sp.getString("account", "");
            String password = sp.getString("password", "");
            etAccount.setText(account);
            etPassword.setText(password);
            checkBox.setChecked(true);
        }

        btLogin.setOnClickListener(v -> {
            String account = etAccount.getText().toString();
            String password = etPassword.getText().toString();

            if (account.equals("admin") && password.equals("123456")) {

                editor = getSharedPreferences("remember_password", MODE_PRIVATE).edit();
                if (checkBox.isChecked()) {
                    editor.putString("account", account);
                    editor.putString("password", password);
                    editor.putBoolean("is_remember", true);
                } else {
                    editor.clear();
                }
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}