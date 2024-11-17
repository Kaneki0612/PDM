package com.example.passsecuritys;

import androidx.room.TypeConverter;
import java.util.ArrayList;
import java.util.Arrays;

public class Converter {
    @TypeConverter
    public static String fromPasswordList(ArrayList<PassWord> passwords) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PassWord password : passwords) {
            stringBuilder.append(password.getAPPName())
                    .append(",")
                    .append(password.getEmail())
                    .append(",")
                    .append(new String(password.getPassword()))
                    .append(";");
        }
        return stringBuilder.toString();
    }

    @TypeConverter
    public static ArrayList<PassWord> fromString(String value) {
        ArrayList<PassWord> passwords = new ArrayList<>();
        String[] items = value.split(";");
        for (String item : items) {
            if (!item.isEmpty()) {
                String[] parts = item.split(",");
                passwords.add(new PassWord(parts[0], parts[1],parts[2]));
            }
        }
        return passwords;
    }
}