package cz.uhk.project.data;

import cz.uhk.project.backend.Country;

import java.io.*;
import java.util.List;

/**
 * Genergic class DataHandler is used to hold methods to manipulated data
 */
public class DataHandler {

    /**
     * Method for writing current country records to a file
     * @param countries list of our countries
     * @return bool success
     */
    public static Boolean saveDataToFile(List<Country> countries) {
        boolean success = false;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("CountryData.txt"));
            for (Country country : countries){
                writer.write(country + "\n");
            }
            writer.close();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean readDataFromFile(){
        boolean success = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("CountryData.txt"));
            String line;
            while ((line = reader.readLine()) != null){

            }
            reader.close();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return success;
    }
}
