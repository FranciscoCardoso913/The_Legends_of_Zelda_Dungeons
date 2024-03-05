package com.l08gr01.legendsOfZeldaDungeons.sprites;

import com.l08gr01.legendsOfZeldaDungeons.gui.Image;

import java.util.ArrayList;

public class LinkSprites implements CharacterSprites {


    ArrayList<Image> attackLeft=new ArrayList<>();
    ArrayList<Image> attackRight=new ArrayList<>();
    ArrayList<Image> attackUp=new ArrayList<>();
    ArrayList<Image> attackDown=new ArrayList<>();

    ArrayList<Image> bowLeft=new ArrayList<>();
    ArrayList<Image> bowRight=new ArrayList<>();
    ArrayList<Image> bowUp=new ArrayList<>();
    ArrayList<Image> bowDown=new ArrayList<>();

    ArrayList<Image> shieldLeft=new ArrayList<>();
    ArrayList<Image> shieldRight=new ArrayList<>();
    ArrayList<Image> shieldUp=new ArrayList<>();
    ArrayList<Image> shieldDown=new ArrayList<>();

    public LinkSprites(){
        createSprites("attack/left/",5,attackLeft);
        createSprites("attack/right/",5,attackRight);
        createSprites("attack/down/",5,attackDown);
        createSprites("attack/up/",5,attackUp);

        createSprites("bow/down/",3,bowDown);
        createSprites("bow/up/",3,bowUp);
        createSprites("bow/left/",3,bowLeft);
        createSprites("bow/right/",3,bowRight);

        createSprites("movement/up/",6,movingUp);
        createSprites("movement/down/",6,movingDown);
        createSprites("movement/left/",6,movingLeft);
        createSprites("movement/right/",6,movingRight);

        shieldDown.add(new Image("/images/link_sprites/movement/down/6.png"));
        shieldLeft.add(new Image("/images/link_sprites/movement/left/2.png"));
        shieldRight.add(new Image("/images/link_sprites/movement/right/2.png"));
        shieldUp.add(new Image("/images/link_sprites/movement/up/4.png"));

    }

    public ArrayList<Image> getMovingDown() {
        return movingDown;
    }

    public ArrayList<Image> getMovingLeft() {
        return movingLeft;
    }

    public ArrayList<Image> getMovingRight() {
        return movingRight;
    }

    public ArrayList<Image> getMovingUp() {
        return movingUp;
    }



    public ArrayList<ArrayList<Image>> getShieldSprites(){
        ArrayList<ArrayList<Image>> sprites= new ArrayList<>();
        sprites.add(shieldUp);
        sprites.add(shieldDown);
        sprites.add(shieldLeft);
        sprites.add(shieldRight);
        return sprites;
    }
    public ArrayList<ArrayList<Image>> getAttackSprites(){
        ArrayList<ArrayList<Image>> sprites= new ArrayList<>();
        sprites.add(attackUp);
        sprites.add(attackDown);
        sprites.add(attackLeft);
        sprites.add(attackRight);
        return sprites;
    }
    public ArrayList<ArrayList<Image>> getBowSprites(){
        ArrayList<ArrayList<Image>> sprites= new ArrayList<>();
        sprites.add(bowUp);
        sprites.add(bowDown);
        sprites.add(bowLeft);
        sprites.add(bowRight);
        return sprites;
    }

    private void createSprites(String path, int quantity, ArrayList<Image> sprites){
        for(int i=1; i<=quantity;i++){
            sprites.add(new Image("/images/link_sprites/"+path+i+".png"));
        }
    }
}
