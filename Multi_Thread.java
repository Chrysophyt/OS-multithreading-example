import java.util.Arrays;
import java.util.List;

public class Multi_Thread {
    public static void main(String[] args){
        System.out.println("Tugas Bab 5 - Pemrograman Multithreading \nChrystian|18/430257/PA/18770");      
        for (String string : args){
            System.out.print(string);
        }

        if (args[0].equalsIgnoreCase("encrypt")){
            System.out.println("encrypting");
        }
    }
}

class CaesarCipher {
    public static String encrypt(String text, int s) 
    { 
        StringBuffer result= new StringBuffer(); 
  
        for (int i=0; i<text.length(); i++) 
        { 
            if (Character.isUpperCase(text.charAt(i))) 
            { 
                char ch = (char)(((int)text.charAt(i) + s - 65) % 26 + 65); 
                result.append(ch); 
            } 
            else
            { 
                char ch = (char)(((int)text.charAt(i) + s - 97) % 26 + 97); 
                result.append(ch); 
            } 
        } 
        return result.toString(); 
    } 

    public static StringBuffer decrypt(String text, int s) 
    { 
        StringBuffer result= new StringBuffer(); 
  
        for (int i=0; i<text.length(); i++) 
        { 
            if (Character.isUpperCase(text.charAt(i))) 
            { 
                char ch = (char)(((int)text.charAt(i) - s - 65 + 26) % 26 + 65); 
                result.append(ch); 
            } 
            else
            { 
                char ch = (char)(((int)text.charAt(i) - s - 97 + 26) % 26 + 97); 
                result.append(ch); 
            } 
        } 
        return result; 
    } 
}

class StringMonitor {
    private int iterations;
    private List<String> listofWords;
    StringMonitor(String text){
        this.listofWords = Arrays.asList(text.split("\\W+"));
    }
}

class MultithreadPrint implements Runnable {
    private Thread t;
    private String threadName;
    int a = 0;

    MultithreadPrint(String name) {
        this.threadName = name;
        System.out.println("Creating " + this.threadName);
    }

    public void start() {
        System.out.println("Starting " + this.threadName);
        if(this.t==null) {
            t = new Thread (this, this.threadName);
            t.start();
        }
    }

    @Override
    public void run() {
        

    }
    
}