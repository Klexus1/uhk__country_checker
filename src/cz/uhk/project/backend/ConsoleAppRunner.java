package cz.uhk.project.backend;

public class ConsoleAppRunner {
    public static void main(String[] args){
        CountryManager countryManager = new CountryManager();
        countryManager.createCountry(new Country("Czechia"));
        countryManager.createCountry(new Country("Poland", "Warsaw", 40_000_000, 312_679));

        System.out.println(countryManager.listCountries());
        System.out.println(countryManager.countryCount());

        countryManager.deleteCountry(countryManager.listCountries().get(0));

        System.out.println(countryManager.listCountries());
        System.out.println(countryManager.countryCount());
    }
}
