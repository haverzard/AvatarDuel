package com.avatarduel.controller;

import com.avatarduel.model.Loc;
import com.avatarduel.model.Player;
import com.avatarduel.view.SideView;

public class SideController {
    private SideView topSideV;
    private SideView bottomSideV;

    public SideController() {
        this(new ButtonController());
    }
    
    public SideController(ButtonController buttonController) {
        Player.getPlayers();
        topSideV = new SideView(Player.player2, Loc.TOP, buttonController);
        bottomSideV = new SideView(Player.player1, Loc.BOTTOM, buttonController);
    }

    public SideView getTopSideV() {
        return topSideV;
    }

    public SideView getBottomSideV() {
        return bottomSideV;
    }

    public SideView getSideV(Loc type) {
        if (type == Loc.TOP) return topSideV;
        else return bottomSideV;
    }
}
