import java.util.*;

public class DiskScheduler {

    private int diskCylinders = 0;
    private int diskStartingPosition = 0;
    private ArrayList requestPositions = null;
    private int totalDiskMovement = 0;
    private String diskScheduleType = null;


    public DiskScheduler(int diskCylinders, int diskStartingPosition, ArrayList requestPositions) {
        this.diskCylinders = diskCylinders;
        this.diskStartingPosition = diskStartingPosition;
        this.requestPositions = requestPositions;
    }

    public DiskScheduler(int diskCylinders, int diskStartingPosition, String requestPositions) {
        this.diskCylinders = diskCylinders;
        this.diskStartingPosition = diskStartingPosition;
        this.requestPositions = stringToList(requestPositions);
    }

    private ArrayList stringToList(String requestPositions) {
        ArrayList returnList = new ArrayList();
        returnList.addAll(Arrays.asList(requestPositions.split(" ")));
        return returnList;
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

    public void setRequestPositions(ArrayList requestPositions) {
        this.requestPositions = requestPositions;
    }

    public String getFCFSResult(){
        diskSchedulerFCFS(this.diskStartingPosition,this.requestPositions);
        return scheduleToString();
    }

    public String getSSTFResult(){
        diskSchedulerSSTF(this.diskStartingPosition,this.requestPositions);
        return scheduleToString();
    }

    public String getSCANResult(){
        diskSchedulerSCAN(this.diskCylinders,this.diskStartingPosition,this.requestPositions);
        return scheduleToString();
    }

    public String getCSCANResult(){
        diskSchedulerCSCAN(this.diskCylinders,this.diskStartingPosition,this.requestPositions);
        return scheduleToString();
    }

    public String getLOOKResult(){
        diskSchedulerLOOK(this.diskStartingPosition,this.requestPositions);
        return scheduleToString();
    }

    public String getCLOOKResult(){
        diskSchedulerCLOOK(this.diskStartingPosition,this.requestPositions);
        return scheduleToString();
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
        ArrayList<Integer> requestListIntegers = stringToIntegerList(requestList);
        for(int i = 0;i < requestListIntegers.size();i++){
            int minDistance = Math.abs(start - requestListIntegers.get(i));
            int minDistanceCursor = i;
            for(int j = 1;j < requestListIntegers.size();j++){
                if(minDistance > Math.abs(start - requestListIntegers.get(j))){
                    minDistance = Math.abs(start - requestListIntegers.get(j));
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
        while(requestListIntegers.iterator().hasNext()) {
            if(start == diskSize){
                start =0;
            }
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
            Collections.sort(requestListIntegers);
            if(start >= requestListIntegers.get(requestListIntegers.size()-1)){
                lookIncrament = -1;
            }else if(start <= requestListIntegers.get(0)){
                lookIncrament = 1;
            }
            if(requestListIntegers.contains(start)){
                requestListIntegers.remove(requestListIntegers.indexOf(start));
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
            if(start == requestListIntegers.get(requestListIntegers.size()-1)){
                start = requestListIntegers.get(0)-1;
                requestListIntegers.remove(requestListIntegers.size()-1);
            }else if(requestListIntegers.contains(start)){
                requestListIntegers.remove(requestListIntegers.indexOf(start));
            }
            start = start+1;
            totalMovement++;
        }

        this.totalDiskMovement = totalMovement-1;
        this.diskScheduleType = "SSTF";
    }

    private ArrayList<Integer>  stringToIntegerList(ArrayList stringList){
        ArrayList intList = new ArrayList();
        for(int i = 0;i < stringList.size();i++){
            intList.add(Integer.parseInt(stringList.get(i).toString()));
        }
        return intList;
    }

    public String scheduleToString(){
        return String.format("For %s, the total head movement was %s cylinders.",this.diskScheduleType, this.totalDiskMovement);
    }
}
