package listener.queue;

import listener.MyAbstractListener;
import javax.jms.MessageListener;

public class QueueReceiverA extends MyAbstractListener implements MessageListener {
    private String classNmae = "QueueReceiverA";
}
