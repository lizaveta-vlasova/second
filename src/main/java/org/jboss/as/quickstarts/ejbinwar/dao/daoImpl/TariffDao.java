package org.jboss.as.quickstarts.ejbinwar.dao.daoImpl;


import org.jboss.as.quickstarts.ejbinwar.domain.Tariff;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TariffDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Tariff> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM Tariff e");
        List <Tariff> tariff = query.getResultList();
        return tariff;

    }

}