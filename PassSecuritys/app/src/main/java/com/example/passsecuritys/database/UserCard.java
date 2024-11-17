package com.example.passsecuritys.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.room.Room;

import com.example.passsecuritys.R;
import com.example.passsecuritys.User;
import com.example.passsecuritys.database.dao.dao;

import java.util.List;

public class UserCard extends LinearLayout {
    TextView userName;
    Button btn_delete;
    private User user;
    View card;
    public UserCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inic();
    }

    public UserCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inic();
    }

    public UserCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inic();
    }

    public UserCard(Context context) {
        super(context);
        inic();
    }

    private void inic(){
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LayoutInflater inflater = LayoutInflater.from(getContext());
        card = inflater.inflate(R.layout.user_card,null);
        userName = card.findViewById(R.id.text_user_name);
        btn_delete = card.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener((event) -> {
            new databaseconect(this.user);
        });
        addView(card);
    }

    public void setUser(User user){
        this.user = user;
        userName.setText(user.getNome());
    }

    private class databaseconect extends Thread{
        private User user;
        public databaseconect(User user){
            this.user = user;
            start();
        }
        @Override
        public void run(){
            Databse db = Room.databaseBuilder(getContext(), Databse.class, "User").build();
            dao Dao = db.Userdao();
            Dao.delete(this.user);
        }
    }
}
