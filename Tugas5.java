import java.util.Arrays;
import java.util.List;

public class Tugas5 {
    public static void main(String[] args){
        System.out.println("Tugas Bab 5 - Pemrograman Multithreading \nChrystian|18/430257/PA/18770\n\n");      

        //get input from args
        String testString = args[0];
        System.out.println("Input string : "+testString);
        TextMonitor textMonitor = new TextMonitor(testString);
        MultithreadPrint.setTextMonitor(textMonitor);

        //setup threads
        MultithreadPrint thread1 = new MultithreadPrint("Thread-1");
        MultithreadPrint thread2 = new MultithreadPrint("Thread-2");
        MultithreadPrint thread3 = new MultithreadPrint("Thread-3");
        MultithreadPrint thread4 = new MultithreadPrint("Thread-4");

        //additional class untuk mengecek waktu dan progress
        ThreadTest benchmark = new ThreadTest("benchmark");

        //mulai threads yang ada
        benchmark.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class TextMonitor {
    private int currIterations;
    private int printIterations;
    private List<String> listofWords;
    //constructor
    TextMonitor(String text){
        printIterations = 0;
        currIterations = 0;
        this.listofWords = Arrays.asList(text.split("\\s+"));
    }

    public int getprintIterations() { // get current iteration that need to be printed.
        return printIterations;
    }
    
    public void finishPrint(){ // get update from thread that finish printing
        printIterations++;
    }

    public int getcurrIterations() { //give iteration on current word in queue
        return currIterations;
    }

    public String getcurrWords() {
        if(currIterations == listofWords.size()){ //check if all words already have been output.
            return null;
        }
        return listofWords.get(currIterations++);
    }

    public boolean isFinished() {
        if(currIterations == listofWords.size()) return true;
        else return false;
    }
}

class MultithreadPrint implements Runnable {
    private Thread t;
    private String threadName;
    public static String result;
    private static TextMonitor textMonitor;
    int a = 0;

    MultithreadPrint(String name) {
        this.threadName = name;
        System.out.println("Creating " + this.threadName);
    }

    public static void setTextMonitor(TextMonitor tM) {
        textMonitor = tM;
        result = "";
    }

    public void start() {
        System.out.println("Starting " + this.threadName);
        if(this.t==null) {
            t = new Thread (this, this.threadName);
            t.start();
        }
    }

    private void tryPrint(int iteration, String word){
        try {
            if(textMonitor.getprintIterations()==iteration){
                result += word+" ";
                System.out.println( "--"+threadName + " printed : "+ word);
                textMonitor.finishPrint();
            } else {
                Thread.sleep(50);
                tryPrint(iteration, word);
            }
        } catch(InterruptedException e) {
            System.out.println(threadName + "err");
        }
    }

    @Override
    public void run() {
        while(!textMonitor.isFinished()){
            int iteration = textMonitor.getcurrIterations();
            String word = textMonitor.getcurrWords();
            System.out.println("-"+t.getName()+" get word : " + word + ", with i : " + iteration);

            tryPrint(iteration, word);
        }
        System.out.println(threadName+" exiting.");
    }
    
}

class ThreadTest extends Thread {
	public ThreadTest(String str) {
		super(str);
	}
    
    @Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Hasil detik ke-"+ ((float)i/10) + " : "+ MultithreadPrint.result+"\n");
			try {
				sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}