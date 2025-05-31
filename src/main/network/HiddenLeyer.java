package main.network;

import java.io.Serializable;
import java.util.Random;

/**
 *
 */
public class HiddenLeyer implements Serializable {
    private final Neuron[] neurons;
    private double learningRate;
    private final Random rn;
    private final int inputSize;

    public HiddenLeyer(int neuronCount, double learningRate, int inputLenght, int layer) {
        neurons = new Neuron[neuronCount];
        this.learningRate = learningRate;
        this.inputSize = inputLenght;
        rn = new Random();
        for (int i = 0; i < neuronCount; i++) {
            neurons[i] = new Neuron(rn.nextDouble(20) - 10, inputLenght);
        }
    }

    /**
     * this method feeds input into neurons that this layer contains
     *
     * @param input values fed to the neurons
     * @return output, that will be passed to the next layer on the network
     */
    public double[] calOut(double[] input) {
        double[] output = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            output[i] = neurons[i].calculateOuput(input);
        }
        return output;
    }

    /**
     * this changes connections and bias in the first layer of the network using chain rule
     *
     * @param expectedOutput is correct output used to calculate how wrong the network is
     * @return errors that will be passed to previous layer of neurons
     */
    public double[] firstProp(double[] expectedOutput) {
        double[] errors = new double[inputSize];
        if (neurons.length == expectedOutput.length) {
            for (int i = 0; i < expectedOutput.length; i++) {
                double[] additionalErrors = neurons[i].firstPropagate(expectedOutput[i], learningRate);
                for (int j = 0; j < additionalErrors.length; j++) {
                    errors[j] += additionalErrors[j];
                }
            }
            return errors;
        } else {
            throw new RuntimeException("nefituje");
        }

    }

    /**
     * this changes connections and bias of the neuron using chain rule
     *
     * @param prevErrors is error of the following layer
     * @return errors that will be passed to the previous layer
     */
    public double[] bProp(double[] prevErrors) {
        double[] errors = new double[inputSize];
        if (neurons.length == prevErrors.length) {
            for (int i = 0; i < prevErrors.length; i++) {
                double[] additionalErrors = neurons[i].backPropagate(prevErrors[i], learningRate);
                for (int j = 0; j < additionalErrors.length; j++) {
                    errors[j] += additionalErrors[j];
                }
            }
            return errors;
        } else {
            throw new RuntimeException("nefituje");
        }

    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
