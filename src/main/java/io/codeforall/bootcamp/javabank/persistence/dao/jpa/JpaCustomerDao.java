package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.TransactionException;
import io.codeforall.bootcamp.javabank.persistence.dao.CustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * A JPA {@link CustomerDao} implementation
 */
public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(JpaSessionManager, Class)
     */
    public JpaCustomerDao(JpaSessionManager sm) {
        super(sm, Customer.class);
    }

    /**
     * @see CustomerDao#getCustomerIds()
     */
    public List<Integer> getCustomerIds() {
        try {

            EntityManager em = sm.getCurrentSession();
            return em.createQuery("select id from Customer", Integer.class)
                    .getResultList();

        } catch (PersistenceException ex) {
            throw new TransactionException(ex);
        }
    }
}
