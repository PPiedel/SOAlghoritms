import java.util.*;


/**
 * Created by Pawel on 2015-03-12.
 */
public class FIFO {
    private ArrayList<Proces> processList;

    FIFO(){
    }


    /*Symulacja algorytmu FIFO*/
    public void generateFifoAlgorithm(ArrayList<Proces> collectiveList){
        int time=0;
        int i=0;

        // uzywam LinkedList jako kolejki procesow
        LinkedList<Proces> queue = new LinkedList<Proces>();
        processList = new ArrayList<Proces>();

        //petla symuluje uplyw czasu i sprawdzanie kolejnych elementow z "banku" procesow
        for( ; i<collectiveList.size() || !queue.isEmpty(); time++){

            //dodaje wszystkie procesy z "banku" procesow, ktore zglosily sie w danej chwili
            //kolejkuje procesy wg czasu zgloszenia
            for ( ; i<collectiveList.size() && collectiveList.get(i).getApplicationTime()==time; i++){
                queue.add(collectiveList.get(i));
            }

            if (!queue.isEmpty()){
                //pobieram, ale nie usuwam pierwszy element kolejki
                Proces current = queue.peek();

                //pracuje nad pierwszym elemenetem symulujac zmniejszanie czasu wykonania
                if (current.getExecutionTime()!=0){
                    current.setExecutionTime(current.getExecutionTime()-1);
                }


                // dla wszyskich procesow czekajacych w kolejce zwiekszam czas oczekiwania
                for (Proces e : queue){
                    if (!e.equals(current)){
                        e.setWaitingTime(e.getWaitingTime()+1);
                    }
                }
                //jesli proces zostal wykonany -> usuwam proces z kolejki
                if (current.getExecutionTime()==0){
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

    public void printStatistics(){
        printList();
        System.out.println("");
        System.out.printf("averageWaitingTime: %.2f\n", averageWaitingTime());
        System.out.println("minWaitingTime: "+minWaitingTime());
        System.out.println("maksWaitingTime: "+maksWaitingTime());
    }

    private void printList(){
        for (Proces e : processList){
            System.out.println("Proces "+e.getNumber()+" waiting time = "+e.getWaitingTime());
        }
    }
}
