package sample.Objets;

import javafx.scene.Group;
import javafx.scene.image.Image;
import sample.Objets.Dynamiques.Balle;
import sample.Objets.Dynamiques.Bowling;
import sample.Objets.Dynamiques.Tennis;
import sample.Objets.Fixes.ObjetFixe;
import sample.Objets.Fixes.PlanDroit;
import sample.Objets.Fixes.PlanIncline;
import sample.Objets.Fixes.PlanInclineInverse;
import sample.Objets.Flags.Fin;
import sample.Objets.Flags.Largage;

import java.util.ArrayList;

public class GenerateurNiveaux {
    private Group objets, flags;
    private int[] limites;
    private ArrayList<Balle> balles;
    private ArrayList<ObjetFixe> fixes;
    private Image bowling, tennis, wood;
    private int numBalles, numFixes, numObjets;
    private Fin fin;
    private Largage largage;

    public GenerateurNiveaux(Group _objets, int[] _limites, ArrayList<Balle> _balles, ArrayList<ObjetFixe> _fixes, Image _bowling, Image _tennis, Image _wood, int _numBalles, int _numFixes, int _numObjets, Largage _largage, Fin _fin, Group _flags) {
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
        flags = _flags;
    }

    public void niveau() {
        for (int i = 0; i < limites.length; i++)
            limites[i] = 0;
        limites[1] = 1;

        ajouterLargage(88, 88);
        ajouterFin(925, 710 - 85);
        ajouterPlanIncline(88, 200);

        ajouterTennis(405, 240);

        int x = 120;
        for (int i = 0; i < 5; i++)
            ajouterPlanDroit(x += 80, 300);

        int y = 10;
        x = 800;
        for (int i = 0; i < 14; i++)
            ajouterPlanDroit(x, y += 40);

        ajouterPlanIncline(680, 630);
    }
    public void niveau2(){
        for (int i = 0; i < limites.length; i++)
            limites[i] = 0;
        limites[1] = 2;

        ajouterLargage(200,100);
        ajouterFin(600,600);
        int y=208;
        int x= 320;
        for(int i=0;i<7;i++){
            ajouterPlanDroit(x,y+=20);
            if(i==5)
            {ajouterPlanInclineInverse(x-60,y);}
            if(i==6){
                ajouterPlanDroit(x-40,y);
            }
        }
        y=112;
        x=72;
        for(int i=0; i<15;i++)
        {
            ajouterPlanDroit(x,y+=20);
            if(i==13)
            {ajouterPlanIncline(x+60,y);}
            if(i==14){
                for(int j=0;j<5;j++){
                    ajouterPlanDroit(x+=80,y+20);
                }
            }
        }
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
    private void ajouterPlanInclineInverse(int x, int y) {
        fixes.add(new PlanInclineInverse(x, y, fixes.size(), balles, wood));
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
        largage = new Largage(balles, x - 175 / 2, y - 175 / 2);
        flags.getChildren().add(largage.affichage());
    }

    private void ajouterFin(int x, int y) {
        fin = new Fin(balles, x - 175 / 2, y - 175 / 2);
        flags.getChildren().add(fin.affichage());
    }

    public void resetGenerateur() {
        balles.clear();
        fixes.clear();
        flags.getChildren().clear();
        objets.getChildren().clear();
        numBalles = 0;
        numFixes = 0;
        numObjets = 0;
    }

    public int getNumBalles() {
        return numBalles;
    }

    public int getNumFixes() {
        return numFixes;
    }

    public int getNumObjets() {
        return numObjets;
    }

    public Fin getFin() {
        return fin;
    }

    public Largage getLargage() {
        return largage;
    }
}