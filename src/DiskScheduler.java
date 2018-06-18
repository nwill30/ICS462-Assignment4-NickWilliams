import java.util.Arrays;
import java.util.List;

public class DiskScheduler {

    private int diskCylinders = 0;
    private int diskStartingPosition = 0;
    private List requestPositions = null;


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

    public void diskSchedulerFCFS(){

    }

    public void diskSchedulerSSTF(){

    }

    public void diskSchedulerSCAN(){

    }

    public void diskSchedulerCSCAN(){

    }

    public void diskSchedulerLOOK(){

    }

    public void diskSchedulerCLOOK(){

    }


}
