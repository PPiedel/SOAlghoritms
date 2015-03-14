import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Pawel on 2015-03-14.
 */
public class SJFex {
    private ArrayList<Proces> processList;

    public SJFex() {
    }

    public void generateSJFExAlghoritm(ArrayList<Proces> collectiveList) {
        //lepiej chyba nie usuwac tylko cały czas sortowac pelna kolejke
        int time = 0;
        int i = 0;

        LinkedList<Proces> queue = new LinkedList<Proces>();
        processList = new ArrayList<Proces>();

        for (; i < collectiveList.size() || !queue.isEmpty(); time++) {

            //dodaje wszystkie procesy z "banku" procesow, ktore zglosily sie w danej chwili
            //kolejkuje procesy wg czasu zgloszenia
            for (; i < collectiveList.size() && collectiveList.get(i).getApplicationTime() == time; i++) {
                queue.add(collectiveList.get(i));
            }


            /* sortuje pozostala kolejke po czasieWykonania by ustawic jako pierwszy element proces
                * z najmniejszym czasem wykonania*/
            Collections.sort(queue, new Comparator<Proces>() {
                @Override
                public int compare(Proces o1, Proces o2) {
                    return o1.getExecutionTime() - o2.getExecutionTime();
                }
            });



            if (!queue.isEmpty()) {
                //pobieram pierwszy element kolejki ale nie usuwam
                Proces current = queue.peek();

                //pracuje nad pierwszym elemenetem symulujac zmniejszanie czasu wykonania
                if (current.getExecutionTime() != 0) {
                    current.setExecutionTime(current.getExecutionTime() - 1);
                }

                // dla wszyskich procesow czekajacych w kolejce oprocz "obecnego" zwiekszam czas oczekiwania
                for (Proces e : queue) {
                    if (!e.equals(current)){
                        e.setWaitingTime(e.getWaitingTime() + 1);
                    }
                }

                /*jesli obecny proces nie zostal wykonany -> przywracam go na pierwsze miejsce w kolejce*/
                if (!(current.getExecutionTime() == 0)) {
                    queue.addFirst(current);
                }
                else if (current.getExecutionTime()==0){
                    processList.add(queue.remove());
                }
            }
        }
    }

    private float averageWaitingTime(){
        int sumWaitTime=0;
        for (Proces e : processList){
            sumWaitTime+=e.getWaitingTime();
        }
        float avgWaitTime = (float)sumWaitTime/ processList.size();

        return avgWaitTime;
    }

    private int minWaitingTime(){
        int minWaitTime = processList.get(0).getWaitingTime();
        for (Proces e : processList){
            if (e.getWaitingTime()<minWaitTime){
                minWaitTime=e.getWaitingTime();
            }
        }
        return minWaitTime;
    }

    private int maksWaitingTime(){
        int maxWaitTime = processList.get(0).getWaitingTime();
        for (Proces e : processList){
            if (e.getWaitingTime()>maxWaitTime){
                maxWaitTime=e.getWaitingTime();
            }
        }
        return maxWaitTime;
    }

    private void printList(){
        for (Proces e : processList){
            System.out.println("Proces "+e.getNumber()+" waiting time = "+e.getWaitingTime());
        }
    }

    public void printStatistics(){
        printList();
        System.out.println("");
        System.out.printf("averageWaitingTime: %.2f\n", averageWaitingTime());
        System.out.println("minWaitingTime: "+minWaitingTime());
        System.out.println("maksWaitingTime: "+maksWaitingTime());
    }

}
