package server;

public class ServerDemoImpl implements ServerDemo{
    @Override
    public String echo(String receiveString) {
        return "ServerDemoImpl" + receiveString;
    }
}
