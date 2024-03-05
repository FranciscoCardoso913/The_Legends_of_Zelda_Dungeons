package com.l08gr01.legendsOfZeldaDungeons.view;

import com.l08gr01.legendsOfZeldaDungeons.Game;
import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw( Game game, GUI gui) throws IOException {
        drawElements(game,gui);
    }

    protected abstract void drawElements(Game game,GUI gui) throws IOException;
}

