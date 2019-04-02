package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.Objets.Fixes.ObjetFixe;

public class PlanIncline  extends ObjetFixe {

    public PlanIncline(float x, float y,float z,float q, float k, float r) {
        this.x = x;
        this.y = y;
        this.z=z;
        this.q=q;
        this.k=k;
        this.r=r;

    }
    public Polygon affichage() {
        Polygon polygon = new Polygon(x, y, z, q, k, r);
        polygon.setFill(Color.BLACK);
        return polygon;
    }


}

