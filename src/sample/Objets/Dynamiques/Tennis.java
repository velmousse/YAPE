package sample.Objets.Dynamiques;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import sample.Objets.Dynamiques.Balle;

import java.util.ArrayList;

public class Tennis extends Balle {

    public Tennis(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        id = _id;
        autres = _a;
        diametre = 15;
        friction = -0.9;
        spring = 0.7;
        masse = 1;
        pattern = new ImagePattern(image);
    }
}
