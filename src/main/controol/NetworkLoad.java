package main.controol;

import main.network.DataReader;
import main.network.Network;

public class NetworkLoad extends Command {
    private Network network;
    private String fileName;
    private String extra;

    @Override
    public Network execute(Network network, DataReader dataReader, String token, double[] input) throws Exception {
        try {
            this.network = Network.readFromFile(token);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return this.network;
    }

    @Override
    public String output() {
        return "";
    }

    @Override
    public void getExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String nextState() {
        return "tick";
    }

}
