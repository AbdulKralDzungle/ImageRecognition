package main.controol;

import main.network.DataReader;
import main.network.Network;
/**
 * this class is used as a command that makes the network train based on data that are obtained from a file that is chosen by user
 */
public class TrainNetwork extends Command {
    private DataReader reader;
    private double learningRate;
    private String output;

    @Override
    public Network execute(Network nt, DataReader dataReader, String token, double[] input) throws Exception {
        Network network = nt;
        dataReader = new DataReader(token);
        learningRate = 0.5;
        for (int i = 0; i < 10; i++) {
            input = dataReader.read();
            do {
                network.bProp(dataReader.getLabel(), input.clone());
                input = dataReader.read();
            } while (input != null);
            learningRate = learningRate * 0.9;
            network.setLearningRate(learningRate);
            dataReader.reset();
            System.out.println("training " + i * 10 + "% finished");
        }
        double temp = 0;
        int i = 0;
        input = dataReader.read();
        do {

            if (network.answer(input) == dataReader.getLabel()) {
                temp++;
            }
            input = dataReader.read();
            i++;
        } while (input != null);
        dataReader.reset();
        double[] temp2 = dataReader.read();
        output = "network have" + (temp / i) * 100 + "% accuracy and " + nt.callCost(dataReader.getLabel(), temp2);
        return network;
    }

    @Override
    public String output() {
        return output;
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
