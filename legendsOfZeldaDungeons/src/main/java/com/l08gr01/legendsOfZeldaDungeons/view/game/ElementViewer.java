package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Element;

import java.io.IOException;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui) throws IOException;


}
