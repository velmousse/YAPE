package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlanIncliné {

    protected float x, y,friction;

    public PlanIncliné(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle affichage(){

        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setRotate(-45);
        rectangle.setFill(Color.BLACK);
        return  rectangle;
    }
}

