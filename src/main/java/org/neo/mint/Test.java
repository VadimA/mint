package org.neo.mint;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vanosov on 20.03.2018.
 */
public class Test {

        public static void main(String[] args)  throws IOException {
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = "word\ncount\ncat\nword\ncount2\ncat2\nword\ncount2\ncat2";
            String [] input = (Stream.of(str).collect(Collectors.joining(" "))).split("\n");
            String currentKey = input[0];
            Integer currentCount = 1;
            for(int i = 1; i < input.length; i++){
                if(!currentKey.equals(input[i])){
                    System.out.println(currentKey + '\t' + currentCount);
                    currentKey = input[i];
                    currentCount = 1;
                }
                else{
                    currentCount++;
                }
            }
        }
}
