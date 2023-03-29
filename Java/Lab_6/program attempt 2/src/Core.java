import java.util.Random; 
public class Core {
    static final int N = 15000;
    static final boolean IT_IS_STRING_PRINTING = false;
    
    public static void printSorted(int[] array) { 
        sort(array);
        String line = "\n" + Thread.currentThread().getName() + ":\n"; 
        for(int i = 0; i < N; i++) {
            line+=array[i] + " ";
        }
        System.out.println(line);
    }
    synchronized
    public  static void printSortedSequently(int[] array) { 
        sort(array);
    
        System.out.println("\n**************************************************");
     
    
        for(int i = 0; i < N/100; i++) { 
            System.out.print(Thread.currentThread().getName()+": "); 
            for (int j = 0; j < 100; j++) {
                System.out.print(array[i*100+j] + " ");
        
        }
        
            System.out.println("\n**************************************************");
        }
    }
    
    private static void sort(int[] array) { 
        for (int i = 0; i + 1 < array.length; i++) {
            for (int j = 0; j + 1 < array.length - i; j++) {
              if (array[j + 1] > array[j]) {
                int tmp = array[j];
                array[j] = array[j+1];
                array[j+1] = tmp;
              }
            }
          }
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        int[] array1 = new int[N], array2 = new int[N],
        
        array3 = new int[N];
        
        Random rand = new Random(); 
        for (int i = 0; i < N; i++) {
          	array1[i] = Math.abs(rand.nextInt()%100); 
array2[i] = Math.abs(rand.nextInt()%100); 
array3[i] = Math.abs(rand.nextInt()%100);
        }
        
        Thread thread1, thread2, thread3; 
  if(IT_IS_STRING_PRINTING) {
            thread1 = new Thread(() -> printSorted(array1)); 
thread2 = new Thread(() -> printSorted(array2)); 
            thread3 = new Thread(() -> printSorted(array3));
        } else {
            thread1 = new Thread(() -> printSortedSequently(array1));
            thread2 = new Thread(() -> printSortedSequently(array2));
            thread3 = new Thread(() -> printSortedSequently(array3));
        }
        
        thread1.setName("Thread_1"); 
        thread1.setPriority(1); 
        thread2.setName("Thread_2"); 
        thread2.setPriority(1); 
        thread3.setName("Thread_3"); 
        thread3.setPriority(1);
        
      	  thread1.start(); 
  thread2.start(); 
  thread3.start();
    }
}
