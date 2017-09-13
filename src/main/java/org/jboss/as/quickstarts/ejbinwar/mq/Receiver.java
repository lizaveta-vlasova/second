package org.jboss.as.quickstarts.ejbinwar.mq;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jboss.as.quickstarts.ejbinwar.dto.TariffDTO;
import org.jboss.as.quickstarts.ejbinwar.service.TariffService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;

@Startup
@Singleton
public class Receiver implements MessageListener {

    @EJB
    private TariffService tariffService;

    @PostConstruct
    public void receive() {

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        props.put("queue.js-queue", "asd");
        props.put("connectionFactoryNames", "queueCF");

        InitialContext context;
        Queue queue = null;
        QueueConnectionFactory connectionFactory = null;
        QueueConnection queueConnection = null;
        try {
            context = new InitialContext(props);
            queue = (Queue) context.lookup("js-queue");
            connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            queueConnection = connectionFactory.createQueueConnection();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            QueueReceiver queueReceiver = queueSession.createReceiver(queue);

            queueReceiver.setMessageListener(this);

            queueConnection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Получено");
        TextMessage textMessage = (TextMessage) message;
//        List<TariffDTO> tariffDTOS = (List<TariffDTO>)
        String textListTariff = "";
        try {
            textListTariff = textMessage.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
       /* String text="";
        try {
            text = textMessage.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }*/
        Gson gson = new Gson();

        Type listType = new TypeToken<List<TariffDTO>>() {}.getType();

        List<TariffDTO> tariffDTOS = new Gson().fromJson(textListTariff, listType);
        tariffService.sendToClient(tariffDTOS);
        System.out.println("Обновлено");
    }

}
