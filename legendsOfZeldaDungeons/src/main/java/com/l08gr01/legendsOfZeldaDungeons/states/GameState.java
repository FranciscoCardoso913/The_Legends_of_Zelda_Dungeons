package com.l08gr01.legendsOfZeldaDungeons.states;

import com.l08gr01.legendsOfZeldaDungeons.controller.Controller;
import com.l08gr01.legendsOfZeldaDungeons.controller.game.ArenaController;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;
import com.l08gr01.legendsOfZeldaDungeons.view.Viewer;
import com.l08gr01.legendsOfZeldaDungeons.view.game.GameViewer;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }
}
