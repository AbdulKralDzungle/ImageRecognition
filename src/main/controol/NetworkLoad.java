package main.controol;

import main.controol.loadCommand.GetBest;
import main.controol.loadCommand.LoadCommand;
import main.network.DataReader;
import main.network.Network;

import java.util.HashMap;

public class NetworkLoad extends Command {
    Network network;
    HashMap<String, LoadCommand> load;

    public NetworkLoad() {
        load = new HashMap<>();
        load.put("best", new GetBest());
    }

    @Override
    public Network execute(Network network, DataReader dataReader, String token, double[] input) throws Exception {

        try {
            this.network = Network.readFromFile(token);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return this.network;
    }

    @Override
    public String output() {
        return "";
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
