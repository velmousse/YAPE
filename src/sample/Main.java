//Copyright @ Vincent Dufour et Jean-Phillippe Pedneault - 2019
//All rights reserved

package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Dynamiques.Bowling;
import sample.Objets.Fixes.ObjetFixe;
import sample.Objets.Fixes.PlanIncline;
import sample.Objets.Dynamiques.Tennis;
import sample.Objets.Objet;

import java.util.ArrayList;

public class Main extends Application {
    private Timeline timeline;
    private Image bowling = new Image("file:ressources/bowling.png");
    private Image tennis = new Image("file:ressources/tennis.png");
    private Scene scene;
    private Group group;
    private final Boolean[] selection = new Boolean[3];
    private int numBalles = 0, numPlanInclines = 0, numObjets = 0;

    private ArrayList<Balle> balles = new ArrayList<>();
    private ArrayList<PlanIncline> planInclines = new ArrayList<>();

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
            else if (event.getCode() == KeyCode.SPACE)
               timeline.play();
            else if (event.getCode() == KeyCode.DIGIT1) {
                for (int i = 0; i < selection.length; i++) selection[i] = false;
                selection[0] = true;
            } else if (event.getCode() == KeyCode.DIGIT2) {
                for (int i = 0; i < selection.length; i++) selection[i] = false;
                selection[1] = true;
            } else if (event.getCode() == KeyCode.DIGIT3) {
                for (int i = 0; i < selection.length; i++) selection[i] = false;
                selection[2] = true;
            }
        });

        scene.setOnMouseClicked(event -> {
            if (selection[0]) {
                balles.add(new Bowling((float) event.getX(), (float) event.getY(), balles.size(), balles, bowling));
                group.getChildren().add(balles.get(numBalles).affichage());
                numBalles++;
                numObjets++;
            } else if (selection[1]) {
                balles.add(new Tennis((float) event.getX(), (float) event.getY(), balles.size(), balles, tennis));
                group.getChildren().add(balles.get(numBalles).affichage());
                numBalles++;
                numObjets++;
            } else if(selection[2]){
                planInclines.add(new PlanIncline((float) event.getX(),(float) event.getY()));
                group.getChildren().add(planInclines.get(numPlanInclines).affichage());
                numPlanInclines++;
                numObjets++;
            }
        });

        primaryStage.setScene(scene);


        timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (numObjets > 0) {
                        int nombreDeBalles = 0;
                        int nombreDePlansInclines = 0;
                        for (int i = 0; i < numObjets; i++) {
                            Object objet = group.getChildren().get(i);
                            if (objet instanceof Ellipse) {
                                balles.get(nombreDeBalles).mouvement();
                                balles.get(nombreDeBalles).collision();
                                group.getChildren().set(i, balles.get(nombreDeBalles++).affichage());
                            }
                            else if (objet instanceof Rectangle) {
                                group.getChildren().set(i, planInclines.get(nombreDePlansInclines++).affichage());
                            }
                        }
                    }
                }
            }
        ), new KeyFrame(Duration.millis(10)));
        timeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.show();
    }

    public void resetScene() {
        timeline.stop();
        balles.clear();
        planInclines.clear();
        group.getChildren().clear();
        numBalles = 0;
        numPlanInclines = 0;
        numObjets = 0;
    }
}