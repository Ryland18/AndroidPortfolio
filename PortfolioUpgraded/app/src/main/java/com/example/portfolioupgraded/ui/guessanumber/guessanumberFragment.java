package com.example.portfolioupgraded.ui.guessanumber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.portfolioupgraded.databinding.FragmentGuessanumberBinding;
import com.example.portfolioupgraded.ui.guessanumber.guessanumberViewModel;

public class guessanumberFragment extends Fragment {

    private FragmentGuessanumberBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            guessanumberViewModel guessanumberViewModel =
                new ViewModelProvider(this).get(guessanumberViewModel.class);

        binding = FragmentGuessanumberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView3;
        guessanumberViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button button = binding.button;
        EditText editText = binding.editTextText;
        textView.setText("Welcome to the Guess a number game where you guess the number the AI comes up with");
        int points = 0;
        int guesses = 10;
        //ai to help convert a text from edit text into a int and to check if they put a number in
        boolean ok = false;
        int guess = 0;

        while(ok){
        String lettnumbers = editText.getText().toString();
        try{
            guess = Integer.parseInt(lettnumbers);
            ok = true;

        }
        catch (NumberFormatException e){
            textView.setText("Invalid number try again");
            editText.setText("");
        }
        }


        //ai to assist in importing and using random to gen between 0 and 100
        Random random = new Random();
        int number = random.nextInt(101);
        int finalGuess = guess;
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (guesses!=0){
                        if (number> finalGuess){

                        }
                    }
                    else{

                    }


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