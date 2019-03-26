package sample.Objets.Dynamiques;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import sample.Objets.Objet;

import java.util.ArrayList;

public class Balle extends Objet{
    protected double x, y, diametre, vx = 0, vy = 0, gravite = 0.09, spring = 0, friction = 0, masse = 0;
    protected int id;
    protected ArrayList<Balle> autres = new ArrayList<>();
    protected ImagePattern pattern;
    protected double angle = 0;

    private int width = 1000, height = 810;

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
