package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Objets.Fixes.ObjetFixe;

public class PlanIncline  extends ObjetFixe {

    public PlanIncline(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle affichage(){
        Rectangle rectangle = new Rectangle(x, y, 40, 50);
        rectangle.setRotate(-45);
        rectangle.setFill(Color.BLACK);
        return rectangle;
    }
}

