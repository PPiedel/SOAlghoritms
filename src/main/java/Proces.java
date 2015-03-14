/**
 * Created by Pawel on 2015-03-12.
 */
public class Proces implements Comparable<Proces> {
    private int number;
    private int applicationTime;
    private int executionTime;
    private int waitingTime;

    public Proces(int number, int applicationTime, int executionTime) {
        this.number = number;
        this.applicationTime = applicationTime;
        this.executionTime = executionTime;
        this.waitingTime=0;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public void setWaitingTime(int waitingTime) {

        this.waitingTime = waitingTime;
    }

    public int getApplicationTime() {
        return applicationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getNumber() {
        return number;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public int compareTo(Proces o) {
        return applicationTime-o.getApplicationTime();
    }

    @Override
    public String toString() {
        return "Proces{" +
                number + " "+
                "applicationTime=" + applicationTime +
                ", executionTime=" + executionTime +
                ", waitingTime=" + waitingTime +
                '}';
    }


}
