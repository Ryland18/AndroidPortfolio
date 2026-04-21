package com.example.portfolioupgraded.ui.doodle;
/*
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.portfolioupgraded.R;
import com.example.portfolioupgraded.databinding.FragmentDoodleBinding;
import com.example.portfolioupgraded.databinding.FragmentMadlibsBinding;
import com.example.portfolioupgraded.ui.madlib.madlibsViewModel;

public class doodleFragment  extends Fragment {
        private ImageButton erase;
        private drawing paper;
        private Button update;
        private EditText size, color;
 //   private @NonNull FragmentDoodleBinding binding;


   /* @Override
  /*  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_doodle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawingfragment), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            erase= v.findViewById(R.id.eraser);
            erase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(doodleFragment.this, doodleFragment.class));
                }
            });
            paper=findViewById(R.id.drawView);
            size=findViewById(R.id.brush_sizeBTN);
            color=findViewById(R.id.paint_colorBTN);
            update=findViewById(R.id.brush_updateBTN);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("MainActivity/updateOnclickListener","updating");

                    if(!size.getText().toString().equals("")){paper.resize(Integer.parseInt(String.valueOf(size.getText())));}
                    try {
                        if(!color.getText().toString().equals("")){paper.repaint(Color.parseColor("#"+color.getText().toString()));}
                    }catch (Exception e){
                        Log.e("MainActivity/updateOnclickListener",e.getMessage());
                    }

                }
            });

            return insets;
        });
    }
}

*\



 */
