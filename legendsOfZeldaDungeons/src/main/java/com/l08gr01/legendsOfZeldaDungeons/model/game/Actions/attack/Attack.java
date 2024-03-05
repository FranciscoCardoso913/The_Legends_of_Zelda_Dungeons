package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.Action;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;

import java.util.HashMap;
import java.util.ArrayList;

public  class Attack implements Action {
    AttackState attackDown;
    AttackState attackUp;
    AttackState attackLeft;
    AttackState attackRight;
    AttackState currentAttackState;
    Character character;
    int damage;

    public  Attack(Character character, ArrayList<ArrayList<Image>> sprites, int framesPerSprite, int damage, ArrayList<Hitbox>weaponHitboxes){
        this.character=character;
        this.attackUp= new AttackState(this,sprites.get(0),framesPerSprite,weaponHitboxes.get(0));
        this.attackDown= new AttackState(this,sprites.get(1),framesPerSprite,weaponHitboxes.get(1));
        this.attackLeft= new AttackState(this,sprites.get(2),framesPerSprite,weaponHitboxes.get(2));
        this.attackRight= new AttackState(this,sprites.get(3),framesPerSprite,weaponHitboxes.get(3));
        this.damage=damage;
        this.currentAttackState = attackUp;
    }

    public void setAttackDown() {
        currentAttackState=attackDown;
    }
    public void setAttackUp() {
        currentAttackState=attackUp;
    }
    public void setAttackLeft() {
        currentAttackState=attackLeft;
    }
    public void setAttackRight() {
        currentAttackState=attackRight;
    }

    public int getDamage(){
        return damage;
    }

    public Hitbox getAttackHitbox(){
        return currentAttackState.getWeaponHitbox();
    }
    public void step(Arena arena){
        currentAttackState.attack();
    };

    public AttackState getAttackDown() {
        return attackDown;
    }

    public AttackState getAttackUp() {
        return attackUp;
    }

    public AttackState getAttackLeft() {
        return attackLeft;
    }

    public AttackState getAttackRight() {
        return attackRight;
    }

    public AttackState getCurrentAttackState() {
        return currentAttackState;
    }

    @Override
    public Image getSprite() {
        return currentAttackState.getSprite();
    }

    @Override
    public void setCharacterAction(Action action) {
            character.setCharacterAction(action);
    }

    @Override
    public Character getCharacter() {
        return character;
    }

}
