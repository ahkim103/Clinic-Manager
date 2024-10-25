package RUClinic;

/**
 * The Doctor class defines the specialty and unique npi number of the provider
 * It is a subclass of the Provider class
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Doctor extends Provider {
    private Specialty specialty;
    private String npi;


    /**
     * Constructor that creates a Doctor object
     * @param profile of the doctor
     * @param location of the clinic where the doctor practices
     * @param specialty of the doctor
     * @param npi of the doctor
     */
    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * Helper method to return the npi of a certain doctor
     * @return npi of the doctor
     */
    public String getNpi() {
        return npi;
    }

    /**
     * Abstract method that returns the rate the doctor charges per visit
     * @return rate that doctor charges patients per visit
     */
    @Override
    public int rate() {
        return specialty.getRate();
    }

    /**
     * Override toString method to print out the doctor's profile, location, specialty and unique npi
     * @return a certain doctor's information in a specific string format
     */
    @Override
    public String toString() {
        return("[" + profile.toString().toUpperCase() + ", " + getLocation().toString() + "]"
                + "[" + (specialty.toString().toUpperCase()) + ", " + "#" + npi + "]");
    }

}
