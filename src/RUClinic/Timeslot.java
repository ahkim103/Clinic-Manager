package RUClinic;

public class Timeslot implements Comparable<Timeslot> {
    private final int hour;
    private final int minute;
    private final String slotNumber;

    public static final Timeslot SLOT1 = new Timeslot(9, 0, "slot 1");
    public static final Timeslot SLOT2 = new Timeslot(9, 30, "slot 2");
    public static final Timeslot SLOT3 = new Timeslot(10, 0, "slot 3");
    public static final Timeslot SLOT4 = new Timeslot(10, 30, "slot 4");
    public static final Timeslot SLOT5 = new Timeslot(11, 0, "slot 5");
    public static final Timeslot SLOT6 = new Timeslot(11, 30, "slot 6");
    public static final Timeslot SLOT7 = new Timeslot(2, 0, "slot 7");
    public static final Timeslot SLOT8 = new Timeslot(2, 30, "slot 8");
    public static final Timeslot SLOT9 = new Timeslot(3, 0, "slot 9");
    public static final Timeslot SLOT10 = new Timeslot(3, 30, "slot 10");
    public static final Timeslot SLOT11 = new Timeslot(4, 0, "slot 11");
    public static final Timeslot SLOT12 = new Timeslot(4, 30, "slot 12");

    /**
     * Constructor that creates the Timeslot object
     * @param hour   Hour of appointment
     * @param minute Minute of the appointment
     * @param slotNumber Text representation of the slot number
     */
    public Timeslot(int hour, int minute, String slotNumber) {
        this.hour = hour;
        this.minute = minute;
        this.slotNumber = slotNumber;
    }

    /**
     * Gets the Timeslot based on the slot number input
     * @param slotNumber The slot number (1-6) to retrieve
     * @return The corresponding timeslot or null if the input is invalid
     */
    public static Timeslot getTimeslot(int slotNumber) {
        return switch (slotNumber) {
            case 1 -> Timeslot.SLOT1;
            case 2 -> Timeslot.SLOT2;
            case 3 -> Timeslot.SLOT3;
            case 4 -> Timeslot.SLOT4;
            case 5 -> Timeslot.SLOT5;
            case 6 -> Timeslot.SLOT6;
            case 7 -> Timeslot.SLOT7;
            case 8 -> Timeslot.SLOT8;
            case 9 -> Timeslot.SLOT9;
            case 10 -> Timeslot.SLOT10;
            case 11 -> Timeslot.SLOT11;
            case 12 -> Timeslot.SLOT12;
            default -> null;
        };
    }

    /**
     * Method to return the corresponding String value of the slot
     * @return slotNumber, the text representation of the slot number
     */
    public String getNumber() {
        return this.slotNumber;
    }

    /**
     * Checks if 2 timeslot objects are equal
     * @param obj The object to compare with
     * @return true if the objects are timeslots and are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Timeslot)) { return false; }
        if (this == obj) { return true; }

        Timeslot other = (Timeslot) obj;
        return (this.hour == other.hour &&
                this.minute == other.minute &&
                this.slotNumber.equals(other.slotNumber));
    }

    /**
     * Text representation of the timeslot
     * @return The time for the requested timeslot
     */
    @Override
    public String toString() {
        String period = (hour >= 9) ? "AM" : "PM";
        String minuteIn12 = String.format("%02d", minute);
        return hour + ":" + minuteIn12 + " " + period;
    }

    /**
     * Compares 2 timeslots based on the slot number
     * @param timeslot The Timeslot to compare with
     * @return -1, 0, or a 1 as this Timeslot
     * is less than, equal to, or greater than the specified Timeslot.
     */
    @Override
    public int compareTo(Timeslot timeslot) {
        int thisSlotNumber = Integer.parseInt(this.slotNumber.split(" ")[1]);
        int otherSlotNumber = Integer.parseInt(timeslot.slotNumber.split(" ")[1]);

        return Integer.compare(thisSlotNumber, otherSlotNumber);
    }
}

