package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.attack.Attack;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Actions.move.Move;
import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class LinkController extends GameController{

    public LinkController(Arena arena) {
        super(arena);
    }

    public void moveLinkLeft() throws IOException {
        for(int i=0; i<getModel().getLink().getVelocity();i++)
            moveLink(getModel().getLink().getHitbox().getHitboxPosition().getLeft(),getModel().getLink().getPosition().getLeft());
        getModel().getLink().getMover().MovingLeft();
    }

    public void moveLinkRight() throws IOException {
        for(int i=0; i<getModel().getLink().getVelocity();i++)
            moveLink(getModel().getLink().getHitbox().getHitboxPosition().getRight(),getModel().getLink().getPosition().getRight());
        getModel().getLink().getMover().MovingRight();
    }

    public void moveLinkUp() throws IOException {
        for(int i=0; i<getModel().getLink().getVelocity();i++)
            moveLink(getModel().getLink().getHitbox().getHitboxPosition().getUp(),getModel().getLink().getPosition().getUp());
        getModel().getLink().getMover().MovingUp();
    }

    public void moveLinkDown() throws IOException {
        for(int i=0; i<getModel().getLink().getVelocity();i++)
            moveLink(getModel().getLink().getHitbox().getHitboxPosition().getDown(),getModel().getLink().getPosition().getDown());
        getModel().getLink().getMover().MovingDown();
    }


    private void moveLink(Position hitBoxPosition,Position imagePosition) throws IOException {
        if (!checkCollision(hitBoxPosition) ) {
            getModel().getLink().getPosition().setPosition(imagePosition);
            getModel().getCamera().update();

        }
    }

    public void moving(GUI.ACTION action) throws IOException {
        if (action == GUI.ACTION.UP) moveLinkUp();
        if (action == GUI.ACTION.RIGHT) moveLinkRight();
        if (action == GUI.ACTION.DOWN) moveLinkDown();
        if (action == GUI.ACTION.LEFT) moveLinkLeft();
        if(action==GUI.ACTION.ATTACK) useSword();
        if(action == GUI.ACTION.ARROW) useBow();
        if(action==GUI.ACTION.DEFEND) useShield();
        if(action== GUI.ACTION.NONE) getModel().getLink().getMover().setDefault();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

        if (getModel().getLink().isInvincible()) {
            checkInvincibilityTime();
        }
        if(getModel().getLink().getCharacterAction() instanceof Move){
            moving(action);
        }
        else if(getModel().getLink().getCharacterAction() instanceof Attack ){
            attack();

        }
        checkMonsterCollision();

    }

    public void checkInvincibilityTime() {
        if (System.currentTimeMillis() - getModel().getLink().getGotInvincible() > 1000) {
            getModel().getLink().setInvincible(false);
        }
    }

    public void checkMonsterCollision() {
        for (Monster monster : getModel().getMonsters()) {
            if (getModel().getLink().getHitbox().collidesWith(monster.getHitbox())){
                getModel().getLink().decreaseHealth(1);
            }
        }
    }

    public boolean checkCollision(Position position) throws IOException {
        int x,y;
        List<Position> hitBox= getNewHitBox(position);
        for(Position pos: hitBox){
            x=pos.getX()/getModel().getSmallerSpritesSize();
            y= pos.getY()/getModel().getSmallerSpritesSize();
            if(getModel().getCollision().get(y).get(x)==0) {
                return true;
            }
            else if(getModel().getCollision().get(y).get(x)==2){

                getModel().loadNextMap();
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
    public void useBow(){
        getModel().getLink().bow();
        getModel().getLink().getMover().attack();
    }
    public void useSword(){
        getModel().getLink().attack();
        getModel().getLink().getMover().attack();
    }
    public void useShield(){
        getModel().getLink().shield();
        getModel().getLink().getMover().attack();
    }
    public void attack(){
        getModel().getLink().getAttack().step(getModel());
    }

}
