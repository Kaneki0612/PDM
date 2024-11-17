package com.example.passsecuritys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.passsecuritys.database.Databse;
import com.example.passsecuritys.database.PassCard;
import com.example.passsecuritys.database.UserCard;
import com.example.passsecuritys.database.dao.dao;

import java.util.List;

public class Show_users extends AppCompatActivity {
    private LinearLayout usersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usersView = findViewById(R.id.user_view);

        new databaseconect();
    }

    private class databaseconect extends Thread{

        public databaseconect(){
            start();
        }
        private boolean existe(List<User> e, String email, String password){
            for(User f : e){
                if (f.getEmail().equals(email) && f.getPassword().equals(password)) return true;
            }
            return false;
        }
        @Override
        public void run(){
            Databse db = Room.databaseBuilder(getApplicationContext(), Databse.class, "User").build();
            dao Dao = db.Userdao();
            List<User> usrs = Dao.getAll();
            Log.d("Ana Consult", String.valueOf(usrs.size()));
            new Handler(Looper.getMainLooper()).post(() -> {
                for(User u : usrs){
                    UserCard userCard = new UserCard(getApplicationContext());
                    userCard.setUser(u);
                    usersView.addView(userCard); //Adicionar card a visualização da app
                }
            });
        }
    }
}