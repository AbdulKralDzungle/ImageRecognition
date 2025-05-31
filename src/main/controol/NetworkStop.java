package main.controol;

import main.network.DataReader;
import main.network.Network;

/**
 * this class is used as a command to stop the application
 */
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
    public void getExtra(String extra) {

    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public String nextState() {
        return "";
    }
}
