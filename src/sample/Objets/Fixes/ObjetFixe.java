package sample.Objets.Fixes;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Objet;

import java.util.ArrayList;

public class ObjetFixe extends Objet {
    protected int type; //0 PlanIncline, 1 PlanDroit
    protected double spring;
    protected ImagePattern image; //À voir plus tard

    public void collision() {
        for (int i = 0; i < autres.size(); i++) {
            Balle dynamique = autres.get(i);
            Polygon plan = this.affichage();
            Ellipse balle = dynamique.affichage();


            switch (type) {
                case 0:
                    Shape inter = Shape.intersect(balle,plan);

                    if (balle.intersects(plan.getBoundsInLocal())) {
                        if ((dynamique.getX() <= x - (20 + dynamique.getRayon()) || dynamique.getX() >= x + (20 + dynamique.getRayon())) && (dynamique.getY() <= y + (20 + dynamique.getRayon()) && dynamique.getY() >= y - (20 + dynamique.getRayon())))
                            dynamique.setVx(-1 * spring);
                        else if ((dynamique.getX() >= x - (20 + dynamique.getRayon()) && dynamique.getX() <= x + (20 + dynamique.getRayon())) && (dynamique.getY() >= y + (20 + dynamique.getRayon()) || dynamique.getY() <= y - (20 + dynamique.getRayon())))
                            dynamique.setVy(-1 * spring);

                        if (dynamique.getVy() <= 0 && dynamique.getVy() > -0.5) {
                            dynamique.setVy(0);
                            dynamique.setY(dynamique.getY() - 0.2);
                        }
                    }
                    break;
                case 1:

                    if (!dynamique.isTurned()) {
                        if (balle.intersects(plan.getBoundsInLocal())) {
                            if ((dynamique.getX() <= x - (40 + dynamique.getRayon()) || dynamique.getX() >= x + (40 + dynamique.getRayon())) && (dynamique.getY() <= y + (20 + dynamique.getRayon()) && dynamique.getY() >= y - (20 + dynamique.getRayon())))
                                dynamique.setVx(-1 * spring);
                            else if ((dynamique.getX() >= x - (40 + dynamique.getRayon()) && dynamique.getX() <= x + (40 + dynamique.getRayon())) && (dynamique.getY() >= y + (20 + dynamique.getRayon()) || dynamique.getY() <= y - (20 + dynamique.getRayon())))
                                dynamique.setVyi(-1 * dynamique.getVyi());

                            if (dynamique.getVyf() <= 0 && dynamique.getVyf() > -0.5) {
                                dynamique.setVyi(0);
                                dynamique.setVyf(0);
                                dynamique.setY(dynamique.getY() - 0.2);
                            }
                        }
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