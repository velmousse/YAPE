package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Balle {
    private double x, y, diametre, vx = 0, vy = 0, gravite = 0.08, spring = 0.7, friction = -0.6;
    private int id;
    private Balle[] autres;
    private Color couleur;

    public Balle(float xin, float yin, float din, int idin, Balle[] ain, Color couleurin) {
        x = xin;
        y = yin;
        diametre = din;
        id = idin;
        autres = ain;
        couleur = couleurin;
    }

    public void collision() {
        for (int i = id + 1; i < autres.length; i++) {
            double dx = autres[i].x - x;
            double dy = autres[i].y - y;
            double distance = Math.sqrt(dx * dx + dy * dy);
            double minDist = autres[i].diametre + diametre;
            if (distance < minDist) {
                double angle = Math.atan2(dy, dx),
                targetX = (x + Math.cos(angle) * minDist),
                targetY = y + (Math.sin(angle) * minDist),
                ax = ((targetX - autres[i].x) * spring),
                ay = ((targetY - autres[i].y) * spring);
                vx -= ax;
                vy -= ay;
                autres[i].vx += ax;
                autres[i].vy += ay;
            }
        }
    }

    void mouvement() {
        vy += gravite;
        x += vx;
        y += vy;
        if (x + diametre > 640) {
            x = 640 - diametre;
            vx *= friction;
        } else if (x - diametre < 0) {
            x = diametre;
            vx *= friction;
        }
        if (y + diametre > 360) {
            y = 360 - diametre;
            vy *= friction;
        } else if (y - diametre < 0) {
            y = diametre;
            vy *= friction;
        }
    }

    Ellipse affichage() {
        Ellipse retour = new Ellipse(x, y, diametre, diametre);
        retour.setFill(couleur);
        retour.setStroke(Color.BLACK);
        retour.setStrokeWidth(1);
        return retour;
    }
}
