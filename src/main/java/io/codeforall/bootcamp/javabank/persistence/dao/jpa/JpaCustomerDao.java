package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.persistence.dao.CustomerDao;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link CustomerDao} implementation
 */
@Repository
public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaCustomerDao() {
        super(Customer.class);
    }
}
