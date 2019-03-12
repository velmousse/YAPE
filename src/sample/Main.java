package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;
import java.util.ArrayList;

public class Main extends Application {
    private Image bowling = new Image("file:ressources/bowling.png");
    private Image tennis = new Image("file:ressources/tennis.png");
    private Scene scene;
    private Group group;
    private final Boolean[] selection = new Boolean[3];
    private int numBalles = 0;
    private ArrayList<Balle> balles = new ArrayList<>();
    public ArrayList<PlanIncliné> planInclinés= new ArrayList<>();

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        for (int i = 0; i < selection.length; i++) selection[i] = false;
        group = new Group();
        primaryStage.setTitle("");
        scene = new Scene(group, 800, 500);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R)
                resetScene();
            else if (event.getCode() == KeyCode.DIGIT1) {
                if (!selection[0]) {
                    for (int i = 0; i < selection.length; i++) selection[i] = false;
                    selection[0] = true;
                } else
                    for (int i = 0; i < selection.length; i++) selection[i] = false;
            } else if (event.getCode() == KeyCode.DIGIT2) {
                if (!selection[1]) {
                    for (int i = 0; i < selection.length; i++) selection[i] = false;
                    selection[1] = true;
                } else
                    for (int i = 0; i < selection.length; i++) selection[i] = false;
            }
            else if (event.getCode() == KeyCode.DIGIT3) {
                if (!selection[2]) {
                    for (int i = 0; i < selection.length; i++) selection[i] = false;
                    selection[2] = true;
                } else
                    for (int i = 0; i < selection.length; i++) selection[i] = false;
            }
        });

        scene.setOnMouseClicked(event -> {
            if (selection[0]) {
                balles.add(new Bowling((float) event.getX(), (float) event.getY(), balles.size(), balles, bowling));
                group.getChildren().add(balles.get(numBalles).affichage());
                numBalles++;
            } else if (selection[1]) {
                balles.add(new Tennis((float) event.getX(), (float) event.getY(), balles.size(), balles, tennis));
                group.getChildren().add(balles.get(numBalles).affichage());
                numBalles++;
            }
            else if(selection[2]){
                planInclinés.add(new PlanIncliné((float)event.getX(),(float)event.getY()));
                group.getChildren().add(planInclinés.get(0).affichage());
            }
        });

        primaryStage.setScene(scene);


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (balles.size() > 0) {
                        for (int i = 0; i < balles.size(); i++) {
                            balles.get(i).mouvement();
                            balles.get(i).collision();
                            group.getChildren().set(i, balles.get(i).affichage());
                        }
                    }
                }
            }
        ), new KeyFrame(Duration.millis(10)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();
    }

    public void resetScene() {
        balles.clear();
        group.getChildren().clear();
        numBalles = 0;
    }
}