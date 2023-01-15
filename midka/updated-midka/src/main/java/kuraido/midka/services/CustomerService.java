package kuraido.midka.services;

import kuraido.midka.models.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void deleteCustomerById(Long id);
}
