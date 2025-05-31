package main.network;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NetworkTest {

    /**
     * makes shure that outputs of network are in range of 0 - 1
     * and that the output have correct lenght
     */
    @Test
    void forwardfeed() {
        Network nt = new Network(50, 784, 0.2, 2, 10);
        Random rand = new Random();
        double[] input = new double[784];
        for (int i = 0; i < 784; i++) {
            input[i] = rand.nextDouble(5);
        }
        double[] d = nt.forwardfeed(input);
        assertTrue(nt.forwardfeed(input).length == 10);
        for (double dd : d) {
            assertTrue(dd <= 1);
            assertTrue(dd >= 0);
        }
    }

    /**
     * makes shure that the cost function of network is in range of 1 - 0
     */
    @Test
    void callCost() {
        Network nt = new Network(50, 784, 0.2, 2, 10);
        Random rand = new Random();
        double[] input = new double[784];
        for (int i = 0; i < 784; i++) {
            input[i] = rand.nextDouble(5);
        }
        double[] d = nt.forwardfeed(input);
        assertTrue(nt.callCost(4, d) >= 0);
        assertTrue(nt.callCost(4, d) <= 1);

    }

    /**
     * makes shure that the answer of network is in range of 10 - 0
     */
    @Test
    void answer() {
        Network nt = new Network(50, 784, 0.2, 2, 10);
        Random rand = new Random();
        double[] input = new double[784];
        for (int i = 0; i < 784; i++) {
            input[i] = rand.nextDouble(5);
        }
        double[] d = nt.forwardfeed(input);
        assertTrue(nt.answer(input) >= 0);
        assertTrue(nt.answer(input) <= 10);
    }
}