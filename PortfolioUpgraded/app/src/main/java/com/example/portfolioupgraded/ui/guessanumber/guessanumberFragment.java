package com.example.portfolioupgraded.ui.guessanumber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}