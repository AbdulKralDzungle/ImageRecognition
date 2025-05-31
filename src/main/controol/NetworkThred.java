package main.controol;

import main.network.DataReader;
import main.network.Network;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class NetworkThred implements Runnable {
    private String filePath;
    private double[] input;
    private String output;
    private String specification;
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
        commands.put("load", new NetworkLoad());
        commands.put("save", new SaveNetwork());
        command = new NetworkTick();
        //---------------------------------------------------
        try {
            reader = new DataReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getOutput() {
        return output;
    }

    public void setCommandText(String commandText) throws Exception {
        String[] commandParts = commandText.split(" ");
        commandParts[0] = commandParts[0].toLowerCase();
        if (!commands.containsKey(commandParts[0])) {
            throw new Exception(commandText + " is not a valid command");
        }
        this.commandText = commandParts[0];
        specification = commandParts[1];
    }


    @Override
    public void run() {
        while (!command.exit()) {
            command = commands.get(commandText);
            try {
                nt = command.execute(nt, reader, specification, input);
                output = command.output();
                if (command.nextState().length() > 1) {
                    commandText = command.nextState();
                }
            } catch (Exception e) {
                output = e.getMessage();
                System.out.println(e.getMessage());
            }

        }
    }
}
