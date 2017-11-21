import bean.MqBean;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SenderDemo {

    public static void main(String[] args) {
        QueueConnectionFactory connectionFactory;
        QueueConnection connection;
        QueueSession session;
        Queue queue;
        QueueSender sender;

        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");

        try {
            connection = connectionFactory.createQueueConnection();
            connection.start();
            session = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
            queue = session.createQueue("listener/queue");
            sender = session.createSender(queue);
            sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            MqBean bean = new MqBean();
            for (int i = 0; i < 100; i++) {
                bean.setAge(i);
                bean.setName("第" + i + "位");
                sender.send(session.createObjectMessage(bean));
                System.out.println(System.currentTimeMillis() / 1000 + "生产第" + i + "位");
                Thread.sleep(1000);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
