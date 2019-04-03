package sample.Objets.Dynamiques;


import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import sample.Objets.Objet;
import sample.Objets.Fixes.*;

import java.util.ArrayList;
import sample.Objets.Fixes.ObjetFixe;

public class Balle extends Objet{
    protected double x, y, diametre, vx = 0, vy = 0, gravite = 0.09, spring = 0, friction = 0, masse = 0;
    protected int id;
    protected ArrayList<Balle> autres = new ArrayList<>();
    protected ArrayList<ObjetFixe> objetsFixes = new ArrayList<>();
    protected ImagePattern pattern;
    protected double angle = 0;

    private int width = 1000, height = 810;
    boolean collision= false;
    Bounds bounds=this.affichage().getBoundsInLocal();


    public ArrayList<ObjetFixe> getObjetsfixes() {
        return objetsFixes;
    }

    public void setObjetsfixes(ArrayList<ObjetFixe> objetsfixes) {
        this.objetsFixes = objetsfixes;
    }

    // .5mv^2+mgy

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

    public void collisionObjet(ObjetFixe objetFixe) {

        if (objetFixe.getP() != 0) {
            double minheight = objetFixe.getY();
            double maxheight = objetFixe.getR();
            double mindepth = objetFixe.getX();
            double maxdepth = objetFixe.getK();
            Rectangle rect= new Rectangle(mindepth,minheight,maxdepth,maxheight);
            Bounds bounds1=rect.getBoundsInLocal();


            if(bounds.intersects(bounds1)){
               collision=true;}

            if(collision){
            if (x + diametre > mindepth) {
                x = mindepth - diametre;
                vx *= friction;
            }else if (x - diametre < maxdepth) {
                x = diametre + maxdepth;
                vx *= friction;

            }
            if (y + diametre > minheight) {
                y = minheight - diametre;
                vy *= friction;


            } else if (y - diametre < maxheight) {
                y = diametre + maxheight;
                vy *= friction;

            }
            }

        }
    }


    public void mouvement() {
        vy += gravite;
        x += vx;
        y += vy;

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
            vy *= friction;


        } else if (y - diametre < 0) {
            y = diametre;
            vy *= friction;

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
