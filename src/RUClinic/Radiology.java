package RUClinic;

/**
 * The Radiology class represents a radiology room that provides imaging services
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class Radiology {
    private ImagingService service;

    /**
     * Constructor for the Radiology class
     * @param service type of imaging service provided
     */
    public Radiology(ImagingService service) {
        this.service = service;
    }

    /**
     * Simple enum type that represents the types of imaging services in the clinic
     */
    public enum ImagingService {
        CATSCAN,
        ULTRASOUND,
        XRAY
    }

    /**
     * Helper method to get the type of imaging service provided
     * @return the imaging service
     */
    public ImagingService getService() {
        return service;
    }

    /**
     * Method to check if the current imaging service is valid
     * The service is valid if it is one of the three possible services provided by the clinic
     * @return true if the service is one of the predefined enum values, otherwise false
     */
    public boolean isImagingValid() {
        if (this.service == null) { return false; }
        String serviceNameUpperCase = this.service.name().toUpperCase();
        for (ImagingService validService : ImagingService.values()) {
            if (validService.name().toUpperCase().equals(serviceNameUpperCase)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Radiology other = (Radiology) obj;
        return service == other.service;
    }

    /**
     * Method to return the service of a given provider from a given appointment in string form
     * @return the radiology type/service in string form
     */
    @Override
    public String toString() {
        return ("[" + this.service + "]");
    }

}
