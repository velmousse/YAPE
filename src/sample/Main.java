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
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private Scene scene;
    private Group group;
    private final Boolean[] first = new Boolean[1];
    private int numBalls = 10;
    private Balle[] balles;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        first[0] = true;
        group = new Group();
        primaryStage.setTitle("");
        Canvas canvas = new Canvas(640, 360);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        scene = new Scene(group, 640, 360);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R)
                resetScene();
        });
        primaryStage.setScene(scene);

        resetScene();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    for (int i = 0; i < balles.length; i++) {
                        balles[i].collision();
                        balles[i].mouvement();
                        if (first[0])
                            group.getChildren().add(balles[i].affichage());
                        else
                            group.getChildren().set(i, balles[i].affichage());
                    }
                    first[0] = false;
                }
            }
        ), new KeyFrame(Duration.millis(10)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();
    }

    public void resetScene() {
        balles = new Balle[numBalls];
        for (int i = 0; i < numBalls; i++) {
            balles[i] = new Balle((float) Math.random()*640, (float) Math.random()*360, (float) Math.random()*40 + 30, i, balles, Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        }
    }
}