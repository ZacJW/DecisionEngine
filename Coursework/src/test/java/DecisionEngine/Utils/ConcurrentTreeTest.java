package DecisionEngine.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ConcurrentTreeTest {
    @Test
    public void singleThreadWithIterator(){
        ConcurrentTree<Integer> tree = new ConcurrentTree<Integer>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(4);
        String out = treeToSet(tree).toString();
        assertEquals("Output: " + out + " is not as expected", out, "[1, 2, 3, 4, 5]");
    }

    @Test
    public void multiThread() throws InterruptedException{
        ConcurrentTree<Integer> tree = new ConcurrentTree<Integer>();
        Integer[] arr1 = {1,2,3};
        Integer[] arr2 = {1,3,4};
        TreeInserter thread1 = new TreeInserter(arr1, tree);
        TreeInserter thread2 = new TreeInserter(arr2, tree);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Set<Integer> out = treeToSet(tree);
        assertTrue("", out.contains(arr1[0]));
    }

    static <T> Set<T> treeToSet(ConcurrentTree<T> tree){
        Set<T> set = new HashSet<T>();
        for (T data : tree){
            set.add(data);
        }
        return set;
    }
}

class TreeInserter extends Thread {
    Integer[] arr;
    ConcurrentTree<Integer> tree;
    TreeInserter(Integer[] arr, ConcurrentTree<Integer> tree){
        this.arr = arr;
        this.tree = tree;
    }

    @Override
    public void run(){
        for (Integer x : arr){
            tree.add(x);
        }
    }
}