/**
 * Created by Alphonse on 14/05/2018.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Fenetre extends JFrame {
    public static ArrayList<Joueur> joueurs = Partie.initGame();
    public static String currentUnite = "Soldat";
    private int numerojoueur = 0; //quand il clique sur passer le tour cette variable se change en (numerojoueur+1)%joueurs.size()
    public Joueur currentJoueur = joueurs.get(numerojoueur);


    public Fenetre() {


        this.setTitle("Jeu Risk par Aymeric Bès de Berc & Alphonse Terrier");

        this.setSize(1300, 690);

        this.setLocationRelativeTo(null);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        Map map = new Map();
        this.setContentPane(map);
        changeCursor("Soldat");
        map.addMouseListener(new NewMouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                super.mouseClicked(event);
                if (event.getButton() == MouseEvent.BUTTON1) {

                    int x = event.getX();
                    int y = event.getY();

/*
                    String countryClicked = Territoire.getCountryName(x, y);

                    System.out.println(countryClicked);
                    System.out.println("x : "+x+", y : "+y);

                //joueurs.get(0).listUnites.remove(0);
                for (int i = 0; i < joueurs.get(0).listTerritoires.size(); i++) {
                    String countryName = joueurs.get(0).listTerritoires.get(i).getName();
                    if(Objects.equals(countryName, countryClicked) && joueurs.get(0).nbUnites > 0) {
                        joueurs.get(0).putUnite(new Soldat(x, y));
                        System.out.println(joueurs.get(0).nbUnites);
                    }
                }
                //System.out.println("x : "+ x +", y : " + y);
*/



                    //Instaurer une conditon pour passer en mode attaque (clique sur le bouton en bas à droite)

                    if (Objects.equals(Partie.phasePartie, "Renforts")) {
                        if (Objects.equals("Soldat", currentUnite)) {
                            currentJoueur.putUnite(new Soldat(x, y));
                        }
                        if (Objects.equals("Canon", currentUnite)) {
                            currentJoueur.putUnite(new Canon(x, y));

                        }
                        if (Objects.equals("Cavalier", currentUnite)) {
                            currentJoueur.putUnite(new Cavalier(x, y));

                        }
                        System.out.println(currentJoueur.nbUnites);

                        repaint();

                        if (currentJoueur.nbUnites == 0) {
                            Partie.phasePartie = "Déplacement";
                            setCursor(Cursor.getDefaultCursor());
                        }

                    }
                    if (Objects.equals(Partie.phasePartie, "Déplacement")) {

                        repaint();
                    }
                    if (Objects.equals(Partie.phasePartie, "Attaque")) {
                        repaint();
                        numerojoueur = (numerojoueur + 1) % joueurs.size();
                        changeCursor("Soldat");
                    }

                }

                if (event.getButton() == MouseEvent.BUTTON3) {
                    int x = event.getX();
                    int y = event.getY();
                    if (Objects.equals(Partie.phasePartie, "Renforts")) {
                        if (Objects.equals("Soldat", currentUnite)) {
                            changeCursor("Canon");
                        } else if (Objects.equals("Canon", currentUnite)) {
                            changeCursor("Cavalier");
                        } else if (Objects.equals("Cavalier", currentUnite)) {
                            changeCursor("Soldat");
                        }
                    }
                }

            }
        });


    }

    public void changeCursor(String pathname) {
        BufferedImage icone = Main.ImageReader(pathname + ".png");
        icone = Main.changeColor(icone, currentJoueur.couleur);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(icone).getImage(), new Point(0, 0), "Curseur"));
        currentUnite = pathname;
    }


}

abstract class NewMouseListener implements MouseListener {

    public void mouseClicked(MouseEvent event) {



    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }
}


