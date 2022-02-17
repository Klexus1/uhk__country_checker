package cz.uhk.project.backend;

public class Country {
    private static long idCounter = 0;
    private long id;
    private String name;
    private String capital;
    private long inhabitants;
    private double area;

    public Country(String name){
        this.name = name;
        this.id = getId();
    }

    public static synchronized long getId()
    {
        Country.idCounter += 1;
        return idCounter;
    }

    public Country(String name, String capital, long inhabitants, double area){
        this.name = name;
        this.capital = capital;
        this.inhabitants = inhabitants;
        this.area = area;
        this.id = getId();
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public long getInhabitants() {
        return inhabitants;
    }

    public double getArea() {
        return area;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setInhabitants(long inhabitants) {
        this.inhabitants = inhabitants;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String toString() {
        return this.id + ". country: " + this.name;
    }
}
