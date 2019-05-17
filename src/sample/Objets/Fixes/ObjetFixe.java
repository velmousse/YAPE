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
    protected ImagePattern image;

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
                    double vecteurCoinHautGauche= vecteurcollisionneur((x-21),(y-19),6,6,dynamique.getX(),dynamique.getY());
                    if(vecteurCoinHautGauche-dynamique.getRayon()<=dynamique.getRayon()&&dynamique.getY()<=y-19&&dynamique.getY()>=y-15)////BOGUE!! :(
                        dynamique.setVyi(dynamique.getVyi()*-1);
                    if (vvecteurdecollision - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20) {
                        dynamique.setVx(-1 * dynamique.getVx());
                        if (dynamique.getVx() == 0) {
                            dynamique.setVx(-1 * spring);
                        }
                    }
                    if (hvecteurdecollision - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20))
                        dynamique.setVyi(-1 * dynamique.getVyi());
                    if (vecteurdecollision - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20)) {
                        double vitessex = dynamique.getVx();
                        vitessex += -(Math.cos(.5 * dynamique.getGravite1()));
                        dynamique.setVx(vitessex);
                        dynamique.setVyi(dynamique.getVyi() * -.5);
                    }
                    if (dynamique.getVyi() <= 0 && dynamique.getVyi() > -.5 && vecteurdecollision > dynamique.getRayon()) {
                        dynamique.setVyf(0);
                        dynamique.setY(dynamique.getY() - 0.2);
                    }


                    break;
                case 1:


                        double vecteurdecollisionVerticalGauche = vecteurcollisionneur((x - 40), (y - 20), 0, -40, dynamique.getX(), dynamique.getY());
                        double vecteurdecollisionVerticalDroit = vecteurcollisionneur((x + 40), (y - 20), 0, 40, dynamique.getX(), dynamique.getY());
                        double vecteurdeCollisionHorizontalHaut = vecteurcollisionneur((x - 40), (y - 20), 80, 0, dynamique.getX(), dynamique.getY());
                        double vecteurdeCollisionHorizontalBas = vecteurcollisionneur((x - 40), (y + 20), 80, 0, dynamique.getX(), dynamique.getY());

                        if ((vecteurdecollisionVerticalGauche - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20))
                            dynamique.setVx(-1 * dynamique.getSpring());
                        if (vecteurdecollisionVerticalDroit - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20)
                            dynamique.setVx(dynamique.getSpring());
                        if ((vecteurdeCollisionHorizontalHaut - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 40 && dynamique.getX() >= x - 40)) {
                            dynamique.setVyi(-1 * dynamique.getVyi() * spring);

                            if (dynamique.getVyf() <= 0 && dynamique.getVyf() > -0.0001 && dynamique.getY() < y && dynamique.getVx() == 0 && dynamique.getVx() <= 0) {
                                dynamique.setVyf(0);
                                dynamique.setDy(0);
                                dynamique.setY(y - (21.00 + dynamique.getRayon()));
                            }
                        }
                        if (vecteurdeCollisionHorizontalBas - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 40 && dynamique.getX() >= x - 40)
                            dynamique.setVyi(-1 * dynamique.getVyi() * spring);

                        if(((vecteurdecollisionVerticalGauche - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20)&&(vecteurdeCollisionHorizontalHaut - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 40 && dynamique.getX() >= x - 40)))
                        {dynamique.setVx(-1*dynamique.getVx());}



                    break;

                case 2:

                    //coté oblique
                    double vecteurdecollision2 = vecteurcollisionneur((x + 20), (y - 20), 40, -40, dynamique.getX(), dynamique.getY());
                    //coté horizontal
                    double hvecteurdecollision2 = vecteurcollisionneur((x - 20), (y + 20), -40, 0, dynamique.getX(), dynamique.getY());
                    //cote vertical
                    double vvecteurdecollision2 = vecteurcollisionneur((x + 20), (y + 20), 0, -40, dynamique.getX(), dynamique.getY());

                    if (vvecteurdecollision2 - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getY() <= y + 20 && dynamique.getY() >= y - 20) {
                        dynamique.setVx(-1 * dynamique.getVx());
                        if (dynamique.getVx() == 0) {
                            dynamique.setVx(-1 * spring);
                        }
                    }
                    if (hvecteurdecollision2 - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20))
                        dynamique.setVyi(-1 * dynamique.getVyi());
                    if (vecteurdecollision2 - dynamique.getRayon() <= dynamique.getRayon() && dynamique.getX() <= x + 20 && dynamique.getX() >= x - (20)) {
                        double vitessex = dynamique.getVx();
                        vitessex -= -(Math.cos(.5 * dynamique.getGravite1()));
                        dynamique.setVx(vitessex);
                        dynamique.setVyi(dynamique.getVyi() * .5);
                    }
                    if (dynamique.getVyi() <= 0 && dynamique.getVyi() > -.5 && vecteurdecollision2 > dynamique.getRayon()) {
                        dynamique.setVyf(0);
                        dynamique.setY(dynamique.getY() - 0.2);
                    }

                break;

                case 3:
                    Rectangle zoneDaction= new Rectangle(x-40,y-100,x+40,y-20);

                    double vecteurdecollisionVerticalGauche2 = vecteurcollisionneur((x - 40), (y - 100), 0, -100, dynamique.getX(), dynamique.getY());
                    double vecteurdecollisionVerticalDroit2 = vecteurcollisionneur((x + 40), (y - 20), 0, 100, dynamique.getX(), dynamique.getY());
                    double vecteurdeCollisionHorizontalHaut2 = vecteurcollisionneur((x - 40), (y - 100), 80, 0, dynamique.getX(), dynamique.getY());
                    double vecteurdeCollisionHorizontalBas2 = vecteurcollisionneur((x - 40), (y - 20), 80, 0, dynamique.getX(), dynamique.getY());


                    if(zoneDaction.contains(dynamique.getX(),dynamique.getY())&&dynamique.getX()+dynamique.getRayon()>=x-40&&dynamique.getX()+dynamique.getRayon()<=x+40&&dynamique.getY()+dynamique.getRayon()<y-20&&dynamique.getY()+dynamique.getRayon()>y-100)
                    {
                        if(!dynamique.isGraviteinversee()){
                            dynamique.inversergravite();
                        }
                    }
                    else{
                        if(dynamique.isGraviteinversee()){
                            dynamique.inversergravite();
                            dynamique.setGraviteinversee(false);

                        }
                    }
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
            case 2:
                retour.getPoints().addAll(new Double[]{
                        x + 20, y - 20,
                        x + 20, y + 20,
                        x - 20, y + 20});
                break;
            case 3 :
                retour.getPoints().addAll(new Double[]{
                        x - 40, y - 20,
                        x - 40, y + 20,
                        x + 40, y + 20,
                        x + 40, y - 20});
                break;
        }

        retour.setFill(image);
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