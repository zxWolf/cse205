
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private final static int RUNS_UP = 1;
    private final static int RUNS_DN = -1;

    public static void main(String[] pArgs) {
        new Main().run();
    }

    private void run() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            list = readInputFile("p1-in.txt"); 
            System.out.println(list.size());
        } catch (FileNotFoundException pException) {
            System.out.println("File not found, please try again!");
            System.exit(-100);
        }
        ArrayList<Integer> listRunsUpCount = new ArrayList<>();
        ArrayList<Integer> listRunsDnCount = new ArrayList<>();

        listRunsUpCount.addAll(findRuns(list, RUNS_UP));
        listRunsDnCount.addAll(findRuns(list, RUNS_DN));

        System.out.println(listRunsUpCount.size());
        System.out.println(listRunsDnCount.size());


        ArrayList<Integer> listRunsCount = new ArrayList<>(mergeLists(listRunsUpCount, listRunsDnCount));
        try {
            writeOutputFile("p1-runs.txt", listRunsCount);

        } catch(FileNotFoundException pException) {
            System.out.println("File not found, please try again!");
            System.exit(-200);
        }

    }

    public ArrayList<Integer> findRuns(ArrayList<Integer> pList, int pDir) {
        ArrayList<Integer> listRunsCount = new ArrayList<Integer>(arrayListCreate(pList.size(), 0));
        int i = 0, k = 0;
        while (i < pList.size() - 1) {
            System.out.println("i:" + i);
            if (pDir == RUNS_UP && pList.get(i) <= pList.get(i + 1)) {
                k++;
                i++;
                System.out.println("Up Works " + k);
            }
            else if (pDir == RUNS_DN && pList.get(i) >= pList.get(i + 1)) {
                k++;
                
                System.out.println("Dn works " + k);

            }
            else {
                if (k != 0) {
                    listRunsCount.add(k, listRunsCount.get(k) + 1);
                    k = 0;
                }
                i++;
            }
            
            
        }
        if (k != 0) {
            listRunsCount.add(k, listRunsCount.get(k) + 1);
        }
        System.out.println(listRunsCount.size());
        return listRunsCount;
    }



    public ArrayList<Integer> mergeLists(ArrayList<Integer> pListRunsUpCount, ArrayList<Integer> pListRunsDnCount) {
        ArrayList<Integer> listRunsCount = new ArrayList<Integer>(arrayListCreate(pListRunsUpCount.size(), 0));
        
        for (int i = 0; i < pListRunsUpCount.size() - 1; i++) {
            listRunsCount.set(i, pListRunsUpCount.get(i) + pListRunsDnCount.get(i));
        }

        return listRunsCount;
    }



    public ArrayList<Integer> arrayListCreate(int pSize, int pInitValue) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < pSize; i++) {
            list.add(pInitValue);
        }
        return list;
    }



    public void writeOutputFile(String pFilename, ArrayList<Integer> pListRuns) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(pFilename);
        int x = 0;
        for (int i = 0; i < pListRuns.size(); i++) {
            x += pListRuns.get(i);
        }
        
        out.println("runs_total: " + x); 
        for (int k = 1; k < pListRuns.size() - 1; k++) {
            out.println("run_" + k + " " + pListRuns.get(k));
        }
        out.close();
    }



    public ArrayList<Integer> readInputFile(String pFilename) throws FileNotFoundException {

        Scanner in = new Scanner(new File(pFilename));
        ArrayList<Integer> list = new ArrayList<>();

        while (in.hasNextInt()) {
            list.add(in.nextInt());
        }
        in.close();

        return list;
    }


}

