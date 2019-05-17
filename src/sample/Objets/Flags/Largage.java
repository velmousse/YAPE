package sample.Objets.Flags;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import sample.Objets.Dynamiques.Balle;

import java.util.ArrayList;


public class Largage {
    ArrayList<Balle> balles;
    float x, y;

    public Largage(ArrayList<Balle> _balles, float _x, float _y) {
        balles = _balles;
        x = _x;
        y = _y;
    }

    public boolean collision(Ellipse balle) {
        boolean retour = false;

        double hvecteur = vecteurcollisionneur(x, y, 0, -176, balle.getCenterX(), balle.getCenterY());
        double vecteurdecollisionVerticalDroit = vecteurcollisionneur((x + 176), (y + 176), 0, 176, balle.getCenterX(), balle.getCenterY());
        double vecteurdeCollisionHorizontalHaut = vecteurcollisionneur((x), (y), 176, 0, balle.getCenterX(), balle.getCenterY());
        double vecteurdeCollisionHorizontalBas = vecteurcollisionneur((x), (y + 176), 176, 0, balle.getCenterX(), balle.getCenterY());


        if (vecteurdeCollisionHorizontalBas <= balle.getRadiusX() && balle.getCenterX() <= x + 176 && balle.getCenterX() >= x)
            retour = true;
        if ((vecteurdeCollisionHorizontalHaut <= balle.getRadiusX() && balle.getCenterX() <= x + 176 && balle.getCenterX() >= x))
            retour = true;
        if (hvecteur <= balle.getRadiusX() && balle.getCenterY() <= y + 176 && balle.getCenterY() >= y)
            retour = true;
        if (vecteurdecollisionVerticalDroit <= balle.getRadiusX() && balle.getCenterY() <= y + 176 && balle.getCenterY() >= y)
            retour = true;
        if (this.affichage().contains(balle.getCenterX(), balle.getCenterY())) retour = true;

        return retour;
    }

    public Rectangle affichage() {
        Rectangle retour = new Rectangle(176, 176);
        retour.setFill(Color.LIGHTGREEN);
        retour.setX(x);
        retour.setY(y);
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
