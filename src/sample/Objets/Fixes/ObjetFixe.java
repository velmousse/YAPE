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
                    double vecteurdecollision = vecteurcollisionneur((x + 20), (y + 20), -40, -40, dynamique.getX(), dynamique.getY());

                    //coté horizontal

                    double hvecteurdecollision = vecteurcollisionneur((x - 20), (y + 20), -40, 0, dynamique.getX(), dynamique.getY());

                    //cote vertical
                    double vvecteurdecollision = vecteurcollisionneur((x - 20), (y - 20), 0, -40, dynamique.getX(), dynamique.getY());


                    if (vvecteurdecollision - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20){
                        dynamique.setVx(-1 * dynamique.getVx());
                    if(dynamique.getVx()==0)
                    {dynamique.setVx(-1*spring);}
                    }
                    else if (hvecteurdecollision - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20))
                        dynamique.setVyi(-1 * dynamique.getVyi());
                    else if (vecteurdecollision - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20)) {
                        if (dynamique.getVx() == 0) {
                            double vitessex=dynamique.getVx();
                            vitessex+=spring;
                            dynamique.setVx(vitessex);
                        } else dynamique.setVyi(dynamique.getVyi() * -.5);
                    }
                    if (dynamique.getVyi() <= 0 && dynamique.getVyi() > -.5 && vecteurdecollision > dynamique.getRayon()) {
                        dynamique.setVyf(0);
                        dynamique.setY(dynamique.getY() - 0.2);
                    }

                    break;
                case 1:

                    if (!dynamique.isTurned()) {

                        double vecteurdecollisionVerticalGauche = vecteurcollisionneur((x - 40), (y - 20), 0, -40, dynamique.getX(), dynamique.getY());
                        double vecteurdecollisionVerticalDroit = vecteurcollisionneur((x + 40), (y - 20), 0, 40, dynamique.getX(), dynamique.getY());
                        double vecteurdeCollisionHorizontalHaut = vecteurcollisionneur((x - 40), (y - 20), 80, 0, dynamique.getX(), dynamique.getY());
                        double vecteurdeCollisionHorizontalBas = vecteurcollisionneur((x - 40), (y + 20), 80, 0, dynamique.getX(), dynamique.getY());


                        if ((vecteurdecollisionVerticalGauche - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20))
                            dynamique.setVx(-1 * spring);
                        if (vecteurdecollisionVerticalDroit-dynamique.getRayon()<= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20)
                            dynamique.setVx( spring);
                        if ((vecteurdeCollisionHorizontalHaut - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 40 && dynamique.getX() >= x - 40) ) {
                            dynamique.setVyi(-1 * dynamique.getVyi() * spring);
                            if (dynamique.getVyi() <= 0 && dynamique.getVyi() > -0.1 && dynamique.getY() < y&&dynamique.getVx()==0) {
                                dynamique.setVyf(0);
                                dynamique.setDy(0);
                                dynamique.setTempsinitial(dynamique.getTempsinitial()+10);
                                dynamique.setY(y - (20 + dynamique.getRayon() * 2));
                            }
                        }
                        if(vecteurdeCollisionHorizontalBas - 7 <= dynamique.getRayon() && dynamique.getX() <= x + 40 && dynamique.getX() >= x - 40)
                            dynamique.setVyi(-1 * dynamique.getVyi() * spring);
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

    public double vecteurcollisionneur(double débutvecteurux, double debutvecteuruy, double longueurx, double longueury, double rayonx, double rayony) {
        double vvecteurux = rayonx - débutvecteurux;
        double vvecteuruy = rayony - debutvecteuruy;
        double vproduitscalaire = vvecteurux * longueurx + (vvecteuruy * longueury);
        double vnorme = (longueurx * longueurx) + (longueury * longueury);
        double vvecteurresultantx = vproduitscalaire / vnorme * longueurx;
        double vvecteurresultanty = vproduitscalaire / vnorme * longueury;
        double vnormevecteurresultant = Math.sqrt((vvecteurresultantx * vvecteurresultantx) + (vvecteurresultanty * vvecteurresultanty));
        double vnormevecteuru = Math.sqrt((vvecteurux * vvecteurux) + (vvecteuruy * vvecteuruy));
        return Math.sqrt((vnormevecteuru * vnormevecteuru) - (vnormevecteurresultant * vnormevecteurresultant));
    }

}