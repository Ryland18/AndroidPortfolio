package com.example.portfolioupgraded.ui.gallery;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.portfolioupgraded.databinding.FragmentMadlibsBinding;

public class madlibsFragment extends Fragment {

    private FragmentMadlibsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        madlibsViewModel galleryViewModel =
                new ViewModelProvider(this).get(madlibsViewModel.class);

        binding = FragmentMadlibsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        
        EditText first = binding.FirstText;
        EditText second = binding.SecondText;
        EditText third = binding.ThirdText;
        EditText fourth = binding.FourthText;
        EditText fifth = binding.FifthText;
        EditText sixth = binding.SixthText;
        EditText seventh = binding.SeventhText;
        EditText eight = binding.EightText;



        Button submit = binding.button3;
        Button reset = binding.button4;




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setVisibility(GONE);
                second.setVisibility(GONE);
                third.setVisibility(GONE);
                fourth.setVisibility(GONE);
                fifth.setVisibility(GONE);
                sixth.setVisibility(GONE);
                seventh.setVisibility(GONE);
                eight.setVisibility(GONE);

                String firstT = first.getText().toString();
                String secondT = second.getText().toString();
                String thirdT = third.getText().toString();
                String fourthT = fourth.getText().toString();
                String fifthT = fifth.getText().toString();
                String sixthT = sixth.getText().toString();
                String seventhT = seventh.getText().toString();
                String eightT = eight.getText().toString();

                textView.setVisibility(VISIBLE);
                textView.setText("In a "+ firstT+ "with many fine variatys of "+secondT+ " although it was missing a couple of "+thirdT+" as well as a few "+fourthT + " however it is still a +"+fifthT+ " with very friendly +"+sixthT+ " as long as you follow the rule of +"+seventhT+" which is to always say "+eightT);


                reset.setVisibility(VISIBLE);
                submit.setVisibility(GONE);

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.setVisibility(VISIBLE);
                second.setVisibility(VISIBLE);
                third.setVisibility(VISIBLE);
                fourth.setVisibility(VISIBLE);
                fifth.setVisibility(VISIBLE);
                sixth.setVisibility(VISIBLE);
                seventh.setVisibility(VISIBLE);
                eight.setVisibility(VISIBLE);

                first.setText("");
                second.setText("");
                third.setText("");
                fourth.setText("");
                fifth.setText("");
                sixth.setText("");
                seventh.setText("");
                eight.setText("");



                textView.setVisibility(GONE);

                submit.setVisibility(VISIBLE);
                reset.setVisibility(GONE);

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