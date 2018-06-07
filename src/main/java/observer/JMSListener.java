/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import models.Achievement;

/**
 * @author runef
 */
@JMSDestinationDefinition(
        name = "java:global/queue/achievementQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "receiverQueue"
)
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java:global/queue/receiverQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class JMSListener implements MessageListener {

    public JMSListener() {
        System.out.println("######################################################################");
        System.out.println("-                        JMS Listener created!                       -");
        System.out.println("-                                                                    -");
        System.out.println("-                  Factoryname:     receiverQueueFactory             -");
        System.out.println("-                  Resourcename:    receiver Queue                 -");
        System.out.println("-                                                                    -");
        System.out.println("-                  IP:              ???                              -");
        System.out.println("-                  Port:            7676                             -");
        System.out.println("######################################################################");
    }

    @Override
    public void onMessage(Message message) {
        try {
            Gson g = new Gson();
            List<Achievement> achievements = g.fromJson(message.getBody(String.class), new TypeToken<List<Achievement>>(){}.getType());
            for(Achievement a : achievements){
                System.out.println(a.getName());
            }
            
            //Do something
        } catch (JMSException ex) {
            Logger.getLogger(MessageConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
