package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.persistence.daos.jdbc.JDBCAccountDao;
import io.codeforall.bootcamp.javabank.persistence.daos.jdbc.JDBCCustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCTransactionManager;
import io.codeforall.bootcamp.javabank.services.AccountServiceImpl;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        JDBCSessionManager JDBCSessionManager = new JDBCSessionManager();
        JDBCTransactionManager transactionManager = new JDBCTransactionManager();
        transactionManager.setConnectionManager(JDBCSessionManager);
        JDBCAccountDao JDBCAccountDao = new JDBCAccountDao();
        JDBCCustomerDao JDBCCustomerDao = new JDBCCustomerDao();

        JDBCAccountDao.setConnectionManager(JDBCSessionManager);
        JDBCCustomerDao.setAccountDAO(JDBCAccountDao);
        JDBCCustomerDao.setConnectionManager(JDBCSessionManager);

        AccountServiceImpl accountService = new AccountServiceImpl();
        CustomerServiceImpl customerService = new CustomerServiceImpl();

        customerService.setCustomerDAO(JDBCCustomerDao);
        customerService.setTm(transactionManager);

        accountService.setAccountDAO(JDBCAccountDao);
        accountService.setTm(transactionManager);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);
        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

        JDBCSessionManager.stopSession();

    }
}
