package main.network;

import java.io.Serializable;
import java.util.Random;

public class Neuron implements Serializable {
    private double[] conections;
    private int layer;
    private double[] lastInput;
    private double lastOutput;
    private double bias;
    private int conectionCount;
    Random rn;

    public double[] getConections() {
        return conections;
    }

    public Neuron(double bias, int conectionCount) {
        rn = new Random();
        this.bias = bias;
        this.conectionCount = conectionCount;
        conections = new double[conectionCount];
        for (int i = 0; i < conectionCount; i++) {
            conections[i] = rn.nextDouble(20) - 10;
        }
    }

    public double calculateOuput(double[] input) {
        lastInput = input;
        if (input.length == conectionCount) {
            double value = 0;
            for (int i = 0; i < input.length; i++) {
                value += input[i] * conections[i];
            }
            lastOutput = sigmoid(value + bias);
            return lastOutput;
        }
        throw new RuntimeException("not matching staff " + input.length + " input " + conectionCount + " conections " + layer);
    }

    private double sigmoid(double x) {
        double y;
        y = 1 / (1 + Math.exp(-x));
        return y;
    }

    public double[] firstPropagate(double expectedOutput, double learningRate) {
        double cost = lastOutput - expectedOutput;
        double delta = cost * lastOutput * (1 - lastOutput);
        for (int i = 0; i < conections.length; i++) {
            conections[i] -= learningRate * delta * lastInput[i];
        }
        bias -= learningRate * delta;
        double[] prevLayerError = new double[conectionCount];
        for (int i = 0; i < conectionCount; i++) {
            prevLayerError[i] = conections[i] * delta;
        }

        return prevLayerError;
    }

    public double[] backPropagate(double learningRate, double error) {
        double delta = error * lastOutput * (1 - lastOutput);
        for (int i = 0; i < conections.length; i++) {
            conections[i] -= learningRate * delta * lastInput[i];
        }
        bias -= learningRate * delta;

        double[] prevLayerError = new double[conectionCount];
        for (int i = 0; i < conectionCount; i++) {
            prevLayerError[i] = conections[i] * delta;
        }

        return prevLayerError;
    }
}
