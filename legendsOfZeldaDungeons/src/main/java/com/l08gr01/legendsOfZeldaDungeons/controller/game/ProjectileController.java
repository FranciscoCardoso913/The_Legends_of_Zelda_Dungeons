package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectileController extends GameController {
    public ProjectileController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        for (Projectile projectile : getModel().getProjectiles()) {
            projectile.updatePosition();
            if (checkHits(projectile)) {
                projectile.destroyed();
            }
        }

    }

    public boolean checkHits(Projectile projectile) {
        for (Monster monster : getModel().getMonsters()) {
            if (projectile.getHitbox().collidesWith(monster.getHitbox())) {
                monster.decreaseHealth(projectile.getDamage());
                return true;
            }
        }

        if (projectile.getHitbox().collidesWith(getModel().getLink().getHitbox())) {
            getModel().getLink().decreaseHealth(projectile.getDamage());
            return true;
        }

        return checkCollision(projectile.getPosition());
    }

    public boolean checkCollision(Position position){
        int x,y;
        List<Position> hitBox= getNewHitBox(position);
        for(Position pos: hitBox){
            x=pos.getX()/32;
            y= pos.getY()/32;
            if(getModel().getCollision().get(y).get(x)==0 ||getModel().getCollision().get(y).get(x)==2) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Position> getNewHitBox(Position position){
        ArrayList<Position> hitbox= new ArrayList<>();
        hitbox.add(new Position(position.getX(),position.getY()+getModel().getLink().getHeight()));
        hitbox.add(new Position(position.getX(),position.getY()+getModel().getLink().getHeight()/2));
        hitbox.add(new Position(position.getX()+getModel().getLink().getWidth(),position.getY()+getModel().getLink().getHeight()/2));
        hitbox.add(new Position(position.getX()+getModel().getLink().getWidth(),position.getY()+getModel().getLink().getHeight()));
        return hitbox;
    }
}
