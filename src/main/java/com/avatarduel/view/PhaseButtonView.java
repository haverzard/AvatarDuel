package com.avatarduel.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * PhaseButtonView describes the buttons for changing the turn phase
 * which includes Draw phase, Main phase, Battle phase, and End phase.
 * 
 * @author Kelompok 2
 */
public class PhaseButtonView extends HBox{
    private Button delete;
    private Button main;
    private Button battle;
    private Button end;

    /**
     * Create the phase buttons on the GUI.
     */
    public PhaseButtonView() {
        super();
        delete = new Button("Delete");
        main = new Button("Main");
        battle = new Button("Battle");
        end = new Button("End");
        setMinWidth(900);
        resetPhaseButtons();
        setAlignment(Pos.CENTER);
        getChildren().add(delete);
        getChildren().add(main);
        getChildren().add(battle);
        getChildren().add(end);
    }

    /**
     * Resets the phase buttons to initial state.
     */
    public void resetPhaseButtons() {
        delete.setDisable(true);
        main.setDisable(false);
        battle.setDisable(true);
        end.setDisable(false);
    }

    /**
     * Gets the delete button.
     * @return The delete button.
     */
    public Button getDelete() { return delete; }

    /**
     * Gets the Main phase button.
     * @return The Main phase button.
     */
    public Button getMain() { return main; }

    /**
     * Gets the Battle phase button.
     * @return The Battle phase button.
     */
    public Button getBattle() { return battle; }

    /**
     * Gets the End phase button.
     * @return The End phase button.
     */
    public Button getEnd() { return end; }
}
