package main;

public class Network {
    HiddenLayer[] layers;

    public Network(int neuronCount, int inputSize, double learningRate, int layerCount, int outputSize) {
        this.layers = new HiddenLayer[layerCount];
        this.layers[0] = new HiddenLayer(neuronCount, learningRate, inputSize, 0);
        for (int i = 1; i < layerCount - 1; i++) {
            this.layers[i] = new HiddenLayer(neuronCount, learningRate, neuronCount, i);
        }
        layers[layers.length - 1] = new HiddenLayer(outputSize, learningRate, neuronCount, layerCount - 1);
    }

    public double[] forwardfeed(double[] input) {
        double[] output = input.clone();
        for (int i = 0; i < this.layers.length; i++) {
            output = this.layers[i].calOut(output).clone();
        }
        return output;
    }

    public double callCost(int tag, double[] input) {
        double[] expected = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[tag] = 1;
        double[] cost = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
        double[] expected = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
        for (HiddenLayer layer : layers) {
            layer.setLearningRate(learningRate);
        }
    }


}
