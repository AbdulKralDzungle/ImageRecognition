package main.network;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataReaderTest {

    @Test
    void getLabel() {
        DataReader dr;
        try {
            dr = new DataReader("LittleData/MnistTiny");
            for (int i = 0; i < 10; i++) {
                dr.read();
                assertTrue(dr.getLabel() <= 9);
                assertTrue(dr.getLabel() >= 0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void read() {
        DataReader dr;
        try {
            dr = new DataReader("LittleData/MnistTiny");
            double[] d = dr.read();
            assertTrue(d.length == 784);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * checks if the data reader resets corectly
     */
    @Test
    void reset() {
        DataReader dr;
        try {
            dr = new DataReader("LittleData/MnistTiny");
            dr.read();
            int label = dr.getLabel();
            dr.read();
            dr.read();
            dr.read();
            dr.reset();
            dr.read();
            assertEquals(label, dr.getLabel());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}