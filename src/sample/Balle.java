package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Balle {
    public float x, y, diametre, vx = 0, vy = 0;
    public int id;
    Balle[] autres;

    public Balle(float xin, float yin, float din, int idin, Balle[] ain) {
        x = xin;
        y = yin;
        diametre = din;
        id = idin;
        autres = ain;
    }

    public void collide() {
        for (int i = id + 1; i < autres.length; i++) {
            float dx = autres[i].x - x;
            float dy = autres[i].y - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            float minDist = autres[i].diametre + diametre;
            if (distance < minDist) {
                float angle = (float) Math.atan2(dy, dx);
                float targetX = (float) (x + Math.cos(angle) * minDist);
                float targetY = y + (float) (Math.sin(angle) * minDist);
                float ax = (float) ((targetX - autres[i].x) * 0.01); //Spring
                float ay = (float) ((targetY - autres[i].y) * 0.01);
                vx -= ax;
                vy -= ay;
                autres[i].vx += ax;
                autres[i].vy += ay;
            }
        }
    }

    void move() {
        vy += 0.05; //Gravité
        x += vx;
        y += vy;
        if (x + diametre > 640) { //Width
            x = 640 - diametre;
            vx *= -0.3; //Friction
        } else if (x - diametre < 0) {
            x = diametre;
            vx *= -0.3;
        }
        if (y + diametre > 360) {
            y = 360 - diametre;
            vy *= -0.3;
        } else if (y - diametre < 0) {
            y = diametre;
            vy *= -0.3;
        }
    }

    Ellipse display() {
        Ellipse retour = new Ellipse(x, y, diametre, diametre);
        retour.setFill(Color.RED);
        retour.setStroke(Color.BLACK);
        return retour;
    }
}
