import org.springframework.context.support.ClassPathXmlApplicationContext;
import server.ServerDemo;

import java.util.Scanner;

public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            ServerDemo serverDemo = (ServerDemo) context.getBean("demoService1");
            String hello = serverDemo.echo(line);
            System.out.println(hello);
            ServerDemo serverDemo2 = (ServerDemo) context.getBean("demoService2");
            String hello2 = serverDemo2.echo(line);
            System.out.println(hello2);
        }
    }
}
