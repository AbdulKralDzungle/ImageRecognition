package main.controol;

import main.network.DataReader;
import main.network.Network;

public class NetworkLoad extends Command {

    @Override
    public void execute(Network network, DataReader dataReader, String token, double[] input) {

    }

    @Override
    public String output() {
        return "";
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
