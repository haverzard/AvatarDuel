package com.avatarduel.controller;

import com.avatarduel.model.Loc;
import com.avatarduel.model.Player;
import com.avatarduel.view.SideView;

/**
 * Represent the Side Controller for MVC pattern in AvatarDuel
 * Control bottom side for in turn player
 */
public class SideController {
    private SideView topSideV;
    private SideView bottomSideV;

    /**
     * Create new side controller
     */
    public SideController() {
        this(new ButtonController());
    }
    
    /**
     * Create new side controller with user defined
     * @param buttonController new button controller
     */
    public SideController(ButtonController buttonController) {
        Player.getPlayers();
        topSideV = new SideView(Player.player2, Loc.TOP, buttonController);
        bottomSideV = new SideView(Player.player1, Loc.BOTTOM, buttonController);
    }

    /**
     * Get top side view
     * @return top side view
     */
    public SideView getTopSideV() {
        return topSideV;
    }

    /**
     * Get bottom side view
     * @return bottom side view
     */
    public SideView getBottomSideV() {
        return bottomSideV;
    }

    /**
     * Get side view
     * @param type side location
     * @return side view from location
     */
    public SideView getSideV(Loc type) {
        if (type == Loc.TOP) return topSideV;
        else return bottomSideV;
    }
}
