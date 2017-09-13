package org.jboss.as.quickstarts.ejbinwar.service;


import org.jboss.as.quickstarts.ejbinwar.controller.TariffWSEndpoint;
import org.jboss.as.quickstarts.ejbinwar.dao.daoImpl.TariffDao;
import org.jboss.as.quickstarts.ejbinwar.domain.Option;
import org.jboss.as.quickstarts.ejbinwar.domain.Tariff;
import org.jboss.as.quickstarts.ejbinwar.dto.TariffDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class TariffService implements Serializable {

    @EJB
    private TariffDao tariffDao;

    @EJB
    private TariffWSEndpoint tariffWSEndpoint;


    public List<TariffDTO> findAll() {
        List<Tariff> tariffList = tariffDao.findAll();
        List<TariffDTO> tariffDTOS = new ArrayList<>();
        for (Tariff tariff : tariffList) {
            TariffDTO tariffDTO = new TariffDTO();
            tariffDTO.setId(tariff.getId());
            tariffDTO.setName(tariff.getName());
            tariffDTO.setPrice(tariff.getPrice());
            tariffDTO.setDescription(tariff.getDescription());
            Set<String> types = new HashSet<>();
            for (Option option: tariff.getAvailableOptions()){
                types.add(option.getType());
            }
            tariffDTO.setOptionsNames(types);
            tariffDTOS.add(tariffDTO);
        }
          return tariffDTOS;
        //return tariffDao.findAll();
    }

    public void sendToClient(List<TariffDTO> tariffDTOS) {
//        List<Tariff> all = tariffDao.findAll();
//        tariffWSEndpoint.send(all);
        tariffWSEndpoint.send(tariffDTOS);
    }
}
