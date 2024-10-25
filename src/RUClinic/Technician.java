package RUClinic;
import java.text.DecimalFormat;

/**
 * The Technician class defines the unique charging rate for each technician
 * It is a subclass of the Provider class.
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Technician extends Provider {
    private int ratePerVisit;

    /**
     * Constructor to create a Technician object given the profile,
     * location, and charging rate for a certain technician
     * @param profile of the technician, including their fname, lname, dob
     * @param location of the technician, including the name, county, zip code
     * @param ratePerVisit of the technician
     */
    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Method to return a certain technician's charging rate
     * @return the charging rate of a certain technician
     */
    @Override
    public int rate() {
        return ratePerVisit;
    }

    /**
     * Override toString method to print out the technician's charging rate
     * @return a certain technician's charging rate in decimal format
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,###.00");
        return("[" + profile.toString().toUpperCase() + ", " + getLocation().toString() + "]"
                + "[rate: " + df.format(ratePerVisit) + "]");
    }
}
