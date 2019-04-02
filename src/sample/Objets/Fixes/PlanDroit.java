package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PlanDroit extends ObjetFixe {
    public PlanDroit(float x, float y,float z,float q, float k, float r,float p, float o) {
        this.x = x;
        this.y = y;
        this.z=z;
        this.q=q;
        this.k=k;
        this.r=r;
        this.p=p;
        this.o=o;
    }
    public Polygon affichage() {
        Polygon polygon = new Polygon(x, y, z, q, k, r,p,o);
        polygon.setFill(Color.BLACK);
        return polygon;
    }
}
