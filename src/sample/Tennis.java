package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

public class Tennis extends Balle {

    public Tennis(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        id = _id;
        autres = _a;
        diametre = 15;
        friction = -0.9;
        masse=.5;
        pattern = new ImagePattern(image);
    }
}
