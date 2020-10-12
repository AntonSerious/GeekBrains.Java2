package hometask5;

public class hometask5 extends Thread{
    static final int size = 10000000;
    static final int h = size/2;
    float[] arr = new float[size];

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of processors" + processors);
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        //doWork(arr);
        doWorkMultiThreadingly(arr);
    }

    private static void doWork( float[] arr){

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
        }
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(arr[67895]); //для сверки
    }

    private static void doWorkMultiThreadingly(float[] arr){
        long startTime = System.currentTimeMillis();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        System.arraycopy(arr, 0, arr1,0, h);
        System.arraycopy(arr, h, arr2,0, h);

        MyThread t1 = new MyThread("thread1", arr1, 0);
        MyThread t2 = new MyThread("thread2", arr2, 1);
        try{
            t1.join();
            t2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(arr[67895]); //для сверки


    }
}
