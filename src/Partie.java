import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public class Partie {

    /**
     *
     */
    public boolean Victoire;

    /**
     * Default constructor
     */
    public Partie() {
    }

    /**
     * @param Territoire t
     * @param Joueur     j
     * @param Mission    m
     * @param Region     r
     */


    public static void initGame() {
        ImageIcon icone = new ImageIcon("iconenbjoueurs.png");
        String[] cbdejoueurs = {"2", "3", "4", "5", "6"};
        JOptionPane jop = new JOptionPane();
        String nombre = (String) jop.showInputDialog(null,
                "Veuillez saisir le nombre de joueurs ?",
                "Choix du nombre de joueurs",
                JOptionPane.QUESTION_MESSAGE,
                icone,
                cbdejoueurs,
                cbdejoueurs[0]);
        int nbJoueurs = Integer.parseInt(nombre);
        System.out.println(nbJoueurs);


        ArrayList<Color> couleurs = new ArrayList<Color>();
        couleurs.add(new Color(210, 21, 27));
        couleurs.add(new Color(255, 133, 0));
        couleurs.add(new Color(11, 100, 155));
        couleurs.add(new Color(11, 141, 9));
        couleurs.add(new Color(154, 68, 178));
        couleurs.add(new Color(102, 15, 51));
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();


        for (int x = 0; x < nbJoueurs; x++) {

            ImageIcon iconejoueur = new ImageIcon("iconejoueur.png");
            JOptionPane jop2 = new JOptionPane();
            String name = (String) jop2.showInputDialog(null,
                    "Entrer le nom du joueur " + (x+1) + ":",
                    "Saisie des noms des joueurs", JOptionPane.QUESTION_MESSAGE,
                    iconejoueur,
                    null,
                    "");
            int nbUnites = 50 - 5 * nbJoueurs;

            joueurs.add(new Joueur(name, new ArrayList<Territoire>(), new ArrayList<Unite>(), nbUnites, new ArrayList<Region>(), couleurs.get(x)));
        }

        ArrayList<Territoire> allTerritories = Territoire.getAllCountriesName();
        Collections.shuffle(allTerritories);


        int i = 0;
        while (i < allTerritories.size()) {
            for (int x = 0; x < nbJoueurs; x++) {
                if (i < allTerritories.size()) {
                    joueurs.get(x).listTerritoires.add(allTerritories.get(i));
                    i += 1;

                }
            }
        }

        // A FAIRE : Manque le placement des unités sur les territoires (partie 3.1.4)
        Fenetre carte = new Fenetre();


    }

}