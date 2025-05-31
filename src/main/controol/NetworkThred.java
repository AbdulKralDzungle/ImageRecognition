package main.controol;

import main.network.DataReader;
import main.network.Network;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class NetworkThred implements Runnable {
    private String filePath;
    private HashMap<String, Command> commands;
    private String commandText;
    private double learningRate;
    private Command command;
    private ArrayList<Integer> inputs;
    //----------------------------------
    private DataReader reader;
    private Network nt;

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
        filePath = "LittleData/MnistTiny";
        //---------------------------------------------------
        learningRate = 0.1;
        nt = new Network(50, 784, learningRate, 2, 10);
        //----------------------------------------------------
        inputs = new ArrayList<>();
        commands = new HashMap<>();
        commandText = "load";
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

    @Override
    public void run() {
        command = commands.get(commandText);
        command.execute(nt, reader, "random string");
    }
}
