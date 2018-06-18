import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        File userDirectory = new File(System.getProperty("user.dir"));
        File inputFile = new File(String.format("%s\\%s",userDirectory.getPath(),"Asg5Data.txt"));
        ArrayList inputData = Utilities.readFileInput(inputFile);
        int[] diskCylinderCounts = {Integer.parseInt(inputData.get(0).toString()),Integer.parseInt(inputData.get(3).toString())};
        int[] diskStartPositions = {Integer.parseInt(inputData.get(1).toString()),Integer.parseInt(inputData.get(4).toString())};
        String[] cylinderReqeustLists = {inputData.get(2).toString(),inputData.get(5).toString()};
    }
}
