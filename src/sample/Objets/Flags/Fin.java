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
        if (affichage().intersects(balle.getBoundsInLocal()))
            retour = true;
        return retour;
    }

    public Rectangle affichage() {
        Rectangle retour = new Rectangle(175, 175);
        retour.setFill(Color.INDIANRED);
        retour.setTranslateX(x);
        retour.setTranslateY(y);
        return retour;
    }
}
