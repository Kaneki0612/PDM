package com.example.passsecuritys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityCypher {
    private SecretKey secretKey;
    private IvParameterSpec ivParam;

    public SecurityCypher(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cypher", Context.MODE_PRIVATE);
        String sk = sharedPreferences.getString("SecurityKey", "");
        String iv = sharedPreferences.getString("iv", "");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !sk.isEmpty() && !iv.isEmpty()) {
            secretKey = new SecretKeySpec(Base64.getDecoder().decode(sk), "AES");
            ivParam = new IvParameterSpec(Base64.getDecoder().decode(iv));
        } else {
            Log.e("ANA_ERRO", "SecurityKey ou IV estão vazios ou inválidos.");
        }
    }

    public byte[] Cifra(String x) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParam);
        byte[] encrypted = cipher.doFinal(x.getBytes(StandardCharsets.UTF_8));
        Log.d("ANA_ERRO", "Criptografia bem-sucedida");
        return encrypted;
    }

    public byte[] Decifra(byte[] x) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParam);
            byte[] decrypted = cipher.doFinal(x);
            Log.d("ANA_ERRO", "Descriptografia bem-sucedida");
            return decrypted;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
            Log.e("ANA_ERRO", "Chave ou IV inválido: " + e.getMessage());
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            Log.e("ANA_ERRO", "Tamanho incorreto dos dados ou dados corrompidos: " + e.getMessage());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            Log.e("ANA_ERRO", "Erro de configuração de criptografia: " + e.getMessage());
        }
        return null;
    }
}

