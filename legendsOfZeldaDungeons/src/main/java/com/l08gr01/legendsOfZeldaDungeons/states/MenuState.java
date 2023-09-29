package com.l08gr01.legendsOfZeldaDungeons.states;

import com.l08gr01.legendsOfZeldaDungeons.controller.Controller;
import com.l08gr01.legendsOfZeldaDungeons.controller.menu.MenuController;
import com.l08gr01.legendsOfZeldaDungeons.model.Menu;
import com.l08gr01.legendsOfZeldaDungeons.view.MenuViewer;
import com.l08gr01.legendsOfZeldaDungeons.view.Viewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel()); // Why does it need to be cast?
    }
}
