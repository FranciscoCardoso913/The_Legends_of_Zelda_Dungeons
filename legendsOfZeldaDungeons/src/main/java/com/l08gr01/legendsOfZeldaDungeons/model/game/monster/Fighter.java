package com.l08gr01.legendsOfZeldaDungeons.model.game.monster;

import com.l08gr01.legendsOfZeldaDungeons.Database;
import com.l08gr01.legendsOfZeldaDungeons.model.Hitbox;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.SwordAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.AttackStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.attackStrategy.MeleeAttack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.MoveStrategy;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.moveStrategy.ChaseMovement;
import com.l08gr01.legendsOfZeldaDungeons.sprites.FighterSprites;
import java.util.ArrayList;

public class Fighter extends Monster {
    private Attack swordAttack;

    public Fighter(int x, int y, int level) {
        super(x, y, level, 20);
        velocity = 2;
        FighterSprites fighterSprites = Database.getDatabase().getFighterSprites();
        hitbox = new Hitbox(25, 19, position, new Position(13, 8));
        move = new Move(this, fighterSprites, 2);
        move.setCurrentMovingState(move.getCharacterMovingUp());
        currentAction = move;
        swordAttack = new SwordAttack(this,fighterSprites.getAttackSprites(),3,20,createSwordHitbox());
        attack = swordAttack;
        setHealth(40*monstersLevel);
        maxHealth=40*monstersLevel;
    }

    @Override

    protected AttackStrategy buildAttackStrategy() {
        return new MeleeAttack(this);
    }

    @Override
    protected MoveStrategy buildMoveStrategy() {
        return new ChaseMovement(this);
    }

    public ArrayList<Hitbox> createSwordHitbox(){
        ArrayList<Hitbox> hitboxes= new ArrayList<>();
        hitboxes.add(new Hitbox(11,9,position,new Position(23,-4)));
        hitboxes.add(new Hitbox(11,9,position,new Position(12,34)));
        hitboxes.add(new Hitbox(9,11,position,new Position(0,19)));
        hitboxes.add(new Hitbox(9,11,position,new Position(34,19)));
        return hitboxes;
    }
}
