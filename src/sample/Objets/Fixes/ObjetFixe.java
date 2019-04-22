package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Objet;

import java.util.ArrayList;

public class ObjetFixe extends Objet {
    protected int type; //0 PlanIncline, 1 PlanDroit
    protected double spring;
    protected ImagePattern image; //À voir plus tard

    public void collision() {
        for (int i = 0; i < autres.size(); i++) {
            switch (type) {
                case 0:

                    break;
                case 1:
                    Balle dynamique = autres.get(i);
                    Polygon plan = this.affichage();
                    Ellipse balle = dynamique.affichage();

                    if (balle.intersects(plan.getBoundsInLocal())) {
                        if ((dynamique.getX() <= x - (40 + dynamique.getRayon()) || dynamique.getX() >= x + (40 + dynamique.getRayon())) && (dynamique.getY() <= y + (20 + dynamique.getRayon()) && dynamique.getY() >= y - (20+dynamique.getRayon())))
                            dynamique.setVx(-1);
                        if ((dynamique.getX() >= x - (40 + dynamique.getRayon()) && dynamique.getX() <= x + (40 + dynamique.getRayon())) && (dynamique.getY() >= y + (20 + dynamique.getRayon()) || dynamique.getY() <= y - (20 + dynamique.getRayon())))
                            dynamique.setVy(-1);
                    }
                    break;
            }
        }
    }

    public Polygon affichage() {
        Polygon retour = new Polygon();
        switch (type) {
            case 0:
                retour.getPoints().addAll(new Double[]{
                        x - 20, y + 20,
                        x - 20, y - 20,
                        x + 20, y + 20});
                break;
            case 1:
                retour.getPoints().addAll(new Double[]{
                        x - 40, y - 20,
                        x - 40, y + 20,
                        x + 40, y + 20,
                        x + 40, y - 20});
                break;
        }
        return retour;
    }
}