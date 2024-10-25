package RUClinic;

/**
 * The Location class keeps track of all the clinic locations via
 * the location's name, county, and zip code.
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Location {
    private String name;
    private String county;
    private String zip;

    /**
     * Constructor that creates a Location object given the available name and
     * assigns their respective county and zip code
     */
    public Location(String name) {
        this.name = name;
        if (this.name.equals("BRIDGEWATER")) { this.county = "Somerset"; this.zip = "08807"; }
        if (this.name.equals("EDISON")) { this.county = "Middlesex"; this.zip = "08817"; }
        if (this.name.equals("PISCATAWAY")) { this.county = "Middlesex"; this.zip = "08854"; }
        if (this.name.equals("PRINCETON")) { this.county = "Mercer"; this.zip = "08542"; }
        if (this.name.equals("MORRISTOWN")) { this.county = "Morris"; this.zip = "07960"; }
        if (this.name.equals("CLARK")) { this.county = "Union"; this.zip = "07066"; }
    }

    /**
     * Helper method that returns the location name
     * @return location name as a String
     */
    public String getName() { return name; }

    /**
     * Helper method that returns the county of a certain location
     * @return String county of a certain location
     */
    public String getCounty() {
        return county;
    }


    /**
     * Returns the text representation of a location
     * in the town, county, zip code format
     */
    @Override
    public String toString() {
        return name + ", " + county + " " + zip;
    }

}
