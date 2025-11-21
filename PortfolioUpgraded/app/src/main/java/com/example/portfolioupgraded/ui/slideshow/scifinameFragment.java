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
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        EditText first = binding.editTextText2;
        EditText last = binding.editTextText3;
        EditText city = binding.editTextText4;
        EditText school = binding.editTextText5;
        EditText pet = binding.editTextText6;
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
                String lastthree = inputfirst.length()>=3 ? inputfirst.substring(0,3): inputfirst;
                String citytwo = inputfirst.length()>=2 ? inputfirst.substring(0,2): inputfirst;
                String schoolthree = inputfirst.length()>=3 ? inputfirst.substring(0,3): inputfirst;


                String firstName = firsttwo+lastthree;
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