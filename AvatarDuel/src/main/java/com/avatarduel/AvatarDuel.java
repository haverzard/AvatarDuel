package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.avatarduel.card.*;
import com.avatarduel.components.*;
import com.avatarduel.deck.StorageDeck;
import com.avatarduel.player.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static final String SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static StorageDeck characterDeck = new StorageDeck(new ArrayList<GameCard>(), 100);
  private static StorageDeck skillDeck = new StorageDeck(new ArrayList<GameCard>(), 100);
  private static StorageDeck landDeck = new StorageDeck(new ArrayList<GameCard>(),100);
  public static StackPane screen = new StackPane();

  public void loadCards(String path, StorageDeck deck, GameCardFactory cardFactory) throws IOException, URISyntaxException {
    File CSVFile = new File(getClass().getResource(path).toURI());
    CSVReader reader = new CSVReader(CSVFile, "\t");
    reader.setSkipHeader(true);
    List<String[]> rows = reader.read();
    for (String[] row : rows) {
      GameCard l = cardFactory.getCard(row);
      deck.addDynamically(l);
    }
  }

  public void loadDeck(Player x) {
    Random rand = new Random();
    for (int i = 0; i < 20; i++) x.addToDeck(characterDeck.access(rand.nextInt(characterDeck.getSize())).clone());
    for (int i = 0; i < 20; i++) x.addToDeck(skillDeck.access(rand.nextInt(skillDeck.getSize())).clone());
    for (int i = 0; i < 20; i++) x.addToDeck(landDeck.access(rand.nextInt(landDeck.getSize())).clone());
    x.shuffleDeck();
  }

  @Override
  public void start(Stage stage) {
    Player.getPlayers();

    HBox store = new HBox();
    store.setMinHeight(440);
    store.setAlignment(Pos.CENTER);
    GameSpecific.cardInfo = Card.getOpenCard(250);
    store.getChildren().add(GameSpecific.cardInfo);

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
    Scene scene = new Scene(screen, 1440, 940);

    // Init some additional functionality
    GameSpecific.initFieldBoxes();

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    try {
      // Load all cards
      this.loadCards(CHARACTER_CSV_FILE_PATH, characterDeck, new CharacterGameCardFactory());
      this.loadCards(SKILL_CSV_FILE_PATH, skillDeck, new AuraSkillGameCardFactory());
      this.loadCards(LAND_CSV_FILE_PATH, landDeck, new LandGameCardFactory());
      this.loadDeck(Player.player1);
      this.loadDeck(Player.player2);
    } catch (Exception e) {
      System.out.println("Something went wrong");
    }
  }

  public static void main(String[] args) {
    launch();
  }
}