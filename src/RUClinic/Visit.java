package RUClinic;

/**
 * The Visit class is used to define a node in a singly linked list
 * that maintains the list of visits in the clinic.
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Visit {
    private Appointment appointment;
    private Visit next;

    /**
     * Creates a singly linked list Visit with one node for appointment
     * @param appointment takes in an appointment object and puts it into
     * the first node of the linked list while the node right after it is null
     */
    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    /**
     * Creates a singly linked list with one node for appointment and another for the next
     * @param appointment takes in an appointment object and puts it into
     * the first node of the linked list while the node right after it is null
     * @param next references to the next Visit in the list and if this is the
     * last node, it would be defined as null
     */
    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    /**
     * Helper method to add a new appointment to the end of the linked list
     * @param appointment is added to the end of the linked list
     */
    public void addAppointment(Appointment appointment) {
        Visit newVisit = new Visit(appointment);
        if (this.next == null) {
            this.next = newVisit;
        } else {
            Visit current = this;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newVisit;
        }
    }

    /**
     * Helper method to set the reference to the next visit node in the linked list.
     * @param next is the Visit object to be set as the next node in the list.
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    /**
     * Helper method to return the next visit right after the current visit
     * @return visit object that is right after the current visit
     */
    public Visit getNext() {
        return this.next;
    }

    /**
     * Helper Method to return the current appointment in the linked list
     * @return appointment object that is associated with the current visit
     */
    public Appointment getAppointment() {
        return this.appointment;
    }
}