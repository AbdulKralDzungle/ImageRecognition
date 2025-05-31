package main.controol;

import main.network.DataReader;
import main.network.Network;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class NetworkThred implements Runnable {
    private String filePath;
    private double[] input;
    private boolean exit;
    private String output;
    private boolean done;
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

    public void setInput(double[][] input) {
        double[][] newInput = rearangeinput(input);
        this.input = new double[784];
        int i = 0;
        for (double[] j : newInput) {
            for (double k : j) {
                this.input[i] = k;
                i++;
            }
        }

    }


    private static double[][] rearangeinput(double[][] input) {
        double[][] newInput = new double[28][28];

        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                try {
                    double temp = input[i][j];
                    temp += input[j - 1][i];
                    temp += input[j - 1][i + 1];
                    temp += input[j - 1][i - 1];
                    temp += input[j + 1][i];
                    temp += input[j + 1][i + 1];
                    temp += input[j + 1][i - 1];
                    temp += input[j][i + 1];
                    temp += input[j][i - 1];
                    newInput[i][j] = temp;
                    newInput[i][j] = newInput[i][j] / 5;
                } catch (Exception e) {
                    //ignoruj
                }

            }
        }
        return newInput;
    }

    /**
     * standard initialization of staff and a design pattern
     */
    private void initialize() {
        exit = false;
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
        commands.put("stop", new NetworkStop());
        commands.put("exit", new NetworkStop());
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

    /**
     * this method extracts command from user input
     *
     * @param commandText is user input
     * @throws Exception is thrown when user uses invalid command
     */
    public void setCommandText(String commandText) throws Exception {
        String[] commandParts = commandText.split(" ");
        commandParts[0] = commandParts[0].toLowerCase();
        if (!commands.containsKey(commandParts[0])) {
            throw new Exception(commandText + " is not a valid command");
        }
        this.commandText = commandParts[0];
        try {
            specification = commandParts[1];
        } catch (Exception e) {
            // try / catch used instead of an if else statement
        }


    }


    public boolean isDone() {
        return done;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * this run method is where the command design pattern is executed
     */
    @Override
    public void run() {
        done = false;
        while (!exit) {
            command = commands.get(commandText);
            try {
                nt = command.execute(nt, reader, specification, input);
                output = command.output();
                if (!command.nextState().isEmpty()) {
                    commandText = command.nextState();
                    command = commands.get(commandText);
                }
            } catch (Exception e) {
                output = e.getMessage();
            }
            exit = command.exit();
        }
        done = true;
    }
}
