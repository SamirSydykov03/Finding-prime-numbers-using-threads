package MultiThreadingTu;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrimeThread implements Runnable{
    int start;
    int stop;
    String file;
    boolean lead = false;

    public void run(){
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for(int i = start; i <=stop; i++){
            if(isPrime(i)){
                primes.add(i);
            }
            if (lead && (i+1)%1000 == 0){
                System.out.println(i + "/" + stop);
            }
        }
        try {
            PrintWriter print = new PrintWriter(new File(file));
            for (int i = 0; i <primes.size();i++){
                print.println(primes.get(i));
            }
            print.close();
        }catch (Exception e){

        }
    }
    public PrimeThread(int startValue, int stopValue, String filename, boolean islead)
    {
        start = startValue;
        stop = stopValue;
        file = filename;
        lead = islead;
    }

    public static boolean isPrime(int toTest){
        if(toTest==2){
            return true;
        }
        for(int n=3; n<toTest; n+=2){
            if(toTest %n == 0){
                return false;
            }
        }
        return true;
    }
}
