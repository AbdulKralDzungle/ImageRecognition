package main.controol;

import main.network.DataReader;
import main.network.Network;

public class NetworkTick extends Command {

    private int answer;

    @Override
    public void execute(Network network, DataReader dataReader, String token, double[] input) {
        answer = network.answer(input);
        //System.out.println("Answer: " + answer);
    }

    @Override
    public String output() {
        return String.valueOf(answer);
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
