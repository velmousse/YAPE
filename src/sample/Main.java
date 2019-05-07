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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Dynamiques.Bowling;
import sample.Objets.Dynamiques.Tennis;
import sample.Objets.Fixes.ObjetFixe;
import sample.Objets.Fixes.PlanDroit;
import sample.Objets.Fixes.PlanIncline;
import sample.Objets.Flags.Fin;
import sample.Objets.Flags.Largage;
import sample.Objets.GenerateurNiveaux;

import java.util.ArrayList;

import static java.lang.System.currentTimeMillis;

public class Main extends Application {

    private final Boolean[] selection = new Boolean[6];
    private Timeline timeline;
    private Image bowling = new Image("file:ressources/bowling.png");
    private Image tennis = new Image("file:ressources/tennis.png");
    private Image wood = new Image("file:ressources/wood.jpg");
    private Scene scene;
    private Group group, objets, flags, uInterface, labels;
    private boolean pause = true;
    private int numBalles = 0, numFixes = 0, numObjets = 0;
    private int[] limites = new int[6];
    private ArrayList<Balle> balles = new ArrayList<>();
    private ArrayList<ObjetFixe> fixes = new ArrayList<>();
    private GenerateurNiveaux genese;
    private boolean niveau = false;
    private Largage largage;
    private Fin fin;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < selection.length; i++) selection[i] = false;
        group = new Group();
        objets = new Group();
        flags = new Group();
        uInterface = new Group();
        labels = new Group();

        group.getChildren().addAll(flags, objets, uInterface);

        primaryStage.setTitle("");
        primaryStage.setX(375);
        primaryStage.setY(125);
        scene = new Scene(group, 1210, 710);


        Group root = new Group();
        Scene ecranDemarrage = new Scene(root, 600, 400);

        Text texte = new Text("La Machine Expérimentale");
        texte.setTextAlignment(TextAlignment.CENTER);
        texte.setX(140);
        texte.setY(100);
        texte.setFont(Font.font(30));

        Button newGame = new Button("Nouvelle partie");
        newGame.setTextAlignment(TextAlignment.CENTER);
        newGame.setPrefHeight(75);
        newGame.setPrefWidth(125);
        newGame.setTranslateX(35);
        newGame.setTranslateY(300);
        newGame.setOnAction(event -> {
            primaryStage.setX(375);
            primaryStage.setY(125);
            primaryStage.setScene(scene);
            for (int i = 0; i < limites.length - 2; i++)
                limites[i] = 999;
            limites[4] = 1;
            limites[5] = 1;
        });

        Button quitter = new Button("Quitter");
        quitter.setTextAlignment(TextAlignment.CENTER);
        quitter.setPrefHeight(75);
        quitter.setPrefWidth(125);
        quitter.setTranslateY(300);
        quitter.setTranslateX(435);
        quitter.setOnAction(event -> primaryStage.close());

        Button charger = new Button("Charger un niveau");
        charger.setTextAlignment(TextAlignment.CENTER);
        charger.setPrefHeight(75);
        charger.setPrefWidth(125);
        charger.setTranslateY(300);
        charger.setTranslateX(235);
        charger.setOnAction(event -> {
            niveau = true;
            genese = new GenerateurNiveaux(objets, limites, balles, fixes, bowling, tennis, wood, numBalles, numFixes, numObjets, largage, fin, flags);
            genese.niveau();
            numObjets = genese.getNumObjets();
            numBalles = genese.getNumBalles();
            numFixes = genese.getNumFixes();
            fin = genese.getFin();
            largage = genese.getLargage();
            primaryStage.setX(375);
            primaryStage.setY(125);
            primaryStage.setScene(scene);
        });

        root.getChildren().addAll(quitter, newGame, charger, texte);


        scene.setOnKeyPressed(event -> { //pause = false;
            if (event.getCode() == KeyCode.S)
                timeline.play();
        });


        scene.setOnMouseClicked(event -> {
            if ((event.getX() > 0 && event.getX() < 1000) /*&& pause*/) {
                if (selection[0] && limites[0] > 0) {
                    balles.add(new Bowling((float) event.getX(), (float) event.getY(), balles.size(), balles, bowling));
                    objets.getChildren().add(balles.get(numBalles).affichage());
                    limites[0]--;
                    numBalles++;
                    numObjets++;
                } else if (selection[1] && limites[1] > 0) {
                    balles.add(new Tennis((float) event.getX(), (float) event.getY(), balles.size(), balles, tennis));
                    objets.getChildren().add(balles.get(numBalles).affichage());
                    limites[1]--;
                    numBalles++;
                    numObjets++;
                } else if (selection[2] && limites[2] > 0) {
                    fixes.add(new PlanIncline((float) event.getX(), (float) event.getY(), fixes.size(), balles, wood));
                    objets.getChildren().add(fixes.get(numFixes).affichage());
                    limites[2]--;
                    numFixes++;
                    numObjets++;
                } else if (selection[3] && limites[3] > 0) {
                    fixes.add(new PlanDroit((float) event.getX(), (float) event.getY(), fixes.size(), balles, wood));
                    objets.getChildren().add(fixes.get(numFixes).affichage());
                    limites[3]--;
                    numFixes++;
                    numObjets++;
                } else if (selection[4] && limites[4] > 0) {
                    largage = new Largage(balles, (float) event.getX() - 175/2, (float) event.getY() - 175/2);
                    flags.getChildren().add(largage.affichage());
                    limites[4]--;
                } else if (selection[5] && limites[5] > 0) {
                    fin = new Fin(balles, (float) event.getX() - 175/2, (float) event.getY() - 175/2);
                    flags.getChildren().add(fin.affichage());
                    limites[5]--;
                }

                for (int i = 0; i < limites.length; i++) {
                    Label label = (Label) labels.getChildren().get(i);
                    label.setText(Integer.toString(limites[i]));
                }
            }
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(ecranDemarrage);

        timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (numObjets > 0) {
                    int nombreDeBalles = 0, nombreDePlans = 0;
                    for (int i = 0; i < numObjets; i++) {
                        Object objet = objets.getChildren().get(i);
                        if (objet instanceof Ellipse) {
                            balles.get(nombreDeBalles).setTempsinitial(currentTimeMillis() / 1000.00);
                            balles.get(nombreDeBalles).mouvement();
                            balles.get(nombreDeBalles).collision();
                            objets.getChildren().set(i, balles.get(nombreDeBalles).affichage());
                            if (fin != null) {
                                if (fin.collision(balles.get(nombreDeBalles).affichage())) {
                                    timeline.stop();
                                    Alert reussite = new Alert(Alert.AlertType.INFORMATION);
                                    reussite.setTitle("YAY!");
                                    reussite.setHeaderText("Complétion du niveau");
                                    reussite.setContentText("Vous avez complété le niveau");
                                    reussite.show();
                                    resetScene();
                                }
                            }
                            nombreDeBalles++;
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
        ), new KeyFrame(Duration.millis(5)));

        timeline.setCycleCount(Animation.INDEFINITE);
        primaryStage.show();
        setInterface();
    }

    private void setInterface() {
        Rectangle menuDroit = new Rectangle(300, 900, Color.LIGHTGRAY);
        menuDroit.setX(1000);
        menuDroit.setY(0);

        Button restart = new Button("Restart");
        restart.setTranslateX(1125);
        restart.setTranslateY(665);
        restart.setScaleX(1.5);
        restart.setScaleY(1.5);
        restart.setOnAction(event -> {
            resetScene();
        });

        Button start = new Button("Start");
        start.setTranslateX(1020);
        start.setTranslateY(665);
        start.setScaleX(1.5);
        start.setScaleY(1.5);
        start.setOnAction(event -> {
            //if (largage != null) {
                timeline.play();
                pause = false;
            //}
        });

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                1015.0 + 25, 190.0 - 20,
                1015.0 + 25, 150.0 - 20,
                1055.0 + 25, 190.0 - 20});
        triangle.setFill(new ImagePattern(wood));

        Group selections = setSelections();
        labels = setLabels();
        uInterface.getChildren().addAll(menuDroit, restart, start, selections, labels, triangle);
    }

    private Group setLabels() {
        Group retour = new Group();

        Label textBowling = new Label(Integer.toString(limites[0]));
        textBowling.setTranslateX(1017);
        textBowling.setTranslateY(75);
        textBowling.setTextFill(Color.FUCHSIA);

        Label textTennis = new Label(Integer.toString(limites[1]));
        textTennis.setTranslateX(1117);
        textTennis.setTranslateY(75);
        textTennis.setTextFill(Color.FUCHSIA);

        Label textPlanIncline = new Label(Integer.toString(limites[2]));
        textPlanIncline.setTranslateX(1017);
        textPlanIncline.setTranslateY(175);
        textPlanIncline.setTextFill(Color.FUCHSIA);

        Label textPlanDroit = new Label(Integer.toString(limites[3]));
        textPlanDroit.setTranslateX(1117);
        textPlanDroit.setTranslateY(175);
        textPlanDroit.setTextFill(Color.FUCHSIA);

        Label textLargage = new Label(Integer.toString(limites[4]));
        textLargage.setTranslateX(1017);
        textLargage.setTranslateY(275);
        textLargage.setTextFill(Color.FUCHSIA);

        Label textFin = new Label(Integer.toString(limites[5]));
        textFin.setTranslateX(1117);
        textFin.setTranslateY(275);
        textFin.setTextFill(Color.FUCHSIA);

        retour.getChildren().addAll(textBowling, textTennis, textPlanIncline, textPlanDroit, textLargage, textFin);
        return retour;
    }

    private Group setSelections() {
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
        boutonPlanIncline.setFill(Color.LIGHTGRAY);
        boutonPlanIncline.setStroke(Color.BLACK);
        boutonPlanIncline.setX(1015);
        boutonPlanIncline.setY(110);

        Rectangle boutonPlanDroit = new Rectangle(80, 80);
        boutonPlanDroit.setFill(new ImagePattern(wood));
        boutonPlanDroit.setStroke(Color.BLACK);
        boutonPlanDroit.setX(1115);
        boutonPlanDroit.setY(110);

        Rectangle boutonLargage = new Rectangle(80, 80);
        boutonLargage.setFill(Color.LIGHTGREEN);
        boutonLargage.setStroke(Color.BLACK);
        boutonLargage.setX(1015);
        boutonLargage.setY(210);

        Rectangle boutonFin = new Rectangle(80, 80);
        boutonFin.setFill(Color.INDIANRED);
        boutonFin.setStroke(Color.BLACK);
        boutonFin.setX(1115);
        boutonFin.setY(210);

        retour.getChildren().addAll(boutonBowling, boutonTennis, boutonPlanIncline, boutonPlanDroit, boutonLargage, boutonFin);

        int nbOptions = 6;
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

        boutonLargage.setOnMouseClicked(event -> {
            for (int i = 0; i < nbOptions; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonLargage.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[4] = true;
        });

        boutonFin.setOnMouseClicked(event -> {
            for (int i = 0; i < nbOptions; i++) {
                Rectangle selection = (Rectangle) retour.getChildren().get(i);
                selection.setStroke(Color.BLACK);
            }
            boutonFin.setStroke(Color.GREEN);
            for (int i = 0; i < selection.length; i++) selection[i] = false;
            selection[5] = true;
        });

        return retour;
    }

    private void resetScene() {
        timeline.stop();
        balles.clear();
        fixes.clear();
        objets.getChildren().clear();
        flags.getChildren().clear();
        if (niveau) {
            genese.resetGenerateur();
            genese.niveau();
            numObjets = genese.getNumObjets();
            numBalles = genese.getNumBalles();
            numFixes = genese.getNumFixes();
            fin = genese.getFin();
            largage = genese.getLargage();
        } else {
            numBalles = 0;
            numFixes = 0;
            numObjets = 0;
            for (int i = 0; i < limites.length - 2; i++)
                limites[i] = 999;
            limites[4] = 1;
            limites[5] = 1;
        }
        pause = true;
    }
}