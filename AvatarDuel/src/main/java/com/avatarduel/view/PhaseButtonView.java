package com.avatarduel.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PhaseButtonView extends HBox{
    private Button delete;
    private Button main1;
    private Button battle;
    private Button main2;
    private Button end;


    public PhaseButtonView() {
        super();
        delete = new Button("Delete");
        main1 = new Button("Main 1");
        main2 = new Button("Main 2");
        battle = new Button("Battle");
        end = new Button("End");
        setMinWidth(900);
        resetPhaseButtons();
        setAlignment(Pos.CENTER);
        getChildren().add(delete);
        getChildren().add(main1);
        getChildren().add(battle);
        getChildren().add(main2);
        getChildren().add(end);
    }

    public void resetPhaseButtons() {
        delete.setDisable(true);
        main1.setDisable(false);
        battle.setDisable(true);
        main2.setDisable(true);
        end.setDisable(false);
    }

    public Button getDelete() { return delete; }
    public Button getMain1() { return main1; }
    public Button getMain2() { return main2; }
    public Button getBattle() { return battle; }
    public Button getEnd() { return end; }
}
