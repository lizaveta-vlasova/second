package org.jboss.as.quickstarts.ejbinwar.service;


import org.jboss.as.quickstarts.ejbinwar.controller.TariffWSEndpoint;
import org.jboss.as.quickstarts.ejbinwar.dao.daoImpl.TariffDao;
import org.jboss.as.quickstarts.ejbinwar.domain.Tariff;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class TariffService implements Serializable {

    @EJB
    private TariffDao tariffDao;

    @EJB
    private TariffWSEndpoint tariffWSEndpoint;


    public List<Tariff> findAll() {
        return tariffDao.findAll();
    }

    public void sendToClient(String text) {
        List<Tariff> all = tariffDao.findAll();
//        tariffWSEndpoint.send(all);
        tariffWSEndpoint.send(text);
    }
}
