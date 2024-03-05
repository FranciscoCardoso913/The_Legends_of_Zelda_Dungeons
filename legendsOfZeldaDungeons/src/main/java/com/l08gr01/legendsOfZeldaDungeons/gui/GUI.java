package com.l08gr01.legendsOfZeldaDungeons.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.l08gr01.legendsOfZeldaDungeons.model.Position;

import java.awt.*;
import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void draw(TerminalPosition position,Image image) throws IOException;

    void drawRetangle(Position position, int width, int height, Color color);
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT,ATTACK,DEFEND,ARROW}
}
