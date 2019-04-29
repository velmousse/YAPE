package sample.Objets;

import javafx.beans.binding.BooleanExpression;
import javafx.scene.Group;
import javafx.scene.image.Image;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Dynamiques.Bowling;
import sample.Objets.Dynamiques.Tennis;
import sample.Objets.Fixes.ObjetFixe;
import sample.Objets.Fixes.PlanDroit;
import sample.Objets.Fixes.PlanIncline;
import sample.Objets.Flags.Fin;
import sample.Objets.Flags.Largage;

import java.util.ArrayList;

public class GenerateurNiveaux {
    private Group objets;
    private int[] limites;
    private ArrayList<Balle> balles;
    private ArrayList<ObjetFixe> fixes;
    private Image bowling, tennis, wood;
    private int numBalles, numFixes, numObjets;
    private Fin fin;
    private Largage largage;

    public GenerateurNiveaux(Group _objets, int[] _limites, ArrayList<Balle> _balles, ArrayList<ObjetFixe> _fixes, Image _bowling, Image _tennis, Image _wood, int _numBalles, int _numFixes, int _numObjets, Largage _largage, Fin _fin) {
        objets = _objets;
        limites = _limites;
        balles = _balles;
        fixes = _fixes;
        bowling = _bowling;
        tennis = _tennis;
        wood = _wood;
        numBalles = _numBalles;
        numFixes = _numFixes;
        numObjets = _numObjets;
        largage = _largage;
        fin = _fin;
    }

    public void niveau() {
        for (int i = 0; i < limites.length; i++)
            limites[i] = 5;
        ajouterBowling(120, 130);
        ajouterTennis(170, 130);
        ajouterPlanDroit(190, 500);
        ajouterPlanIncline(190, 50);
    }

    private void ajouterBowling(int x, int y) {
        balles.add(new Bowling(x, y, balles.size(), balles, bowling));
        objets.getChildren().add(balles.get(balles.size() - 1).affichage());
        numBalles++;
        numObjets++;
    }

    private void ajouterTennis(int x, int y) {
        balles.add(new Tennis(x, y, balles.size(), balles, tennis));
        objets.getChildren().add(balles.get(numBalles).affichage());
        numBalles++;
        numObjets++;
    }

    private void ajouterPlanIncline(int x, int y) {
        fixes.add(new PlanIncline(x, y, fixes.size(), balles, wood));
        objets.getChildren().add(fixes.get(numFixes).affichage());
        numFixes++;
        numObjets++;
    }

    private void ajouterPlanDroit(int x, int y) {
        fixes.add(new PlanDroit(x, y, fixes.size(), balles, wood));
        objets.getChildren().add(fixes.get(numFixes).affichage());
        numFixes++;
        numObjets++;
    }

    private void ajouterLargage(int x, int y) {

    }

    public int getNumBalles() {return numBalles;}

    public int getNumFixes() {return numFixes;}

    public int getNumObjets() {return numObjets;}
}