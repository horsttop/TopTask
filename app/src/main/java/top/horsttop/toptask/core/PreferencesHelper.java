package top.horsttop.toptask.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by horsttop on 16/1/2.
 */
public class PreferencesHelper {
    public static final String PREF_FILE_NAME = "horsttop";

    private final SharedPreferences mPref;

    public PreferencesHelper( ) {
        mPref = GenApplication.getApplication().getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 轻量存String
     * @param key
     * @param value
     */
    public void saveConfig(String key,String value){
        mPref.edit().putString(key,value).apply();
    }

    /**
     * 轻量存int
     * @param key
     * @param value
     */
    public void saveConfig(String key,int value){
        mPref.edit().putInt(key,value).apply();
    }

    /**
     * 取String,默认为""
     * @param key
     * @return
     */
    public String getStringConfig(String key){
        return mPref.getString(key,"");
    }

    /**
     * 取int,默认为0
     * @param key
     * @return
     */
    public int getIntConfig(String key){
        return mPref.getInt(key,0);
    }

    /**
     * 清除所有项
     */
    public void clear() {
        mPref.edit().clear().apply();
    }
}
