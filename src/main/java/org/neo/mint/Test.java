package org.neo.mint;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by vanosov on 20.03.2018.
 */
public class Test {

        public static void main(String[] args)  throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder text = new StringBuilder();
            String line;
            while( (line = br.readLine()) != null) {
                text.append(line);
            }
            String [] input = text.toString().split(" ");
            for(int i = 0; i < input.length-1; i++){
                System.out.println(input[i] + "\t1");
            }
    }
}
