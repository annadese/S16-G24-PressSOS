package com.mobdeve.s16.desembrana.annapatricia.PressSOS;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreferences {

    public static final String SP_FILE_NAME = "sp";
    private static SharedPreferences mPrefs;
    private static SharedPreferences.Editor mPrefsEditor;

    // For alarm toggle switch
    public static boolean isButtonCLicked(Context ctx) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        return mPrefs.getBoolean("button_clicked", true);
    }

    public static void setButtonCLicked(Context ctx, Boolean value) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEditor = mPrefs.edit();
        mPrefsEditor.putBoolean("button_clicked", value);
        mPrefsEditor.apply();
    }
}