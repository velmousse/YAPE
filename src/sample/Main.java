//Copyright @ Vincent Dufour et Jean-Phillippe Pedneault - 2019
//All rights reserved

package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
import sun.plugin2.util.ColorUtil;

import java.util.ArrayList;

public class Main extends Application {
    private Timeline timeline;
    private Image bowling = new Image("file:ressources/bowling.png");
    private Image tennis = new Image("file:ressources/tennis.png");
    private Scene scene;
    private Group group, objets, uinterface;
    private final Boolean[] selection = new Boolean[3];
    private boolean pause = true;
    private int numBalles = 0, numPlanInclines = 0, numObjets = 0;

    private ArrayList<Balle> balles = new ArrayList<>();
    private ArrayList<PlanIncline> planInclines = new ArrayList<>();

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        for (int i = 0; i < selection.length; i++) selection[i] = false;
        group = new Group();
        objets = new Group();
        uinterface = new Group();
        group.getChildren().addAll(objets, uinterface); //Ordre prédéfini nécessaire

        primaryStage.setTitle("");
        scene = new Scene(group, 1200, 800);


        scene.setOnMouseClicked(event -> {  //Ne pas oublier de vérifier s'il y a un imbriquement
            if ((event.getX() > 0 && event.getX() < 1000) && pause) {
                if (selection[0]) {
                    balles.add(new Bowling((float) event.getX(), (float) event.getY(), balles.size(), balles, bowling));
                    objets.getChildren().add(balles.get(numBalles).affichage());
                    numBalles++;
                    numObjets++;
                } else if (selection[1]) {
                    balles.add(new Tennis((float) event.getX(), (float) event.getY(), balles.size(), balles, tennis));
                    objets.getChildren().add(balles.get(numBalles).affichage());
                    numBalles++;
                    numObjets++;
                } else if (selection[2]) {
                    planInclines.add(new PlanIncline((float) event.getX(), (float) event.getY(), null));
                    objets.getChildren().add(planInclines.get(numPlanInclines).affichage());
                    numPlanInclines++;
                    numObjets++;
                }
            }
        });

        setInterface();

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);


        timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (numObjets > 0) {
                        int nombreDeBalles = 0;
                        int nombreDePlansInclines = 0;
                        for (int i = 0; i < numObjets; i++) {
                            Object objet = objets.getChildren().get(i);
                            if (objet instanceof Ellipse) {
                                balles.get(nombreDeBalles).mouvement();
                                balles.get(nombreDeBalles).collision();
                                objets.getChildren().set(i, balles.get(nombreDeBalles++).affichage());
                            }
                            /*
                            else if (objet instanceof Rectangle) {
                                group.getChildren().set(i, planInclines.get(nombreDePlansInclines++).affichage());
                            }*/
                        }
                    }
                }
            }
        ), new KeyFrame(Duration.millis(10)));
        timeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.show();
    }

    public void setInterface() {
        Rectangle menuDroit = new Rectangle(300, 900, Color.LIGHTGRAY);
        menuDroit.setX(1000);
        menuDroit.setY(0);

        Button restart = new Button("Restart");
        restart.setTranslateX(1135);
        restart.setTranslateY(770);
        restart.setScaleX(1.5);
        restart.setScaleY(1.5);
        restart.setOnAction(event -> { resetScene(); });

        Button start = new Button("Start");
        start.setTranslateX(1025);
        start.setTranslateY(770);
        start.setScaleX(1.5);
        start.setScaleY(1.5);
        start.setOnAction(event -> {
            timeline.play();
            pause = false;
        });

        Group selections = setSelections();

        uinterface.getChildren().addAll(menuDroit, restart, start, selections);
    }

    public Group setSelections() {
        Group retour = new Group();

        Rectangle boutonBowling = new Rectangle(80, 80);
        boutonBowling.setFill(new ImagePattern(bowling));
        boutonBowling.setStroke(Color.BLACK);
        boutonBowling.setX(1015);
        boutonBowling.setY(10);

        Rectangle boutonTennis = new Rectangle(80, 80);
        boutonTennis.setFill(new ImagePattern(tennis));
        boutonTennis.setStroke(Color.BLACK);
        boutonTennis.setX(1115);
        boutonTennis.setY(10);

        retour.getChildren().addAll(boutonBowling, boutonTennis);

        boutonBowling.setOnMouseClicked(event -> {
            for (int i = 0; i < 2; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonBowling.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[0] = true;
        });

        boutonTennis.setOnMouseClicked(event -> {
            for (int i = 0; i < 2; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonTennis.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[1] = true;
        });
        return retour;
    }

    public void resetScene() {
        timeline.stop();
        balles.clear();
        planInclines.clear();
        objets.getChildren().clear();
        numBalles = 0;
        numPlanInclines = 0;
        numObjets = 0;
        pause = true;
    }
}