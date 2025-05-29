package main;

import java.util.Random;

public class HiddenLayer {
    private Neuron[] neurons;
    private double learningRate;
    private Random rn;
    private int inputSize;

    public HiddenLayer(int neuronCount, double learningRate, int inputLenght, int layer) {
        neurons = new Neuron[neuronCount];
        this.learningRate = learningRate;
        this.inputSize = inputLenght;
        rn = new Random();
        for (int i = 0; i < neuronCount; i++) {
            neurons[i] = new Neuron(rn.nextDouble(20) - 10, inputLenght);
        }
    }

    public double[] calOut(double[] input) {
        double[] output = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            output[i] = neurons[i].calculateOuput(input);
        }
        return output;
    }

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
