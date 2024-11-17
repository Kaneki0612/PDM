package com.example.passsecuritys.database;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.passsecuritys.R;

//Criação de um elememto XML que represente um Object Password
public class PassCard extends LinearLayout {
    private TextView name;
    private TextView Email;
    private TextView PassWord;
    private View card;

    public PassCard(Context context) {
        super(context);
        inic();
    }

    public PassCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inic();
    }

    public PassCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inic();
    }

    public PassCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inic();
    }

    //Inicialização. Altera o layout para a View selecionada
    private void inic () {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        card = inflater.inflate(R.layout.pass_card,null);
        name = card.findViewById(R.id.text_AppName);
        Email = card.findViewById(R.id.text_email);
        PassWord = card.findViewById(R.id.text_pass );
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        card.setLayoutParams(layoutParams);
        this.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(card);
    }

    public void setEmail(String email){
        Email.setText(email);
    };
    public void setName(String Nome){
        name.setText(Nome);
    };
    public  void setPassWord(String Pass){
        PassWord.setText(Pass);
    };

}
