package sample.Objets.Fixes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Fixes.ObjetFixe;

import java.util.ArrayList;

public class PlanIncline  extends ObjetFixe {

    public PlanIncline(float _x, float _y, int _id, ArrayList<Balle> _a, Image image) {
        x = _x;
        y = _y;
        autres = _a;
        type = 0;
        spring = 0.9;
        this.image = new ImagePattern(image);
    }
}

