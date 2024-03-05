package com.l08gr01.legendsOfZeldaDungeons.view.game;

import com.l08gr01.legendsOfZeldaDungeons.gui.GUI;
import com.l08gr01.legendsOfZeldaDungeons.model.game.Arena.Arena;

import com.l08gr01.legendsOfZeldaDungeons.model.game.monster.Monster;
import com.l08gr01.legendsOfZeldaDungeons.view.game.HUD.LinkHealthHud;
import com.l08gr01.legendsOfZeldaDungeons.view.game.HUD.MonsterHud;
import com.l08gr01.legendsOfZeldaDungeons.view.game.HUD.ScoreHud;

import java.io.IOException;

public class HudViewer {
    LinkHealthHud linkHealthHud;
    MonsterHud monsterHud;
    ScoreHud scoreHud;
    public HudViewer(){
         linkHealthHud= new LinkHealthHud(10);
         monsterHud= new MonsterHud(20);
         scoreHud= new ScoreHud();

    }
    public void upDateAll(Arena arena,GUI gui) throws IOException {
        linkHealthHud.update(arena.getLink(),gui,arena.getCamera());
        for(Monster monster: arena.getMonsters()) monsterHud.update(monster,gui,arena.getCamera());
        scoreHud.update(arena.getScore(), gui);
    }
}
