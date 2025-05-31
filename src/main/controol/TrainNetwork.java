package main.controol;

import main.network.DataReader;
import main.network.Network;

public class TrainNetwork extends Command {


    @Override
    public void execute(Network network, DataReader dataReader, String token, double[] input) {

    }

    @Override
    public int output() {
        return 0;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
