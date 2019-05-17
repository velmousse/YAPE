package sample.Objets.Dynamiques;


import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import sample.Objets.Objet;

import static java.lang.System.currentTimeMillis;

public class Balle extends Objet {
    protected double diametre, vx = 0, vy = 0, gravite = 0.09, spring = 0, masse = 0, vxi = 0.00, vxf = 0, vyi = 0.0, vyf = 0, dy = 0;
    protected ImagePattern pattern;
    protected double angle = 0;
    protected boolean turned = false;
    private int width = 1000, height = 710;
    protected double pixel = 37.795275591;
    protected double gravite1 = pixel * 9.8;
    public double tempsinitial = 0;
    public boolean graviteinversee= false;


    public boolean isGraviteinversee() {
        return graviteinversee;
    }

    public void setGraviteinversee(boolean graviteinversee) {
        this.graviteinversee = graviteinversee;
    }

    public void inversergravite(){
        gravite1=gravite1*-1;
        graviteinversee=true;
    }

    public void setVyi(double vyi) {
        this.vyi = vyi;
    }

    public void setVyf(double vyf) {
        this.vyf = vyf;
    }

    public double getVyi() {
        return vyi;
    }

    public double getVyf() {
        return vyf;
    }

    public double getSpring() {
        return spring;
    }

    public void collision() {
        for (int i = id + 1; i < autres.size(); i++) {
            double dx = autres.get(i).x - x;
            double dy = (autres.get(i).y - y);
            double distance = Math.sqrt(dx * dx + dy * dy);
            double minDist = autres.get(i).diametre + diametre;
            if (distance < minDist) {
                double angle = Math.atan2(dy, dx);
                if(vx<1&&vx>-1) {
                    double vxtempo = ((masse - autres.get(i).getMasse()) / (masse + autres.get(i).getMasse()) * vx) + 2 * autres.get(i).getMasse() / (masse + autres.get(i).getMasse()) * autres.get(i).getVx();
                    autres.get(i).vx = ((2 * masse) / (masse + autres.get(i).getMasse()) * vx) + (autres.get(i).getMasse() - masse) / (masse + autres.get(i).getMasse()) * autres.get(i).vx + Math.cos(angle);
                    vx = vxtempo - Math.cos(angle);
                }
                else{
                    vx=vx*.0000005;
                    autres.get(i).vx=-autres.get(i).vx*.00005;
                }



                double vytempo = ((masse - autres.get(i).getMasse()) / (masse + autres.get(i).getMasse()) * vyi) + 2 * autres.get(i).getMasse() / (masse + autres.get(i).getMasse()) * autres.get(i).getVyi();
                autres.get(i).vyi = ((2 * masse) / (masse + autres.get(i).getMasse()) * vyi) + (autres.get(i).getMasse() - masse) / (masse + autres.get(i).getMasse()) * autres.get(i).vyi + Math.sin(angle);
                vyi = vytempo - Math.sin(angle);
            }
        }
    }

    public double getMasse() {
        return masse;
    }

    public double getRayon() {
        return diametre / 2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public boolean isTurned() {
        return turned;
    }

    public void setX(double _x) {
        x = _x;
    }

    public void setY(double _y) {
        y = _y;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double multiplicateur) {
        vy *= multiplicateur;
    }

    public void setTurned(boolean tourne) {
        turned = tourne;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getVxi() {
        return vxi;
    }

    public double getGravite1() {
        return gravite1;
    }

    public void setGravite1(double gravite1) {
        this.gravite1 = gravite1;
    }

    public double getTempsinitial() {
        return tempsinitial;
    }

    public void setTempsinitial(double tempsinitial) {
        this.tempsinitial = tempsinitial;
    }

    public void setVxi(double vxi) {
        this.vxi = vxi;
    }

    public double getVxf() {
        return vxf;
    }

    public void setVxf(double vxf) {
        this.vxf = vxf;
    }

    public void mouvement() {

        double tempsfinal = ((currentTimeMillis() + 10) / 1000.00);
        double deltat = tempsfinal - tempsinitial;
        tempsinitial = tempsfinal;
        vyf = vyi + (gravite1 * deltat);
        dy = (vyi + vyf) * deltat / 2;

        vyi = vyf;
        y += dy;
        x += vx;
        if (vx > 0)
            vx -= 0.001;
        else if (vx < 0)
            vx += 0.001;

        if (x + diametre > width) {
            x = width - diametre;
            vx *= friction;

        } else if (x - diametre < 0) {
            x = diametre;

            vx *= friction;
        }
        if (y + diametre > height) {
            y = height - diametre;
            vyi *= friction;


        } else if (y - diametre < 0) {
            y = diametre;
            vyi *= friction;

        }

    }

    public Ellipse affichage() {
        Ellipse retour = new Ellipse(x, y, diametre, diametre);
        retour.setFill(pattern);
        angle += vx;
        retour.setRotate(angle / 2);
        retour.autosize();
        return retour;
    }
}
