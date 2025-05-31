package main.network;

import java.io.*;

public class Network implements Serializable {
    HiddenLeyer[] layers;

    public Network(int neuronCount, int inputSize, double learningRate, int layerCount, int outputSize) {
        this.layers = new HiddenLeyer[layerCount];
        this.layers[0] = new HiddenLeyer(neuronCount, learningRate, inputSize, 0);
        for (int i = 1; i < layerCount - 1; i++) {
            this.layers[i] = new HiddenLeyer(neuronCount, learningRate, neuronCount, i);
        }
        layers[layers.length - 1] = new HiddenLeyer(outputSize, learningRate, neuronCount, layerCount - 1);
    }

    public double[] forwardfeed(double[] input) {
        double[] output = input.clone();
        for (int i = 0; i < this.layers.length; i++) {
            output = this.layers[i].calOut(output).clone();
        }
        return output;
    }

    public double callCost(int tag, double[] input) {
        double[] expected = new double[10];
        expected[tag] = 1;
        double[] cost = new double[10];
        for (int i = 0; i < input.length; i++) {
            cost[i] = input[i] - expected[i];
        }
        double finalCost = 0;
        for (double i : cost) {
            finalCost += i;
        }
        finalCost = finalCost / cost.length;
        return finalCost;
    }

    /**
     * here the magic happens
     * this method takes the input, feed it to the network
     * and then based ot the output and the tag it changes network accordingly
     *
     * @param tag   the correct answer to the input provided
     * @param input is whatever goes to the network
     */
    public void bProp(int tag, double[] input) {
        double[] expected = new double[10];
        expected[tag] = 1;
        this.forwardfeed(input);
        double[] errors = layers[layers.length - 1].firstProp(expected);
        for (int i = layers.length - 2; i >= 0; i--) {
            errors = layers[i].bProp(errors);
        }

        //layers[layers.length - 1].bProp(costs);
    }

    public int answer(double[] input) {
        int answer = 0;
        double[] output = forwardfeed(input);

        for (int i = 1; i < output.length; i++) {
            if (output[answer] < output[i]) {
                answer = i;
            }
        }
        return answer;
    }

    public void setLearningRate(double learningRate) {
        for (HiddenLeyer layer : layers) {
            layer.setLearningRate(learningRate);
        }
    }

    public void writeToFile(String fileName) throws IOException {
        makeFile(fileName);
        ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(fileName)
        );
        stream.writeObject(this);
        stream.close();
    }

    public void makeFile(String fileName) throws IOException {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Network readFromFile(String fileName) throws
            IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new
                FileInputStream(fileName));
        return (Network) stream.readObject();
    }


}
