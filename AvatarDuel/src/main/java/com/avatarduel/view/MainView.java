package com.avatarduel.view;

import com.avatarduel.AvatarDuel;
import com.avatarduel.components.Basic;
import com.avatarduel.components.Space;
import com.avatarduel.model.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * MainView describes the main screen view that extend StackPane class.
 * 
 * @author Kelompok 2
 */
public class MainView extends StackPane {
    private static MainView instance = null;
    public static StackPane screen = new StackPane();
    private static final String IMAGE_LOGO = "com/avatarduel/assets/image/avatarLogo.png";
    private static final String MAIN_BACKGROUND = "com/avatarduel/assets/image/mainScreen.jpg";

    /**
     * Create the main menu screen GUI.
     * 
     * @param main The main program object class.
     */
    public MainView(AvatarDuel main) {
        // Must reset players first to set name
        Player.resetPlayers();

        // Main Screen
        ImageView background = new ImageView(new Image(MAIN_BACKGROUND));
        background.setFitWidth(1440);
        background.setFitHeight(940);
        VBox scr = new VBox();
        scr.setMinSize(1440,940);
        scr.setAlignment(Pos.CENTER);
        ImageView logo = new ImageView(new Image(IMAGE_LOGO));
        logo.setFitWidth(720);
        logo.setFitHeight(480);

        HBox names = new HBox();
        names.setMinWidth(1440);
        names.setAlignment(Pos.CENTER);
        VBox nameBox1 = new VBox();
        VBox nameBox2 = new VBox();
        TextArea name1 = new TextArea("Kelompok");
        TextArea name2 = new TextArea("Dua");
        Text nameLabel1 = new Text("Player 1 Name:");
        Text nameLabel2 = new Text("Player 2 Name:");
        nameLabel1.setFont(new Font(30));
        nameLabel2.setFont(new Font(30));
        nameLabel1.setFill(Color.WHITE);
        nameLabel2.setFill(Color.WHITE);
        nameLabel1.setEffect(Basic.getShadow(Color.BLACK, 10));
        nameLabel2.setEffect(Basic.getShadow(Color.BLACK, 10));
        name1.setMaxSize(200,30);
        name2.setMaxSize(200,30);
        nameBox1.getChildren().add(nameLabel1);
        nameBox1.getChildren().add(name1);
        nameBox2.getChildren().add(nameLabel2);
        nameBox2.getChildren().add(name2);
        names.getChildren().add(nameBox1);
        names.getChildren().add(new Space(50));
        names.getChildren().add(nameBox2);

        Button play = new Button("Let's play!");
        play.setMinSize(200,50);
        play.setOnAction(e-> {
            screen.getChildren().remove(this);
            Player.player1.setName(name1.getText());
            Player.player2.setName(name2.getText());
            main.initGame();
        });
        scr.getChildren().add(logo);
        scr.getChildren().add(names);
        scr.getChildren().add(new Space(50));
        scr.getChildren().add(play);
        getChildren().add(background);
        getChildren().add(scr);
    }

    /**
     * Gets the instance.
     * @return The instance.
     */
    public static MainView getInstance() {
        if (instance == null) instance = new MainView(AvatarDuel.getInstance());
        return instance;
    }

    /**
     * Load the match result screen.
     * @param winner The Player who wins the match.
     */
    public void loadLoseScreen(Player winner) {
        MainView.screen.getChildren().add(Basic.getScreen("The winner is "+winner.getName()+"! Congratz!"));
        Player.resetPlayers();
        Basic.scr.setOnMouseClicked(e -> {
            MainView.screen.getChildren().clear();
            MainView.screen.getChildren().add(getInstance());
        });
    }
}
