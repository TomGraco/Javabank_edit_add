package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.model.Recipient;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipientsControllerTest {

    private RecipientsController recipientsController;
    private CustomerService customerService;
    private AuthService authService;
    private Customer customer;
    private View view;

    @Before
    public void setup() {

        recipientsController = new RecipientsController();
        view = mock(View.class);
        customerService = mock(CustomerService.class);
        authService = mock(AuthService.class);
        customer = mock(Customer.class);

        recipientsController.setView(view);
        recipientsController.setCustomerService(customerService);
        recipientsController.setAuthService(authService);

    }

    @Test
    public void initTest() {

        recipientsController.init();
        verify(view).show();

    }

    @Test
    public void getRecipientsTest() {

        // fake accessing customer
        Integer fakeId = 999;
        List<Recipient> fakeRecipients = new ArrayList<>();
        when(authService.getAccessingCustomer()).thenReturn(customer);
        when(customer.getId()).thenReturn(fakeId);
        when(customerService.listRecipients(fakeId)).thenReturn(fakeRecipients);

        assertEquals(recipientsController.getRecipients(), fakeRecipients);
    }
}
