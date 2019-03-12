package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

public class Bowling extends Balle {

    public Bowling(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        id = _id;
        autres = _a;
        diametre = 30;
        friction = -.5;
        masse=6;
        pattern = new ImagePattern(image);
    }
}
