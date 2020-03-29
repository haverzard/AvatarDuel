package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.card.CharacterCard;
import com.avatarduel.components.*;
import com.avatarduel.deck.Deck;
import com.avatarduel.deck.StorageDeck;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import com.avatarduel.element.Element;
import com.avatarduel.model.Land;
import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static Deck charDeck = new StorageDeck(100);
  public static StackPane screen = new StackPane();

  public void loadCards() throws IOException, URISyntaxException {
    File charCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader charReader = new CSVReader(charCSVFile, "\t");
    charReader.setSkipHeader(true);
    List<String[]> charRows = charReader.read();
    for (String[] row : charRows) {
      CharacterCard l = new CharacterCard(row[1], row[3], Element.valueOf(row[2]), row[4],
              Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]));
      charDeck.add(l);
    }
  }

  @Override
  public void start(Stage stage) {
    HBox store = new HBox();
    store.setMinHeight(440);
    store.setAlignment(Pos.CENTER);
    store.getChildren().add(Card.getOpenCard(250, false));

    BorderPane cardLayout2 = new BorderPane();
    cardLayout2.setMinWidth(250);
    cardLayout2.setMaxHeight(400);
    cardLayout2.setBorder(Basic.getBorder(1));

    HBox store2 = new HBox();
    store2.setMinHeight(440);
    store2.setAlignment(Pos.CENTER);
    store2.getChildren().add(cardLayout2);

    // Left Layout
    BorderPane sidebar = new BorderPane();
    sidebar.setMinWidth(290);
    sidebar.setMinHeight(880);
    sidebar.setTop(store);
    sidebar.setBottom(store2);

    // Right Layout
    BorderPane plane = new BorderPane();
    plane.setMinWidth(900);
    plane.setMaxHeight(840);
    plane.setTop(GameSpecific.genPlane("top"));
    plane.setCenter(GameSpecific.genButton());
    plane.setBottom(GameSpecific.genPlane("bottom"));

    HBox planeBox = new HBox();
    planeBox.setMinWidth(1000);
    planeBox.setAlignment(Pos.CENTER);
    planeBox.getChildren().add(plane);

    // Main Layout
    BorderPane mainLayout = new BorderPane();
    mainLayout.setLeft(sidebar);
    mainLayout.setCenter(planeBox);
    screen.getChildren().add(mainLayout);
    Scene scene = new Scene(screen, 1380, 880);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
    } catch (Exception e) {
    }
  }

  public static void main(String[] args) {
    launch();
  }
}