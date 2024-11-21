package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BalanceControllerTest {

    private final static double DOUBLE_DELTA = 0.1;

    private BalanceController balanceController;
    private CustomerService customerService;
    private AuthService authService;
    private Customer customer;
    private View view;

    @Before
    public void setup() {

        balanceController = new BalanceController();
        view = mock(View.class);
        customerService = mock(CustomerService.class);
        authService = mock(AuthService.class);
        customer = mock(Customer.class);

        balanceController.setView(view);
        balanceController.setCustomerService(customerService);
        balanceController.setAuthService(authService);

    }

    @Test
    public void initTest() {

        balanceController.init();
        verify(view).show();

    }

    @Test
    public void getCustomerBalanceTest() {

        // fake accessing customer
        Integer fakeId = 999;
        double fakeBalance = 20.5;

        when(authService.getAccessingCustomer()).thenReturn(customer);
        when(customer.getId()).thenReturn(fakeId);
        when(customerService.getBalance(fakeId)).thenReturn(fakeBalance);

        assertEquals(balanceController.getCustomerBalance(), fakeBalance, DOUBLE_DELTA);

    }

    @Test
    public void getCustomerTest() {

        // fake accessing customer
        when(authService.getAccessingCustomer()).thenReturn(customer);

        Customer bCustomer = balanceController.getCustomer();
        assertEquals(bCustomer, customer);
    }
}
