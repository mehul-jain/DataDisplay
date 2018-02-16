package com.example.admin.showdata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by admin on 16-02-2018.
 */

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sharedPreferences =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPreferences.contains("NAME") || !sharedPreferences.contains("AGE") || !sharedPreferences.contains("MOBILE"))
            displayResultDialog();
        updateProfile();
    }

    private  void displayResultDialog()
    {
        Context context = ProfileActivity.this;
        String title = "Hide Treasure";
        LayoutInflater li= LayoutInflater.from(context);
        View dialogView =li.inflate(R.layout.dialog_profile,null);
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setView(dialogView);
        final EditText editTextName=(EditText)dialogView.findViewById(R.id.et_user_name);
        final EditText editTextAge=(EditText)dialogView.findViewById(R.id.et_user_age);
        final EditText editTextMobile=(EditText)dialogView.findViewById(R.id.et_user_mobile);
        ad.setPositiveButton(
                "SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor =sharedPreferences.edit();
                        editor.putString("NAME",editTextName.getText().toString().trim());
                        editor.putString("AGE",editTextAge.getText().toString().trim());
                        editor.putString("MOBILE",editTextMobile.getText().toString().trim());
                        editor.commit();
                        updateProfile();
                    }
                });
        ad.show();
    }
    private void updateProfile()
    {
        TextView textViewName=(TextView) findViewById(R.id.name);
        TextView textViewAge=(TextView) findViewById(R.id.age);
        TextView textViewMobile=(TextView) findViewById(R.id.mobile);
        SharedPreferences sharedPreferences =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(sharedPreferences.contains("NAME"))
            textViewName.setText(sharedPreferences.getString("NAME","default_name"));
        if(sharedPreferences.contains("AGE"))
            textViewAge.setText(sharedPreferences.getString("AGE","default_age"));
        if(sharedPreferences.contains("MOBILE"))
            textViewMobile.setText(sharedPreferences.getString("MOBILE","default_mobile"));
    }
}
