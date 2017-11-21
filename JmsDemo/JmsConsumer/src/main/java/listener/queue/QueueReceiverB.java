package listener.queue;

import listener.MyAbstractListener;

import javax.jms.MessageListener;

public class QueueReceiverB extends MyAbstractListener implements MessageListener {
    private String classNmae = "QueueReceiver";
}
