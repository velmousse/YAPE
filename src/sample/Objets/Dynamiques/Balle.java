package sample.Objets.Dynamiques;


import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import sample.Objets.Objet;
import sample.Objets.Fixes.*;

import java.util.ArrayList;

import sample.Objets.Fixes.ObjetFixe;

import static java.lang.System.currentTimeMillis;

public class Balle extends Objet {
    protected double diametre, vx = 0, vy = 0, gravite = 0.09, spring = 0, masse = 0,vxi=0.00,vxf=0,vyi=0.0,vyf=0,dy=0;
    protected ImagePattern pattern;
    protected double angle = 0;
    protected boolean turned = false;
    private int width = 1000, height = 710;
    protected double pixel= 37.795275591;
    protected double gravite1= pixel*9.8;
    public double tempsinitial=0;

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
            double dy = autres.get(i).y - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double minDist = autres.get(i).diametre + diametre;
            if (distance < minDist) {
                double angle = Math.atan2(dy, dx),
                        targetX = (x + Math.cos(angle) * minDist),
                        targetY = (y + Math.sin(angle) * minDist),
                        ax = ((targetX - autres.get(i).x) * 0.7)*.5,
                        ay = ((targetY - autres.get(i).y) * 0.7);

                if (autres.get(i).masse >= masse) {
                    vx  -= ax*.5;
                    if(vyi>0)
                    vyi-= ay;
                    if(vyi<=0)
                    vyi=vyi*-1*spring;
                    autres.get(i).vx += ax * (masse / autres.get(i).masse)*.5;
                    autres.get(i).vyi += ay * (masse / autres.get(i).masse);
                } else  {
                    vx -= ax * (autres.get(i).masse / masse)*.5;
                    vyi -= ay * (autres.get(i).masse / masse);
                    autres.get(i).vx += ax*.5;
                    autres.get(i).vyi += ay*.5;
                }
            }
        }
    }

    public double getRayon() {return diametre / 2;}

    public double getX() {return x;}

    public double getY() {return y;}

    public double getVx() {return vx;}

    public double getVy() {return vy;}

    public boolean isTurned() {return turned;}

    public void setX(double _x) {x = _x;}

    public void setY(double _y) {y = _y;}

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double multiplicateur) {vy *= multiplicateur;}

    public void setTurned(boolean tourne) {turned = tourne;}

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

        double tempsfinal= ((currentTimeMillis()+10)/1000.00);
        double deltat=tempsfinal-tempsinitial;
        tempsinitial=tempsfinal;
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
        retour.setRotate(angle/2);
        retour.autosize();
        return retour;
    }
}
