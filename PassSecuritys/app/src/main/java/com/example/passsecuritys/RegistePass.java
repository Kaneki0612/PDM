package com.example.passsecuritys;

import android.content.Intent;
import android.media.MediaTimestamp;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.passsecuritys.database.Databse;
import com.example.passsecuritys.database.dao.dao;

public class RegistePass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registe_pass);
        String email = getIntent().getStringExtra("User");

        EditText APPName = findViewById(R.id.text_appName);
        EditText name = findViewById(R.id.text_email);
        EditText pass = findViewById(R.id.text_pass);
        Button btn_regidter = findViewById(R.id.btn_registre);

        btn_regidter.setOnClickListener((event) ->{
            RSAEncryptionHelper rsa = new RSAEncryptionHelper(getApplicationContext());
            PassWord p = new PassWord(APPName.getText().toString(), name.getText().toString(), rsa.encrypt(pass.getText().toString()));
            new conection(email,p);
        });
    }

    private class conection extends Thread{
        private String email;
        private PassWord passWord;
        private User user;
        public conection(String email, PassWord passWord){
            this.email = email;
            this.passWord = passWord;
            start();
        }

        @Override
        public void run(){
            Databse db = Room.databaseBuilder(getApplicationContext(), Databse.class, "User").build();
            dao Dao = db.Userdao();
            User user = Dao.getUserEmail(email);
            user.Addpassword(passWord);
            Dao.update(user);
            startActivity(new Intent(RegistePass.this, Homepage.class));
        }
    }
}