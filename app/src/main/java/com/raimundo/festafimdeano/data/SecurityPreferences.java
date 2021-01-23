package com.raimundo.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences (Context mContext){
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String valor){
        this.mSharedPreferences.edit().putString(key, valor).apply();
    }

    public String getStoreString(String key){
        return this.mSharedPreferences.getString(key, "");
    }
}
