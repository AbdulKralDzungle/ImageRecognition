package main.controol;

import java.util.HashMap;

public class NetworkThred implements Runnable {
    private HashMap<String, Command> commands;
    private String commandText;
    private Command command;

    public NetworkThred() {
        initialize();
    }

    private void initialize() {
        commands = new HashMap<>();
        commands.put("train", new TrainNetwork());
        commands.put("tick", new TrainNetwork());
        commands.put("load", new TrainNetwork());
        commands.put("sleep", new TrainNetwork());
        command = new NetworkTick();
    }

    @Override
    public void run() {
        while (!command.exit()) {
            command.execute();
        }
    }
}
