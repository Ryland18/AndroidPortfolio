package com.example.portfolioupgraded.ui.doodle;

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
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.portfolioupgraded.R;
import com.example.portfolioupgraded.databinding.FragmentDoodleBinding;
import com.example.portfolioupgraded.ui.doodle.doodleView;
import com.example.portfolioupgraded.databinding.FragmentGuessanumberBinding;
import com.example.portfolioupgraded.databinding.FragmentMadlibsBinding;
import com.example.portfolioupgraded.ui.guessanumber.guessanumberViewModel;
import com.example.portfolioupgraded.ui.madlib.madlibsViewModel;

public class doodleFragment  extends Fragment {
    private ImageButton erase;
    private doodleView paper;
    private Button update;
    private EditText size;
    private SeekBar red,blue,green;

    private FragmentDoodleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDoodleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
            erase = root.findViewById(R.id.eraser);
            erase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(requireContext(),getActivity().getClass()));
                }
            });
            paper = root.findViewById(R.id.drawView);
            size = root.findViewById(R.id.brush_sizeBTN);
            red = root.findViewById(R.id.red);
            green = root.findViewById(R.id.green);
            blue = root.findViewById(R.id.blue);
            update = root.findViewById(R.id.brush_updateBTN);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("MainActivity/updateOnclickListener", "updating");

                    if (!size.getText().toString().equals("")) {
                        paper.resize(Integer.parseInt(String.valueOf(size.getText())));
                    }
                    paper.repaint(red.getProgress(),green.getProgress(),blue.getProgress());

                }
            });

        return root;
    }
}




