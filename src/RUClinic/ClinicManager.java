package RUClinic;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * The ClinicManager class is used for processing input commands from the terminal
 * It allows for users to manage and interact with the clinic
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class ClinicManager {
    List<Provider> providerList = new List<>();
    List<Technician> technicianList = new List<>();
    List<Appointment> appointmentList = new List<>();
    CircularlyLinkedList technicianCircular = new CircularlyLinkedList();


    /**
     * Method to read and input providers into the providerList from providers.txt
     */
    private void readProvidersTXT() {
        File file = new File("providers.txt");
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                String key = tokenizer.nextToken();  // D or T
                String fname = tokenizer.nextToken();
                String lname = tokenizer.nextToken();
                String date = tokenizer.nextToken();
                Date dob = new Date(date);
                Profile profile = new Profile(fname,lname,dob);
                String loc = tokenizer.nextToken();
                Location location = new Location(loc);
                if (key.equals("D")) {
                    String spec = tokenizer.nextToken();
                    Specialty specialty = new Specialty(spec);
                    String npi = tokenizer.nextToken();
                    Doctor doctor = new Doctor (profile, location, specialty, npi);
                    providerList.add(doctor);
                }
                if (key.equals("T")) {
                    String rate = tokenizer.nextToken();
                    int rateInt = Integer.parseInt(rate);
                    Technician technician = new Technician(profile, location, rateInt);
                    providerList.add(technician);
                    technicianList.add(technician);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) { System.out.println("File not found: " + e.getMessage()); }
    }

    /**
     * Method to turn the technician list into a circular linked list as defined in the technician class
     * @param technicianList the list of technicians being turned into a circular linked list
     */
    private CircularlyLinkedList technicianList(List<Technician> technicianList) {
        CircularlyLinkedList technicianCircular = new CircularlyLinkedList();
        for (Technician technician : technicianList) {
            technicianCircular.add(technician);
        }
        technicianCircular.reverse();
        return technicianCircular;
    }

    /**
     * Method to check if a date is invalid and the reason it is invalid
     * @param date to be checked if valid or not
     * @return true if the date is valid and false if it isn't
     */
    private boolean dateCheck(Date date) {
        boolean check = false;
        if (!date.isDateValid()) {
            System.out.println("Appointment date: " + date.toString() + " is not a valid calendar date");
            return check;
        } else if (!date.isTodayOrBefore()) {
            System.out.println("Appointment date: " + date.toString() + " is today or a date before today.");
            return check;
        } else if (!date.isInSixMonths()) {
            System.out.println("Appointment date: " + date.toString() + " is not within six months.");
            return check;
        } else if (!date.isWeekend()) {
            System.out.println("Appointment date: " + date.toString() + " is Saturday or Sunday.");
            return check;
        } else {
            check = true;
            return check;
        }
    }

    /**
     * Method to check if a dob is invalid and the reason it is invalid
     * @param birthday to be checked if valid or not
     * @return true if the dob is valid and false if it isn't
     */
    private boolean dobCheck(Date birthday) {
        boolean check = false;
        if (!birthday.isDateValid()) {
            System.out.println("Patient dob: " + birthday.toString() + " is not a valid calendar date");
            return check;
        } else if (birthday.isTodayOrBefore()) {
            System.out.println("Patient dob: " + birthday.toString() + " is today or a date after today.");
            return check;
        } else {
            check = true;
            return check;
        }
    }

    /**
     * Method to check if a certain patient is available for an appointment or not
     * @param appointment we are seeing patient is available for or ot
     * @return true if patient is available, false if not
     */
    private boolean patientAvailable(Appointment appointment) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment currentAppointment = appointmentList.get(i);
            if (currentAppointment.getDate().equals(appointment.getDate()) &&
                    currentAppointment.getTimeslot().equals(appointment.getTimeslot()) &&
                    currentAppointment.getPatient().equals(appointment.getPatient())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a given command line has the right amount of data tokens.
     * @param command the command given in StringTokenizer form
     * @param operation the type of command being inputted
     * @return true if the command is valid, false if not
     */
    private boolean isCommandValid(StringTokenizer command, String operation) {
        boolean valid = true;
        final int DTR = 6;
        final int C = 5;
        if (operation.equals("D") || operation.equals("T") || operation.equals("R")) {
            if (command.countTokens() != DTR) {
                valid = false;
            }
        }
        if (operation.equals("C")) {
            if (command.countTokens() != C) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Method to determine if the newAppointment being requested is available to be booked
     * It checks if the patient and provider is available and that there is no already
     * existing appointment occupying the requested timeslot
     * @param newAppointment the appointment being booked
     * @param existingAppointment null if there is not another appointment occupying that timeslot
     *                            or provider being occupied
     */
    private void printD(Appointment newAppointment, Appointment existingAppointment) {
        if (existingAppointment == null) {
            if (patientAvailable(newAppointment)) {
                appointmentList.add(newAppointment);
                System.out.println(newAppointment.toString() + " booked.");
            } else {
                System.out.println(newAppointment.getPatient().getProfile().toString() + " has an existing appointment at the same time slot.");
            }
        } else {
            if(patientAvailable(newAppointment)) {
                System.out.println(newAppointment.getProvider().toString() + " is not available at " + newAppointment.getTimeslot().getNumber());
            }
            else {
                System.out.println(newAppointment.getPatient().getProfile().toString() + " has an existing appointment at the same time slot.");
            }
        }
    }

    /**
     * Method to handle patient inputs with the "D" command,
     * indicating a new office appointment with a specific doctor
     * @param command input line from the run function
     */
    private void operationD(StringTokenizer command) {
        Date date = new Date(command.nextToken());
        String timeToken = command.nextToken();
        int time;
        try { time = Integer.parseInt(timeToken); }
        catch (NumberFormatException e) { System.out.println(timeToken + " is not a valid time slot."); return; }
        if (time <= 0 || time > 12) { System.out.println(time + " is not a valid time slot."); return; }
        Timeslot timeslot = Timeslot.getTimeslot(time);
        String fname = command.nextToken();
        String lname = command.nextToken();
        Date dob = new Date(command.nextToken());
        if (!dateCheck(date) || !dobCheck(dob)) { return; }
        Profile pat = new Profile(fname,lname,dob);
        Person patient = new Person(pat);
        String npi = command.nextToken();

        Doctor foundDoctor = null;
        for (int i = 0; i < providerList.size(); i++) {
            if (providerList.get(i) instanceof Doctor) {
                Doctor doctor = (Doctor) providerList.get(i);
                if (doctor.getNpi().equals(npi)) { foundDoctor = doctor; }
            }
        }
        if (foundDoctor == null) { System.out.println(npi + " - provider doesn't exist."); return; }

        Appointment newAppointment = new Appointment(date, timeslot, patient, foundDoctor);
        Appointment existingAppointment = null;

        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment currentAppointment = appointmentList.get(i);
            if (currentAppointment.getDate().equals(newAppointment.getDate()) &&
                    currentAppointment.getTimeslot().equals(newAppointment.getTimeslot()) &&
                    currentAppointment.getProvider().equals(newAppointment.getProvider())) {
                existingAppointment = currentAppointment;
                break;
            }
        }
        printD(newAppointment, existingAppointment);
    }

    /**
     * Method to check if a certain location has a room available for a certain type of imaging
     * service at the requested date and timeslot
     * @param date of the requested appointment
     * @param timeslot of the requested appointment
     * @param room type of imaging service
     * @param location of the clinic where the imaging type is provided
     * @return
     */
    private boolean isLocationAvailable(Date date, Timeslot timeslot, Radiology room, Location location) {
        boolean available = true;
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i) instanceof Imaging) {
                Imaging currentImaging = (Imaging) appointmentList.get(i);
                if (currentImaging.getDate().equals(date) &&
                    currentImaging.getTimeslot().equals(timeslot) &&
                    currentImaging.getTechnician().getLocation().getName().equals(location.getName()) &&
                    currentImaging.getRoom().equals(room)) {
                    available = false;
                    break;
                }
            }
        }
        return available;
    }

    /**
     * Method to check if a technician is available for the requested imaging appointment
     * @param technician being checked for availability
     * @param date of the requested imaging appointment
     * @param timeslot of the requested imaging appointment
     * @return true if the technician is available and false if not
     */
    private boolean isTechnicianAvailable(Technician technician, Date date, Timeslot timeslot) {
        boolean available = true;
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment currentAppointment = appointmentList.get(i);
            if ((currentAppointment.getDate().equals(date)) &&
                    (currentAppointment.getTimeslot().equals(timeslot)) &&
                    (currentAppointment.getProvider().equals(technician))) {
                available = false;
                break;
            }
        }
        return available;
    }

    /**
     * Method to handle patient inputs with the "T" command,
     * indicating a new office appointment with any available
     * technician of a specific imaging service at a certain
     * location
     * @param command input line from the run function
     * @param technicianCircular the circularly linked list of technicians
     * from which it assigns a certain appointment
     */
    private void operationT(StringTokenizer command, CircularlyLinkedList technicianCircular) {
        Date date = new Date(command.nextToken());
        String timeToken = command.nextToken();
        int time;
        try { time = Integer.parseInt(timeToken); }
        catch (NumberFormatException e) { System.out.println(timeToken + " is not a valid time slot."); return; }
        if (time <= 0 || time > 12) { System.out.println(time + " is not a valid time slot."); return; }
        Timeslot timeslot = Timeslot.getTimeslot(time);
        String fname = command.nextToken();
        String lname = command.nextToken();
        Date dob = new Date(command.nextToken());
        if (!dateCheck(date) || !dobCheck(dob)) { return; }
        Profile pat = new Profile(fname,lname,dob);
        Person patient = new Person(pat);
        String roomServiceStr = command.nextToken();
        Radiology.ImagingService roomService;
        try { roomService = Radiology.ImagingService.valueOf(roomServiceStr.toUpperCase()); }
        catch (IllegalArgumentException e) { System.out.println(roomServiceStr + " - imaging service not provided."); return; }
        Radiology room = new Radiology(roomService);
        if (!room.isImagingValid())  {System.out.println(roomServiceStr + " - imaging service not provided."); return; }

        Technician assignedTechnician = null;
        Technician currentTechnician = technicianCircular.getHead();

        do { if (!isLocationAvailable(date,timeslot,room,currentTechnician.getLocation()) || !isTechnicianAvailable(currentTechnician, date, timeslot)) {
            currentTechnician = technicianCircular.next(currentTechnician); }
            else if (isLocationAvailable(date,timeslot,room,currentTechnician.getLocation()) && isTechnicianAvailable(currentTechnician, date, timeslot)) {
                assignedTechnician = currentTechnician; break; }
        } while (currentTechnician != technicianCircular.getHead());

        if (assignedTechnician != null) {
            Imaging image = new Imaging(date, timeslot, patient, assignedTechnician, room);
            if (patientAvailable(image)) {
                appointmentList.add(image);
                System.out.println(image.toString() + room.toString() + " booked.");
                technicianCircular.setHead(technicianCircular.next(assignedTechnician));
            } else { System.out.println(image.getPatient().getProfile().toString() + " has an existing appointment at the same time slot."); }
        } else { System.out.println("Cannot find an available technician at all locations for " + room.toString() + " at " + timeslot.getNumber() + "."); }
    }

    /**
     * Method to see if a certain patient has an appointment at a certain time/date
     * @param date of supposed appointment
     * @param timeslot of supposed appointment
     * @param patient being examined
     * @return the appointment that corresponds to the given data or null if not
     */
    private Appointment patientHasAppointment(Date date, Timeslot timeslot, Person patient) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment currentAppointment = appointmentList.get(i);
            if (currentAppointment.getPatient().equals(patient) &&
                    currentAppointment.getDate().equals(date) &&
                    currentAppointment.getTimeslot().equals(timeslot)) {
                return currentAppointment;
            }
        }
        return null;
    }

    /**
     * Method to handle patient inputs with the "C" command,
     * indicating cancelling an existing appointment
     * @param command input line from the run function
     */
    private void operationC(StringTokenizer command) {
        Date date = new Date(command.nextToken());
        String timeToken = command.nextToken();
        int time;
        try { time = Integer.parseInt(timeToken); }
        catch (NumberFormatException e) { System.out.println(timeToken + " is not a valid time slot."); return; }
        if (time <= 0 || time > 12) { System.out.println(time + " is not a valid time slot."); return; }
        Timeslot timeslot = Timeslot.getTimeslot(time);
        String fname = command.nextToken();
        String lname = command.nextToken();
        Date dob = new Date(command.nextToken());
        if (!dateCheck(date) || !dobCheck(dob)) { return; }
        Profile pat = new Profile(fname,lname,dob);
        Person patient = new Person(pat);
        boolean cancelled = false;

        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment currentAppointment = appointmentList.get(i);
            if (currentAppointment.getDate().equals(date) &&
                    currentAppointment.getTimeslot().equals(timeslot) &&
                    currentAppointment.getPatient().equals(patient)) {
                System.out.println(date.toString() + " " +
                        timeslot.toString() + " " + fname + " " + lname + " " +
                        dob.toString() + " " + " - appointment has been canceled.");
                appointmentList.remove(currentAppointment);
                cancelled = true;
                break;
            }
        }
        if (!cancelled) { System.out.println(date.toString() + " " +
                timeslot.toString() + " " + fname + " " + lname + " " +
                dob.toString() + " " + " - appointment does not exist."); }
    }

    /**
     * Method to check if a certain patient is available for an appointment or not
     * @param appointment we are seeing patient is available for or ot
     * @return true if patient is available, false if not
     */
    private boolean providerAvailable(Appointment appointment) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment currentAppointment = appointmentList.get(i);
            if (currentAppointment.getDate().equals(appointment.getDate()) &&
                    currentAppointment.getTimeslot().equals(appointment.getTimeslot()) &&
                    currentAppointment.getProvider().equals(appointment.getProvider())) {
                return false;
            }
        }
        return true;
    }


    /**
     * If R button is pressed, this method will reschedule an appointment if possible. It wouldn't be possible
     * if the doctor or technician already has an appointment at the new timeslot, the old appointment didn't exist.
     * @param command input line from the run function
     */
    private void operationR(StringTokenizer command) {
        Date date = new Date(command.nextToken());
        String timeToken = command.nextToken();
        int time;
        try { time = Integer.parseInt(timeToken); }
        catch (NumberFormatException e) { System.out.println(timeToken + " is not a valid time slot."); return; }
        if (time <= 0 || time > 12) { System.out.println(time + " is not a valid time slot."); return; }
        Timeslot timeslot = Timeslot.getTimeslot(time);
        String fname = command.nextToken();
        String lname = command.nextToken();
        Date dob = new Date(command.nextToken());
        if (!dateCheck(date) || !dobCheck(dob)) { return; }
        Profile pat = new Profile(fname,lname,dob);
        Person patient = new Person(pat);
        String timeToken2 = command.nextToken();
        int time2;
        try { time2 = Integer.parseInt(timeToken2); }
        catch (NumberFormatException e) { System.out.println(timeToken2 + " is not a valid time slot."); return; }
        if (time2 <= 0 || time2 > 12) { System.out.println(time2 + " is not a valid time slot."); return; }
        Timeslot newTimeslot = Timeslot.getTimeslot(time2);

        Appointment oldAppointment = patientHasAppointment(date, timeslot, patient);
        if (oldAppointment == null) {
            System.out.println(date.toString() + " " + timeslot.toString() + " " + patient.getProfile().toString() +  " does not exist."); return;
        } else {
            Appointment updatedAppointment = new Appointment(oldAppointment);
            updatedAppointment.setTimeslot(newTimeslot);
            if (!providerAvailable(updatedAppointment)) {
                System.out.println("[" + updatedAppointment.getProvider().toString() + "]" + " is not available at " + newTimeslot.getNumber() + ".");
            } else if (!patientAvailable(updatedAppointment)){
                System.out.println(updatedAppointment.getPatient().getProfile().toString() + " has an existing appointment at " +
                        updatedAppointment.getDate().toString() + " " + updatedAppointment.getTimeslot().toString());
            } else {
                System.out.println("Rescheduled to " + updatedAppointment.toString());
                appointmentList.add(updatedAppointment);
                appointmentList.remove(oldAppointment);
            }
        }
    }


    /**
     * Method to handle the "PC" command, which makes the program print out all the
     * providers sorted by profile and how much they are owed for all their visits
     */
    private void operationPC() {
        if (appointmentList.isEmpty()) { System.out.println("Schedule calendar is empty."); return; }
        System.out.println();
        System.out.println("** Credit amount ordered by provider. **");
        Provider[] uniqueProviders = new Provider[providerList.size()];
        int uniqueCount = 0;
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            Provider tempProvider = (Provider) appointment.getProvider();
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueProviders[j].equals(tempProvider)) { found = true; break; }
            } if (!found) { uniqueProviders[uniqueCount++] = tempProvider; }
        }
        for (int i = 0; i < uniqueCount - 1; i++) {
            for (int j = 0; j < uniqueCount - 1 - i; j++) {
                if (uniqueProviders[j].getProfile().compareTo(uniqueProviders[j + 1].getProfile()) > 0) {
                    Provider temp = uniqueProviders[j];
                    uniqueProviders[j] = uniqueProviders[j + 1];
                    uniqueProviders[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < uniqueCount; i++) {
            Provider provider = uniqueProviders[i];
            int totalCredit = 0;
            for (int j = 0; j < appointmentList.size(); j++) {
                if (appointmentList.get(j).getProvider().equals(provider)) {
                    totalCredit += provider.rate();
                }
            }
            DecimalFormat dollarFormat = new DecimalFormat("$#,###.00");
            String formattedAmount = dollarFormat.format(totalCredit);
            System.out.println("(" + (i+1) + ") " + provider.getProfile().toString().toUpperCase() + " [credit amount: " + formattedAmount + "]");
        }
        System.out.println("** end of list **");
    }

    /**
     * Method to handle the "PS" command, which makes the program print out all the
     * patients sorted by profile and how much they owe for all their visits, also
     * empties the appointment list after
     */
    private void operationPS() {
        if (appointmentList.isEmpty()) { System.out.println("Schedule calendar is empty."); return; }
        System.out.println();
        System.out.println("** Billing statement ordered by patient. **");
        Person[] uniquePatients = new Person[100];
        int uniqueCount = 0;
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            Person tempPatient = appointment.getPatient();
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniquePatients[j].equals(tempPatient)) { found = true; break; }
            }
            if (!found) { uniquePatients[uniqueCount++] = tempPatient; }
        }
        for (int i = 0; i < uniqueCount - 1; i++) {
            for (int j = 0; j < uniqueCount - 1 - i; j++) {
                if (uniquePatients[j].getProfile().compareTo(uniquePatients[j + 1].getProfile()) > 0) {
                    Person temp = uniquePatients[j];
                    uniquePatients[j] = uniquePatients[j + 1];
                    uniquePatients[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < uniqueCount; i++) {
            Person  patient = uniquePatients[i];
            int totalCharge = 0;
            for (int j = 0; j < appointmentList.size(); j++) {
                if (appointmentList.get(j).getPatient().equals(patient)) {
                    Provider provider = (Provider) appointmentList.get(j).getProvider();
                    totalCharge += provider.rate();
                }
            }
            DecimalFormat dollarFormat = new DecimalFormat("$#,###.00");
            String formattedAmount = dollarFormat.format(totalCharge);
            System.out.println("(" + (i+1) + ") " + patient.getProfile().toString() + " [due: " + formattedAmount + "]");
        }
        System.out.println("** end of list **");
        appointmentList = null;
    }

        /**
         * Main method to run the project, taking in inputs and expressing outputs.
         */
        public void run () {
            readProvidersTXT();
            Sort.provider(providerList);
            CircularlyLinkedList technicianCircular = technicianList(technicianList);
            technicianCircular.print();
            System.out.println();

            boolean clinicRunning = true;
            System.out.println("Clinic Manager is running.");
            Scanner scan = new Scanner(System.in);
            while (clinicRunning) {
                String input = scan.nextLine();
                if (input.isEmpty()) { continue; }
                StringTokenizer command = new StringTokenizer(input, ",");
                String operation = command.nextToken();
                if (!isCommandValid(command, operation)) { System.out.println("Missing data tokens."); continue; }
                if (operation.equals("D")) { operationD(command); continue; }
                if (operation.equals("T")) { operationT(command, technicianCircular); continue; }
                if (operation.equals("PA") || operation.equals("PP") || operation.equals("PL")
                        || operation.equals("PO") || operation.equals("PI")) {
                    Sort.appointment(appointmentList, operation); continue; }
                if (operation.equals("C")) { operationC(command); continue; }
                if (operation.equals("R")) { operationR(command); continue; }
                if (operation.equals("PC")) { operationPC(); continue; }
                if (operation.equals("PS")) { operationPS(); continue; }
                if (operation.equals("Q")) { System.out.println("Clinic Manager terminated.");
                    clinicRunning = false; break; }
                System.out.println("Invalid command!");
            }
        }
    }