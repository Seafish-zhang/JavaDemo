package listener;

import bean.MqBean;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public abstract class MyAbstractListener implements MessageListener{

    private String className = "MyAbstractListener";

    public void onMessage(Message message) {
        try {
            MqBean bean = (MqBean) ((ObjectMessage) message).getObject();
            System.out.println(className + "在" + System.currentTimeMillis() + "收到消息" + bean.getAge() + "   " + bean.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
