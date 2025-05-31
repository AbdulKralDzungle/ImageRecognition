package main.controol;

import main.network.DataReader;
import main.network.Network;

public class TrainNetwork extends Command {
    private DataReader reader;
    private double learningRate;
    private String output;

    @Override
    public void execute(Network network, DataReader dataReader, String token, double[] input) throws Exception {
        dataReader = new DataReader(token);
        learningRate = 0.2;


        for (int i = 0; i < 10; i++) {
            input = dataReader.read();
            do {
                network.bProp(dataReader.getLabel(), input.clone());
                input = dataReader.read();
            } while (input != null);
            dataReader.reset();
        }
        int temp = 0;
        int i = 0;
        input = dataReader.read();
        do {
            if (network.answer(input) == dataReader.getLabel()) {
                temp++;
            }
            input = dataReader.read();
            i++;
        } while (input != null);
        output = (temp / i) * 100 + "%";
        System.out.println(output);
    }

    @Override
    public String output() {
        return output;
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
