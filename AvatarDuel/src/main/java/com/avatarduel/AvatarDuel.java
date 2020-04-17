package com.avatarduel;

import com.avatarduel.card.GameCard;
import com.avatarduel.cardfactory.*;
import com.avatarduel.view.MainView;
import com.avatarduel.view.GameView;
import com.avatarduel.deck.StorageDeck;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.util.CSVReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvatarDuel extends Application {
  private static AvatarDuel instance = null;
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static final String SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String DESTROY_CSV_FILE_PATH = "card/data/skill_destroy.csv";
  private static final String POWER_UP_CSV_FILE_PATH = "card/data/skill_powerup.csv";
  private static StorageDeck characterDeck = new StorageDeck(new ArrayList<>(), 100);
  private static StorageDeck skillDeck = new StorageDeck(new ArrayList<>(), 100);
  private static StorageDeck landDeck = new StorageDeck(new ArrayList<>(),100);
  private static StorageDeck destroyDeck = new StorageDeck(new ArrayList<>(),100);
  private static StorageDeck powerUpDeck = new StorageDeck(new ArrayList<>(),100);

  public static AvatarDuel getInstance() { return instance; }

  public void loadCards(String path, StorageDeck deck, GameCardFactory cardFactory) throws IOException, URISyntaxException {
    File CSVFile = new File(getClass().getResource(path).toURI());
    CSVReader reader = new CSVReader(CSVFile, ",");
    reader.setSkipHeader(true);
    List<String[]> rows = reader.read();
    for (String[] row : rows) {
      GameCard l = cardFactory.getCard(row);
      deck.addDynamically(l);
    }
  }

  public void loadDeck(Player x) {
    Random rand = new Random();
    for (int i = 0; i < 24; i++) x.addToDeck(characterDeck.access(rand.nextInt(characterDeck.getSize())).clone());
    for (int i = 0; i < 8; i++) x.addToDeck(skillDeck.access(rand.nextInt(skillDeck.getSize())).clone());
    for (int i = 0; i < 8; i++) x.addToDeck(powerUpDeck.access(rand.nextInt(powerUpDeck.getSize())).clone());
    for (int i = 0; i < 8; i++) x.addToDeck(destroyDeck.access(rand.nextInt(destroyDeck.getSize())).clone());
    for (int i = 0; i < 12; i++) x.addToDeck(landDeck.access(rand.nextInt(landDeck.getSize())).clone());
    x.shuffleDeck();
  }

  public void initDraw() {
    for (int i = 0; i < 6; i++) {
      Player.player1.takeCard();
      Player.player2.takeCard();
    }
  }

  @Override
  public void start(Stage stage) {
    // Initial
    instance = this;

    MainView.screen.getChildren().add(new MainView(this));
    Scene scene = new Scene(MainView.screen, 1440, 940);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

  public void initGame() {
    try {
      // Load all cards
      this.loadCards(CHARACTER_CSV_FILE_PATH, characterDeck, new CharacterGameCardFactory());
      this.loadCards(SKILL_CSV_FILE_PATH, skillDeck, new AuraSkillGameCardFactory());
      this.loadCards(LAND_CSV_FILE_PATH, landDeck, new LandGameCardFactory());
      this.loadCards(DESTROY_CSV_FILE_PATH, destroyDeck, new DestroySkillGameCardFactory());
      this.loadCards(POWER_UP_CSV_FILE_PATH, powerUpDeck, new PowerUpSkillGameCardFactory());
      this.loadDeck(Player.player1);
      this.loadDeck(Player.player2);
      initDraw();

      MainView.screen.getChildren().add(new GameView());

      // Cheats, please delete later
      // for (int i=0; i<100; i++) {
      //   Player.player1.addPower(Element.AIR);
      //   Player.player1.addPower(Element.FIRE);
      //   Player.player1.addPower(Element.WATER);
      //   Player.player1.addPower(Element.EARTH);
      //   Player.player1.addPower(Element.ENERGY);
      //   Player.player2.addPower(Element.AIR);
      //   Player.player2.addPower(Element.FIRE);
      //   Player.player2.addPower(Element.WATER);
      //   Player.player2.addPower(Element.EARTH);
      //   Player.player2.addPower(Element.ENERGY);
      // }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}