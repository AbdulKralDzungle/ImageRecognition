package main.controol;

import main.network.DataReader;
import main.network.Network;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkThred implements Runnable {
    private HashMap<String, Command> commands;
    private String commandText;
    private double learningRate;
    private Command command;
    private ArrayList<Integer> inputs;
    //----------------------------------
    DataReader reader;
    Network nt;

    //----------------------------------

    public NetworkThred() {
        initialize();
    }

    public void setInputs(int[][] inputs) {
        this.inputs.clear();
        for (int[] i : inputs) {
            for (int j : i) {
                this.inputs.add(j);
            }
        }
    }

    private void initialize() {
        learningRate = 0.1;
        nt = new Network(50, 784, learningRate, 2, 10);
        inputs = new ArrayList<>();
        commands = new HashMap<>();
        commandText = "tick";
        commands.put("train", new TrainNetwork());
        commands.put("tick", new NetworkTick());
        commands.put("load", new TrainNetwork());
        commands.put("sleep", new TrainNetwork());
        commands.put("save", new TrainNetwork());
        command = new NetworkTick();
    }

    @Override
    public void run() {
        command = commands.get(commandText);
        command.execute();
    }
}
