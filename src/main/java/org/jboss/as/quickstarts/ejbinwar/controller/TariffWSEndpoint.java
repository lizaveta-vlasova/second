package org.jboss.as.quickstarts.ejbinwar.controller;

import org.jboss.as.quickstarts.ejbinwar.domain.Tariff;
import org.jboss.as.quickstarts.ejbinwar.dto.TariffDTO;
import org.jboss.as.quickstarts.ejbinwar.json.JSONEncoder;
import org.jboss.as.quickstarts.ejbinwar.service.SessionRegistry;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;


@ServerEndpoint(value ="/socket/tariff", encoders={JSONEncoder.class})
@Stateless
public class TariffWSEndpoint {


    @EJB
    private SessionRegistry sessionRegistry;


    @OnOpen
    public void open(Session session) {
        sessionRegistry.add(session);
        System.out.println("Open session:" + session.getId());
    }

    @OnClose
    public void close(Session session, CloseReason c) {
        sessionRegistry.remove(session);
        System.out.println("Closing:" + session.getId());
    }

/*    public void send(List<Tariff> tariffs) {
        sessionRegistry.getAll().forEach(session -> session.getAsyncRemote().sendObject(tariffs));
    }*/

    public void send(List<TariffDTO> tariffDTOS) {
        sessionRegistry.getAll().forEach(session -> session.getAsyncRemote().sendObject(tariffDTOS));
    }

    @OnMessage
    public void receiveMessage(String message, Session session) {

        System.out.println("Received : "+ message + ", session:" + session.getId());
//        log.info("Received : "+ message + ", session:" + session.getId());
    }
}
