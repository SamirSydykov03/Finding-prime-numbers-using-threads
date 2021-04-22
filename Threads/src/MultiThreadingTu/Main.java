package MultiThreadingTu;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int threadCount = Runtime.getRuntime().availableProcessors();
    public static int stop = 80000;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Spawning threads " );
        ArrayList<Thread> threads = new ArrayList<Thread>();
        int incrementAmount = stop/threadCount;
        int starting = 1;


        for(int i = 0; i<threadCount;i++){
            if(!((i+1)== threadCount)){
                threads.add(new Thread(new PrimeThread(starting, starting + incrementAmount,
                        i + ".txt", false)));
                starting+=incrementAmount;
            }
            else{
                threads.add(new Thread(new PrimeThread(starting, starting + incrementAmount,
                        i + ".txt", true)));
            }
        }
        for (int i=0;i< threads.size();i++){
            threads.get(i).start();
        }
        for (int i=0;i< threads.size();i++){
            try{
            threads.get(i).join();
        }catch (Exception e){
                System.out.println("Flip");
            }
        }
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for (int i=0;i< threads.size();i++){
            try{
                File f = new File(i+".txt");
                Scanner scan = new Scanner(f);
            while (scan.hasNextInt()){
                primes.add(scan.nextInt());
                }
                scan.close();
            }
            catch (Exception e){
                System.out.println("Flip");
            }
        }
        try{
            PrintWriter print = new PrintWriter(new File("primes.txt"));
                for (int i = 0; i < primes.size();i++){
                    print.println((primes.get(i)));
                }
                print.close();
        }catch (Exception e){
            System.out.println("Flip x2");
            }
        }
    }

