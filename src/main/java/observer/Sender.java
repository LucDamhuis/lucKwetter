/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sun.messaging.Queue;

import javax.jms.JMSException;
import models.Objective;
import com.google.gson.Gson;
import enums.ObjectiveType;
import java.util.ArrayList;
import java.util.List;
import javax.jms.*;

/**
 *
 * @author Luc
 */
public class Sender {
    
    public static void main(String[] args) {
        Objective o = new Objective();
        o.setId(1l);
        o.setObjectiveType(ObjectiveType.TWEETS_MADE);
        o.setObjectivePointsNeeded(1209349087247892354.0);
        List<Objective> objectives = new ArrayList<>();
        Sender.sendMessage(objectives);
    }
    
    public static void sendMessage(final List<Objective> objective) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "localhost");
            Queue q = new Queue("achievementQueue");
            Gson gson = new Gson();
            String msg = gson.toJson(objective);
            try (Connection connection = connectionFactory.createConnection();
                    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    MessageProducer producer = session.createProducer(q)) {
                Message message = session.createTextMessage(msg);
                producer.send(message);
            }
        } catch (JMSException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
