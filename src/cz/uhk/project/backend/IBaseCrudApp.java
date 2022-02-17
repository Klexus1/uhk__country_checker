package cz.uhk.project.backend;

import java.util.List;

public interface IBaseCrudApp {

    /**
     * Create new country record
     */
    void createCountry(Country newCountry);

    /**
     * Get list of all known countries
     * @return List of countries
     */
    List<Country> listCountries();

    /**
     * Update info for a specific country
     */
    void updateCountry();

    /**
     *  Delete a country from the country list
     */
    int deleteCountry(Country countryToDelete);

    int countryCount();
}
