import bean.MqBean;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PublisherDemo {
    public static void main(String[] args) {
        TopicConnectionFactory connectionFactory;
        TopicConnection connection;
        TopicSession session;
        Topic topic;
        TopicPublisher publisher;


        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");

        try {
            connection = connectionFactory.createTopicConnection();
            connection.start();
            session = connection.createTopicSession(false, Session.CLIENT_ACKNOWLEDGE);
            topic = session.createTopic("listener/topic");
            publisher = session.createPublisher(topic);
            publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            MqBean bean = new MqBean();
            for (int i = 0; i < 100; i++) {
                bean.setName("第" + i + "位");
                bean.setAge(i);
                publisher.send(session.createObjectMessage(bean));
                System.out.println(System.currentTimeMillis() / 1000 + "生产第" + i + "位");
                Thread.sleep(1000);
            }

        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
