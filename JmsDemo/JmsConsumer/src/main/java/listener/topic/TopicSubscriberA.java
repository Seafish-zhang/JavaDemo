package listener.topic;

import bean.MqBean;
import listener.MyAbstractListener;

import javax.jms.MessageListener;

public class TopicSubscriberA extends MyAbstractListener implements MessageListener {
    private String classNmae = "TopicSubscriberA";
}
