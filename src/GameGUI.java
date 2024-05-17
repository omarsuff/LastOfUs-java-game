import java.awt.Point;
import java.io.IOException;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class GameGUI extends Application {
	private Stage primaryStage;
	private Scene mainMenuScene;
	private Scene howToPlayScene;
	private Scene chooseAHeroScene;
	private Label popupLabel;
	private Scene StartingGameScene;
	private Scene GameScene;
	private VBox buttonContainer;
	private static Button heroBox1 = new Button(null);
	private static Button heroBox2 = new Button(null);
	private static Button heroBox3 = new Button(null);
	private static Button heroBox4 = new Button(null);
	private static Button heroBox5 = new Button(null);
	private static Button heroBox6 = new Button(null);
	private static Button heroBox7 = new Button(null);
	private static Button heroBox8 = new Button(null);

	private static GridPane gridPane = new GridPane();
	private static VBox statsBox;
	private final static int GRID_SIZE = 15;
	private final static double CELL_SIZE = 44;
	private static Hero selectedHero;
	private static Scene GameOverScene;
	private static Scene winScene;
	private static Supply supplies = new Supply();
	private static Vaccine vaccines = new Vaccine();
	private static int fps = 0;
	private String message;
	Image icon = new Image("icon.png");
	Image backgroundImage = new Image("MMBackground1.png");
	Image backgroundImageHowtoPlay = new Image("MMBackground.png");
	Image backgroundImageStartGame = new Image("MMBackground.png");
	Image backgroundImageGame = new Image("MMBackground.png");
	Image startB = new Image("Start.png");
	Image quitB = new Image("Quit.png");
	static Image BacktoMain = new Image("Quit.png");
	static Image zombieImage = new Image("Clicker_1.png");
	static Image JoelPX = new Image("Joel.png");
	static Image ElliePX = new Image("Ellie.png");
	static Image TessPX = new Image("Tess.png");
	static Image RileyPX = new Image("Riley.png");
	static Image TommyPX = new Image("Tommy.png");
	static Image BillPX = new Image("Bill.png");
	static Image DavidPX = new Image("David.png");
	static Image HenryPX = new Image("Henry.png");
	static Image VaccineImageView = new Image("Vaccine.png");
	static Image SupplyImageView = new Image("Supplies.png");
	static Image JoelHs = new Image("JoelHS.png");
	static Image EllieHS = new Image("EllieHS.png");
	static Image TessHS = new Image("TessHS.png");
	static Image ReilyHS = new Image("RielyHS.png");
	static Image TommyHS = new Image("TommyHS.png");
	static Image BillHS = new Image("BillHS.png");
	static Image DavidHS = new Image("DavidHS.png");
	static Image HenryHS = new Image("HenryHS.png");

	Game game = new Game();
///////////////////////////////////////////////////////////////////

	@Override
	public void start(Stage primaryStage) throws IOException {

		popupLabel = new Label("This is a popup label");
		popupLabel.setStyle("-fx-background-color: white; -fx-padding: 100px;");
		popupLabel.setVisible(false);

		primaryStage.setTitle("THE LAST OF US");

		primaryStage.getIcons().add(icon);

		// Create UI components
		Label titleLabel = new Label("");
		titleLabel.setAlignment(Pos.TOP_LEFT);
		titleLabel.getStyleClass().add("transparent-button");
		Font font = Font.font("Impact", FontWeight.EXTRA_BOLD, 80);
		titleLabel.setFont(font);

		Font buttonsFont = Font.font("Lucida Sans Unicode", FontWeight.EXTRA_BOLD, 20);
		Font buttonsFont1 = Font.font("Lucida Sans Unicode", FontWeight.EXTRA_BOLD, 20);

		Button startButton = new Button(null);
		startButton.setFont(buttonsFont);
		startButton.setTextAlignment(TextAlignment.CENTER);
		startButton.getStyleClass().add("transparent-button");
		startButton.setPrefWidth(173);
		startButton.setPrefHeight(86);
		ImageView startb = new ImageView(startB);
		startb.setFitWidth(173);
		startb.setFitHeight(86);
		startButton.setGraphic(startb);
		startButton.setTranslateX(44);

		Button quitButton = new Button(null);
		quitButton.setFont(buttonsFont);
		quitButton.setTextAlignment(TextAlignment.CENTER);
		quitButton.getStyleClass().add("transparent-button");
		quitButton.setPrefWidth(215);
		quitButton.setPrefHeight(83);
		ImageView quitb = new ImageView(quitB);
		quitb.setFitWidth(173);
		quitb.setFitHeight(86);
		quitButton.setGraphic(quitb);
		quitButton.setTranslateX(36);
		quitButton.setTranslateY(-15);

		// Set event handlers for the buttons
		startButton.setOnAction(e -> {
			// Add your game logic for starting the game here
			System.out.println("Starting the game");
			primaryStage.setScene(chooseAHeroScene);
			primaryStage.setFullScreen(true);
		});

		quitButton.setOnAction(e -> {
			// Add your game logic for quitting the game here

			System.out.println("Quitting the game");

			primaryStage.close();
		});

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// MAIN MENU

		VBox mainMenuLayout = new VBox(25);
		mainMenuLayout.setAlignment(Pos.BOTTOM_LEFT);

		// Load the background image

		// Create a BackgroundImage with the loaded image
		BackgroundImage backgroundImg5 = new BackgroundImage(backgroundImageGame, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));

		BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
		BackgroundImage backgroundImg1 = new BackgroundImage(backgroundImageHowtoPlay, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
		BackgroundImage backgroundImg2 = new BackgroundImage(backgroundImageStartGame, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));

		// Set the background image to the mainMenuLayout
		mainMenuLayout.setBackground(new Background(backgroundImg));
		mainMenuLayout.getChildren().addAll(titleLabel, startButton, quitButton);

		// Obtain the screen dimensions
		Screen screen = Screen.getPrimary();
		double screenWidth = screen.getBounds().getWidth();
		double screenHeight = screen.getBounds().getHeight();

		// Create a scene with the layout and set it as the primary stage scene
		mainMenuScene = new Scene(mainMenuLayout, screenWidth, screenHeight);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// HOW TO PLAY IN THE MAIN MENU
		VBox howToPlayLayout = new VBox(300);
		howToPlayLayout.setBackground(new Background(backgroundImg1));
		mainMenuScene.getStylesheets().add("style.css");
		howToPlayLayout.setAlignment(Pos.CENTER);
		Label howToPlayLabel = new Label(
				"The player starts off in a 15x15 grid map with just one hero and 10 zombies. The player can\r\n"
						+ "only see the directly adjacent cells next to their pool of heroes. The player then keeps taking\r\n"
						+ "his turn trying to collect vaccines, and cure or kill zombies. The game ends when the player\r\n"
						+ "has collected and used all vaccines or when all heroes have been overwhelmed and defeated by\r\n"
						+ "the zombies.\r\n"
						+ "The player only wins if he has successfully collected and used all vaccines and has 5 or more\r\n"
						+ "heroes alive.");

		howToPlayLabel.getStyleClass().add("label-white");
		howToPlayLabel.setFont(buttonsFont);

		Button backToMainMenuButton = new Button("Back to Main Menu");
		backToMainMenuButton.setFont(buttonsFont);
		backToMainMenuButton.getStyleClass().add("transparent-button");
		backToMainMenuButton.getStyleClass().add("white-border-button");

		backToMainMenuButton.setOnAction(e -> {
			// Switch back to the main menu scene
			primaryStage.setScene(mainMenuScene);
			primaryStage.setFullScreen(true);
		});

		howToPlayLayout.getChildren().addAll(howToPlayLabel, backToMainMenuButton);

		// Create the "How To Play" scene
		howToPlayScene = new Scene(howToPlayLayout, screenWidth, screenHeight);
		howToPlayScene.getStylesheets().add("style.css");
		/////////////////////////////////////////////////////////////////////////////////////////

		// CHOOSING A HERO AFTER STARTING THE GAME
		// CHARACTER SELECT

		VBox ChooseCharater = new VBox();
		Label ChooseAHeroLabel = new Label("CHOOSE A CHARACTER");
		ChooseAHeroLabel.setFont(font);
		ChooseAHeroLabel.getStyleClass().add("label-white");

		Label popupMessage = new Label();
		popupMessage.getStyleClass().add("popup-message");
		popupMessage.setVisible(false);
		popupMessage.setMinWidth(0);
		// CHOOSING A HERO AFTER STARTING THE GAME

		HBox chooseAHeroLayout1 = new HBox(15);
		chooseAHeroLayout1.setAlignment(Pos.CENTER);

		// Joel button

		heroBox1.setPrefWidth(200);
		heroBox1.setPrefHeight(164);
		ImageView JoelImage = new ImageView("JoelHS.png");
		JoelImage.setFitWidth(200);
		JoelImage.setFitHeight(164);
		heroBox1.setGraphic(JoelImage);
		heroBox1.getStyleClass().add("image-button");
		heroBox1.setFont(buttonsFont1);
		heroBox1.setTextAlignment(TextAlignment.CENTER);
		heroBox1.getStyleClass().add("transparent-button");

		// Create a Tooltip for the button
		Tooltip tooltip = new Tooltip("Hero type: Fighter, HP = 140, Action Points = 5, Attack Damage = 30");
		Tooltip.install(heroBox1, tooltip);

		// Ellie button
		Button heroBox2 = new Button(null);
		heroBox2.setPrefWidth(200);
		heroBox2.setPrefHeight(172);
		ImageView EllieImage = new ImageView("EllieHS.png");
		EllieImage.setFitWidth(175);
		EllieImage.setFitHeight(152.25);
		heroBox2.setGraphic(EllieImage);
		heroBox2.setTextAlignment(TextAlignment.CENTER);
		heroBox2.getStyleClass().add("transparent-button");
		heroBox2.setTranslateX(11);
		heroBox2.setTranslateY(11);

		Tooltip tooltip1 = new Tooltip("Hero type : Medic , HP = 110 , Action Points = 6 , Attack Damage = 15");
		Tooltip.install(heroBox2, tooltip1);

		// Tess button
		Button heroBox3 = new Button(null);
		heroBox3.setPrefWidth(200);
		heroBox3.setPrefHeight(164);
		ImageView TessImage = new ImageView("TessHS.png");
		TessImage.setFitHeight(164);
		TessImage.setFitWidth(200);
		heroBox3.setGraphic(TessImage);
		heroBox3.setTextAlignment(TextAlignment.CENTER);
		heroBox3.getStyleClass().add("transparent-button");

		Tooltip tooltip2 = new Tooltip("Hero type : Explorer , HP = 80 , Action Points = 6 , Attack Damage = 20");
		Tooltip.install(heroBox3, tooltip2);

		// Riley Button
		Button heroBox4 = new Button(null);
		heroBox4.setPrefWidth(200);
		heroBox4.setPrefHeight(164);
		ImageView RielyImage = new ImageView("RielyHS.png");
		RielyImage.setFitHeight(164);
		RielyImage.setFitWidth(200);
		heroBox4.setGraphic(RielyImage);
		heroBox4.setTextAlignment(TextAlignment.CENTER);
		heroBox4.getStyleClass().add("transparent-button");

		Tooltip tooltip3 = new Tooltip("Hero type : Explorer , HP = 90 , Action Points = 5 , Attack Damage = 25");
		Tooltip.install(heroBox4, tooltip3);

		HBox chooseAHeroLayout2 = new HBox(15);
		chooseAHeroLayout2.setAlignment(Pos.BOTTOM_CENTER);

		// Tommy Button
		Button heroBox5 = new Button(null);
		heroBox5.setPrefWidth(175);
		heroBox5.setPrefHeight(145);
		ImageView TommyImage = new ImageView("TommyHS.png");
		TommyImage.setFitWidth(175);
		TommyImage.setFitHeight(145);
		heroBox5.setGraphic(TommyImage);
		heroBox5.setTextAlignment(TextAlignment.CENTER);
		heroBox5.getStyleClass().add("transparent-button");

		Tooltip tooltip4 = new Tooltip("Hero type : Explorer , HP = 95 , Action Points = 5 , Attack Damage = 25");
		Tooltip.install(heroBox5, tooltip4);

		// Bill button
		Button heroBox6 = new Button(null);
		heroBox6.setPrefWidth(175);
		heroBox6.setPrefHeight(167.125);
		ImageView BillImage = new ImageView("BillHS.png");
		BillImage.setFitWidth(175);
		BillImage.setFitHeight(167.125);

		heroBox6.setGraphic(BillImage);
		heroBox6.setTextAlignment(TextAlignment.CENTER);
		heroBox6.getStyleClass().add("transparent-button");
		heroBox6.setTranslateX(-15);
		heroBox6.setTranslateY(-21);

		Tooltip tooltip5 = new Tooltip("Hero type : Medic , HP = 100 , Action Points = 7 , Attack Damage = 10");
		Tooltip.install(heroBox6, tooltip5);

		// David button
		Button heroBox7 = new Button(null);
		heroBox7.setPrefWidth(175);
		heroBox7.setPrefHeight(162.75);
		ImageView DavidImage = new ImageView("DavidHS.png");
		DavidImage.setFitWidth(175);
		DavidImage.setFitHeight(162.75);
		heroBox7.setGraphic(DavidImage);
		heroBox7.setTextAlignment(TextAlignment.CENTER);
		heroBox7.getStyleClass().add("transparent-button");
		heroBox7.setTranslateX(-30);
		heroBox7.setTranslateY(-17);

		Tooltip tooltip6 = new Tooltip("Hero type : Fighter , HP = 150 , Action Points = 4 , Attack Damage = 35");
		Tooltip.install(heroBox7, tooltip6);

		// henry button
		Button heroBox8 = new Button(null);
		heroBox8.setPrefWidth(175);
		heroBox8.setPrefHeight(166.25);
		ImageView HenryImage = new ImageView("HenryHS.png");
		HenryImage.setFitWidth(165);
		HenryImage.setFitHeight(156.75);
		heroBox8.setGraphic(HenryImage);
		heroBox8.setTextAlignment(TextAlignment.CENTER);
		heroBox8.getStyleClass().add("transparent-button");
		heroBox8.setTranslateX(-69);
		heroBox8.setTranslateY(-21);

		Tooltip tooltip7 = new Tooltip("Hero type : Medic , HP = 105 , Action Points = 6 ,Attack Damage = 15");
		Tooltip.install(heroBox8, tooltip7);

		String tooltipStyle = "-fx-font-size: 15; -fx-text-fill: yellow;";
		tooltip.setStyle(tooltipStyle);
		tooltip1.setStyle(tooltipStyle);
		tooltip2.setStyle(tooltipStyle);
		tooltip3.setStyle(tooltipStyle);
		tooltip4.setStyle(tooltipStyle);
		tooltip5.setStyle(tooltipStyle);
		tooltip6.setStyle(tooltipStyle);
		tooltip7.setStyle(tooltipStyle);

		// Add the button to your layout

		// Show the tooltip on mouse hover
		tooltip.setShowDelay(Duration.ZERO);
		tooltip1.setShowDelay(Duration.ZERO);
		tooltip2.setShowDelay(Duration.ZERO);
		tooltip3.setShowDelay(Duration.ZERO);
		tooltip4.setShowDelay(Duration.ZERO);
		tooltip5.setShowDelay(Duration.ZERO);
		tooltip6.setShowDelay(Duration.ZERO);
		tooltip7.setShowDelay(Duration.ZERO);

		// Set the tooltip position next to the button
		tooltip.setShowDuration(Duration.INDEFINITE);
		tooltip1.setShowDuration(Duration.INDEFINITE);
		tooltip2.setShowDuration(Duration.INDEFINITE);
		tooltip3.setShowDuration(Duration.INDEFINITE);
		tooltip4.setShowDuration(Duration.INDEFINITE);
		tooltip5.setShowDuration(Duration.INDEFINITE);
		tooltip6.setShowDuration(Duration.INDEFINITE);
		tooltip7.setShowDuration(Duration.INDEFINITE);

		tooltip.setHideDelay(Duration.ZERO);
		tooltip1.setHideDelay(Duration.ZERO);
		tooltip2.setHideDelay(Duration.ZERO);
		tooltip3.setHideDelay(Duration.ZERO);
		tooltip4.setHideDelay(Duration.ZERO);
		tooltip5.setHideDelay(Duration.ZERO);
		tooltip6.setHideDelay(Duration.ZERO);
		tooltip7.setHideDelay(Duration.ZERO);

		// Adjust the tooltip position relative to the button
		tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		tooltip1.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		tooltip2.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		tooltip3.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		tooltip4.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		tooltip5.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		tooltip6.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
		tooltip7.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);

		// Set the preferred size for the buttons
		heroBox1.setPrefWidth(250);
		heroBox1.setPrefHeight(20);
		heroBox2.setPrefWidth(250);
		heroBox2.setPrefHeight(20);
		heroBox3.setPrefWidth(250);
		heroBox3.setPrefHeight(20);
		heroBox4.setPrefWidth(250);
		heroBox4.setPrefHeight(20);
		heroBox5.setPrefWidth(250);
		heroBox5.setPrefHeight(20);
		heroBox6.setPrefWidth(250);
		heroBox6.setPrefHeight(20);
		heroBox7.setPrefWidth(250);
		heroBox7.setPrefHeight(20);
		heroBox8.setPrefWidth(250);
		heroBox8.setPrefHeight(20);

		Button backToMainMenuButton1 = new Button("Back to Main Menu");
		backToMainMenuButton1.setFont(buttonsFont1);
		;
		backToMainMenuButton1.getStyleClass().add("transparent-button");
		backToMainMenuButton1.getStyleClass().add("white-border-button");
		backToMainMenuButton1.setPrefWidth(250);
		backToMainMenuButton1.setPrefHeight(20);

		backToMainMenuButton1.setOnAction(e -> {
			// Switch back to the main menu scene
			primaryStage.setScene(mainMenuScene);
			primaryStage.setFullScreen(true);
		});

		Button backToMainMenuButton2 = new Button(null);
		backToMainMenuButton2.setPrefWidth(700);
		backToMainMenuButton2.setPrefHeight(40);
		backToMainMenuButton2.getStyleClass().add("transparent-button");
		backToMainMenuButton2.setPrefWidth(215);
		backToMainMenuButton2.setPrefHeight(83);
		backToMainMenuButton2.setGraphic(quitb);
		backToMainMenuButton2.setTranslateX(400);
		backToMainMenuButton2.setTranslateY(-50);
		backToMainMenuButton2.setOnAction(e -> {

			primaryStage.setScene(mainMenuScene); // Switch back to main menu
			primaryStage.setFullScreen(true);
		});

		VBox ChooseCharater1 = new VBox();
		Label ChooseAHeroLabel1 = new Label("CHOOSE A CHARACTER");
		ChooseAHeroLabel1.setFont(font);
		ChooseAHeroLabel1.getStyleClass().add("label-white");
		ChooseAHeroLabel1.setAlignment(Pos.TOP_CENTER);

		ChooseCharater1.setTranslateY(-450);
		ChooseCharater1.setTranslateX(200 - 17);

		VBox container = new VBox(60); // 10 is the spacing between each hero box

		HBox one = new HBox(10);
		one.getChildren().addAll(heroBox1, heroBox2, heroBox3, heroBox4);
		one.setTranslateY(175);

		HBox two = new HBox(30);
		two.getChildren().addAll(heroBox5, heroBox6, heroBox7, heroBox8);
		two.setTranslateY(175);

		container.getChildren().addAll(one, two, ChooseCharater1, backToMainMenuButton2);

		VBox mainContainer = new VBox(60);
		mainContainer.getChildren().addAll(container);

		StackPane pane = new StackPane();
		pane.getChildren().addAll(chooseAHeroLayout1, chooseAHeroLayout2);

		chooseAHeroLayout1.setBackground(new Background(backgroundImg2));
		chooseAHeroLayout1.getChildren().addAll(ChooseAHeroLabel1);

		chooseAHeroLayout2.getChildren().addAll(mainContainer);
		ChooseCharater1.getChildren().addAll(ChooseAHeroLabel1);

		VBox gameLayout = new VBox(200);
		gameLayout.setAlignment(Pos.CENTER);

		chooseAHeroScene = new Scene(pane, screenWidth, screenHeight);
		chooseAHeroScene.getStylesheets().add("style.css");

		//////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////

		HBox startingGameLayout = new HBox(100);

		startingGameLayout.setBackground(new Background(backgroundImg5));

		heroBox1.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedHero = Game.availableHeroes.get(0);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);

			// ImageView heroImageView = createHeroImageView(h);
			// gridPane.add(heroImageView, h.getLocation().x, h.getLocation().y);

			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);

			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);
			StartingGameScene.getStylesheets().add("style.css");

			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});
		heroBox2.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedHero = Game.availableHeroes.get(1);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);
			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);
			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);
			StartingGameScene.getStylesheets().add("style.css");

			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});
		heroBox3.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedHero = Game.availableHeroes.get(2);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);
			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);
			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);
			StartingGameScene.getStylesheets().add("style.css");
			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});
		heroBox4.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedHero = Game.availableHeroes.get(3);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);
			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);
			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);
			StartingGameScene.getStylesheets().add("style.css");
			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});
		heroBox5.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedHero = Game.availableHeroes.get(4);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);
			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);
			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);
			StartingGameScene.getStylesheets().add("style.css");
			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});
		heroBox6.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedHero = Game.availableHeroes.get(5);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);
			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);
			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);
			StartingGameScene.getStylesheets().add("style.css");
			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});
		heroBox7.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			selectedHero = Game.availableHeroes.get(6);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);
			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);
			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);

			StartingGameScene.getStylesheets().add("style.css");
			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});
		heroBox8.setOnAction(e -> {
			System.out.println("Button pressed");

			try {
				Game.loadHeroes("Heroes.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/////////////////////////////////////

			////////////////////////////////////

			selectedHero = Game.availableHeroes.get(7);

			Game.startGame(selectedHero);
			{
				Game.map[0][1].setVisible(false);
				Game.map[0][0].setVisible(false);
				Game.map[1][0].setVisible(false);
				Game.map[1][1].setVisible(false);
			}
			((CharacterCell) Game.map[selectedHero.getLocation().x][selectedHero.getLocation().y]).setCharacter(null);
			((CharacterCell) Game.map[14][0]).setCharacter(selectedHero);
			selectedHero.setLocation(new Point(14, 0));

			Game.adjustVisibility(selectedHero);
			startingGameLayout.getChildren().addAll(createButtonContainer(primaryStage), createGridPane(primaryStage),
					statsBox);
			StartingGameScene = new Scene(startingGameLayout, screenWidth, screenHeight);
			StartingGameScene.getStylesheets().add("style.css");
			primaryStage.setScene(StartingGameScene);
			primaryStage.setFullScreen(true);
		});

		statsBox = new VBox(10);
		statsBox.setAlignment(Pos.CENTER);
		statsBox.setSpacing(10);
		primaryStage.setOnShown(event -> {
			AnimationTimer fpsTimer = new AnimationTimer() {
				private long lastTime = System.nanoTime();

				@Override
				public void handle(long now) {
					long elapsedTime = now - lastTime;
					double frameRate = 1_000_000_000.0 / elapsedTime;
					fps = (int) frameRate;
					lastTime = now;
				}
			};
			fpsTimer.start();
		});
		Font font1 = Font.font("Impact", FontWeight.EXTRA_BOLD, 180);
		VBox GameOverScenelayout = new VBox(100);

        Label youlose = new Label("YOU LOSE!!!!");
        youlose.setFont(font1);
        youlose.getStyleClass().add("label-white");
        GameOverScenelayout.setBackground(new Background(backgroundImg5));
        GameOverScenelayout.getChildren().addAll(youlose);

        GameOverScene = new Scene(GameOverScenelayout, screenWidth, screenHeight);


        primaryStage.setScene(mainMenuScene); // Set the main menu scene as the initial scene
        primaryStage.setFullScreen(true); // Set the stage to fullscreen mode
        primaryStage.setFullScreenExitHint(""); // Set an empty string to disable the exit hint
        primaryStage.show(); // Show the stage
        VBox WinScenelayout = new VBox(100);

        Label youwin = new Label("YOU WIN!!!!");
        youwin.setFont(font1);
        youwin.getStyleClass().add("label-white");
        WinScenelayout.setBackground(new Background(backgroundImg5));
        WinScenelayout.getChildren().addAll(youwin);

        winScene = new Scene(WinScenelayout, screenWidth, screenHeight);


        primaryStage.setScene(mainMenuScene); // Set the main menu scene as the initial scene
        primaryStage.setFullScreen(true); // Set the stage to fullscreen mode
        primaryStage.setFullScreenExitHint(""); // Set an empty string to disable the exit hint
        primaryStage.show(); // Show the stage

	}

	private static GridPane createGridPane(Stage primaryStage) {

		gridPane.setAlignment(Pos.CENTER);
		if (Game.heroes.size() == 0) {
			if (Game.checkGameOver() == true) {
				primaryStage.setScene(GameOverScene);
				primaryStage.setFullScreen(true);

				System.out.println("Game Ended");
			}

		} else {
			if (Game.checkWin() == true) {
				primaryStage.setScene(winScene);
				primaryStage.setFullScreen(true);
				System.out.println("Game won");
			} else {

				for (int row = 0; row < GRID_SIZE; row++) {
					for (int col = 0; col < GRID_SIZE; col++) {
						Button button = createButton();

						if (Game.map[row][col] instanceof CharacterCell) {
							CharacterCell characterCell = (CharacterCell) Game.map[row][col];

							if (characterCell.getCharacter() == null) {
								if (characterCell.isVisible() == true) {
									button.setOnAction(e -> {

									});
									button.setStyle("-fx-background-color: white; -fx-border-color: black;");
								} else {
									button.setOnAction(e -> {

									});
									button.setStyle("-fx-background-color: grey; -fx-border-color: black;");
								}
							}
							if (characterCell.getCharacter() instanceof Zombie) {

								if (characterCell.isVisible() == true) {

									Image zombieImage = new Image("Clicker_1.png");
									String imageStyle = "-fx-background-image: url('" + zombieImage.getUrl() + "'); "
											+ "-fx-background-repeat: no-repeat; " + "-fx-background-size: " + CELL_SIZE
											+ "px " + CELL_SIZE + "px; " + "-fx-background-position: center;";
									button.setStyle(imageStyle);
									button.getStyleClass().add("image-button"); // Add a custom CSS class for the button
									button.setOnAction(e -> {

										selectedHero.setTarget(characterCell.getCharacter());
										System.out.println("Target has been setted");

										gridPane = createGridPane(primaryStage);
										updateStatsBox(primaryStage, selectedHero);
										Game.adjustVisibility(selectedHero);

									});
								} else {

									button.setOnAction(e -> {
										// Handle button click event if needed
									});
									button.setStyle("-fx-background-color: grey; -fx-border-color: black;");
								}

							}

							if (characterCell.getCharacter() instanceof Hero) {

								String heroImageURL = ""; // Specify the URL or file path for the hero's image

								// Determine the image URL or file path based on the selected hero
								if (characterCell.getCharacter().getName().equals("Joel Miller")) {
									heroImageURL = "Joel.png";
								} else if (characterCell.getCharacter().getName().equals("Bill")) {
									heroImageURL = "Bill.png";
								} else if (characterCell.getCharacter().getName().equals("Tess")) {
									heroImageURL = "Tess.png";
								} else if (characterCell.getCharacter().getName().equals("Ellie Williams")) {
									heroImageURL = "Ellie.png";
								} else if (characterCell.getCharacter().getName().equals("Riley Abel")) {
									heroImageURL = "Riley.png";
								} else if (characterCell.getCharacter().getName().equals("Henry Burell")) {
									heroImageURL = "Henry.png";
								} else if (characterCell.getCharacter().getName().equals("Tommy Miller")) {
									heroImageURL = "Tommy.png";
								} else if (characterCell.getCharacter().getName().equals("David")) {
									heroImageURL = "David.png";
								}
								// Add more else if conditions for the remaining heroes

								Image heroImage = new Image(heroImageURL);
								String imageStyle = "-fx-background-image: url('" + heroImage.getUrl() + "'); "
										+ "-fx-background-repeat: no-repeat; " + "-fx-background-size: " + CELL_SIZE
										+ "px " + CELL_SIZE + "px; " + "-fx-background-position: center;";
								button.setStyle(imageStyle);
								button.getStyleClass().add("image-button"); // Add a custom CSS class for the button

								button.setOnAction(e -> {

									selectedHero = (Hero) characterCell.getCharacter();
									// Do something with the selected hero
									System.out.println("Selected Hero: " + selectedHero.getName());

									gridPane = createGridPane(primaryStage);
									updateStatsBox(primaryStage, selectedHero);
									Game.adjustVisibility(selectedHero);
								});

							}
						}

						if (Game.map[row][col] instanceof CollectibleCell) {
							CollectibleCell collectibleCell = (CollectibleCell) Game.map[row][col];
							if (collectibleCell.getCollectible() instanceof Vaccine) {
								if (collectibleCell.isVisible() == true) {
									Image VaccineImageView = new Image("Vaccine.png");
									String imageStyle = "-fx-background-image: url('" + VaccineImageView.getUrl()
											+ "'); " + "-fx-background-repeat: no-repeat; " + "-fx-background-size: "
											+ CELL_SIZE + "px " + CELL_SIZE + "px; "
											+ "-fx-background-position: center;";
									button.setStyle(imageStyle);
									button.getStyleClass().add("image-button"); // Add a custom CSS class for the button

									button.setOnAction(e -> {
										// Handle button click event here
										System.out.println("Button clicked!");

										// Check if the hero is moving to the button's location
										if (selectedHero.getLocation().equals(collectibleCell)) {

											vaccines.pickUp(selectedHero);
											updateStatsBox(primaryStage, selectedHero);
											// Perform the desired action when the hero moves to the button
											// Add your code here
										}
									});

								} else {
									button.setOnAction(e -> {

									});
									button.setStyle("-fx-background-color: grey; -fx-border-color: black;");
								}

								button.setOnAction(e -> {
									// Handle button click event if needed
								});
							}
							if (collectibleCell.getCollectible() instanceof Supply) {
								if (collectibleCell.isVisible() == true) {
									Image SupplyImageView = new Image("Supplies.png");
									String imageStyle = "-fx-background-image: url('" + SupplyImageView.getUrl()
											+ "'); " + "-fx-background-repeat: no-repeat; " + "-fx-background-size: "
											+ CELL_SIZE + "px " + CELL_SIZE + "px; "
											+ "-fx-background-position: center;";
									button.setStyle(imageStyle);
									button.getStyleClass().add("image-button"); // Add a custom CSS class for the button
									button.setOnAction(e -> {
										// Handle button click event here
										System.out.println("Button clicked!");

										// Check if the hero is moving to the button's location
										if (selectedHero.getLocation().equals(collectibleCell)) {

											supplies.pickUp(selectedHero);
											updateStatsBox(primaryStage, selectedHero);
											// Perform the desired action when the hero moves to the button
											// Add your code here
										}
									});
								} else {
									button.setOnAction(e -> {
										// Handle button click event if needed
									});
									button.setStyle("-fx-background-color: grey; -fx-border-color: black;");
								}

								button.setOnAction(e -> {
									// Handle button click event if needed
								});
							}

						}
						if (Game.map[row][col] instanceof TrapCell) {
							TrapCell trapCell = (TrapCell) Game.map[row][col];

							if (trapCell.isVisible()) {
								button.setOnAction(e -> {
									if (selectedHero.getLocation().equals(trapCell)) {

									}
								});

								button.setStyle("-fx-background-color: white; -fx-border-color: black;");
							} else {
								button.setOnAction(e -> {

								});

								button.setStyle("-fx-background-color: grey; -fx-border-color: black;");
							}
						}
						gridPane.add(button, col, row);
					}
				}

				// Create a button

			}
		}

		return gridPane;
	}

	// Method to create and show the trap cell alert

	// Add the button to your scene or stage

	private static void updateStatsBox(Stage primaryStage, Hero hero) {
		Font buttonsFont = Font.font("Lucida Sans Unicode", FontWeight.EXTRA_BOLD, 20);
		Button endturnButton = new Button("End Turn");
		endturnButton.setFont(buttonsFont);
		endturnButton.setTextAlignment(TextAlignment.CENTER);
		endturnButton.getStyleClass().add("transparent-button");
		endturnButton.getStyleClass().add("white-border-button");
		endturnButton.setPrefSize(200, 100);
		endturnButton.setOnAction(d -> {
			try {
				Game.endTurn();
				gridPane = createGridPane(primaryStage);
				updateStatsBox(primaryStage, hero);
			} catch (InvalidTargetException ex) {
				// Handle the movement exception (character trying to move outside the map)
				// Display an error message, play a sound, etc.
				System.out.println("Invalid Target: " + ex.getMessage());
			} catch (NotEnoughActionsException ex) {
				// Handle the not enough actions exception (character does not have enough
				// actions to move)
				// Display an error message, disable the button, etc.
				System.out.println("Not enough actions : " + ex.getMessage());
			}
			// Handle button click event here
			System.out.println("Button clicked!");

		});
		Button SpecialAction = new Button("Special Action");
		SpecialAction.setFont(buttonsFont);
		SpecialAction.setTextAlignment(TextAlignment.CENTER);
		SpecialAction.getStyleClass().add("transparent-button");
		SpecialAction.getStyleClass().add("white-border-button");
		SpecialAction.setPrefSize(200, 100);
		SpecialAction.setOnAction(d -> {

			try {
				if (selectedHero instanceof Medic) {
					selectedHero.setTarget(hero);
					
				}
				hero.useSpecial();

				gridPane = createGridPane(primaryStage);
				updateStatsBox(primaryStage, hero);
			} catch (InvalidTargetException ex) {
				// Handle the movement exception (character trying to move outside the map)
				// Display an error message, play a sound, etc.
				System.out.println("Invalid Target: " + ex.getMessage());
			} catch (NoAvailableResourcesException ex) {
				// Handle the not enough actions exception (character does not have enough
				// actions to move)
				// Display an error message, disable the button, etc.
				System.out.println("No Avaiable Resources : " + ex.getMessage());
			}

		});
		Button Cure = new Button("Cure");
		Cure.setFont(buttonsFont);
		Cure.setTextAlignment(TextAlignment.CENTER);
		Cure.getStyleClass().add("transparent-button");
		Cure.getStyleClass().add("white-border-button");
		Cure.setPrefSize(200, 100);
		Cure.setOnAction(d -> {

			try {
				hero.cure();
				gridPane = createGridPane(primaryStage);
				updateStatsBox(primaryStage, hero);
			} catch (InvalidTargetException ex) {
				// Handle the movement exception (character trying to move outside the map)
				// Display an error message, play a sound, etc.
				System.out.println("Invalid Target: " + ex.getMessage());
			} catch (NoAvailableResourcesException ex) {
				// Handle the not enough actions exception (character does not have enough
				// actions to move)
				// Display an error message, disable the button, etc.
				System.out.println("No Avaiable Resources : " + ex.getMessage());
			} catch (NotEnoughActionsException ex) {
				// Handle the movement exception (character trying to move outside the map)
				// Display an error message, play a sound, etc.
				System.out.println("Not Enough Actions: " + ex.getMessage());
			}

		});
		Button Attack = new Button("Attack");
		Attack.setFont(buttonsFont);
		Attack.setTextAlignment(TextAlignment.CENTER);
		Attack.getStyleClass().add("transparent-button");
		Attack.getStyleClass().add("white-border-button");
		Attack.setPrefSize(200, 100);
		Attack.setOnAction(d -> {

			try {
				hero.attack();
				if (hero.getTarget().getCurrentHp() == 0) {
					hero.setTarget(null);

				}
				Game.adjustVisibility(hero);
				gridPane = createGridPane(primaryStage);
				updateStatsBox(primaryStage, hero);

			} catch (InvalidTargetException ex) {
				// Handle the movement exception (character trying to move outside the map)
				// Display an error message, play a sound, etc.
				System.out.println("Invalid Target: " + ex.getMessage());
			} catch (NotEnoughActionsException ex) {
				// Handle the movement exception (character trying to move outside the map)
				// Display an error message, play a sound, etc.
				System.out.println("Not Enough Actions: " + ex.getMessage());
			}

		});

		Label fpsLabel = new Label("FPS: " + fps);
		fpsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		Timeline fpsTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			fpsLabel.setText("FPS: " + fps);
			fps = 0;
		}));
		fpsTimeline.setCycleCount(Timeline.INDEFINITE);
		fpsTimeline.play();

		// Clear the statsBox
		statsBox.getChildren().clear();

		fpsLabel.setStyle("-fx-text-fill: white;");
		statsBox.getChildren().addAll(fpsLabel);

		// Add the hero's stats to the statsBox
		if (hero != null) {
			// Create a label to display the hero's name
			Label nameLabel = new Label("Name: " + hero.getName());
			nameLabel.setFont(buttonsFont);
			nameLabel.setStyle("-fx-text-fill: white;");

			// Create a progress bar to represent the hero's health
			ProgressBar healthBar = new ProgressBar();
			double x = (double) (hero.getMaxHp());
			double z = (hero.getCurrentHp() / x);
			healthBar.setProgress(z);

			// Set the color of the health bar based on the health percentage
			if (z <= 0.75 && z > 0.35) {
				healthBar.setStyle("-fx-accent: orange;");
			} else if (z <= 0.35) {
				healthBar.setStyle("-fx-accent: red;");
			} else {
				healthBar.setStyle("-fx-accent: green;");
			}

			if (hero.getCurrentHp() == 0) {
				hero.onCharacterDeath();

			}

			healthBar.setPrefWidth(200); // Set the preferred width of the health bar
			healthBar.setPrefHeight(30); // Set the preferred height of the health bar

			statsBox.getChildren().add(healthBar);
			Label Health = new Label("HP : " + hero.getCurrentHp());
			Health.setFont(buttonsFont);
			Health.setStyle("-fx-text-fill: white;");
			Label Damage = new Label("Attack Damage : " + hero.getAttackDmg());
			Damage.setFont(buttonsFont);
			Damage.setStyle("-fx-text-fill: white;");
			Label Type = new Label("Name: " + hero.toString());
			Type.setFont(buttonsFont);
			Type.setStyle("-fx-text-fill: white;");

			// Create a label to display the hero's available action points
			Label actionPointsLabel = new Label("Action Points: " + hero.getActionsAvailable());
			actionPointsLabel.setFont(buttonsFont);
			actionPointsLabel.setStyle("-fx-text-fill: white;");

			vaccines.setHero(hero);
			Label vaccineinv = new Label("Vaccines Inventory: " + vaccines.toString());
			vaccineinv.setFont(buttonsFont);
			vaccineinv.setStyle("-fx-text-fill: white;");
			supplies.setHero(hero);
			Label supplyinv = new Label("Supplies Inventory: " + supplies.toString());
			supplyinv.setFont(buttonsFont);
			supplyinv.setStyle("-fx-text-fill: white;");

			statsBox.getChildren().add(nameLabel);
			statsBox.getChildren().add(Type);
			statsBox.getChildren().add(Health);
			statsBox.getChildren().add(Damage);
			statsBox.getChildren().add(actionPointsLabel);
			statsBox.getChildren().add(vaccineinv);
			statsBox.getChildren().add(supplyinv);
			statsBox.getChildren().add(Attack);
			statsBox.getChildren().add(SpecialAction);
			statsBox.getChildren().add(Cure);
			statsBox.getChildren().add(endturnButton);

			statsBox.setAlignment(Pos.TOP_RIGHT);
			statsBox.setPadding(new Insets(25));
		}
	}

	private static Button createButton() {

		Button button = new Button();
		button.setPrefSize(CELL_SIZE, CELL_SIZE);

		button.setStyle("-fx-background-color: grey; -fx-border-color: black;");

		// Add event handler for button click
		button.setOnAction(e -> {
			// Handle button click event here
			System.out.println("Button clicked!");

			// Add visual effect
			button.setDisable(true); // Disable the button temporarily
			button.setStyle("-fx-background-color: white; -fx-border-color: gold;");

			// Get the location of the button

			// ...

		});

		return button;
	}

	private static VBox createButtonContainer(Stage primaryStage) {
		Button upButton = createArrowButton(primaryStage, "↑", Direction.UP);
		Button downButton = createArrowButton(primaryStage, "↓", Direction.DOWN);
		Button leftButton = createArrowButton(primaryStage, "←", Direction.LEFT);
		Button rightButton = createArrowButton(primaryStage, "→", Direction.RIGHT);

		VBox buttonContainer = new VBox(20);
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.setPadding(new Insets(75));

		HBox topButtonsContainer = new HBox(150);
		topButtonsContainer.setAlignment(Pos.CENTER_RIGHT);
		topButtonsContainer.getChildren().addAll(leftButton, upButton, rightButton);

		buttonContainer.getChildren().addAll(upButton, topButtonsContainer, downButton);

		return buttonContainer;
	}

	private static Button createArrowButton(Stage primaryStage, String text, Direction direction) {
		Button button = new Button(text);
		Font buttonsFont3 = Font.font("Impact", FontWeight.EXTRA_BOLD, 35);
		button.setFont(buttonsFont3);
		button.setAlignment(Pos.CENTER);
		button.setPrefSize(100, 100);
		button.getStyleClass().add("arrow-button");

		button.setOnAction(e -> {
			button.setStyle("-fx-background-color: Darkgrey	 ; -fx-border-color: black;"); // Change the style to green
			PauseTransition pause = new PauseTransition(Duration.seconds(0.1)); // Pause for 0.1 seconds
			pause.setOnFinished(event -> {
				button.setStyle("-fx-background-color: white; -fx-border-color: black;"); // Revert the style back to
																							// white
				// Add the logic or action you want to perform when the button is clicked
			});

			if (button.getText().equals("↑")) {
				// Move the character up (replace with your own implementation)
				if (selectedHero != null) {
					try {

						Game.move(selectedHero, Direction.DOWN);
						System.out.println("Moved UP");
						gridPane = createGridPane(primaryStage);

						Game.adjustVisibility(selectedHero);
						// Update the game state or perform any required actions
						// Redraw the grid or update the UI accordingly
					} catch (MovementException ex) {
						// Handle the movement exception (character trying to move outside the map)
						// Display an error message, play a sound, etc.
						System.out.println("Cannot move up: " + ex.getMessage());
					} catch (NotEnoughActionsException ex) {
						// Handle the not enough actions exception (character does not have enough
						// actions to move)
						// Display an error message, disable the button, etc.
						System.out.println("Not enough actions to move up: " + ex.getMessage());
					}
				}
			}

			if (button.getText().equals("↓")) {
				// Move the character up (replace with your own implementation)
				if (selectedHero != null) {
					try {

						Game.move(selectedHero, Direction.UP);
						System.out.println("Moved Down");
						gridPane = createGridPane(primaryStage);

						Game.adjustVisibility(selectedHero);

						// Update the game state or perform any required actions
						// Redraw the grid or update the UI accordingly
					} catch (MovementException ex) {
						// Handle the movement exception (character trying to move outside the map)
						// Display an error message, play a sound, etc.
						System.out.println("Cannot move up: " + ex.getMessage());
					} catch (NotEnoughActionsException ex) {
						// Handle the not enough actions exception (character does not have enough
						// actions to move)
						// Display an error message, disable the button, etc.
						System.out.println("Not enough actions to move up: " + ex.getMessage());
					}
				}
			}

			if (button.getText().equals("←")) {
				// Move the character up (replace with your own implementation)
				if (selectedHero != null) {
					try {

						Game.move(selectedHero, Direction.LEFT);
						System.out.println("Moved Left");
						gridPane = createGridPane(primaryStage);

						Game.adjustVisibility(selectedHero);

						// Update the game state or perform any required actions
						// Redraw the grid or update the UI accordingly
					} catch (MovementException ex) {
						// Handle the movement exception (character trying to move outside the map)
						// Display an error message, play a sound, etc.
						System.out.println("Cannot move up: " + ex.getMessage());
					} catch (NotEnoughActionsException ex) {
						// Handle the not enough actions exception (character does not have enough
						// actions to move)
						// Display an error message, disable the button, etc.
						System.out.println("Not enough actions to move up: " + ex.getMessage());
					}
				}
			}

			if (button.getText().equals("→")) {
				// Move the character up (replace with your own implementation)
				if (selectedHero != null) {
					try {
						Game.move(selectedHero, Direction.RIGHT);
						System.out.println("Moved Right");
						gridPane = createGridPane(primaryStage);

						Game.adjustVisibility(selectedHero);

						// Update the game state or perform any required actions
						// Redraw the grid or update the UI accordingly
					} catch (MovementException ex) {
						// Handle the movement exception (character trying to move outside the map)
						// Display an error message, play a sound, etc.
						System.out.println("Cannot move up: " + ex.getMessage());
					} catch (NotEnoughActionsException ex) {
						// Handle the not enough actions exception (character does not have enough
						// actions to move)
						// Display an error message, disable the button, etc.
						System.out.println("Not enough actions to move up: " + ex.getMessage());
					}
				}
			}

			updateStatsBox(primaryStage, selectedHero);
			pause.play();
		});

		return button;
	}

	public static void main(String[] args) {
		launch(args);
	}
}