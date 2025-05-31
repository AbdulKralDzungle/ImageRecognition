package main.controol;

import main.network.DataReader;
import main.network.Network;

/**
 * serializes the network
 */
public class SaveNetwork extends Command {

    @Override
    public Network execute(Network network, DataReader dataReader, String token, double[] input) throws Exception {
        network.writeToFile(token);
        return network;
    }

    @Override
    public String output() {
        return "";
    }

    @Override
    public void getExtra(String extra) {

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
