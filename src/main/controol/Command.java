package main.controol;

import main.network.DataReader;
import main.network.Network;

public abstract class Command {
    public abstract void execute(Network network, DataReader dataReader, String token, double[] input);

    public abstract int output();

    public abstract boolean exit();
}
