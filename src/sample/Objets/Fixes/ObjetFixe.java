package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Objets.Objet;

import java.util.ArrayList;

public class ObjetFixe extends Objet{
    protected float x, y, friction;

    public Rectangle affichage(){
        Rectangle rectangle = new Rectangle(x, y, 40, 50);
        rectangle.setRotate(-45);
        rectangle.setFill(Color.BLACK);
        return rectangle;
    }
}
