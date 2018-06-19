import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        File userDirectory = new File(System.getProperty("user.dir"));
        File inputFile = new File(String.format("%s\\%s",userDirectory.getPath(),"Asg5Data.txt"));
        ArrayList inputData = Utilities.readFileInput(inputFile);
        int[] diskCylinderCounts = {Integer.parseInt(inputData.get(0).toString()),Integer.parseInt(inputData.get(3).toString())};
        int[] diskStartPositions = {Integer.parseInt(inputData.get(1).toString()),Integer.parseInt(inputData.get(4).toString())};
        String[] cylinderReqeustLists = {inputData.get(2).toString(),inputData.get(5).toString()};
        LinkedList<String> outputData = new LinkedList<>();

        for(int i =0;i < diskCylinderCounts.length;i++){
            DiskScheduler fcfs = new DiskScheduler(diskCylinderCounts[i],diskStartPositions[i],cylinderReqeustLists[i]);
            DiskScheduler sstf = new DiskScheduler(diskCylinderCounts[i],diskStartPositions[i],cylinderReqeustLists[i]);
            DiskScheduler scan = new DiskScheduler(diskCylinderCounts[i],diskStartPositions[i],cylinderReqeustLists[i]);
            DiskScheduler cscan = new DiskScheduler(diskCylinderCounts[i],diskStartPositions[i],cylinderReqeustLists[i]);
            DiskScheduler look = new DiskScheduler(diskCylinderCounts[i],diskStartPositions[i],cylinderReqeustLists[i]);
            DiskScheduler clook = new DiskScheduler(diskCylinderCounts[i],diskStartPositions[i],cylinderReqeustLists[i]);
            outputData.add(fcfs.getFCFSResult());
            outputData.add(sstf.getSSTFResult());
            outputData.add(scan.getSCANResult());
            outputData.add(cscan.getCSCANResult());
            outputData.add(look.getLOOKResult());
            outputData.add(clook.getCLOOKResult());

        }

        try {
            File outputFile = Utilities.createFile("diskScheduleOutput", userDirectory.getPath());
            Utilities.writeToFile(outputFile, outputData);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
