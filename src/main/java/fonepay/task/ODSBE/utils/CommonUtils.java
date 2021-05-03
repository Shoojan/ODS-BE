package fonepay.task.ODSBE.utils;

import java.util.Random;

public class CommonUtils {

    public static int getRandomNumber(int min, int max) {
        if (min > max) min -= max;
        return new Random().nextInt((max - min) + 1) + min;
    }

}
