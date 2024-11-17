package com.example.passsecuritys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.passsecuritys.database.Databse;
import com.example.passsecuritys.database.PassCard;
import com.example.passsecuritys.database.dao.dao;

public class Homepage extends AppCompatActivity {
    private LinearLayout pass_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        String email = getIntent().getStringExtra("User");
        pass_layout = findViewById(R.id.pass_layout);

        Button btn_registar = findViewById(R.id.btn_create_pass);
        btn_registar.setOnClickListener((event) ->{
            Intent intent = new Intent(Homepage.this, RegistePass.class);
            intent.putExtra("User", email);
            startActivity(intent);
        });
        new connectDB(email);
    }
        // Aceder á base de dados e lista o conteudo do utilazor logado
    private class connectDB extends Thread{
        private String email;
        private User user;

        public connectDB(String email){
            this.email = email;
            start();
        }
        @Override
        public void run(){
            Databse db = Room.databaseBuilder(getApplicationContext(), Databse.class, "User").build();
            dao Dao = db.Userdao();
            user = Dao.getUserEmail(email);
            Log.d("ANA_HOME", user.toString());
            //Ir busca o Loop principal da aplicação, para poder alterar o layout:
            new Handler(Looper.getMainLooper()).post(() -> {
                //Precorer a lista de password do utilizador e construir o card visual que represente a password
                RSAEncryptionHelper rsa = new RSAEncryptionHelper(getApplicationContext());
                for (PassWord p : user.getPassWords()){
                    PassCard passCard = new PassCard(getApplicationContext());
                    passCard.setName(p.getAPPName());
                    passCard.setEmail(p.getEmail());
                    passCard.setPassWord(rsa.decrypt(p.getPassword()));
                    pass_layout.addView(passCard); //Adicionar card a visualização da app
                }
            });
        }
    }
}