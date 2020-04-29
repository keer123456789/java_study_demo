package org.keer;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        if(args.length==0){
            System.out.println("you can input help");
            return;
        }
        if(args.length==1&&args[1].equals("help")){
            System.out.println("path  dir path example: E://oo//oo");
            System.out.println("type  code file type. example: java,py");
            System.out.println("output  output file path. example: test.json");
            System.out.println("example input: path file/path type java output test.json");
            System.out.println("param type now just support: java,py. output file type just is json file");
            return;
        }
        if (args.length != 6 || !(args[0].equals("path")) || !(args[2].equals("type"))||!(args[4].equals("output"))) {
            System.out.println("param error");
            System.out.println("type  code file type. example: java,py");
            System.out.println("output  output file path. example: test.json");
            System.out.println("example input: path file/path type java output test.json");
            System.out.println("param type now just support: java,py. output file type just is json file");
            return;
        }

        String path = args[1];
        String type = args[3];
        String output=args[5];

        FileRowCount fileRowCount = new FileRowCount(path, type,output);
        fileRowCount.countRow();
        System.out.println(fileRowCount.toString());

    }
}
