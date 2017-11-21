package server;

public interface MyInterface {

    public final static int i = 0;

    public static String test1(){
        return "MyInterface test1";
    }

    private String test2(){
        return "MyInterface test2";
    }

    default String check() {
        String result = test1();
        result = test2();
        return "MyInterface check";
    }

    String test3();
}
