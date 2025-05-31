package main.controol;

import main.network.DataReader;
import main.network.Network;

public abstract class Command {
    public abstract Network execute(Network network, DataReader dataReader, String token, double[] input) throws Exception;

    public abstract String output();

    public abstract void getExtra(String extra);

    public abstract boolean exit();

    public abstract String nextState();
}
