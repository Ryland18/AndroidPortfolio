package com.example.portfolioupgraded;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.preference.PreferenceFragmentCompat;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class SettingsActivity extends AppCompatActivity {
    private TextInputEditText newUsername;
    private Switch darkLightMode;
    private Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

                EdgeToEdge.enable(this);
                setContentView(R.layout.settings_activity);
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.action_Settings), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);


                    newUsername = findViewById(R.id.newUsername);

                    darkLightMode = findViewById(R.id.darkOrLight);

                    saveBtn = findViewById(R.id.savestuff);

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

                    saveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String un = newUsername.getText().toString();

                            // Validate username is not empty
                            if (un.isEmpty()) {
                                Snackbar.make(v, "Username cannot be empty", Snackbar.LENGTH_SHORT).show();
                                return;
                            }


                            // Update password if it passes strength validation
                        }
                    });

                    return insets;});

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

        }



    
