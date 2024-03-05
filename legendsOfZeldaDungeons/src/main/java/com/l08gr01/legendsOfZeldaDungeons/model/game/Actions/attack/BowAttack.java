package com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Character;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.projectile.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class BowAttack extends Attack{
    public BowAttack(Character character, ArrayList<ArrayList<Image>> sprites, int framesPerSprite, int damage, ArrayList<Hitbox> weaponHitboxes) {
        super(character, sprites, framesPerSprite, damage, weaponHitboxes);
    }
    @Override
    public void step(Arena arena) {
        currentAttackState.attack();
        Position position= currentAttackState.getWeaponHitbox().getHitboxPosition();
        int width= currentAttackState.getWeaponHitbox().getWidth();
        int height= currentAttackState.getWeaponHitbox().getHeight();
        if (currentAttackState.getCurrSprite() == currentAttackState.getQuantity()-1) {
            Database database = Database.getDatabase();
            ArrayList<Image> arrowSprites = this.character instanceof Link ? database.getProjectileSprites().getLinkArrows() : database.getProjectileSprites().getMonsterArrows();
            if (currentAttackState == attackUp) {
                arena.getProjectiles().add(new ProjectileUp(arrowSprites.get(0), position, 8, getDamage(),width,height));
            } else if (currentAttackState == attackDown) {
                arena.getProjectiles().add(new ProjectileDown(arrowSprites.get(1), position, 8, getDamage(),width,height));
            } else if (currentAttackState == attackLeft) {
                arena.getProjectiles().add(new ProjectileLeft(arrowSprites.get(2), position, 8, getDamage(),width,height));
            } else if (currentAttackState == attackRight) {
                arena.getProjectiles().add(new ProjectileRight(arrowSprites.get(3),position, 8, getDamage(),width,height));
            }
        }

    }
}
