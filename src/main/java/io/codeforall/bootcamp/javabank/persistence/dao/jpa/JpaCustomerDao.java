package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.dao.CustomerDao;

import java.util.List;

/**
 * A JPA {@link CustomerDao} implementation
 */
public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public JpaCustomerDao() {
        super(Customer.class);
    }

    /**
     * @see CustomerDao#getCustomerIds()
     */
    public List<Integer> getCustomerIds() {
        return em.createQuery("select id from Customer", Integer.class)
                .getResultList();
    }
}
