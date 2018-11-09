package com.example.poplify.baby_guru_sample.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveData {

    private Context _context;
    private SharedPreferences shared;
    private String SHARED_NAME = "Shelf";
    private Editor edit;
    //private String Key2 = "hello_world";

    public SaveData(Context c) {
        _context = c;
        shared = _context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        edit = shared.edit();
        //System.out.println("edit ::::: value : "+edit);
    }

    // ============================================//
    public void save(String key, String value) {
        edit.putString(key, value);
        edit.commit();
    }


    // ============================================//
    public void save(String key, float value) {
        edit.putFloat(key, value);
        edit.commit();
    }

    // ============================================//
    public void save(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.commit();
    }

    // ============================================//
    public void save(String key, int value) {
        edit.putInt(key, value);
        edit.commit();
    }

    // ============================================//
    public String getString(String key) {
        //System.out.println("Default key is" +key+ "and ::::::: "+shared.getString(key, null));
        return shared.getString(key, key);

    }

    // ============================================//
    public int getInt(String key) {
        return shared.getInt(key, 0);

    }

    // ============================================//
    public float getFloat(String key) {
        return shared.getFloat(key, 0);

    }

    // ============================================//
    public boolean getBoolean(String key) {
        return shared.getBoolean(key, true);

    }

    // ============================================//
    public boolean isExist(String key) {
        return shared.contains(key);

    }

    // ============================================//
    public void remove(String key) {

        edit.remove(key);
        edit.commit();

    }

    // ============================================//
    public String get(String key) {
        return shared.getString(key, key);

    }

    public void clearAll() {
        shared = _context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        shared.edit().clear().commit();

    }
}
