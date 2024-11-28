package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.persistence.dao.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link AccountDao} implementation
 */
@Repository
public class JpaAccountDao extends GenericJpaDao<Account> implements AccountDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaAccountDao() {
        super(Account.class);
    }
}
