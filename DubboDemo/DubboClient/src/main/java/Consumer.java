import org.springframework.context.support.ClassPathXmlApplicationContext;
import server.ServerDemo;

import java.util.Scanner;

public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            ServerDemo serverDemo = (ServerDemo) context.getBean("demoService");
            String hello = serverDemo.echo(scanner.nextLine());
            System.out.println(hello);
        }
    }
}
