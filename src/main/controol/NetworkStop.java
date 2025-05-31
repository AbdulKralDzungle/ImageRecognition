package main.controol;

import main.network.DataReader;
import main.network.Network;

public class NetworkStop extends Command {
    @Override
    public Network execute(Network network, DataReader dataReader, String token, double[] input) throws Exception {
        return network;
    }

    @Override
    public String output() {
        return "";
    }

    @Override
    public boolean exit() {
        return true;
    }

    @Override
    public String nextState() {
        return "";
    }
}
