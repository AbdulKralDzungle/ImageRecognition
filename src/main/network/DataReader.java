package main.network;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    private double[] data;
    private int label;
    private String path;

    private BufferedReader bf;

    public DataReader(String path) throws FileNotFoundException {
        data = new double[784];
        this.path = path;
        //System.out.println("Reading data from " + path);
        bf = new BufferedReader(new FileReader(path));
    }

    public int getLabel() {
        return label;
    }

    public double[] read() throws IOException {
        String s = bf.readLine();
        if (s == null) {
            return null;
        }
        String[] split = s.split(",");
        label = Integer.parseInt(split[0]);
        for (int i = 0; i < data.length; i++) {
            data[i] = (double) Integer.parseInt(split[i + 1]) / 225;
        }
        return data;
    }

    public void reset() {
        try {
            bf = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
