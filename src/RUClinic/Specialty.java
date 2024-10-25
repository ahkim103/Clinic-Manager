package RUClinic;

/**
 * The Specialty class defines and maintains the certain specialties the
 * providers have which determines how much they charge patients per visit
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Specialty {
    private String name;
    private int rate;

    /**
     * Constructor that creates a Specialty object for doctors
     * and assigns their rate given their specialty
     * @param name of a given doctor's specialty
     */
    public Specialty(String name) {
        this.name = name;
        if (this.name.equals("FAMILY")) { this.rate = 250; }
        if (this.name.equals("PEDIATRICIAN")) { this.rate = 300; }
        if (this.name.equals("ALLERGIST")) { this.rate = 350; }
    }

    /**
     * Returns the name of the specialty as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rate at which a doctor or technician charges per visit
     */
    public int getRate() {
        return rate;
    }

    /**
     * Returns the textual representation of a Specialty object
     */
    @Override
    public String toString() {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
