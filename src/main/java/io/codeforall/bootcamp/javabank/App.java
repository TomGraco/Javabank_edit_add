package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.services.AccountServiceImpl;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaAccountDao;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaCustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);

        JpaSessionManager sm = new JpaSessionManager(emf);
        TransactionManager tx = new JpaTransactionManager(sm);

        App app = new App();
        app.bootStrap(tx, sm);

        emf.close();

    }

    private void bootStrap(TransactionManager tx, JpaSessionManager sm) {

        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.setAccountDao(new JpaAccountDao(sm));
        accountService.setTransactionManager(tx);

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.setCustomerDao(new JpaCustomerDao(sm));
        customerService.setTransactionManager(tx);

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
