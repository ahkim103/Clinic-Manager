package RUClinic;

/**
 * The Profile class manages the profiles of all people in the clinic,
 * including their fname, lname, and date of birth
 * This class serves as a neater and more efficient way of
 * managing/maintaining peoples' information
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructor to create a Profile object of a certain Person
     * @param fname first name of the Person
     * @param lname last name of the Person
     * @param dob date of birth of the Person MM/DD/YYYY
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Return method to get the first name of any profile
     * @return first name of any profile
     */
    public String getFname() {
        return fname;
    }

    /**
     * Return method to get the last name of any profile
     * @return last name of any profile
     */
    public String getLname() {
        return lname;
    }

    /**
     * Capitalizes the first letter and makes the rest lowercase
     * @param name The word to be capitalized
     * @return the correct name
     */
    private String capitalize(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Returns a textual representation of a person's profile
     * Format: fname lname dob(MM/DD/YYYY)
     * @return returns the profile string
     */
    @Override
    public String toString() {
        return capitalize(fname) + " " + capitalize(lname) + " " + dob; }

    /**
     * Seperate method to get the textual representation of a provider's profile
     * Format: fname lname dob(MM/DD/YYYY), all capitalized
     * @return returns the profile string
     */
    public String providerToString() {
        return fname.toUpperCase() + " " + lname.toUpperCase() + " " + dob; }

    /**
     * equals method will be used to tell if a profile already exists
     * @param obj The object to be compared
     * @return true if the profiles are the same, false if they are different
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile person = (Profile) obj;
            return (person.lname.equalsIgnoreCase(this.lname)) &&
                    (person.fname.equalsIgnoreCase(this.fname)) &&
                    (person.dob.equals(this.dob));
        }
        return false;
    }

    public static final int LARGER = 1;
    public static final int SMALLER = -1;
    public static final int EQUALS = 0;
    /**
     * Overrides the compareTo() method for the Profile class
     * @param obj The object to be compared.
     * @return returns LARGER if greater, SMALLER if less, or EQUALS to another
     */
    @Override
    public int compareTo(Profile obj) {

        String fName = this.fname;
        String lName = this.lname;
        Date dob = this.dob;

        Profile profile = obj;

        String compareFirstName = profile.fname;
        String compareLastName = profile.lname;
        Date compareDate = profile.dob;

        if (lName.compareTo(compareLastName) < 0) {
            return SMALLER;
        } else if (lName.compareTo(compareLastName) > 0) {
            return LARGER;
        } else {
            if (fName.compareTo(compareFirstName) < 0) {
                return SMALLER;
            } else if (fName.compareTo(compareFirstName) > 0) {
                return LARGER;
            } else {
                if (dob.compareTo(compareDate) < 0) {
                    return SMALLER;
                } else if (dob.compareTo(compareDate) > 0) {
                    return LARGER;
                } else {
                    return EQUALS;
                }
            }
        }
    }

}
