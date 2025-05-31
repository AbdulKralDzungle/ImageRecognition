package main.network;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HiddenLeyerTest {

    /**
     * makes shure the output sizes are in range of 1 - 0
     */
    @Test
    void calOut() {
        HiddenLeyer hl = new HiddenLeyer(10, 0.1, 10, 10);
        Random rand = new Random();
        double[] input = new double[10];
        for (int i = 0; i < 10; i++) {
            input[i] = rand.nextDouble(5);
        }

        assertTrue(hl.calOut(input).length == 10);
        for (double i : hl.calOut(input)) {
            assertTrue(i <= 1);
            assertTrue(i >= 0);
        }
    }

}