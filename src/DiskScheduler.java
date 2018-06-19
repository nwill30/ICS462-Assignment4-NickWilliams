import java.util.*;

public class DiskScheduler {

    private int diskCylinders = 0;
    private int diskStartingPosition = 0;
    private List requestPositions = null;
    private int totalDiskMovement = 0;
    private String diskScheduleType = null;


    public DiskScheduler(int diskCylinders, int diskStartingPosition, List requestPositions) {
        this.diskCylinders = diskCylinders;
        this.diskStartingPosition = diskStartingPosition;
        this.requestPositions = requestPositions;
    }

    public DiskScheduler(int diskCylinders, int diskStartingPosition, String requestPositions) {
        this.diskCylinders = diskCylinders;
        this.diskStartingPosition = diskStartingPosition;
        this.requestPositions = stringToList(requestPositions);
    }

    private List stringToList(String requestPositions) {

        return Arrays.asList(requestPositions.split(" "));
    }

    public int getDiskCylinders() {
        return diskCylinders;
    }

    public void setDiskCylinders(int diskCylinders) {
        this.diskCylinders = diskCylinders;
    }

    public int getDiskStartingPosition() {
        return diskStartingPosition;
    }

    public void setDiskStartingPosition(int diskStartingPosition) {
        this.diskStartingPosition = diskStartingPosition;
    }

    public List getRequestPositions() {
        return requestPositions;
    }

    public void setRequestPositions(List requestPositions) {
        this.requestPositions = requestPositions;
    }

    /**
     * a.	FCFS
     b.	SSTF
     c.	SCAN
     d.	C-SCAN
     e.	LOOK
     f.	C-LOOK

     * */

    public void diskSchedulerFCFS(int start, ArrayList requestList){{
        int totalMovement = 0;
        ArrayList<Integer> requestListIntegers = stringToIntegerList(requestList);
        for(int i = 0;i < requestList.size();i++){
            totalMovement = totalMovement +  Math.abs(start - requestListIntegers.get(i));
            start = requestListIntegers.get(i);
        }
        this.totalDiskMovement = totalMovement;
        this.diskScheduleType = "FCFS";
    }

    }

    public void diskSchedulerSSTF(int start, ArrayList requestList){
        int totalMovement = 0;
        int initialListSize = requestList.size();
        ArrayList<Integer> requestListIntegers = stringToIntegerList(requestList);
        for(int i = 0;i < initialListSize;i++){
            int minDistance = Math.abs(start = requestListIntegers.get(i));
            int minDistanceCursor = i;
            for(int j = 1;j < requestListIntegers.size();j++){
                if(minDistance > Math.abs(start - requestListIntegers.get(i))){
                    minDistance = Math.abs(start - requestListIntegers.get(i));
                    minDistanceCursor = j;
                }
            }
            totalMovement = totalMovement +  Math.abs(start - requestListIntegers.get(i));
            start = requestListIntegers.get(i);
            requestListIntegers.remove(requestListIntegers.indexOf(start));
        }
        this.totalDiskMovement = totalMovement;
        this.diskScheduleType = "SSTF";
    }

    public void diskSchedulerSCAN(int diskSize, int start, ArrayList requestList){
        int totalMovement = 0;
        int scanIncrament = 1;
        ArrayList<Integer> requestListIntegers = stringToIntegerList(requestList);
        while(requestListIntegers.iterator().hasNext()){
            if(requestListIntegers.contains(start)){
                requestListIntegers.remove(requestListIntegers.indexOf(start));
            }
            if(start == diskSize){
                scanIncrament = -1;
            }else if(start == 0){
                scanIncrament = 1;
            }
            start = start + scanIncrament;
            totalMovement++;
        }
        this.totalDiskMovement = totalMovement;
        this.diskScheduleType = "SCAN";
    }

    public void diskSchedulerCSCAN(int diskSize, int start, ArrayList requestList){
        int totalMovement = 0;
        ArrayList<Integer> requestListIntegers = stringToIntegerList(requestList);
        if(start == diskSize){
            start =0;
        }
        while(requestListIntegers.iterator().hasNext()) {
            if (requestListIntegers.contains(start)) {
                requestListIntegers.remove(requestListIntegers.indexOf(start));
            }
            start++;
            totalMovement++;
        }
        this.totalDiskMovement = totalMovement;
        this.diskScheduleType = "CSCAN";
    }

    public void diskSchedulerLOOK(int start, ArrayList requestList){
        int totalMovement = 0;
        List<Integer> requestListIntegers = stringToIntegerList(requestList);
        Collections.sort(requestListIntegers);
        int lookIncrament = 1;
        while(requestListIntegers.iterator().hasNext()){
            if(requestListIntegers.contains(start)){
                requestListIntegers.remove(requestListIntegers.indexOf(start));
            }
            if(start == requestListIntegers.get(requestListIntegers.size())){
                lookIncrament = -1;
            }else if(start == requestListIntegers.get(0)){
                lookIncrament = 1;
            }
            start = start + lookIncrament;
            totalMovement++;
        }
        this.totalDiskMovement = totalMovement;
        this.diskScheduleType = "SSTF";
    }

    public void diskSchedulerCLOOK(int start, ArrayList requestList){
        int totalMovement = 0;
        ArrayList<Integer> requestListIntegers = stringToIntegerList(requestList);
        Collections.sort(requestListIntegers);
        while(requestListIntegers.iterator().hasNext()){
            if(start == requestListIntegers.get(requestListIntegers.size())){
                start = requestListIntegers.get(0);
                requestListIntegers.remove(requestListIntegers.indexOf(requestListIntegers.size()));
            }else if(requestListIntegers.contains(start)){
                requestListIntegers.remove(requestListIntegers.indexOf(start));
            }
            start = start++;
            totalMovement++;
        }

        this.totalDiskMovement = totalMovement;
        this.diskScheduleType = "SSTF";
    }

    private ArrayList<Integer>  stringToIntegerList(ArrayList stringList){
        ArrayList intList = new ArrayList();
        for(int i = 0;i < stringList.size();i++){
            intList.add(Integer.parseInt(stringList.get(0).toString()));
        }
        return intList;
    }

    public String scheduleToString(){
        return String.format("For %s, the total head movement was %s cylinders.",this.diskScheduleType, this.totalDiskMovement);
    }
}
