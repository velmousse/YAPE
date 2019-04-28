//Copyright @ Vincent Dufour et Jean-Phillippe Pedneault - 2019
//All rights reserved

package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Dynamiques.Bowling;
import sample.Objets.Dynamiques.Tennis;
import sample.Objets.Fixes.ObjetFixe;
import sample.Objets.Fixes.PlanDroit;
import sample.Objets.Fixes.PlanIncline;

import java.util.ArrayList;

public class Main extends Application {
    private final Boolean[] selection = new Boolean[4];

    private Timeline timeline;
    private Image bowling = new Image("file:ressources/bowling.png");
    private Image tennis = new Image("file:ressources/tennis.png");
    private Scene scene;
    private Group group, objets, uinterface;
    private boolean pause = true;
    private int numBalles = 0, numFixes = 0, numObjets = 0;
    private ArrayList<Balle> balles = new ArrayList<>();
    private ArrayList<ObjetFixe> fixes = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < selection.length; i++) selection[i] = false;
        group = new Group();
        objets = new Group();
        uinterface = new Group();

        group.getChildren().addAll(objets, uinterface);
        Group root = new Group();
        group.setTranslateX(0);
        group.setTranslateY(0);

        Scene ecrandemarrage = new Scene(root, 600, 400);
        primaryStage.setTitle("");
        scene = new Scene(group, 1210, 810);


        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.S)
                timeline.play();
        });


        scene.setOnMouseClicked(event -> {  //Ne pas oublier de vérifier s'il y a un imbriquement
            if ((event.getX() > 0 && event.getX() < 1000)) { //&& pause
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
                    fixes.add(new PlanIncline((float) event.getX(), (float) event.getY(), fixes.size(), balles));
                    objets.getChildren().add(fixes.get(numFixes).affichage());
                    numFixes++;
                    numObjets++;
                } else if (selection[3]) {
                    fixes.add(new PlanDroit((float) event.getX(), (float) event.getY(), fixes.size(), balles));
                    objets.getChildren().add(fixes.get(numFixes).affichage());
                    numFixes++;
                    numObjets++;
                }
            }
        });


        Button newGame = new Button("Nouvelle Partie");
        newGame.setPrefHeight(50);
        newGame.setPrefWidth(100);
        newGame.setTranslateX(30);
        newGame.setTranslateY(250);
        newGame.setOnAction(event -> {primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setHeight(810);
        primaryStage.setWidth(1210);});

        Button quitter = new Button("Quitter");
        quitter.setPrefHeight(50);
        quitter.setPrefWidth(100);
        quitter.setTranslateY(250);
        quitter.setTranslateX(370);
        quitter.setOnAction(event -> primaryStage.close());

        Button charger = new Button("Charger un niveau");


        root.getChildren().addAll(quitter, newGame);

        setInterface();

        primaryStage.setResizable(false);
        primaryStage.setScene(ecrandemarrage);


        timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (numObjets > 0) {
                    int nombreDeBalles = 0, nombreDePlans = 0;
                    for (int i = 0; i < numObjets; i++) {
                        Object objet = objets.getChildren().get(i);
                        if (objet instanceof Ellipse) {
                            balles.get(nombreDeBalles).mouvement();
                            balles.get(nombreDeBalles).collision();
                            objets.getChildren().set(i, balles.get(nombreDeBalles++).affichage());
                        } else if (objet instanceof Polygon) {
                            fixes.get(nombreDePlans).collision();
                            if (((Polygon) objet).getPoints().size() == 6) {
                                objets.getChildren().set(i, fixes.get(nombreDePlans++).affichage());
                            } else if (((Polygon) objet).getPoints().size() == 8) {
                                objets.getChildren().set(i, fixes.get(nombreDePlans++).affichage());
                            }
                        }
                    }
                }
            }
        }
        ), new KeyFrame(Duration.millis(15)));
        timeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.show();
    }

    public void setInterface() {
        Rectangle menuDroit = new Rectangle(300, 900, Color.LIGHTGRAY);
        menuDroit.setX(1000);
        menuDroit.setY(0);

        Button restart = new Button("Restart");
        restart.setTranslateX(1125);
        restart.setTranslateY(765);
        restart.setScaleX(1.5);
        restart.setScaleY(1.5);
        restart.setOnAction(event -> {
            resetScene();
        });

        Button start = new Button("Start");
        start.setTranslateX(1020);
        start.setTranslateY(765);
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

        Rectangle boutonPlanIncline = new Rectangle(80, 80);
        boutonPlanIncline.setFill(Color.ORANGE);
        boutonPlanIncline.setStroke(Color.BLACK);
        boutonPlanIncline.setX(1015);
        boutonPlanIncline.setY(110);

        Rectangle boutonPlanDroit = new Rectangle(80, 80);
        boutonPlanDroit.setFill(Color.RED);
        boutonPlanDroit.setStroke(Color.BLACK);
        boutonPlanDroit.setX(1115);
        boutonPlanDroit.setY(110);

        retour.getChildren().addAll(boutonBowling, boutonTennis, boutonPlanIncline, boutonPlanDroit);

        int nbOptions = 4;
        boutonBowling.setOnMouseClicked(event -> {
            for (int i = 0; i < nbOptions; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonBowling.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[0] = true;
        });

        boutonTennis.setOnMouseClicked(event -> {
            for (int i = 0; i < nbOptions; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonTennis.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[1] = true;
        });

        boutonPlanIncline.setOnMouseClicked(event -> {
            for (int i = 0; i < nbOptions; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonPlanIncline.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[2] = true;
        });

        boutonPlanDroit.setOnMouseClicked(event -> {
            for (int i = 0; i < nbOptions; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonPlanDroit.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[3] = true;
        });

        return retour;
    }

    public void resetScene() {
        timeline.stop();
        balles.clear();
        fixes.clear();
        objets.getChildren().clear();
        numBalles = 0;
        numFixes = 0;
        numObjets = 0;
        pause = true;
    }
}