package RUClinic;

/**
 * The Patient class defines a person as a patient and keeps track of their
 * profile and the visits they made to the providers in our scheduler
 * It is a subclass of the superclass Person
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Patient extends Person implements Comparable<Person> {
    private Visit visit;

    /**
     * Constructor to create a Patient with no linked list
     */
    public Patient(Profile profile) {
        super(profile);
        this.visit = null;
    }

    /**
     * Constructor to create a Patient with a linked list
     */
    public Patient(Profile profile, Visit visit) {
        super(profile);
        this.visit = visit;
    }

    /**
     * Iterates through the visits linked list to determine the total amount
     * the patient is being charged for the visits
     * @return total amount the patient is being charged for the visits
     */
    public int charge() {
        int totalCharge = 0;
        Visit currentVisit = this.visit;
        Profile currentProfile = this.profile;

        while (currentVisit != null) {
            Appointment appointment = currentVisit.getAppointment();
            if (appointment != null && currentProfile.equals(appointment.getPatient().getProfile())) {
                Person personProvider = appointment.getProvider();
                if (personProvider instanceof Provider) {
                    Provider provider = (Provider) personProvider;
                    totalCharge += provider.rate();
                }
            }
            currentVisit = currentVisit.getNext();
        }
        return totalCharge;
    }

    /**
     * Override toString method for the Patient object
     * @return a Text representation of the patient
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(profile.toString()).append("\n");

        Visit currentVisit = visit;
        while (currentVisit != null) {
            result.append(currentVisit.getAppointment().toString()).append("\n");
            currentVisit = currentVisit.getNext();
        }
        return result.toString();
    }

}
