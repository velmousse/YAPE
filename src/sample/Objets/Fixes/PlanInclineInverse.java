package sample.Objets.Fixes;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import sample.Objets.Dynamiques.Balle;

import java.util.ArrayList;

public class PlanInclineInverse extends ObjetFixe{

    public PlanInclineInverse(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        autres = _a;
        type = 2;
        spring = 0.9;
        this.image = new ImagePattern(image);
    }
}
