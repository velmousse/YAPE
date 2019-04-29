package sample.Objets.Flags;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import sample.Objets.Dynamiques.Balle;

import java.util.ArrayList;

public class Fin {
    ArrayList<Balle> balles;
    float x, y;

    public Fin(ArrayList<Balle> _balles, float _x, float _y) {
        balles = _balles;
        x = _x;
        y = _y;
    }

    public boolean collision(Ellipse balle) {
        boolean retour = false;

        double hvecteur = vecteurcollisionneur(x,y,0,-175,balle.getCenterX(),balle.getCenterY());
        double rayonok= Math.sqrt((balle.getRadiusX()*balle.getRadiusX())+(balle.getRadiusY()*balle.getCenterY()));
        if (hvecteur-balle.getRadiusY()<=balle.getRadiusY())
            retour = true;
        return retour;
    }

    public Rectangle affichage() {
        Rectangle retour = new Rectangle(175, 175);
        retour.setFill(Color.INDIANRED);
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
