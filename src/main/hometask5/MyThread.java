package hometask5;

public class MyThread extends Thread{
    private boolean isRunning;
    private float[] arr;
    private final int threadNumber;
    public MyThread(String name, float[] arr, int threadNumber){
        super(name);
        this.arr = arr;
        this.threadNumber = threadNumber;
        start();
        isRunning = true;

    }
    @Override
    public void run() {
        System.out.println(this.getName() + " started");
        int l = this.arr.length;
        int k = this.threadNumber * l;
        for (int i = 0; i < l; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i + k) / 5.0) * Math.cos(0.2f + (i + k) / 5.0) * Math.cos(0.4f + (i + k) / 2.0));
        }
        System.out.println(this.getName() + " finished");

    }
    public void setRunning(boolean running){
        isRunning = running;
    }
}
