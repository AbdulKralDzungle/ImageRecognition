package main.controol;

import main.network.DataReader;
import main.network.Network;

public abstract class Command {
    public abstract void execute(Network network, DataReader dataReader, String token, double[] input) throws Exception;

    public abstract String output();

    public abstract boolean exit();

    public abstract String nextState();
}
