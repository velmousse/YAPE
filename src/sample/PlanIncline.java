package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlanIncline {

    protected float x, y,friction;

    public PlanIncline(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle affichage(){

        Rectangle rectangle = new Rectangle(x,y,1,10);
        rectangle.setRotate(-45);
        rectangle.setFill(Color.BLACK);
        rectangle.setWidth(1);
        return  rectangle;
    }
}

