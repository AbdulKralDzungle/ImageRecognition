package main.network;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NeuronTest {

    /**
     * this test checks if the scale of conections is correct
     */
    @Test
    void getConections() {
        Neuron neuron = new Neuron(3, 10);
        double[] d = neuron.getConections();
        for (double x : d) {
            assertTrue(x > 11);
            assertTrue(x < 11);
        }
    }

    /**
     * this test chacks if output of the neuron is within bounds of 0 to 1
     */
    @Test
    void calculateOuput() {
        Neuron neuron = new Neuron(3, 10);
        Random rand = new Random();
        double[] input = new double[10];
        for (int i = 0; i < 10; i++) {
            input[i] = rand.nextDouble(5);
        }
        assertTrue(neuron.calculateOuput(input) <= 1);
        assertTrue(neuron.calculateOuput(input) >= 0);
    }
}