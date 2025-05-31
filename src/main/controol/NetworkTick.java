package main.controol;

import main.network.DataReader;
import main.network.Network;

public class NetworkTick extends Command {

    private int answer;

    @Override
    public void execute(Network network, DataReader dataReader, String token, double[] input) {
     answer = network.answer(input)
    }

    @Override
    public int output() {
        return 0;
    }
}
