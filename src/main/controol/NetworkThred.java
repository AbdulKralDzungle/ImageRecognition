package main.controol;

import main.network.DataReader;
import main.network.Network;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class NetworkThred implements Runnable {
    private String filePath;
    private double[] input;
    private int output;
    private HashMap<String, Command> commands;
    private String commandText;
    private double learningRate;
    private Command command;
    //----------------------------------
    private DataReader reader;
    private Network nt;

    //----------------------------------

    public NetworkThred() {
        initialize();
    }

    public void setInput(int[][] input) {
        this.input = new double[784];
        int i = 0;
        for (int[] j : input) {
            for (int k : j) {
                this.input[i] = k;
                i++;
            }
        }

    }

    private void initialize() {
        filePath = "LittleData/MnistTiny";
        //---------------------------------------------------
        learningRate = 0.1;
        nt = new Network(50, 784, learningRate, 2, 10);
        //----------------------------------------------------
        commands = new HashMap<>();
        commandText = "tick";
        commands.put("train", new TrainNetwork());
        commands.put("tick", new NetworkTick());
        commands.put("load", new TrainNetwork());
        commands.put("sleep", new TrainNetwork());
        commands.put("save", new TrainNetwork());
        command = new NetworkTick();
        //---------------------------------------------------
        try {
            reader = new DataReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int getOutput() {
        return output;
    }

    @Override
    public void run() {
        while (!command.exit()) {
            command = commands.get(commandText);
            command.execute(nt, reader, "random string", input);
            output = command.output();
        }
    }
}
