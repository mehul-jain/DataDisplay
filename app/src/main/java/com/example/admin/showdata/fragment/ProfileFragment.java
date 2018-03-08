package com.example.admin.showdata.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.showdata.R;

/**
 * Created by admin on 08-03-2018.
 */

public class ProfileFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        if (!sharedPreferences.contains("NAME") || !sharedPreferences.contains("AGE") || !sharedPreferences.contains("MOBILE"))
            displayResultDialog();
        updateProfile();
    }

    private void displayResultDialog() {
        Context context = ProfileFragment.this.getActivity();
        String title = "Hide Treasure";
        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.dialog_profile, null);
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setView(dialogView);
        final EditText editTextName = (EditText) dialogView.findViewById(R.id.et_user_name);
        final EditText editTextAge = (EditText) dialogView.findViewById(R.id.et_user_age);
        final EditText editTextMobile = (EditText) dialogView.findViewById(R.id.et_user_mobile);
        ad.setPositiveButton(
                "SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("NAME", editTextName.getText().toString().trim());
                        editor.putString("AGE", editTextAge.getText().toString().trim());
                        editor.putString("MOBILE", editTextMobile.getText().toString().trim());
                        editor.commit();
                        updateProfile();
                    }
                });
        ad.show();
    }
    private void updateProfile() {
        TextView textViewName = (TextView)getView().findViewById(R.id.name);
        TextView textViewAge = (TextView) getView().findViewById(R.id.age);
        TextView textViewMobile = (TextView) getView().findViewById(R.id.mobile);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        if (sharedPreferences.contains("NAME"))
            textViewName.setText(sharedPreferences.getString("NAME", "default_name"));
        if (sharedPreferences.contains("AGE"))
            textViewAge.setText(sharedPreferences.getString("AGE", "default_age"));
        if (sharedPreferences.contains("MOBILE"))
            textViewMobile.setText(sharedPreferences.getString("MOBILE", "default_mobile"));
    }

}
