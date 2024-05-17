import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameGUI extends Application {

    private static final String HEROES_CSV = "Joel Miller,FIGH,140,5,30\n" +
            "Ellie Williams,MED,110,6,15\n" +
            "Tess,EXP,80,6,20\n" +
            "Riley Abel,EXP,90,5,25\n" +
            "Tommy Miller,EXP,95,5,25\n" +
            "Bill,MED,100,7,10\n" +
            "David,FIGH,150,4,35\n" +
            "Henry Burell,MED,105,6,15";

    private String[] heroesData;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game GUI");

        // Parse the heroes' data from the CSV
        heroesData = HEROES_CSV.split("\n");

        // Create UI components
        Label titleLabel = new Label("Select a Hero:");
        VBox heroButtons = new VBox(10);
        heroButtons.setAlignment(Pos.CENTER);

        for (String heroData : heroesData) {
            String[] heroInfo = heroData.split(",");
            String heroName = heroInfo[0];
            Button heroButton = new Button(heroName);
            heroButton.setOnAction(e -> {
                // Display the selected hero's details
                int index = heroButtons.getChildren().indexOf(heroButton);
                if (index >= 0 && index < heroesData.length) {
                    String selectedHeroData = heroesData[index];
                    String[] selectedHeroInfo = selectedHeroData.split(",");
                    String selectedHeroName = selectedHeroInfo[0];
                    String selectedHeroType = selectedHeroInfo[1];
                    String selectedHeroMaxHP = selectedHeroInfo[2];
                    String selectedHeroAttackDamage = selectedHeroInfo[3];
                    String selectedHeroMaxActions = selectedHeroInfo[4];

                    System.out.println("Selected Hero: " + selectedHeroName);
                    System.out.println("Type: " + selectedHeroType);
                    System.out.println("Max HP: " + selectedHeroMaxHP);
                    System.out.println("Attack Damage: " + selectedHeroAttackDamage);
                    System.out.println("Max Actions: " + selectedHeroMaxActions);
                }
            });
            heroButtons.getChildren().add(heroButton);
        }

        // Create a layout container (VBox) and add the UI components
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel, heroButtons);

        // Create a scene with the layout and set it as the primary stage scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
