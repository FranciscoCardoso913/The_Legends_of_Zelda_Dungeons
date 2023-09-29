package com.l08gr01.legendsOfZeldaDungeons.model.game;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.ShieldAttack;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.BowAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.SwordAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.sprites.LinkSprites;

import java.util.ArrayList;

public class Link extends Character {
    private Attack swordAttack;
    private Attack shieldAttack;
    private Attack bowAttack;

    private boolean isInvincible;
    private long gotInvincible;


    public Link(int x, int y) {
        super(x, y);
        velocity = 5;
        health = 160;
        maxHealth=160;
        hitbox = new Hitbox(15, 16, position,new Position(13,15));
        isInvincible = false;
        LinkSprites linkSprites = Database.getDatabase().getLinkSprites();
        swordAttack= new SwordAttack(this,linkSprites.getAttackSprites(),1,20,createLinkSword());
        bowAttack= new BowAttack(this,linkSprites.getBowSprites(),2,10,createLinkBow());
        shieldAttack= new ShieldAttack(this,linkSprites.getShieldSprites(),15,0,createLinkShield());
        attack= swordAttack;
        move = new Move(this,linkSprites,1);
        currentAction= move;
        move.setCurrentMovingState(move.getCharacterMovingUp());
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public long getGotInvincible() {
        return gotInvincible;
    }

    @Override
    public Image getSprite() {
        return currentAction.getSprite();
    }

    public void attack(){
        attack=swordAttack;
    }

    @Override
    public void decreaseHealth(int damage) {

        if(!isInvincible) {
            setHealth(getHealth() - damage);
            setInvincible(true);
            gotInvincible = System.currentTimeMillis();
        }
    }

    public void bow(){
       attack=bowAttack;
    }

    public void shield(){
        attack=shieldAttack;
    }


    public ArrayList<Hitbox> createLinkSword(){
        ArrayList<Hitbox> hitboxes= new ArrayList<>();
        hitboxes.add(new Hitbox(10,42,position,new Position(0,0)));
        hitboxes.add(new Hitbox(10,42,position,new Position(0,32)));
        hitboxes.add(new Hitbox(42,10,position,new Position(0,0)));
        hitboxes.add(new Hitbox(42,10,position,new Position(31,0)));
        return hitboxes;
    }
    public ArrayList<Hitbox> createLinkBow(){
        ArrayList<Hitbox> hitboxes= new ArrayList<>();
        hitboxes.add(new Hitbox(15,5,position,new Position(15,-1)));
        hitboxes.add(new Hitbox(15,5,position,new Position(15,31)));
        hitboxes.add(new Hitbox(5,15,position,new Position(-5,19)));
        hitboxes.add(new Hitbox(5,15,position,new Position(31,19)));
        return hitboxes;
    }
    public ArrayList<Hitbox> createLinkShield(){
        ArrayList<Hitbox> hitboxes= new ArrayList<>();
        hitboxes.add(new Hitbox(1,14,position,new Position(14,15)));
        hitboxes.add(new Hitbox(1,14,position,new Position(14,30)));
        hitboxes.add(new Hitbox(14,1,position,new Position(13,16)));
        hitboxes.add(new Hitbox(14,1,position,new Position(28,16)));
        return hitboxes;
    }

    @Override
    public void increaseHealth(int recovery) {
        if(!isInvincible) {
            setHealth(getHealth()+recovery);
        }
    }


}