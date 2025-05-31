package main.network;

import java.io.*;

public class Network implements Serializable {
    HiddenLeyer[] layers;
    private static final long serialVesionUID = 1L;

    public Network(int neuronCount, int inputSize, double learningRate, int layerCount, int outputSize) {
        this.layers = new HiddenLeyer[layerCount];
        this.layers[0] = new HiddenLeyer(neuronCount, learningRate, inputSize, 0);
        for (int i = 1; i < layerCount - 1; i++) {
            this.layers[i] = new HiddenLeyer(neuronCount, learningRate, neuronCount, i);
        }
        layers[layers.length - 1] = new HiddenLeyer(outputSize, learningRate, neuronCount, layerCount - 1);
    }

    /**
     * feeds input trough network
     *
     * @param input double[] witch is fed to first layer
     * @return whatever the network calculated as correct output
     */
    public double[] forwardfeed(double[] input) {
        double[] output = input.clone();
        for (int i = 0; i < this.layers.length; i++) {
            output = this.layers[i].calOut(output).clone();
        }
        return output;
    }

    /**
     * this method calculates the 'amount of mistake' that the network does
     *
     * @param tag   int that contains information about the correct answer
     * @param input is the input based on witch network is making it's gess
     * @return 'amount of mistake' in a form of double
     */
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

    /**
     * this feeds input to the network, and then translates its output into single int containing its answer
     *
     * @param input is what will be fed to the network
     * @return answer of the network
     */
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

    // traditional serialization
    public void writeToFile(String fileName) throws IOException {
        makeFile(fileName);
        ObjectOutputStream stream = new ObjectOutputStream(
                new FileOutputStream(fileName)
        );
        stream.writeObject(this);
        stream.close();
    }

    /**
     * @param fileName is input name, if a file with this name does not exist, this method will create it
     */
    public void makeFile(String fileName) {
        File myObj = new File(fileName);
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //traditional deserialization
    public static Network readFromFile(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
        Network network = (Network) stream.readObject();
        return network;
    }


}
