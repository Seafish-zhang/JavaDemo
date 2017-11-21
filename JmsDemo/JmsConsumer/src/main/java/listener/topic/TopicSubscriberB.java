package listener.topic;

import listener.MyAbstractListener;

import javax.jms.MessageListener;

public class TopicSubscriberB extends MyAbstractListener implements MessageListener {
    private String classNmae = "TopicSubscriberB";

}
