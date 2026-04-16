package com.example.portfolioupgraded.ui.pokemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolioupgraded.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {



        private List<Pokemon> pokemonList;

        public CardAdapter(List<Pokemon> pokemonList) {
            this.pokemonList = pokemonList;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView id, description, name, hp, move1, move2, move1dps, move2dps, length, weight, weakness, resistance ,preEvolutionimg, type;
            ImageView image;
            public ViewHolder(View itemView) {
                super(itemView);
                 preEvolutionimg= itemView.findViewById(R.id.pokemonStageImageView);
                 name = itemView.findViewById(R.id.pokemonNameTextView);
                 id = itemView.findViewById(R.id.pokemonIdTXT);
                 hp = itemView.findViewById(R.id.pokemonHPTextView);
                 move1 = itemView.findViewById(R.id.pokemonMove1TextView);
                 move2 = itemView.findViewById(R.id.pokemonMove2TextView);
                 move1dps = itemView.findViewById(R.id.pokemonMove1PowerTextView);
                 move2dps = itemView.findViewById(R.id.pokemonMove2PowerTextView);
                 length = itemView.findViewById(R.id.pokemonLengthTextView);
                 weight = itemView.findViewById(R.id.pokemonWeightTextView);
                 weakness = itemView.findViewById(R.id.pokemonWeaknessTextView);
                 resistance = itemView.findViewById(R.id.pokemonResistanceTextView);
                image = itemView.findViewById(R.id.pokemonImageView);
                 type = itemView.findViewById(R.id.pokemonTypeImageView);
                 description = itemView.findViewById(R.id.pokemonDescriptionTextView);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pokemon_card, parent, false); // 👈 IMPORTANT
            return new ViewHolder(view);
        }

    @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Pokemon p = pokemonList.get(position);
            holder.id.setText(p.getId());
            holder.type.setText(p.getTyping());
            holder.hp.setText(p.getHp());
            holder.move1.setText(p.getMove1());
            holder.move2.setText(p.getMove2());
            holder.move1dps.setText(p.getMove1dps());
            holder.move2dps.setText(p.getMove2dps());
            holder.name.setText(p.getName());
            holder.length.setText((int) p.getLength());
            holder.weakness.setText(p.getWeakness());
            holder.weight.setText((int) p.getWeight());
            holder.resistance.setText(p.getResistance());
            holder.preEvolutionimg.setText(p.getPreEvolution());
            holder.description.setText(p.getDescription());

    }

        @Override
        public int getItemCount() {
            return pokemonList.size();
        }
    }

