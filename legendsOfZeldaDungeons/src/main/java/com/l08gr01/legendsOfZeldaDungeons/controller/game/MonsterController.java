package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Link;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MonsterController extends GameController {

    public MonsterController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        for (Monster monster : getModel().getMonsters()) {

            if (monster.getCharacterAction() instanceof Move) {
                Position monsterCurrPos = monster.getHitbox().getHitboxPosition();
                Position aux= monster.getPosition();
                Position monsterNewPos = monster.getMoveStrategy().getNextPosition(time, aux, getModel());

                if (!monsterNewPos.equals(monsterCurrPos)) {
                    if (!checkCollision(monsterNewPos, monster)) {
                        monster.getPosition().setPosition(monsterNewPos);
                    }
                }
            }

            if (monster.getCharacterAction() instanceof Attack) {
                monster.getAttack().step(getModel());
            }
            else if (monster.getAttackStrategy().canAttack(time, getModel().getLink())) {
                turnToLink(monster, getModel().getLink());
                monster.getMover().attack();
            }
        }
    }

    public boolean checkCollision(Position position, Monster monster) {
        int x,y;
        List<Position> hitBox= getNewHitBox(position, monster);
        for(Position pos: hitBox){
            x = pos.getX()/32;
            y = pos.getY()/32;
            if(getModel().getCollision().get(y).get(x)==0 || getModel().getCollision().get(y).get(x)==2) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Position> getNewHitBox(Position position, Monster monster){
        position=position.translate(monster.getHitbox().getTranslation());
        ArrayList<Position> hitbox = new ArrayList<>();
        hitbox.add(new Position(position.getX(),position.getY()+monster.getHeight()));
        hitbox.add(new Position(position.getX(),position.getY()+ monster.getHeight()/2));
        hitbox.add(new Position(position.getX()+monster.getWidth(),position.getY()+ monster.getHeight()/2));
        hitbox.add(new Position(position.getX()+ monster.getWidth(),position.getY()+ monster.getHeight()));
        return hitbox;
    }

    public void turnToLink(Monster monster, Link link) {
        if (Math.abs(monster.getCenterPoint().getX() - link.getCenterPoint().getX()) > Math.abs(monster.getCenterPoint().getY() - link.getCenterPoint().getY())) {
            if (monster.getCenterPoint().getX() < link.getCenterPoint().getX())
                monster.getMover().MovingRight();
            else
                monster.getMover().MovingLeft();
        }
        else {
            if (monster.getCenterPoint().getY() < link.getCenterPoint().getY())
                monster.getMover().MovingDown();
            else
                monster.getMover().MovingUp();
        }
    }
}
