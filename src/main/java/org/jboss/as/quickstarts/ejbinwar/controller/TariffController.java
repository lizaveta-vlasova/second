package org.jboss.as.quickstarts.ejbinwar.controller;


import org.jboss.as.quickstarts.ejbinwar.domain.Tariff;
import org.jboss.as.quickstarts.ejbinwar.dto.TariffDTO;
import org.jboss.as.quickstarts.ejbinwar.service.TariffService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/tariff")
@RequestScoped
public class TariffController {

    @EJB
    private TariffService tariffService;

    @Path("/all")
    @GET
    @Produces("application/json")
    public List<TariffDTO> tariffList() {
        return tariffService.findAll();
    }

}
