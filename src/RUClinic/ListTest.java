package RUClinic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ListTest {
    List<Provider> providerList = new List<>();

    @Before
    public void setUp() throws Exception {}

    @Test
    public void listTest() {
        Date dob1 = new Date("01/21/1989");
        Location location1 = new Location("BRIDGEWATER");
        Specialty specialty1 = new Specialty("FAMILY");
        Profile profile1 = new Profile("ANDREW", "PATEL", dob1);
        Doctor doctor1 = new Doctor(profile1, location1, specialty1, "01");

        Date dob2 = new Date("11/14/1987");
        Location location2 = new Location("PISCATAWAY");
        Profile profile2 = new Profile("GARY", "JOHNSON", dob2);
        Technician technician1 = new Technician(profile2, location2, 110);

        providerList.add(doctor1); // Add doctor to the providerList
        providerList.add(technician1); // Add technician to the providerList
        assert(providerList.contains(doctor1));
        assert(providerList.contains(technician1));

        providerList.remove(doctor1); // Remove doctor from the providerList
        providerList.remove(technician1); // Remove technician from the providerList
        assertFalse(providerList.contains(doctor1));
        assertFalse(providerList.contains(technician1));
    }

}

