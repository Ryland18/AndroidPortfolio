package com.example.portfolioupgraded.ui.pokemon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.portfolioupgraded.R;
import com.example.portfolioupgraded.databinding.FragmentMadlibsBinding;
import com.example.portfolioupgraded.databinding.FragmentPokemonBinding;
import com.example.portfolioupgraded.ui.madlib.madlibsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PokemonFragment extends Fragment {
    private FragmentPokemonBinding binding;
    private ViewPager2 viewPager;
    private LinearLayout dotsLayout;
    private List<Pokemon> thePokemonList = new ArrayList<>();
    private TextView[] dots;
    private HashSet<JsonObjectRequest> requestQueue;
    private CardAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        madlibsViewModel galleryViewModel =
                new ViewModelProvider(this).get(madlibsViewModel.class);

        binding = FragmentPokemonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Sample data

        viewPager = root.findViewById(R.id.viewPager);
        dotsLayout = root.findViewById(R.id.dotsLayout);

        thePokemonList = new ArrayList<>();

        adapter = new CardAdapter(thePokemonList);
        viewPager.setAdapter(adapter);
        loadPokemon();


        setupDots(adapter.getItemCount());
        selectDot(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }
        });

        return root;
    }

    public void loadPokemon(){
        //the URL to request
        String url = "https://pokeapi.co/api/v2/pokemon?limit=151";
        //set up request for jasons
        //new request(web method, url ,anyListeners , mehtods to happen after data pull, what to do if errors)
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                // if we get a 200 response code - http response code
                try{
                    //put into a JSONArray
                    JSONArray results = jsonObject.getJSONArray("results");
                    //loop through array and get the name and url of each pokemon
                    for (int i=0; i<results.length();i++){
                        JSONObject data = results.getJSONObject(i); // converts each data item into a jason object
                        String name = data.getString("name"); // name and url are the data keywords
                        String url = data.getString("url");
                        thePokemonList.add(new Pokemon(name,url)); // add the pokemon to the list
                    }
                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e){
                    Log.e("Adapter LoadPokemon","Json error",e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Adapter LoadPokemon", "PokeApi " + volleyError);
            }
        });
        //add the request to the queue
        requestQueue.add(request);
        /////add request to the queue



    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void setupDots(int count) {
        dots = new TextView[count];
        dotsLayout.removeAllViews();

        for (int i = 0; i < count; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setBackground(getResources().getDrawable(R.drawable.dot_inactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(8, 0, 8, 0);

            dotsLayout.addView(dots[i], params);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void selectDot(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setBackground(getResources().getDrawable(R.drawable.dot_active));
            } else {
                dots[i].setBackground(getResources().getDrawable(R.drawable.dot_inactive));
            }
        }
    }
}
