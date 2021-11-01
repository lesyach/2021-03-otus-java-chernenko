package homework;

import java.util.LinkedList;

public class CustomerReverseOrder {

    private LinkedList<Customer> linkedList = new LinkedList<>();

    public void add(Customer customer) {
        linkedList.addLast(customer);
    }

    public Customer take() {
        Customer customer = linkedList.getLast();
        linkedList.removeLast();
        return customer;
    }
}
