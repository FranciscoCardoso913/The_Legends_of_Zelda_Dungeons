package com.l08gr01.legendsOfZeldaDungeons.model.game.Arena;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.view.game.HUD.LinkHealthHud;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.view.game.HUD.ScoreHud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

public class Arena{
    private int smallerSpritesSize;
    private ArrayList<Map> maps;
    private ArrayList<Monster> monsters;
    private ArrayList<Projectile> projectiles;
    private int currLevel;
    private int score;
    private int maxScore;
    private Link link;
    private int monstersLevel;

    Camera camera;

  public Arena(Game game) throws IOException {

      currLevel=-1;
      link= new Link(0,0);
      score= 0;
      monstersLevel=1;
      maxScore= 999999;
      smallerSpritesSize=32;
      projectiles = new ArrayList<>();
      camera =new Camera(new Position(0,0),game.getWidth(), game.getHeight(), this);
      loadMaps();
      loadNextMap();
  }

    public  void loadMaps() throws IOException {
        maps = new ArrayList<>();
      Map1 map1= new Map1(link, monstersLevel);
      maps.add(map1);
      Map2 map2=new Map2(link, monstersLevel);
      maps.add(map2);
      Map3 map3= new Map3(link, monstersLevel);
      maps.add(map3);
      Map4 map4= new Map4(link, monstersLevel);
      maps.add(map4);
    }



    public void loadNextMap() throws IOException {
      currLevel= (currLevel+1);
      if(currLevel>=maps.size()){
          currLevel=0;
          monstersLevel++;
          loadMaps();
      }
      camera.setPosition(maps.get(currLevel).getCameraIntialPosition());
      maps.get(currLevel).resetMap();
      monsters = getMonstersFromMap();
    }

    public Link getLink(){
        return link;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    private ArrayList<Monster> getMonstersFromMap(){ return maps.get(currLevel).getMonsters();}

    public ArrayList<ArrayList<Image>> getMap(){
        return maps.get(currLevel).getMap();
    }

    public Map getCurrMap(){ return maps.get(currLevel); }

    public ArrayList<ArrayList<Integer>> getCollision(){return  maps.get(currLevel).getCollision();}

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public int getSmallerSpritesSize() {
        return smallerSpritesSize;
    }

    public Camera getCamera() {
        return camera;
    }

    public int getScore() { return score; }

    public int getMonstersLevel() { return monstersLevel; }

    public int getCurrLevel() { return currLevel; }

    public ArrayList<Map> getMaps() { return maps; }

    public int getMaxScore() { return maxScore; }

    public void setScore(int score) { this.score = score; }

    public void setMonsters(ArrayList<Monster> monsters) { this.monsters = monsters; }

}
