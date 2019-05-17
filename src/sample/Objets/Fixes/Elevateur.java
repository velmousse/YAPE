package sample.Objets.Fixes;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import sample.Objets.Dynamiques.Balle;

import java.util.ArrayList;

public class Elevateur extends ObjetFixe {
    public Elevateur(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        autres = _a;
        type = 3;
        this.image = new ImagePattern(image);
    }
}
