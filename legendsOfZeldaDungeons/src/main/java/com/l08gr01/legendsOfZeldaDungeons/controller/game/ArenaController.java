package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.Sound;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;

import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.Menu;

import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;
import com.l08gr01.legendsOfZeldaDungeons.states.MenuState;


import java.io.IOException;
import java.util.ArrayList;

public class ArenaController extends GameController{
    private final LinkController heroController;
    private final MonsterController monsterController;
    private final ProjectileController projectileController;
    Sound sound;

    public ArenaController(Arena arena) {
        super(arena);
        sound = new Sound("/sounds/game.wav");
        this.heroController = new LinkController(arena);
        this.monsterController = new MonsterController(arena);
        this.projectileController = new ProjectileController(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if(!sound.isRunning()) sound.start();
        if(action == GUI.ACTION.QUIT || getModel().getLink().getHealth()<=0) {
            if (getModel().getLink().getHealth()<=0) {
                if (getModel().getScore() > Database.getDatabase().getHighscore()) {
                    Database.getDatabase().setHighscore(getModel().getScore());
                }
            }
            sound.stop();
            game.setState(new MenuState(new Menu())) ;
        }
        else{
           // Projectiles move first?
            heroController.step(game, action, time);
            projectileController.step(game, action, time);
            monsterController.step(game, action, time);
            upDate();
        }
    }

    public void upDate(){
        ArrayList<Monster> monstersAlive= new ArrayList<>();
        for(Monster monster: getModel().getMonsters()){
            if(monster.getHealth()>0) monstersAlive.add(monster);
            else {
                incrementScore(monster.getPoints());
            }
        }
        getModel().setMonsters(monstersAlive);

        ArrayList<Projectile> arrowsIntact= new ArrayList<>();
        for(Projectile projectile : getModel().getProjectiles())
            if(!projectile.isFoundTarget())arrowsIntact.add(projectile);
        getModel().setProjectiles(arrowsIntact);
    }
    public void incrementScore(int increment){
        int score = getModel().getScore() + increment;
        int maxScore = getModel().getMaxScore();
        if(score > maxScore){
            score = maxScore;
        }
        getModel().setScore(score);
    }
    public Sound getSound(){
        return sound;
    }
}
