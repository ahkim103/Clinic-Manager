package RUClinic;

/**
 * The Person class is the superclass of the Patient and Provider class
 * It is the basis for all people in the clinic
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Person implements Comparable<Person>{
    protected Profile profile;

    /**
     * Constructor to create a Person object with a given profile
     */
    public Person(Profile profile) {
        this.profile = profile;
    }

    /**
     * Helper method to return the profile of a certain person
     * @return the profile of a certain person
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Helper method to set a profile object to a certain person
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Compares the profiles of objects of the Person class
     * @param other the person object to be compared
     * @return returns 1 if greater, -1 if smaller, 0 if equal
     */
    @Override
    public int compareTo(Person other) {
        return this.profile.compareTo(other.profile);
    }

    /**
     * Override the equals method to compare two Person objects based on their profiles
     * @param obj object being compared
     * @return true if both objects are persons and both are equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person other = (Person) obj;
            return this.profile.equals(other.profile);
        }
        return false;
    }
}



