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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Dynamiques.Bowling;
import sample.Objets.Fixes.ObjetFixe;
import sample.Objets.Fixes.PlanDroit;
import sample.Objets.Fixes.PlanIncline;
import sample.Objets.Dynamiques.Tennis;
import sample.Objets.Objet;
import sun.plugin2.util.ColorUtil;

import java.util.ArrayList;

public class Main extends Application {
    private final Boolean[] selection = new Boolean[4];
    public ArrayList<PlanIncline> planInclines = new ArrayList<>();
    public ArrayList<PlanDroit> planDroits = new ArrayList<>();
    private Timeline timeline;
    private Image bowling = new Image("file:ressources/bowling.png");
    private Image tennis = new Image("file:ressources/tennis.png");
    private Scene scene;
    private Group group, objets, uinterface;
    private boolean pause = true;
    private int numBalles = 0, numPlanInclines = 0, numObjets = 0, numPlanDroits = 0;
    private ArrayList<Balle> balles = new ArrayList<>();

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

        scene.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.S)
            timeline.play(); });


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
                } else if (selection[2]) { planInclines.add(new PlanIncline((float) event.getX(), (float) event.getY() - 40, (float) event.getX() + 40, (float) event.getY(), (float) event.getX(), (float) event.getY()));
                    objets.getChildren().add(planInclines.get(numPlanInclines).affichage());
                    for (Balle balle : balles) {
                        for (int i = 0; i < planInclines.size(); i++) {
                            balle.getObjetsfixes().add(planInclines.get(i));}}
                    numPlanInclines++;
                    numObjets++;
                } else if (selection[3]) {
                    planDroits.add(new PlanDroit((float) event.getX() - 30, (float) event.getY() - 20, (float) event.getX() + 30, (float) event.getY() - 20, (float) event.getX() + 30, (float) event.getY() + 20, (float) event.getX() - 30, (float) event.getY() + 20));
                    objets.getChildren().add(planDroits.get(numPlanDroits).affichage());
                    for (Balle balle : balles) {
                        for (int i = 0; i < planDroits.size(); i++) {
                            balle.getObjetsfixes().add(planDroits.get(i));}}
                    numPlanDroits++;
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
                        int nombreDePlansDroits= 0;
                        for (int i = 0; i < numObjets; i++) {
                            Object objet = objets.getChildren().get(i);
                            if (objet instanceof Ellipse) {
                                balles.get(nombreDeBalles).mouvement();
                                balles.get(nombreDeBalles).collision();
                                objets.getChildren().set(i, balles.get(nombreDeBalles++).affichage());
                            }
                            else if (objet instanceof Polygon) {
                                if(((Polygon) objet).getPoints().size()==6)
                                objets.getChildren().set(i, planInclines.get(nombreDePlansInclines++).affichage());
                                if(((Polygon) objet).getPoints().size()==8)
                                    objets.getChildren().set(i, planDroits.get(nombreDePlansDroits++).affichage());
                            }
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
        planInclines.clear();
        planDroits.clear();
        objets.getChildren().clear();
        numBalles = 0;
        numPlanInclines = 0;
        numPlanDroits = 0;
        numObjets = 0;
        pause = true;
    }
}