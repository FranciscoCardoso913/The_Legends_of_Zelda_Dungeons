package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.Projectile;

import java.util.ArrayList;

public class ShieldAttack extends Attack{
    public ShieldAttack(Character character, ArrayList<ArrayList<Image>> sprites, int framesPerSprite, int damage, ArrayList<Hitbox> weaponHitboxes) {
        super(character, sprites, framesPerSprite, damage, weaponHitboxes);
    }
    @Override
    public void step(Arena arena){
        currentAttackState.attack();
        for( Character monster: arena.getMonsters()){

            if(monster.getAttack().getAttackHitbox().collidesWith(currentAttackState.getWeaponHitbox() ) && monster.getCharacterAction() instanceof Attack ){
               if(!arena.getLink().isInvincible()) character.increaseHealth(monster.getAttack().getDamage());

            }
        }
        for(Projectile projectile: arena.getProjectiles()) {
            if (projectile.getNextPosition().collidesWith(currentAttackState.getWeaponHitbox()) )
                 if(!arena.getLink().isInvincible())character.increaseHealth(projectile.getDamage());
        }
    }
}
