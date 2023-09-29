package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class Map {
    Link link;
    ArrayList<Monster> monsters;
    protected int monstersLevel;
    ArrayList<ArrayList<Integer>>collision;
    int monsterlevel;

    Position cameraIntialPosition;
    public Map(Link link,int monstersLevel){
        this.link= link;
        map= new ArrayList<>();
        collision= new ArrayList<>();
        monsters = new ArrayList<>();
        this.monstersLevel=monstersLevel;

    }
    protected ArrayList<ArrayList<Image>> map;
    public Link getLink(){
        return link;
    }
    public ArrayList<Monster> getMonsters(){ return monsters;}

    public ArrayList<ArrayList<Integer>> getCollision() {return collision;}
    public ArrayList<ArrayList<Image>> getMap(){
        return map;
    }

    public Position getCameraIntialPosition(){
        return cameraIntialPosition;
    }
    public abstract void resetMap();
    public void loadMap(int level) throws IOException {

        URL resource = Arena.class.getResource("/levels/level" + level + ".csv");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        readLines(br);
    }

    public void readLines(BufferedReader br) throws IOException {
        HashMap<String, Image> backgroundSprites = Database.getDatabase().getBackgroundSprites().getBackgroundSprites();
        ArrayList<Integer> auxiliary= new ArrayList<>();
        ArrayList<Image> lines = new ArrayList<>();
        StringBuilder string;
        for (String line; (line = br.readLine()) != null; ) {
            auxiliary=new ArrayList<>();
            String[] splited = line.split(",");
            lines=new ArrayList<>();
            for(String files:splited){
                string= new StringBuilder(files);
                if(string.charAt(0)=='P') auxiliary.add(1);
                else if(string.charAt(0)=='D')auxiliary.add(0);
                else auxiliary.add(2);
                string.deleteCharAt(0);

                lines.add( backgroundSprites.get(string.toString()));

            }
            collision.add(auxiliary);
            map.add(lines);
        }
    }

}
