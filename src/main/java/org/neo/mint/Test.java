package org.neo.mint;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by vanosov on 20.03.2018.
 */
public class Test {

        public static void main(String[] args)  throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String [] input = br.readLine().split(" ");
            System.out.println("Length = " + input.length);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for(int i = 0; i < input.length-1; i++){
                bw.write(args[i] + "\t1");
            }
    }
}
