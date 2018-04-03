package tech.ideashare.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.RandomStringGenerator;

public class IS_RandomUtils extends RandomUtils{
    
    public static String nextString(int length){
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
        .withinRange('a', 'z').build();
        return  generator.generate(length);
    }

}