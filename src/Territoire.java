import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Territoire {
    public ArrayList<Unite> listUnits;
    private String name;

    public Territoire(String name) {
        this.name = name;
        //this.listUnits = Unite.getAllUnitsinTerritoire(name, new ArrayList<Unite>()); // Remplacer new ArrayList<Unite>()
    }


    public static String getCountryName(int x, int y) {
        File folder = new File("./Terre/countries_png");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String filename = new File(String.valueOf(file)).getName().replaceFirst("[.][^.]+$", "");
                try {
                    BufferedImage country = ImageIO.read(file);
                    Color color = new Color(country.getRGB(x-Map.x_adapt, y-Map.y_adapt));
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    if (red < 255 || green < 255 || blue < 255) {
                        return (filename);
                    }
                } catch (IOException e) {

                    e.printStackTrace();

                }


            }
        }
        return ("C'est pas l'homme qui prend la mer, c'est la mer qui prend l'homme");


    }

    public static boolean areTheseCountriesAdjacents(String countryname1, String countryname2) {

        if (Objects.equals(countryname1, countryname2)) {
            return true;
        }

        try {
            String line;
            BufferedReader frontieres = new BufferedReader(new FileReader("./Terre/frontieres.txt"));
            while ((line = frontieres.readLine()) != null) {
                String[] thatLine = line.split(";");
                if (Objects.equals(thatLine[0], countryname1)) {
                    for (int x = 1; x < thatLine.length; x++) {
                        if (Objects.equals(thatLine[x], countryname2)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Territoire> getAllCountriesName() {
        File folder = new File("./Terre/countries_png");
        File[] listOfFiles = folder.listFiles();
        ArrayList listCountries = new ArrayList();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String filename = new File(String.valueOf(file)).getName().replaceFirst("[.][^.]+$", "");
                listCountries.add(new Territoire(filename));
            }
        }
        return listCountries;
    }

    public static boolean checkIfThisIsOneOfMyCountry(Joueur joueur, String country) {

        for (int i = 0; i < joueur.listTerritoires.size(); i++) {
            if(Objects.equals(country, joueur.listTerritoires.get(i).name)) {
                return true;
            }
        }


        return false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}