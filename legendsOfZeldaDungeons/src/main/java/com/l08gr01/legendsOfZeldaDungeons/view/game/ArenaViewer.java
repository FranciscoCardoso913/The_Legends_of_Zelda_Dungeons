package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.gui.Image;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import java.io.IOException;

public class ArenaViewer {

    public void draw(Game game, GUI gui, Arena arena) throws IOException {

        Position initialPosition= initialPosition(arena);
        Image image;
        TerminalPosition terminalPosition;
        int cameraX= arena.getCamera().getCameraPositon().getX();
        int cameraY=arena.getCamera().getCameraPositon().getY();
        int converter= arena.getSmallerSpritesSize();
        int x=initialPosition.getX(),y=initialPosition.getY();
        for(int yy=y;yy<cameraY+arena.getCamera().getTerminalHeight();yy+= converter){
            for(int xx=x; xx<cameraX+arena.getCamera().getTerminalWidth();xx+= converter){
                image=arena.getMap().get(yy/ converter).get(xx/ converter);
                terminalPosition=new TerminalPosition(xx-cameraX,yy-cameraY );
                gui.draw(terminalPosition,image);
            }
        }
    }
    Position initialPosition(Arena arena){
      int x,y;
      x=arena.getCamera().getCameraPositon().getX()/ arena.getSmallerSpritesSize();
      y=arena.getCamera().getCameraPositon().getY()/ arena.getSmallerSpritesSize();
      return new Position(x*32,y*32);

    }



}
