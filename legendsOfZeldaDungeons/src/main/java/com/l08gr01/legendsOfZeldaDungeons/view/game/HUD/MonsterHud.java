package com.l08gr01.legendsOfZeldaDungeons.view.game.HUD;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Camera;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;

import java.awt.*;
import java.io.IOException;
import java.lang.Math;

public class MonsterHud implements HealthHud{
    int nHearts;
    int healthBar;
    Position position;
    public MonsterHud(int nHearts){
        this.nHearts=nHearts;
    }

    @Override
    public void update(Character character, GUI gui, Camera camera) throws IOException {

        double health=character.getHealth();
        double maxHealth= character.getMaxHealth();
        double n = Math.ceil(health*nHearts/maxHealth);
        healthBar=(int)n;
        int x=character.getHitboxPosition().getX()-camera.getCameraPositon().getX();
        int y=character.getHitboxPosition().getY()-camera.getCameraPositon().getY()-10;
        position=new Position(x,y);
        draw(gui);

    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.drawRetangle(position,nHearts,2,new Color(113,0,0));
        gui.drawRetangle(position, healthBar, 2, new Color(0, 113, 0));
       }
    }

