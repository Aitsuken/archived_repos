package kuraido.midka.controllers;


import kuraido.midka.models.Customer;
import kuraido.midka.repositories.CustomerRepository;
import kuraido.midka.services.CustomerService;
import org.hibernate.engine.jdbc.env.spi.AnsiSqlKeywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/addCustomer")
    public String doCreateUser(@Valid @ModelAttribute("formData") Customer customer,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "customers/create";
        }

        customerService.addCustomer(customer);

        return "redirect:/customers";
    }

    @RequestMapping("/customers")
    public ModelAndView getCustomersPage(){
        return new ModelAndView("customers","customers",customerService.getAllCustomers());
    }



}

