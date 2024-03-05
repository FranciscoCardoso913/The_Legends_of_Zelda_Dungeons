package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;

import java.util.ArrayList;

public class AttackState {
    Attack attack;
    ArrayList<Image> sprites;
    int currSprite=0;

    int framesPerSprite;

    int quantity;
    Hitbox hitbox;

    public AttackState(Attack attack, ArrayList<Image>sprites, int framesPerSprite, Hitbox hitbox){
        this.attack = attack;
        quantity=sprites.size();
        this.sprites= sprites;
        this.framesPerSprite=framesPerSprite;
        this.hitbox=hitbox;
    }


    public void attack() {
        if(currSprite+1>=quantity*framesPerSprite) {
            currSprite = 0;
            attack.setCharacterAction(attack.getCharacter().getMover());
        }
        else
            currSprite= (currSprite+1);
    }
    public Hitbox getWeaponHitbox(){
        return hitbox;
    }
    public int getFramesPerSprite(){return framesPerSprite;}
    public Image getSprite() {
        return sprites.get(currSprite/framesPerSprite);
    }

    public int getCurrSprite() {
        return currSprite;
    }

    public int getQuantity() {
        return quantity;
    }
}
