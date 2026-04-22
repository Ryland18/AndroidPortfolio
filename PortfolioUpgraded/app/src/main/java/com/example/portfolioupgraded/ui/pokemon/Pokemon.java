package com.example.portfolioupgraded.ui.pokemon;
public class Pokemon {
    private String name, move1, move2, typing, description, preEvolution, weakness, resistance, url;
    private int id, hp, move1dps, move2dps;

    private double  length, weight;

    //constructors


    public Pokemon(int id,String name, int hp,String typing, double length, double weight,String preEvolution, String move1, String move2, int move1dps, int move2dps, String weakness, String resistance) {

        this.id = id;
        this.name = name;
        this.hp = hp;
        this.typing = typing;
        this.length = length;
        this.weight = weight;
        this.preEvolution = preEvolution;
        this.move1 = move1;
        this.move2 = move2;
        this.move1dps = move1dps;
        this.move2dps = move2dps;
        this.weakness = weakness;
        this.resistance = resistance;
    }

    //getters and setters


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMove1() {
        return move1;
    }

    public void setMove1(String move1) {
        this.move1 = move1;
    }

    public String getMove2() {
        return move2;
    }

    public void setMove2(String move2) {
        this.move2 = move2;
    }

    public String getTyping() {
        return typing;
    }

    public void setTyping(String typing) {
        this.typing = typing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getPreEvolution() {
        return preEvolution;
    }

    public void setPreEvolution(String preEvolution) {
        this.preEvolution = preEvolution;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMove1dps() {
        return move1dps;
    }

    public void setMove1dps(int move1dps) {
        this.move1dps = move1dps;
    }

    public int getMove2dps() {
        return move2dps;
    }

    public void setMove2dps(int move2dps) {
        this.move2dps = move2dps;
    }

    //toString


    @Override
    public String toString() {
        return "Pokemon{" +
                ", id=" + id +
                "name='" + name + '\'' +
                ", hp=" + hp + '\''+
                ", typing='" + typing + '\'' +
                ", description='" + description + '\'' +
                ", length='" + length + '\'' +
                ", weight='" + weight + '\'' +
                ", preEvolution='" + preEvolution + '\'' +
                ", move1='" + move1 + '\'' +
                ", move2='" + move2 + '\'' +
                ", move1dps=" + move1dps +
                ", move2dps=" + move2dps +
                ", weakness='" + weakness + '\'' +
                ", resistance='" + resistance +
                '}';
    }


}




