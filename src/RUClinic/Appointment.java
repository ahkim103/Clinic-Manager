package RUClinic;

/**
 * The Appointment class is used to create appointments in the clinic that contain the
 * date and time of the appointment, the name of the patient and the
 * name of the provider that is being seen.
 * This class serves as the backbone of the project, since Appointment objects are the main focus.
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Appointment implements Comparable<Appointment> {
    protected Date date;
    protected Timeslot timeslot;
    protected Person patient;
    protected Person provider;

    /**
     * Parameterized Constructor
     * @param date      date of the appointment
     * @param timeslot  time in 00:00 AM/PM
     * @param provider  the fname, lname, and date of birth of the provider
     * @param patient   the fname, lname and date of birth of the patient
     */
    public Appointment(Date date, Timeslot timeslot, Person patient, Person provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Copy Constructor
     * @param other appointment being copied
     */
    public Appointment(Appointment other) {
        this.date = other.date;
        this.timeslot = other.timeslot;
        this.patient = other.patient;
        this.provider = other.provider;
    }

    // Return Methods Below
    /**
     * Helper method to return the date of a certain appointment
     * @return the date of a certain appointment
     */
    public Date getDate() { return this.date; }

    /**
     * Helper method to get the timeslot of a certain appointment
     * @return the timeslot of a certain appointment
     */
    public Timeslot getTimeslot() { return this.timeslot; }

    /**
     * Helper method to get the patient of a certain appointment
     * @return the patient object of a certain appointment
     */
    public Person getPatient() { return this.patient; }

    /**
     * Helper method to get the provider of a certain appointment
     * @return the provider object of a certain appointment
     */
    public Person getProvider() { return this.provider; }


    // Set Methods Below
    /**
     * Helper method to set the date of a certain appointment
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * Helper method to set a new timeslot for a certain appointment
     */
    public void setTimeslot(Timeslot timeslot) { this.timeslot = timeslot; }

    /**
     * Helper method to set the patient's info of a certain appointment
     */
    public void setPatient(Person patient) { this.patient = patient; }

    /**
     * Helper method to set a new provider for a certain appointment
     */
    public void setProvider(Person provider) { this.provider = provider; }


    /**
     * Overriding the toString() method to provide a textual representation of
     * a certain appointment in the DATE TIME FIRSTNAME LASTNAME BIRTHDATE
     * (PROVIDER) [NAME, LOCATION, TYPE] format
     * @return a string as a text representation of a certain appointment
     */
    @Override
    public String toString() {
        Provider provider1 = (Provider) provider;
        return date + " " + timeslot.toString() + " " +
                patient.getProfile().toString() + " " +
                provider.toString();
    }

    /**
     * Overriding the compareTo() method to see if one appointment is earlier than the other
     * @param otherAppointment the object to be compared to the first appointment
     * @return 1 if otherAppointment is later, -1 otherAppointment is earlier, 0 if equal
     */
    @Override
    public int compareTo(Appointment otherAppointment) {
        int dateComparison = this.date.compareTo(otherAppointment.date);
        if (dateComparison != 0) {
            return dateComparison;
        }

        int timeComparison = this.timeslot.compareTo(otherAppointment.timeslot);
        if (timeComparison != 0) {
            return timeComparison;
        }

        return this.patient.compareTo(otherAppointment.patient);
    }

    /**
     * Overrides the equals method for Appointment class to see if two given appointments are the same
     * @param obj The object appointment to be compared to the first appointment
     * @return true if both objects are the same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        Appointment otherAppointment = (Appointment) obj;
        return (this.date.equals(otherAppointment.date) &&
                this.timeslot == otherAppointment.timeslot &&
                this.patient.equals(otherAppointment.patient));
    }
}