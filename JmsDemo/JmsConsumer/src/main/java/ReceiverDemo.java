import bean.MqBean;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ReceiverDemo {
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory;
        QueueConnection connection;
        QueueSession session;
        Queue queue;
        QueueReceiver receiver;

        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
        // activeMq 5.12 要设置包运行
        connectionFactory.setTrustAllPackages(true);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createQueueConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createQueueSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            queue = session.createQueue("listener/queue");
            receiver = session.createReceiver(queue);

            receiver.setMessageListener((Message message) -> {
                try {
                    MqBean bean = (MqBean) ((ObjectMessage) message).getObject();
                    System.out.println(bean);
                    System.out.println(System.currentTimeMillis() + "收到消息" + bean.getAge() + "   " + bean.getName());
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}