package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.model.Recipient;
import io.codeforall.bootcamp.javabank.persistence.dao.RecipientDao;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaSessionManager;

/**
 * A JPA {@link RecipientDao} implementation
 */
public class JpaRecipientDao extends GenericJpaDao<Recipient> implements RecipientDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(JpaSessionManager, Class)
     */
    public JpaRecipientDao(JpaSessionManager sm) {
        super(sm, Recipient.class);
    }
}
