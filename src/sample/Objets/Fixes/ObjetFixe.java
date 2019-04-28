package sample.Objets.Fixes;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
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

                    //coté oblique
                    double vecteurux = dynamique.getX() - (x + 20);
                    double vecteuruy = dynamique.getY() - (y + 20);
                    double vecteurvx = -40;
                    double vecteurvy = -40;
                    double produitscalaire = vecteurux * vecteurvx + (vecteuruy * vecteurvy);
                    double norme = (vecteurvx * vecteurvx) + (vecteurvy * vecteurvy);
                    double vecteurresultantx = produitscalaire / norme * vecteurvx;
                    double vecteurresultanty = produitscalaire / norme * vecteurvy;
                    double normevecteurresultant = Math.sqrt((vecteurresultantx * vecteurresultantx) + (vecteurresultanty * vecteurresultanty));
                    double normevecteuru = Math.sqrt((vecteurux * vecteurux) + (vecteuruy * vecteuruy));
                    double vecteurdecollision = Math.sqrt((normevecteuru * normevecteuru) - (normevecteurresultant * normevecteurresultant));

                    //coté horizontal

                    double hvecteurux = dynamique.getX() - (x - 20);
                    double hvecteuruy = (y + 20) - dynamique.getY();
                    double hvecteurvx = -40;
                    double hvecteurvy = 0;
                    double hproduitscalaire = hvecteurux * hvecteurvx + (hvecteuruy * hvecteurvy);
                    double hnorme = (hvecteurvx * hvecteurvx) + (hvecteurvy * hvecteurvy);
                    double hvecteurresultantx = hproduitscalaire / hnorme * hvecteurvx;
                    double hvecteurresultanty = hproduitscalaire / hnorme * hvecteurvy;
                    double hnormevecteurresultant = Math.sqrt((hvecteurresultantx * hvecteurresultantx) + (hvecteurresultanty * hvecteurresultanty));
                    double hnormevecteuru = Math.sqrt((hvecteurux * hvecteurux) + (hvecteuruy * hvecteuruy));
                    double hvecteurdecollision = Math.sqrt((hnormevecteuru * hnormevecteuru) - (hnormevecteurresultant * hnormevecteurresultant));

                    //cote vertical

                    double vvecteurux = dynamique.getX() - (x - 20);
                    double vvecteuruy = dynamique.getY() - (y - 20);
                    double vvecteurvx = 0;
                    double vvecteurvy = -40;
                    double vproduitscalaire = vvecteurux * vvecteurvx + (vvecteuruy * vvecteurvy);
                    double vnorme = (vvecteurvx * vvecteurvx) + (vvecteurvy * vvecteurvy);
                    double vvecteurresultantx = vproduitscalaire / vnorme * vvecteurvx;
                    double vvecteurresultanty = vproduitscalaire / vnorme * vvecteurvy;
                    double vnormevecteurresultant = Math.sqrt((vvecteurresultantx * vvecteurresultantx) + (vvecteurresultanty * vvecteurresultanty));
                    double vnormevecteuru = Math.sqrt((vvecteurux * vvecteurux) + (vvecteuruy * vvecteuruy));
                    double vvecteurdecollision = Math.sqrt((vnormevecteuru * vnormevecteuru) - (vnormevecteurresultant * vnormevecteurresultant));


                    if (vvecteurdecollision - 7 <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20)
                        dynamique.setVx(-1 * dynamique.getVx());
                    if (hvecteurdecollision - 7 <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20))
                        dynamique.setVyi(-1 * dynamique.getVyi());
                    if (vecteurdecollision - 7 <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20)) {
                        if (dynamique.getVx() == 0) {
                            dynamique.setVx(1);
                        } else dynamique.setVyi(dynamique.getVyi() * -1);
                    }
                    if (dynamique.getVyi() <= 0 && dynamique.getVyi() > -.5 && vecteurdecollision > dynamique.getRayon()) {
                        dynamique.setVyf(0);
                        dynamique.setY(dynamique.getY() - 0.2);
                    }


                    break;
                case 1:

                    if (!dynamique.isTurned()) {
                        if (balle.intersects(plan.getBoundsInLocal())) {
                            if ((dynamique.getX() <= x - (40 + dynamique.getRayon()) || dynamique.getX() >= x + (40 + dynamique.getRayon())) && (dynamique.getY() <= y + (20 + dynamique.getRayon()) && dynamique.getY() >= y - (20 + dynamique.getRayon())))
                                dynamique.setVx(-1 * spring);
                            else if ((dynamique.getX() >= x - (40 + dynamique.getRayon()) && dynamique.getX() <= x + (40 + dynamique.getRayon())) && (dynamique.getY() >= y + (20 + dynamique.getRayon()) || dynamique.getY() <= y - (20 + dynamique.getRayon())))
                                dynamique.setVyi(-1 * dynamique.getVyi() * spring);

                            if (dynamique.getVyf() <= 0 && dynamique.getVyf() > -5000) {
                                dynamique.setVyf(0);
                                dynamique.setDy(0);

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