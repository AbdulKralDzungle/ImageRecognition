package main.controol;

import main.network.DataReader;
import main.network.Network;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NetworkTickTest {

    @Test
    void execute() {
        NetworkTick networkTick = new NetworkTick();
        Random rand = new Random();
        double[] input = new double[784];
        for (int i = 0; i < 784; i++) {
            input[i] = rand.nextDouble(5);
        }
        try {
            assertTrue(networkTick.execute(new Network(50, 784, 0.2, 2, 10), new DataReader("LittleData/MnistTiny"), "token", input) instanceof Network);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * checks if the exit value is correct
     */
    @Test
    void exit() {
        NetworkTick networkTick = new NetworkTick();
        assertTrue(!networkTick.exit());
    }

}