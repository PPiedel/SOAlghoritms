import java.util.*;

/**
 * Created by Pawel on 2015-03-12.
 */
public class Main {


    public static ArrayList<Proces> generateCollectiveList(int procesAmount){
        ArrayList<Proces> collectiveList = new ArrayList<Proces>();
        Random generator = new Random();

        Proces proces0 = new Proces(0,0,generator.nextInt(30));
        collectiveList.add(proces0);
        for (int i=1; i<procesAmount; i++){
            Proces proces = new Proces(i,((generator.nextInt(collectiveList.get(i-1).getExecutionTime()+1)+ collectiveList.get(i-1).getApplicationTime()+1)-1), generator.nextInt(30) );
            collectiveList.add(proces);
        }
        return collectiveList;
    }

    public static void main(String[] args) {

        ArrayList<Proces> collectiveList = new ArrayList<Proces>(generateCollectiveList(50));

        System.out.println("Common list: ");
        for (Proces e : collectiveList){
            System.out.println(e.toString());
        }

        System.out.println("\n\nFIFO statistics: ");
        FIFO fifo = new FIFO();
        fifo.generateFifoAlgorithm(collectiveList);
        fifo.printStatistics();

        System.out.println("\n\n----------------------------------------------------------\n\n");

        System.out.println("SJF statistics: ");
        SJF sjf = new SJF();
        sjf.generateSJFAlghoritm(collectiveList);
        sjf.printStatistics();

    }
}
