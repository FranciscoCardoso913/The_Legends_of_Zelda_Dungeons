package com.l08gr01.legendsOfZeldaDungeons.controller.game;

import com.l08gr01.legendsOfZeldaDungeons.controller.Controller;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
