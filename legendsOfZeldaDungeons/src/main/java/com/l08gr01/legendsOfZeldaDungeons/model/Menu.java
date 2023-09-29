package com.l08gr01.legendsOfZeldaDungeons.model;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<String> entries;
    private int currEntry = 0;

    public Menu() {
        this.entries = Arrays.asList("/images/menu/start_selected.png", "/images/menu/exit_selected.png");
    }

    public List<String> getEntries(){
        return entries;
    }

    public int getCurrEntry(){
        return currEntry;
    }

    public void previousEntry(){
        if(entries.size() > 1) {
            if (currEntry == 0) {
                currEntry = entries.size() - 1;

            } else {
                currEntry--;
            }
        }
    }

    public void nextEntry(){
        if(entries.size() > 1) {
            if (currEntry == (entries.size()-1)) {
                currEntry = 0;
            } else {
                currEntry++;
            }
        }
    }
}