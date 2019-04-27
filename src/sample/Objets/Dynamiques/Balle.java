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

public class Balle extends Objet {
    protected double diametre, vx = 0, vy = 0, gravite = 0.09, spring = 0, masse = 0,vxi=0,vxf=0,vyi=0,vyf=0,dy=0;
    protected ImagePattern pattern;
    protected double angle = 0;
    protected boolean turned = false;
    private int width = 1000, height = 810;
    protected double pixel= 37.795275591;
    protected double gravite1= pixel*9.8;
    double tempsinitial= System.currentTimeMillis()/1000.00;

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
                        ax = ((targetX - autres.get(i).x) * spring),
                        ay = ((targetY - autres.get(i).y) * spring);

                if (autres.get(i).masse >= masse) {
                    vx -= ax;
                    vy -= ay;
                    autres.get(i).vx += ax * (masse / autres.get(i).masse);
                    autres.get(i).vy += ay * (masse / autres.get(i).masse);
                } else if (autres.get(i).masse <= masse) {
                    vx -= ax * (autres.get(i).masse / masse);
                    vy -= ay * (autres.get(i).masse / masse);
                    autres.get(i).vx += ax;
                    autres.get(i).vy += ay;
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

    public void setVx(double multiplicateur) {vx *= multiplicateur;}

    public void setVy(double multiplicateur) {vy *= multiplicateur;}

    public void setTurned(boolean tourne) {turned = tourne;}

    public void mouvement() {

        double tempsfinal= (System.currentTimeMillis()/1000.00);
        double deltat=tempsfinal-tempsinitial;
        tempsinitial=tempsfinal;
        vyf=vyi+(gravite1*deltat) ;
        dy=(vyi+vyf)*deltat/2;
        vyi=vyf;
        y +=dy ;
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
        retour.setRotate(angle);
        retour.autosize();
        return retour;
    }
}
