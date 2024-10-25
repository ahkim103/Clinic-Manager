package RUClinic;

/**
 * This class is solely for maintaining the technicians in a circular linked list
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class CircularlyLinkedList {
    private Node head;
    private Node current;

    /**
     * Node class for the linked list
     */
    private class Node {
        Technician technician;
        Node next;

        public Node(Technician technician) {
            this.technician = technician;
            this.next = null;
        }
    }

    /**
     * Method to add a technician to the linked list
     * @param technician being added
     */
    public void add(Technician technician) {
        Node newNode = new Node(technician);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node tail = head;
            while (tail.next != head) {
                tail = tail.next;
            }
            tail.next = newNode;
            newNode.next = head;
        }
    }

    /**
     * Method to reverse a given linked list
     */
    public void reverse() {
        if (head == null || head.next == head) { return; }

        Node prev = head;
        Node current = head.next;
        Node nextNode;

        while (current != head) {
            nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        head.next = prev;
        head = prev;
    }

    /**
     * Method to retrieve the next technician after a given technician.
     * @param currentTechnician the current Technician from which we want the next.
     * @return the next Technician in the circular list.
     */
    public Technician next(Technician currentTechnician) {
        if (head == null || currentTechnician == null) {
            return null;
        }

        Node currentNode = head;
        do {
            if (currentNode.technician.equals(currentTechnician)) {
                return currentNode.next.technician;
            }
            currentNode = currentNode.next;
        } while (currentNode != head);

        return null;
    }

    /**
     * Method to get the starting point (head) of the circular list.
     * @return the first Technician in the list (head).
     */
    public Technician getHead() {
        if (head != null) {
            return head.technician;
        }
        return null;
    }

    /**
     * Method to set the head of the circular list to a specific technician.
     * @param technician the technician to be set as the new head.
     */
    public void setHead(Technician technician) {
        if (head == null || technician == null) {
            return;
        }

        Node currentNode = head;

            do {
                if (currentNode.technician.equals(technician)) {
                head = currentNode;
                return;
            }
            currentNode = currentNode.next;
        } while (currentNode != head);
    }


    /**
     * Method for printing out the linked list
     */
    public void print() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Rotation list for the technicians.");
        Node current = head;
        do {
            Profile profile = current.technician.getProfile();
            System.out.println(profile.getFname().toUpperCase() + " " +
                    profile.getLname().toUpperCase() + " (" +
                    current.technician.getLocation().getName() + ")");
            current = current.next;
            if (current != head) {
                System.out.print(" --> ");
            }
        } while (current != head);
    }
}
