package cz.uhk.project.data;

import cz.uhk.project.backend.Country;
import cz.uhk.project.backend.CountryManager;
import cz.uhk.project.frontend.GuiAppRunner;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Genergic class DataHandler is used to hold methods to manipulated data
 */
public class DataHandler {
    private final static Logger logger = Logger.getLogger(GuiAppRunner.class.getName());
    /**
     * Method for writing current country records to a file
     * @param countries list of our countries
     * @return bool success
     */
    public static Boolean saveDataToFile(List<Country> countries) {
        logger.info("Starting to save countries to the CountryData.txt file.");
        boolean success = false;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("CountryData.txt"));
            for (Country country : countries){
                writer.write(
                        country.getName() + "|" + country.getCapital()  + "|" + country.getInhabitants() + "|" + country.getArea() + "\n"
                        );
            }
            writer.close();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Done saving countries to a file. Success: " + success);
        return success;
    }

    /**
     * Method for loading data from a file
     * @return nothing that could user explicitly see, but countries are loaded into the countries list and can be viewed
     * on next data load / refresh.
     */
    public static boolean readDataFromFile(){
        logger.info("Starting to load countries from the country text file database.");
        boolean success = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("CountryData.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String [] country_details = line.split("|");
                String country_name = country_details[0];
                if (CountryManager.countryExists(country_name)){
                    continue;
                }
                try {
                    String country_capital = country_details[1];
                    long country_inhab;
                    double country_area;
                    try{
                        country_inhab = !Objects.equals(country_details[2], "") ? Long.parseLong(country_details[2]) : 0;
                    }
                    catch (Exception __){
                        country_inhab = 0;
                    }

                    try{
                        country_area = !Objects.equals(country_details[3], "") ? Double.parseDouble(country_details[3]) : 0;
                    }
                    catch (Exception __){
                        country_area = 0;
                    }
                    new Country(
                        country_name,
                        country_capital,
                        country_inhab,
                        country_area
                    );
                }
                catch (NumberFormatException __){
                    logger.info("Failed to parse data for country " + country_details[0] + " - continuing to next one.");
                }
            }
            reader.close();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Done loading countries from a file. Success: " + success);
        return success;
    }
}
