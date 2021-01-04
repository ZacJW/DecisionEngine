package DecisionEngine.Utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentAVLTree<T>{
    T data = null;
    Lock lock;
    int balanceFactor = 0;
    ConcurrentAVLTree<T> left = null;
    ConcurrentAVLTree<T> right = null;
    
    public ConcurrentAVLTree(){
        lock = new ReentrantLock();
    }
    void add(T data){
        lock.lock();
        if (this.data == null){
            this.data = data;
        }else if(data.hashCode() < this.data.hashCode()){
            if (left == null){
                left = new ConcurrentAVLTree<T>();
            }
            left.add(data);
        }else if (data.hashCode() > this.data.hashCode()){
            if (right == null){
                right = new ConcurrentAVLTree<T>();
            }
            right.add(data);
        }
        lock.unlock();
    }
}
