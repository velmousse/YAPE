package sample.Objets.Fixes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Objet;

import java.util.ArrayList;

public class ObjetFixe extends Objet{
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getQ() {
        return q;
    }

    public void setQ(float q) {
        this.q = q;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

    public float getO() {
        return o;
    }

    public void setO(float o) {
        this.o = o;
    }

    protected float x, y,z,q,k,r,p,o;


    public Polygon affichage(){
        return new Polygon();
    }

}
