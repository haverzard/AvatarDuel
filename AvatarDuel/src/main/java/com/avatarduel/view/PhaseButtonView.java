package com.avatarduel.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PhaseButtonView extends HBox{
    private Button delete;
    private Button main;
    private Button battle;
    private Button end;


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

    public void resetPhaseButtons() {
        delete.setDisable(true);
        main.setDisable(false);
        battle.setDisable(true);
        end.setDisable(false);
    }

    public Button getDelete() { return delete; }
    public Button getMain() { return main; }
    public Button getBattle() { return battle; }
    public Button getEnd() { return end; }
}
