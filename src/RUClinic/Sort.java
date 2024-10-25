package RUClinic;

/**
 * The Sort class handles all the sorting done for the lists of people in the clinic
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Sort {

    /**
     * Method to sort a given list of appointments by patient, date, then timeslot
     * @param appointments the list of appointments of which is being sorted
     */
    public static void sortByPatient(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = 0; j < appointments.size() - 1 - i; j++) {
                Appointment a1 = appointments.get(j);
                Appointment a2 = appointments.get(j + 1);

                int patientCompare = a1.getPatient().compareTo(a2.getPatient());
                int dateCompare = a1.getDate().compareTo(a2.getDate());
                int timeslotCompare = a1.getTimeslot().compareTo(a2.getTimeslot());

                if (patientCompare > 0 ||
                        (patientCompare == 0 && dateCompare > 0) ||
                        (patientCompare == 0 && dateCompare == 0 && timeslotCompare > 0)) {
                    Appointment temp = appointments.get(j);
                    appointments.set(j, appointments.get(j + 1));
                    appointments.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Method to sort a given list of appointments by location, date, then timeslot
     * @param appointments the list of appointments of which is being sorted
     */
    public static void sortByLocation(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = 0; j < appointments.size() - 1 - i; j++) {
                Appointment a1 = appointments.get(j);
                Appointment a2 = appointments.get(j + 1);

                Person person1 = a1.getProvider();
                Provider p1 = (Provider) person1;
                Person person2 = a2.getProvider();
                Provider p2 = (Provider) person2;

                int locationCompare = p1.getLocation().getCounty().compareTo(p2.getLocation().getCounty());
                int dateCompare = a1.getDate().compareTo(a2.getDate());
                int timeslotCompare = a1.getTimeslot().compareTo(a2.getTimeslot());

                if (locationCompare > 0 ||
                        (locationCompare == 0 && dateCompare > 0) ||
                        (locationCompare == 0 && dateCompare == 0 && timeslotCompare > 0)) {
                    Appointment temp = appointments.get(j);
                    appointments.set(j, appointments.get(j + 1));
                    appointments.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Method to sort a given list of appointments by date, timeslot, then provider's first name.
     * @param appointments the list of appointments to be sorted
     */
    public static void sortByAppointment(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = 0; j < appointments.size() - 1 - i; j++) {
                Appointment a1 = appointments.get(j);
                Appointment a2 = appointments.get(j + 1);

                int dateComparison = a1.getDate().compareTo(a2.getDate());
                if (dateComparison > 0) {
                    swap(appointments, j, j + 1);
                } else if (dateComparison == 0) {
                    int timeComparison = a1.getTimeslot().compareTo(a2.getTimeslot());
                    if (timeComparison > 0) {
                        swap(appointments, j, j + 1);
                    } else if (timeComparison == 0) {
                        Person provider1 = a1.getProvider();
                        Person provider2 = a2.getProvider();
                        Profile profile1 = provider1.getProfile();
                        Profile profile2 = provider2.getProfile();
                        if (profile1.compareTo(profile2) > 0) { swap(appointments, j, j + 1); }
                    }
                }
            }
        }
    }

    /**
     * Helper method to swap two appointments in the list.
     * @param appointments the list of appointments
     * @param i index of the first appointment to swap
     * @param j index of the second appointment to swap
     */
    private static void swap(List<Appointment> appointments, int i, int j) {
        Appointment temp = appointments.get(i);
        appointments.set(i, appointments.get(j));
        appointments.set(j, temp);
    }

    /**
     * Method to print out a given list of appointments
     * @param appointments the list of appointments being printed out
     */
    public static void printAppointments(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment current = appointments.get(i);
            if (current.getProvider() instanceof Doctor) {
                Doctor d1 = (Doctor) current.getProvider();
                System.out.println(current.toString());
            }
            else if (current instanceof Imaging) {
                Imaging currentI = (Imaging) current;
                System.out.println(currentI.toString() + "[" + currentI.getRoom().getService().toString() + "]");
            }
        }
    }

    /**
     * Method to print out a given list of providers
     * @param providers the list of providers being printed out
     */
    public static void printProviders(List<Provider> providers) {
        for (int i = 0; i < providers.size(); i++) {
            Provider current = providers.get(i);
            System.out.println(current.toString());
        }
    }

    /**
     * Static method to sort the appointments by date then timeslot
     * then provider profile and then print out the sorted list
     * @param list of appointments being sorted
     */
    public static void appointmentPA(List<Appointment> list) {
        System.out.println();
        System.out.println("** List of appointments, ordered by date/time/provider.");
        sortByAppointment(list);
        printAppointments(list);
        System.out.println("** end of list **");
        System.out.println();
    }

    /**
     * Static method to sort the appointments by patient profile, date, then timeslot
     * and then print out the sorted list
     * @param list of appointments being sorted
     */
    public static void appointmentPP(List<Appointment> list) {
        System.out.println();
        System.out.println("** List of appointments, ordered by patient/date/time **");
        sortByPatient(list);
        printAppointments(list);
        System.out.println("** end of list **");
        System.out.println();
    }

    public static void appointmentPL(List<Appointment> list) {
        System.out.println();
        System.out.println("** List of appointments, ordered by county/date/time.");
        sortByLocation(list);
        printAppointments(list);
        System.out.println("** end of list **");
        System.out.println();
    }

    public static void appointmentPO(List<Appointment> list) {
        System.out.println();
        System.out.println("** List of office appointments ordered by county/date/time.");
        List<Appointment> newList = new List<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProvider() instanceof Doctor) {
                newList.add(list.get(i));
            }
        }
        sortByLocation(newList);
        printAppointments(newList);
        System.out.println("** end of list **");
        System.out.println();
    }

    public static void appointmentPI(List<Appointment> list) {
        System.out.println();
        System.out.println("** List of radiology appointments ordered by county/date/time.");
        List<Appointment> newList = new List<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProvider() instanceof Technician) {
                newList.add(list.get(i));
            }
        }
        sortByLocation(newList);
        printAppointments(newList);
        System.out.println("** end of list **");
        System.out.println();
    }

    /**
     * Method to sort and print out a given list of appointments given the
     * list and the key specifying how to sort the list
     * @param list of appointments being sorted
     * @param key string indicating how the list should be sorted
     */
    public static void appointment(List<Appointment> list, String key) {
        if (list == null || list.isEmpty()) {
            System.out.println("Schedule calendar is empty.");
            return;
        }
        else if (list != null && key.equals("PA")) { appointmentPA(list); }
        else if (list != null && key.equals("PP")) { appointmentPP(list); }
        else if (list != null && key.equals("PL")) { appointmentPL(list); }
        else if (list != null && key.equals("PO")) { appointmentPO(list); }
        else if (list != null && key.equals("PI")) { appointmentPI(list); }

    }

    /**
     * Method to sort the list of providers from providers.txt
     * The list is sorted by the profiles of the providers
     * @param list of the providers taken from providers.txt
     */
    public static void provider(List<Provider> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Provider p1 = list.get(j);
                Provider p2 = list.get(j + 1);

                if (p1.getProfile().compareTo(p2.getProfile()) > 0) {
                    Provider temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        System.out.println("Providers loaded to the list.");
        printProviders(list);
        System.out.println();
    }

}
