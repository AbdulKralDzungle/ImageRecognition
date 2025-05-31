package main.controol;

import main.network.DataReader;
import main.network.Network;

/**
 * this is class that is used to make the command design pattern
 */
public abstract class Command {
    /**
     * this is main body of the class
     *
     * @param network    is main subject of all of the classes that are extended from this one
     * @param dataReader is a link between the class and files
     * @param token      is peace of usser input
     * @param input      is whatever should be fed to the network
     * @return new network (in case changes are made)
     * @throws Exception is thrown when this method is unable to finish, the issue is displayed to the user
     */
    public abstract Network execute(Network network, DataReader dataReader, String token, double[] input) throws Exception;

    public abstract String output();

    public abstract void getExtra(String extra);

    /**
     * @return is method responsible for exiting application
     */
    public abstract boolean exit();

    /**
     * @return weather or not this command will change what will happen in the next iteration
     */
    public abstract String nextState();
}
