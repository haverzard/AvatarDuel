package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.avatarduel.card.*;
import com.avatarduel.cardfactory.AuraSkillGameCardFactory;
import com.avatarduel.cardfactory.CharacterGameCardFactory;
import com.avatarduel.cardfactory.GameCardFactory;
import com.avatarduel.cardfactory.LandGameCardFactory;
import com.avatarduel.components.*;
import com.avatarduel.deck.StorageDeck;
import com.avatarduel.element.Element;
import com.avatarduel.player.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static final String SKILL_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static StorageDeck characterDeck = new StorageDeck(new ArrayList<GameCard>(), 100);
  private static StorageDeck skillDeck = new StorageDeck(new ArrayList<GameCard>(), 100);
  private static StorageDeck landDeck = new StorageDeck(new ArrayList<GameCard>(),100);

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
    for (int i = 0; i < 15; i++) x.addToDeck(characterDeck.access(rand.nextInt(characterDeck.getSize())).clone());
    for (int i = 0; i < 15; i++) x.addToDeck(skillDeck.access(rand.nextInt(skillDeck.getSize())).clone());
    for (int i = 0; i < 30; i++) x.addToDeck(landDeck.access(rand.nextInt(landDeck.getSize())).clone());
    x.shuffleDeck();
  }

  public void initDraw() {
    for (int i = 0; i < 7; i++) {
      Player.player1.takeCard();
      Player.player2.takeCard();
    }
  }

  @Override
  public void start(Stage stage) {


    Player.getPlayers();
    Player.player2.setHealth(65);
    try {
      // Load all cards
      this.loadCards(CHARACTER_CSV_FILE_PATH, characterDeck, new CharacterGameCardFactory());
      this.loadCards(SKILL_CSV_FILE_PATH, skillDeck, new AuraSkillGameCardFactory());
      this.loadCards(LAND_CSV_FILE_PATH, landDeck, new LandGameCardFactory());
      this.loadDeck(Player.player1);
      this.loadDeck(Player.player2);
      initDraw();
      GameSpecific.updateHand(Player.player2,Player.player1);
    } catch (Exception e) {
      System.out.println("Something went wrong");
    }

    // Cheats
    for (int i=0; i<100; i++) {
      Player.player1.addPower(Element.AIR);
      Player.player1.addPower(Element.FIRE);
      Player.player1.addPower(Element.WATER);
      Player.player1.addPower(Element.EARTH);
      Player.player2.addPower(Element.AIR);
      Player.player2.addPower(Element.FIRE);
      Player.player2.addPower(Element.WATER);
      Player.player2.addPower(Element.EARTH);
    }

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
    GameSpecific.screen.getChildren().add(mainLayout);

    // Game Setting Screen

    // Main Screen
    StackPane mainScr = new StackPane();
    ImageView background = new ImageView(new Image("com/avatarduel/card/image/mainScreen.jpg"));
    background.setFitWidth(1440);
    background.setFitHeight(940);
    VBox screen = new VBox();
    screen.setMinSize(1440,940);
    screen.setAlignment(Pos.CENTER);
    ImageView logo = new ImageView(new Image("com/avatarduel/card/image/avatarLogo.png"));
    logo.setFitWidth(720);
    logo.setFitHeight(480);

    HBox names = new HBox();
    names.setMinWidth(1440);
    names.setAlignment(Pos.CENTER);
    VBox nameBox1 = new VBox();
    VBox nameBox2 = new VBox();
    TextArea name1 = new TextArea("Beza");
    TextArea name2 = new TextArea("Lel");
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
    names.getChildren().add(Basic.getSpace(50));
    names.getChildren().add(nameBox2);

    Button play = new Button("Let's play!");
    play.setMinSize(200,50);
    play.setOnAction(e-> {
      GameSpecific.screen.getChildren().remove(mainScr);
      GameSpecific.playerTopName.setText("Player 2 - " + name2.getText());
      GameSpecific.playerBottomName.setText("Player 1 - " + name1.getText());
    });
    screen.getChildren().add(logo);
    screen.getChildren().add(names);
    screen.getChildren().add(Basic.getSpace(50));
    screen.getChildren().add(play);
    mainScr.getChildren().add(background);
    mainScr.getChildren().add(screen);
    GameSpecific.screen.getChildren().add(mainScr);

    Scene scene = new Scene(GameSpecific.screen, 1440, 940);

    // Init some additional functionality
    GameSpecific.initFieldBoxes();
    GameSpecific.initDeckButton();
    GameSpecific.updatePowerCounters(GameSpecific.powerCountersBottom, Player.player1);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}