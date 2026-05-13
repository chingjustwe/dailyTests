package dataStructure;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: lam
 * @create: 2020/11/14
 **/
public class AVLDemo {
    private static final String QUERY_STRING = "qry";
    private static final String INSERT_STRING = "ins";
    private static final String DEFAULT_INPUT_FILE_NAME = "ops-half.txt";
    private static final String OUTPUT_FILE_NAME = "output.txt";

    private static String workingFolder;

    public static void main(String[] args) throws Exception {
        long timestampStart = System.currentTimeMillis();
        String sourceFilePath = checkAndGetSourceFile(args);
        String outputFilePath = renewOutputFile();
        AVLTree tree = new AVLTree();
        try (PrintWriter out = new PrintWriter(outputFilePath)) {
            List<String> allLines = Files.readAllLines(Paths.get(sourceFilePath));
            String[] lineArr;
            for (String line : allLines) {
                // in format "ins {value}" or "qry {value}"
                // e.g.
                // ins 5
                // ins 10
                // qry 3
                // ins 7
                // qry 9
                lineArr = line.split(" ");
                if (INSERT_STRING.equals(lineArr[0])) {
                    tree.insert(Integer.valueOf(lineArr[1]));
                }
                else if (QUERY_STRING.equals(lineArr[0])) {
                    int predecessor = tree.getPredecessor(Integer.valueOf(lineArr[1]));
                    out.println(predecessor == -1 ? "no" : predecessor);
                }
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Target file not found!");
                return;
            }
            System.out.println("Can not read file! please check if the file exists.");
            e.printStackTrace();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | NumberFormatException e1) {
            System.out.println("Incorrect input file format! please double check input file.");
        }

        long timestampEnd = System.currentTimeMillis();
        System.out.println("Process done! result saved to '" + outputFilePath + "', time cost(ms): " + (timestampEnd - timestampStart));
    }

    // delete and create new output file
    private static String renewOutputFile() throws IOException {
        String outputFilePath = workingFolder + File.separator + OUTPUT_FILE_NAME;
        File outputFile = new File(outputFilePath);
        if (outputFile.exists()) {
            outputFile.delete();
        }
        outputFile.createNewFile();
        return outputFilePath;
    }

    private static String checkAndGetSourceFile(String[] args) throws IOException {
        // parse args, args[0] should be the input file name;
        String inputFilePath = "";
        if (args == null || args.length == 0) {
            // using default input value: "{currentDirectory}/ops-half.txt"
            inputFilePath = Paths.get("").toAbsolutePath().toString() + File.separator + DEFAULT_INPUT_FILE_NAME;
            System.out.println("Did not specify a input file, using a default one: " + inputFilePath);
        }
        else {
            String arg = args[0];
            if (!arg.contains("/") && !arg.contains(File.separator)) {// relative path
                // input argument is a file name: "{currentDirectory}/{arg}"
                inputFilePath = Paths.get("").toAbsolutePath().toString() + File.separator + arg;
            }
            else {
                inputFilePath = arg;
            }
            System.out.println("Using '" + inputFilePath + "' as the input file.");
        }
        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            throw new IOException("Target file " + inputFilePath + " not exists!");
        }
        if (inputFile.isDirectory()) {
            throw new IOException("Target file " + inputFilePath + " is a directory!");
        }

        workingFolder = inputFile.getParentFile().getAbsolutePath();
        return inputFilePath;
    }
}
