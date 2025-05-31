package main.controol;

import main.network.DataReader;
import main.network.Network;

public class NetworkTick extends Command {

    private int answer;

    @Override
    public void execute(Network network, DataReader dataReader, String token, double[] input) {
        answer = network.answer(input);
        System.out.println("Answer: " + answer);
    }

    @Override
    public int output() {
        return answer;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
