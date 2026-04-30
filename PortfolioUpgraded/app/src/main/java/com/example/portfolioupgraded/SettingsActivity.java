package com.example.portfolioupgraded;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegate;


import androidx.appcompat.app.AppCompatActivity;

import com.example.portfolioupgraded.databinding.SettingsActivityBinding;
import com.example.portfolioupgraded.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

public class SettingsActivity extends AppCompatActivity {
    private EditText newUsername;
    private Switch darkLightMode;

    private SettingsActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


                    newUsername = findViewById(R.id.newUsername);

                    darkLightMode = findViewById(R.id.darkOrLight);

                    SharedPreferences sharedPreferences = getSharedPreferences("darkMode", MODE_PRIVATE);
                    darkLightMode.setChecked(sharedPreferences.getBoolean("isDarkMode", false));


                    darkLightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            // Save preference
                            SharedPreferences.Editor editor = getSharedPreferences("darkMode", MODE_PRIVATE).edit();
                            editor.putBoolean("isDarkMode", isChecked);
                            editor.apply();

                            // Apply the theme change
                            if (isChecked) {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            } else {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            }

                            // Recreate the current activity to apply the theme immediately
                            recreate();
                        }
                    });

//                    saveBtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String un = newUsername.getText().toString();
//
//                            // Validate username is not empty
//                            if (un.isEmpty()) {
//                                Snackbar.make(v, "Username cannot be empty", Snackbar.LENGTH_SHORT).show();
//                                return;
//                            }
//
//
//                            // Update password if it passes strength validation
//                        }
//                    });

            }

            // Apply theme preference from SharedPreferences
            private void applyThemePreference() {
                boolean isDarkMode = getSharedPreferences("darkMode", MODE_PRIVATE)
                        .getBoolean("isDarkMode", false);

                if (isDarkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","ran");
        String oldHello = String.valueOf(HomeFragment.hello.getText());

        if (newUsername.getText().length()>=1) {
            Log.d("new user content", newUsername.getText().toString());
            HomeFragment.hello.setText("Hello "+newUsername.getText().toString()+". "+oldHello);
        }
    }
}



    
