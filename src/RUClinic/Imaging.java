package RUClinic;

/**
 * The Imaging class represents an imaging appointment in the clinic.
 * It extends the Appointment class to include additional details specific
 * to imaging appointments.
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Imaging extends Appointment {
    private Radiology room;

    /**
     * Constructor for the Imaging class.
     * @param date     Date of the appointment
     * @param timeslot Time of the appointment
     * @param patient  The patient for the appointment
     * @param provider The provider for the appointment
     * @param room     The room type requested for the appointment
     */
    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room) {
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    /**
     * Helper method to get the radiology room for this imaging appointment
     * @return the radiology service type
     */
    public Radiology getRoom() {
        return this.room;
    }

    /**
     * Helper method to get the technician for this imaging appointment
     * @return the technician for the appointment
     */
    public Technician getTechnician() {
        if (super.getProvider() instanceof Technician) {
            return (Technician) super.getProvider();
        }
        return null;
    }
    /**
     * Overrides the equals method for Imaging class to check if two imaging appointments are the same
     * @param obj The object to be compared to this imaging appointment
     * @return true if both objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Imaging)) return false;

        Imaging otherImaging = (Imaging) obj;
        return (this.room.equals(otherImaging.room));
    }

}