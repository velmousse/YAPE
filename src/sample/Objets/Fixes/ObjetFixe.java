package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Objet;

import java.util.ArrayList;

public class ObjetFixe extends Objet{
    protected int type; //0 PlanIncline, 1 PlanDroit
    protected double spring;
    protected ImagePattern image; //À voir plus tard

    public void collision() {
        for (int i = id + 1; i < autres.size(); i++) {

        }
    }

    public Polygon affichage() {
        Polygon retour = null;
        switch (type) {
            case 0:

                break;
            case 1:
                retour = new Polygon(x - 20, y + 10, x + 20, y - 10);
                retour.setFill(Color.BLACK);
                break;
        }
        return retour;
    }
}