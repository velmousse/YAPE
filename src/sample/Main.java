package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

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

        for (Balle balle : balles) {
            balle.collide();
            balle.move();
            group.getChildren().add(balle.display());
        }

        new AnimationTimer() {
            @Override public void handle(long currentNanoTime) {
                for (int i = 0; i < 50; i++) {
                    double ran = Math.random() * 640;
                    graphics.strokeOval(ran, ran, ran, ran);
                }

                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) { }
            }
        }.start();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}