package sample.Objets.Dynamiques;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import sample.Objets.Dynamiques.Balle;

import java.util.ArrayList;

public class Bowling extends Balle {

    public Bowling(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        id = _id;
        autres = _a;
        diametre = 30;
        friction = -.5;
        spring = 0.7;
        masse = 5;
        pattern = new ImagePattern(image);
    }
}
