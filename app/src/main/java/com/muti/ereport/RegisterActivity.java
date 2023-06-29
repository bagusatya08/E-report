package com.muti.ereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText nikEditText;
    EditText phoneEditText;
    EditText alamatEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordConfirmationEditText;
    Button loginBtn;
    Button registerBtn;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent toLoginActivity = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(toLoginActivity);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(nameEditText.getText().toString().equals("")||
                        nikEditText.getText().toString().equals("")||
                        phoneEditText.getText().toString().equals("")||
                        alamatEditText.getText().toString().equals("")||
                        emailEditText.getText().toString().equals("")||
                        passwordEditText.getText().toString().equals("")||
                        passwordConfirmationEditText.getText().toString().equals("")||
                        !(passwordConfirmationEditText.getText().toString().equals(passwordEditText.getText().toString()))){
                    Snackbar.make(view,"Mohon periksa data register anda",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                }
                else{
                    Boolean checkUser = dbHelper.checkUsernameUsers(emailEditText.getText().toString());
                    if(checkUser==false){
                        Boolean insert = dbHelper.insertDataUsers(emailEditText.getText().toString(), passwordEditText.getText().toString(), nameEditText.getText().toString(), nikEditText.getText().toString(), phoneEditText.getText().toString(), alamatEditText.getText().toString());
                        if(insert==true){
                            Snackbar.make(view,"Sukses Mendaftar Akun",Snackbar.LENGTH_SHORT)
                                    .setAction("Tutup", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    }).show();
                            dbHelper.getDataUsers(getApplicationContext(), emailEditText.getText().toString(), passwordEditText.getText().toString());
                            nameEditText.setText("");
                            nikEditText.setText("");
                            phoneEditText.setText("");
                            alamatEditText.setText("");
                            emailEditText.setText("");
                            passwordEditText.setText("");
                            passwordConfirmationEditText.setText("");

                            Intent toLaporanSayaActivity = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(toLaporanSayaActivity);
                            finish();// biar org gbs balik ke sini
                        }
                        else{
                            Snackbar.make(view,"Gagal Mendaftar Akun!",Snackbar.LENGTH_SHORT)
                                    .setAction("Tutup", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    }).show();
                        }
                    }
                    else{
                        Snackbar.make(view,"Akun Sudah Ada!",Snackbar.LENGTH_SHORT)
                                .setAction("Tutup", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }).show();
                    }
                }
            }
        });
    }
    void initialize() {
        nameEditText = findViewById(R.id.namaEditText);
        nikEditText = findViewById(R.id.nikEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        alamatEditText = findViewById(R.id.alamatEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordConfirmationEditText = findViewById(R.id.passwordConfirmationEditText);
        registerBtn = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(this);
    }
}