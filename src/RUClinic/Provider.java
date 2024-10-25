package RUClinic;

/**
 * The Provider class defines a person as a provider and keeps track of their
 * profile and their practice location
 * It is a subclass of the superclass Person but also the superclass of the Doctor
 * and the Technician class
 * @author Katrina Tong
 * @author Andrew Kim
 */
public abstract class Provider extends Person {
    private Location location;
    private Visit visit;

    /**
     * Constructor to create a Provider with a given profile and location.
     */
    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
    }

    /**
     * Grabs the location of the provider
     * @return The location of the provider
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Abstract method that returns how much a certain provider charges a patient per visit
     */
    public abstract int rate();

    /**
     * Calculates the total credit for all visits made to this provider
     * @return The total amount charged by this provider for all visits
     */
    public int credit() {
        int totalCredit = 0;
        Visit currentVisit = this.visit;
        while (currentVisit != null) {
            Appointment appointment = currentVisit.getAppointment();
            if (appointment != null && appointment.getProvider().equals(this)) {
                totalCredit += this.rate();
            }
            currentVisit = currentVisit.getNext();
        }
        return totalCredit;
    }

}
