package com.muti.ereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.muti.ereport.model.Laporan;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    Button registerBtn;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent toRegisterActivity = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(toRegisterActivity);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(emailEditText.getText().toString().equals("")||
                        passwordEditText.getText().toString().equals("")){
                    Snackbar.make(view,"Mohon periksa data login anda",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                }else{
                    Boolean checkUserData = dbHelper.checkPasswordUsers(emailEditText.getText().toString(), passwordEditText.getText().toString());
                    if(checkUserData==true){
                        Intent toLaporanSayaActivity = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(toLaporanSayaActivity);
                        finish();// biar org gbs balik ke sini
                        Snackbar.make(view,"Sukses Login",Snackbar.LENGTH_SHORT)
                                .setAction("Tutup", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }).show();
                        dbHelper.getDataUsers(getApplicationContext(), emailEditText.getText().toString(), passwordEditText.getText().toString());
                        emailEditText.setText("");
                        passwordEditText.setText("");
                    }
                    else{
                        Snackbar.make(view,"Data Login Tidak Ditemukan!",Snackbar.LENGTH_SHORT)
                                .setAction("Tutup", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }).show();
                        emailEditText.setText("");
                        passwordEditText.setText("");
                    }
                }
            }
        });
    }
    void initialize() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerBtn = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(this);
    }
}