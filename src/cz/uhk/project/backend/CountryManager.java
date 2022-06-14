package cz.uhk.project.backend;

import cz.uhk.project.frontend.GuiAppRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

public class CountryManager implements IBaseCrudApp{
    private final static Logger logger = Logger.getLogger(GuiAppRunner.class.getName());
    public static List<Country> countries = new ArrayList<>();


    @Override
    public void createCountry(Country newCountry) {
        countries.add(newCountry);
    }

    @Override
    public List<Country> listCountries() {
        return countries;
    }

    @Override
    public void updateCountry() {

    }

    @Override
    public int deleteCountry(Country countryToDelete) {
        if (countries.contains(countryToDelete)) {
            countries.remove(countryToDelete);
            return 204;
        }
        return 404;
    }

    @Override
    public int countryCount() {
        return countries.size();
    }

    public static boolean countryExists(String name){
        return countries.stream().anyMatch(c -> c.getName().equals(name));
    }

    public static Country getCountryFromKnownName(String submittedName){
        Country cntry = null;
        for (Country country : CountryManager.countries) {
            if (country != null){
                if (Objects.equals(country.getName().toLowerCase(Locale.ROOT), submittedName.toLowerCase(Locale.ROOT))) {
                    cntry = country;
                }
            }
        }
        return cntry;
    }

    public static void removeCountry(Country countryToDelete) {
        countries.remove(countryToDelete);
    }

    public static List<Country> searchCountriesByName(String searchInput){
        logger.info("Starting to search the countries list. Searching for: " + searchInput);
        List<Country> countriesMatched = new ArrayList<>();

        for (Country country : countries){
            if (country.getName().contains(searchInput)){
                countriesMatched.add(country);
            }
        }

        return countriesMatched;
    }
}
