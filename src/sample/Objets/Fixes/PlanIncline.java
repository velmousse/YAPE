package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Fixes.ObjetFixe;

import java.util.ArrayList;

public class PlanIncline  extends ObjetFixe {

    public PlanIncline(float _x, float _y, int _id, ArrayList<Balle> _a) {
        x = _x;
        y = _y;
        id = _id;
        autres = _a;
    }
}

