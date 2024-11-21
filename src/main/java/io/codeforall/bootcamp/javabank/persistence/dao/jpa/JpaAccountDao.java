package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.dao.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaSessionManager;

/**
 * A JPA {@link AccountDao} implementation
 */
public class JpaAccountDao extends GenericJpaDao<Account> implements AccountDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(JpaSessionManager, Class)
     */
    public JpaAccountDao(JpaSessionManager sm) {
        super(sm, Account.class);
    }
}
