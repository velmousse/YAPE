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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group group = new Group();
        primaryStage.setTitle("");
        Canvas canvas = new Canvas(640, 360);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        int numBalls = 12;
        Balle[] balles = new Balle[numBalls];

        for (int i = 0; i < numBalls; i++) {
            balles[i] = new Balle((float) Math.random()*640, (float) Math.random()*360, (float) Math.random()*40 + 30, i, balles);
        }


        primaryStage.setScene(new Scene(group, 640, 360));




        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        for (Balle balle : balles) {
                            balle.collide();
                            balle.move();
                            group.getChildren().add(balle.display());

                        }
                    }
                }

        ), new KeyFrame(Duration.millis(25)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}