package sample.Objets.Dynamiques;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;

import java.util.ArrayList;

public class Balle {
    protected double x, y, diametre, vx = 0, vy = 0, gravite = 0.09, spring = 0.7, friction,masse;
    protected int id;
    protected ArrayList<Balle> autres = new ArrayList<>();
    protected ImagePattern pattern;

    private int width = 800, height = 500;
    protected double energie=(500-y);

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
                {
                vx -= ax;
                vy -= ay;
                energie-=((int)ax^2)*masse;
                energie-=(int)ay^2;
                autres.get(i).vx += ax;
                autres.get(i).vy += ay;
                autres.get(i).energie+=ax;
                autres.get(i).energie+=ay;}

            }
        }
    }

    public void mouvement() {
        {
        vy += gravite;
        x += vx;
        y += vy;

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
    }

    public Ellipse affichage() {
        Ellipse retour = new Ellipse(x, y, diametre, diametre);
        retour.setFill(pattern);
        retour.autosize();
        return retour;
    }
}
