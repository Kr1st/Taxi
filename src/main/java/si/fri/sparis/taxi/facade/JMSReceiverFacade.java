/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.fri.sparis.taxi.facade;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Kristian
 */
@Singleton
public class JMSReceiverFacade {

    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private ConnectionFactory connectionFactory;
    private Connection c;
    private Session s;
    private MessageConsumer mc;

    @PostConstruct
    public void init() {
        connectionFactory = new ActiveMQConnectionFactory(url);
        try {
            c = connectionFactory.createConnection();
            s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination d = s.createQueue("mojaVrsta");

            mc = s.createConsumer(d);

            c.start();
        } catch (JMSException ex) {
            Logger.getLogger(NarociloFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Session getSession() {
        return s;
    }

    public ObjectMessage receive() {

        try {

            Message msg = mc.receive();

            if (msg instanceof ObjectMessage) {
                ObjectMessage omsg = (ObjectMessage) msg;
                return omsg;
            }
            
        } catch (JMSException ex) {
            Logger.getLogger(JMSReceiverFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @PreDestroy
    public void destroy() {
        try {
            c.close();
        } catch (JMSException ex) {
            Logger.getLogger(JMSReceiverFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
