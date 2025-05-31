package main.controol;

import main.network.DataReader;
import main.network.Network;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NetworkLoad extends Command {
    Network network;

    @Override
    public Network execute(Network network, DataReader dataReader, String token, double[] input) throws Exception {
        try {
            this.network = readFromFile(token);
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

    public static Network readFromFile(String fileName) throws
            IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new
                FileInputStream(fileName));
        return (Network) stream.readObject();
    }
}
