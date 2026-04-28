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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
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
    private RequestQueue requestQueue;
    private CardAdapter adapter;

    private String spriteName = "";

    private static ArrayList<String> resistance;
    private static ArrayList<String> weaknesse;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        madlibsViewModel galleryViewModel =
                new ViewModelProvider(this).get(madlibsViewModel.class);

        binding = FragmentPokemonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Sample data


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        viewPager = root.findViewById(R.id.viewPager);
        dotsLayout = root.findViewById(R.id.dotsLayout);

        thePokemonList = new ArrayList<>();

        adapter = new CardAdapter(thePokemonList);
        viewPager.setAdapter(adapter);

        loadPokemon("1007",100,80);



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

    public void loadPokemon(String urlNumber, int power1, int power2){
        //the URL to request
        String url = "https://pokeapi.co/api/v2/pokemon/"+urlNumber;
        //set up request for jasons
        //new request(web method, url ,anyListeners , mehtods to happen after data pull, what to do if errors)
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                // if we get a 200 response code - http response code
                try{
                    //from chatgpt in assistig with grabbing only one pokemon
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name"); // name and url are the data keywords

                    int hp = jsonObject.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
                    double length = jsonObject.getDouble("height");
                    double weight = jsonObject.getDouble("weight");
                    String preEvolution = "none";


                    JSONArray typesD = jsonObject.getJSONArray("types");
                    resistance = new ArrayList<>();
                    weaknesse = new ArrayList<>();
                    ArrayList<String> type = new ArrayList<>();
                    ArrayList<String> typeurList = new ArrayList<>();
                    for (int t = 0; t<typesD.length();t++){
                        JSONObject typeEntry = typesD.getJSONObject(t);
                        JSONObject typeData = typeEntry.getJSONObject("type");
                        String typeName = typeData.getString("name");
                        String typeurl = typeData.getString("url");
                        type.add(typeName);
                        typeurList.add(typeurl);

                    }

                    Log.d("resistancePrivate -- After", resistance.toString());



                    // add the pokemon to the list
                    JSONArray moveList = jsonObject.getJSONArray("moves");
                    ArrayList<String> moveBook = new ArrayList<>();
                    for (int p=0;p<moveList.length();p++){
                        JSONObject moveEntry = moveList.getJSONObject(p);
                        JSONObject moveData = moveEntry.getJSONObject("move");
                        String moveName = moveData.getString("name");
                        moveBook.add(moveName);
                        }

                    JSONObject spriteList = jsonObject.getJSONObject("sprites");


                    spriteName = spriteList.getString("front_default");

                   // Log.d("loadDetails",String.valueOf(spriteName));
                   //from https://www.youtube.com/watch?v=9_Tf3NSD2-M
                   // Glide.
                   //         with(pokemon_card.this).
                   //         load(spriteName).
                   //         into(mainImage);

                    //from chatgpt to get all calls complete

                    int totalCalls = typeurList.size() * 2;
                    int[] competedCalls = {0};
                    for (int w = 0; w<typeurList.size(); w++){

                        getResistance(typeurList.get(w), new ResistanceCallback() {
                            @Override
                            public void onSuccess(String res) {
                                Log.d("resistance lp1",res);
                                resistance.add(res);
                                competedCalls[0]++;
                                 checkIfDone();
                            }
                            private void checkIfDone(){
                                if (competedCalls[0] == totalCalls){
                                    thePokemonList.add(new Pokemon(id,name,hp,type.toString(),length ,weight,preEvolution,moveBook.get(69),moveBook.get(68),power1,power2,weaknesse.toString(),resistance.toString(),spriteName));

                                }
                            }
                        });
                        getWeakness(typeurList.get(w), new ResistanceCallback() {
                            @Override
                            public void onSuccess(String weakness) {
                                weaknesse.add(weakness);
                                competedCalls[0]++;
                                checkIfDone();

                            }
                            private void checkIfDone(){
                                if (competedCalls[0] == totalCalls){
                                    thePokemonList.add(new Pokemon(id,name,hp,type.toString(),length,weight,preEvolution,moveBook.get(69),moveBook.get(68),power1,power2,weaknesse.toString(),resistance.toString(),spriteName));

                                }
                            }
                        });
                    }






                     Log.d("resistance lp1", thePokemonList.toString());
                    Log.d("loadDetails",String.valueOf(moveBook));

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



    public void getWeakness(String urls, ResistanceCallback callback){
        //the URL to request
        //set up request for jasons
        //new request(web method, url ,anyListeners , mehtods to happen after data pull, what to do if errors)
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urls, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                // if we get a 200 response code - http response code
                try{
                    //from chatgpt in assistig with grabbing only one pokemon


                    JSONObject resistList = jsonObject.getJSONObject("damage_relations");
                    JSONArray weakObjects = resistList.getJSONArray("double_damage_from");
                    ArrayList<String> weakBook = new ArrayList<>();
                    for (int p=0;p<weakObjects.length();p++){
                        JSONObject weakEntry = weakObjects.getJSONObject(p);
                        String weakName = weakEntry.getString("name");
                        weakBook.add(weakName);
                    }

                    adapter.notifyDataSetChanged();

                    String weaknesses = String.join(" ",weakBook);
                    Log.d("weakness",weaknesses);
                    callback.onSuccess(weaknesses);


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

    //from chatgpt on how to return a string for weakness and resistance
    public interface ResistanceCallback {
        void onSuccess(String resistance);
    }
    public interface WeaknessCallback {
        void onSuccess(String weakness);
    }
    public void getResistance(String urls, ResistanceCallback callback) {
        //the URL to request
        //set up request for jasons
        //new request(web method, url ,anyListeners , mehtods to happen after data pull, what to do if errors)
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urls, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                // if we get a 200 response code - http response code
                try {
                    //from chatgpt in assistig with grabbing only one pokemon

                    ///Resistance


                    JSONObject resistList = jsonObject.getJSONObject("damage_relations");
                    JSONArray resistObjects = resistList.getJSONArray("half_damage_from");
                    ArrayList<String> resistBook = new ArrayList<>();
                    for (int p = 0; p < resistObjects.length(); p++) {
                        JSONObject resistEntry = resistObjects.getJSONObject(p);
                        String resistName = resistEntry.getString("name");
                        resistBook.add(resistName);
                    }


                    /////weakness


                    String resistances = String.join(" ",resistBook);

                    callback.onSuccess(resistances);

                } catch (JSONException e) {
                    Log.e("Adapter LoadPokemon", "Json error", e);
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
