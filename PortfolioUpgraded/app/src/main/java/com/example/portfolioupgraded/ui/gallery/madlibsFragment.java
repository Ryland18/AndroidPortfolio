package com.example.portfolioupgraded.ui.gallery;

import static android.view.View.GONE;

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
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        EditText first = binding.FirstText;
        EditText second = binding.SecondText;
        EditText third = binding.ThirdText;
        EditText fourth = binding.FourthText;
        EditText fifth = binding.FifthText;
        EditText sixth = binding.SixthText;
        EditText seventh = binding.SeventhText;
        EditText eight = binding.EightText;



        Button submit = binding.button3;

        String firstT = first.getText().toString();
        String secondT = second.getText().toString();
        String thirdT = third.getText().toString();
        String fourthT = fourth.getText().toString();
        String fifthT = fifth.getText().toString();
        String sixthT = sixth.getText().toString();
        String seventhT = seventh.getText().toString();
        String eightT = eight.getText().toString();


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


                textView.setText("In a "+ firstT+ "with many fine variatys of "+secondT+ " although it was missing a couple of "+thirdT+" as well as a few "+fourthT);




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