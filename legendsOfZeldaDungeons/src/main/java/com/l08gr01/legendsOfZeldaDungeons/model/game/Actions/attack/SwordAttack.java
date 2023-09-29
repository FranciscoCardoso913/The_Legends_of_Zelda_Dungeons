package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;

import java.util.ArrayList;

public class SwordAttack extends Attack {
    public SwordAttack(Character character, ArrayList<ArrayList<Image>> sprites, int framesPerSprite, int damage, ArrayList<Hitbox> weaponHitboxes) {
        super(character, sprites, framesPerSprite, damage,weaponHitboxes);
    }
    @Override
    public void step(Arena arena){
        currentAttackState.attack();
        int framesPerSprite= currentAttackState.getFramesPerSprite();
        int quantity=currentAttackState.getQuantity();
        for(Character monster:arena.getMonsters()){
            if(monster.getHitbox().collidesWith(currentAttackState.getWeaponHitbox()))
                monster.decreaseHealth(damage/(framesPerSprite*quantity));
        }
        if(arena.getLink().getHitbox().collidesWith(currentAttackState.getWeaponHitbox()) && !arena.getLink().isInvincible())
            arena.getLink().decreaseHealth(damage);
    };
}
