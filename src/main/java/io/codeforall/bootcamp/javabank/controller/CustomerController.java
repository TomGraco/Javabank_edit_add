package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "customer/list";
    }

    /**
     * Renders a view with customer details
     *
     * @param id    the customer id
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        model.addAttribute("recipients", customerService.listRecipients(id));
        return "customer/show";
    }

    /**
     * Deletes a customer
     *
     * @param id the customer id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }

    /**
     * Deletes a recipient from a customer
     *
     * @param cid the customer id
     * @param rid the recipient id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/recipient/{rid}/delete/")
    public String deleteRecipient(@PathVariable Integer cid, @PathVariable Integer rid) {
        customerService.removeRecipient(cid, rid);
        return "redirect:/customer/" + cid;
    }


    @RequestMapping(method = RequestMethod.GET, path = "{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {
        Customer customer = customerService.get(id);
        if (customer == null) {
            // Handle customer not found case (could redirect to an error page)
            return "redirect:/customer/list";
        }
        model.addAttribute("customer", customer);
        return "/customer/editcustomer";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public String saveCustomer( @PathVariable Integer id,@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult,RedirectAttributes redirectAttributes) {

        Customer exists = customerService.get(id);
        if(bindingResult.hasErrors()){
            return "/customer/editcustomer";
        }

        exists.setFirstName(customer.getFirstName());
        exists.setLastName(customer.getLastName());
        exists.setEmail(customer.getEmail());
        exists.setPhone(customer.getPhone());
        customerService.update(exists);


        redirectAttributes.addFlashAttribute("lastAction", "Edit customer successfully!");
        return "redirect:/customer/list";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "/customer/add";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/add"})
    public String saveCustomer(@Valid @ModelAttribute("customer")CustomerDto customerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        Customer savedCustomer = new Customer();
        if(bindingResult.hasErrors()){
            return "/customer/add";
        }
        savedCustomer.setFirstName(customerDto.getFirstName());
        savedCustomer.setLastName(customerDto.getLastName());
        savedCustomer.setEmail(customerDto.getEmail());
        savedCustomer.setPhone(customerDto.getPhone());
        customerService.update(savedCustomer);

        redirectAttributes.addFlashAttribute("lastAction", "Added customer successfully!");
        return "redirect:/customer/list";

    }
}

