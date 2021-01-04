package DecisionEngine.Utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentTree<T> implements Iterable<T>{
    final Lock lock;
    volatile T data = null;
    volatile ConcurrentTree<T> left = null;
    volatile ConcurrentTree<T> right = null;
    public ConcurrentTree(){
        lock = new ReentrantLock();
    }
    public void add(T data){
        lock.lock();
        if(this.data == null){
            this.data = data;
            left = new ConcurrentTree<T>();
            right = new ConcurrentTree<T>();
            lock.unlock();
            return;
        }else{
            lock.unlock();
            if(data.hashCode() < this.data.hashCode()){
                left.add(data);
            }else if (data.hashCode() > this.data.hashCode()){
                right.add(data);
            }
        }
    }
    public ConcurrentTreeIterator<T> iterator(){
        return new ConcurrentTreeIterator<T>(this);
    }
    public void clear(){
        lock.lock();
        data = null;
        left = null;
        right = null;
    }
}