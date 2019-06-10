import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private int x=1;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.replaceAll(a ->a==1?a:a+1);
        for(int i=0;i<3;i++){
            System.out.println("list:"+list.get(i));
        }

        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Condition condition= lock2.newCondition();

        Main main = new Main();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock2.lock();
                while (true){
                    ++main.x;
                    System.out.println("thread-name1:"+Thread.currentThread());
                    if(main.x==100) {
                        lock2.unlock();
                        System.out.println("main.x1："+main.x);
                    }
                    if(main.x==300){
//                        lock1.lock();
                        System.out.println("-------------------------------------------------main.x1==300");
                    }
                    if(main.x==500){
//                        lock1.unlock();
                        condition.signal();
                        System.out.println("-------------------------------------------------main.x1==500");
                    }
                    if(main.x==1000){
                        break;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock2.lock();
                while (true){
                    if(main.x==350){
//                        lock1.lock();
                        System.out.println("----------------------------------------------------main.x2==350");
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("----------------------------------------------------lock2.wait()");
                    }
                    ++main.x;
                    System.out.println("thread-name2:"+Thread.currentThread());
                    if(main.x==400) {
                        lock2.unlock();
                        break;
                    }
                }
            }
        }).start();

    }
}
