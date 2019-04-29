package sample.Objets.Fixes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import sample.Objets.Dynamiques.Balle;

import java.util.ArrayList;

public class PlanDroit extends ObjetFixe {
    public PlanDroit(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        autres = _a;
        type = 1;
        spring = 0.9;
        this.image = new ImagePattern(image);
    }
}
