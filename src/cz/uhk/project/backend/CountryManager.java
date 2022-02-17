package cz.uhk.project.backend;

import java.util.ArrayList;
import java.util.List;

public class CountryManager implements IBaseCrudApp{
    public List<Country> countries = new ArrayList<>();

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
}
