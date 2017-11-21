import bean.MqBean;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SubscriberDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            subscriber(i);
        }
    }

    private static void subscriber(int no) {
        ActiveMQConnectionFactory connectionFactory;
        TopicConnection connection;
        TopicSession session;
        Topic topic;
        TopicSubscriber subscriber;

        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
        connectionFactory.setTrustAllPackages(true);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createTopicConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createTopicSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic("listener/topic");
            subscriber = session.createSubscriber(topic);

            subscriber.setMessageListener((Message message) -> {
                try {
                    MqBean bean = (MqBean) ((ObjectMessage) message).getObject();
                    System.out.println(bean);
                    System.out.println("订阅者" + no + "在" + System.currentTimeMillis() + "收到消息" + bean.getAge() + "   " + bean.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
