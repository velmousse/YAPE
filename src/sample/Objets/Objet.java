package sample.Objets;

import sample.Objets.Dynamiques.Balle;
import sample.Objets.Fixes.ObjetFixe;

import java.util.ArrayList;

public class Objet {
    protected double x, y, friction;
    protected int id;
    protected ArrayList<Balle> autres = new ArrayList<>();
    protected ArrayList<ObjetFixe> objetsFixes = new ArrayList<>();
}
