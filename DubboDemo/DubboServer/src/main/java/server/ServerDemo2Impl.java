package server;

public class ServerDemo2Impl implements ServerDemo{
    @Override
    public String echo(String receiveString) {
        return "ServerDemo2Impl" + receiveString;
    }
}
