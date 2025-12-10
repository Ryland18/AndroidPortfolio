package com.example.portfolioupgraded.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.portfolioupgraded.databinding.FragmentScifinameBinding;

public class scifinameFragment extends Fragment {

    private FragmentScifinameBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scifinameViewModel slideshowViewModel =
                new ViewModelProvider(this).get(scifinameViewModel.class);

        binding = FragmentScifinameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;

        EditText first = binding.editTextText6;
        EditText last = binding.editTextText2;
        EditText city = binding.editTextText3;
        EditText school = binding.editTextText4;
        EditText pet = binding.editTextText5;
        EditText chara = binding.editTextText7;





        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String inputfirst = first.getText().toString();
                String inputlast = last.getText().toString();
                String inputcity = city.getText().toString();
                String inputschool = school.getText().toString();
                String inputpet = pet.getText().toString();
                String inputchara = chara.getText().toString();

                String firsttwo = inputfirst.length()>=2 ? inputfirst.substring(0,2): inputfirst;
                String lastthree = inputlast.length()>=3 ? inputlast.substring(0,3): inputlast;
                String citytwo = inputschool.length()>=2 ? inputschool.substring(0,2): inputschool;
                String schoolthree = inputcity.length()>=3 ? inputcity.substring(0,3): inputcity;


                String firstName = firsttwo + lastthree;
                String lastName = citytwo + schoolthree;
                String origin = inputpet + " "+ inputchara;


                textView.setText( firstName + " "+ lastName + " from the planet "+ origin);




            }
        });




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}