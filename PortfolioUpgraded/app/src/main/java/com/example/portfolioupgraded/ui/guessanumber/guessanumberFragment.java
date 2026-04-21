package com.example.portfolioupgraded.ui.guessanumber;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.portfolioupgraded.databinding.FragmentGuessanumberBinding;
import com.example.portfolioupgraded.ui.guessanumber.guessanumberViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class guessanumberFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean guessing;

    private FragmentGuessanumberBinding binding;
    private String username;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            guessanumberViewModel guessanumberViewModel =
                new ViewModelProvider(this).get(guessanumberViewModel.class);

        binding = FragmentGuessanumberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        
        final TextView textView = binding.textView3;
        guessanumberViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        guessing = false;

        Button button = binding.button;
        TextView leaderboard = binding.leaderBoard;
        EditText editText = binding.editTextText;
        TextView score = binding.textView5;
        textView.setText("Welcome to the Guess a number game where you guess the number the AI comes up with");
        final int[] points = {0};
        final int[] guesses = {10};
        //ai to help convert a text from edit text into a int and to check if they put a number in
        final boolean[] ok = {true};
        final int[] guess = {0};
        username = "";

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                leaderboard.setText(document.getData().toString());
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });





        //ai to assist in importing and using random to gen between 0 and 100
        Random random = new Random();
        final int[] number = {random.nextInt(101)};
        final int[] finalGuess = {0};
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (guessing) {
                        String lettnumbers = editText.getText().toString();
                        try {
                            guess[0] = Integer.parseInt(lettnumbers);

                            if (!(guesses[0] == 0)) {
                                finalGuess[0] = guess[0];
                                if (number[0] > finalGuess[0]) {
                                    textView.setText("You are too low you have " + guesses[0] + " guesses left");
                                    //ai for subtracting when it had errors for subtraction
                                    guesses[0]--;
                                } else if (number[0] < finalGuess[0]) {

                                    guesses[0]--;
                                    textView.setText("You are too high you have " + guesses[0] + " guesses left");
                                } else {
                                    textView.setText("congradulations you got it, now try again with a diffrent number");
                                    points[0] += 50;
                                    int extras = 5 * guesses[0];
                                    points[0] += extras;
                                    guesses[0] = 10;
                                    number[0] = random.nextInt(101);
                                    score.setText(String.valueOf(points[0]));

                                }
                            } else {
                                storeUsers(points[0]);
                                if (points[0] == 0) {

                                    textView.setText("Game Over, You guessed wrong");
                                    //ai for learning on how to shut off the button
                                    button.setEnabled(false);

                                } else {
                                    textView.setText("Game Over, You guessed wrong. But you have a score of " + points[0]);
                                    button.setEnabled(false);
                                }
                            }
                        } catch (NumberFormatException e) {
                            textView.setText("Invalid number try again");
                            editText.setText("");
                        }


                    }
                    else {
                        guessing =true;
                        username= textView.getText().toString();
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

    public void storeUsers(int score){
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put(username, score);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


}