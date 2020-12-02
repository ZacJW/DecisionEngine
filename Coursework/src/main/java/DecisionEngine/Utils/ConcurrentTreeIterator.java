package DecisionEngine.Utils;

import java.util.Iterator;
import java.util.Stack;

public class ConcurrentTreeIterator<T> implements Iterator<T>{
    Stack<ConcurrentTree<T>> stack;
    public ConcurrentTreeIterator(ConcurrentTree<T> tree){
        stack = new Stack<ConcurrentTree<T>>();
        pushLeft(tree);
    }
    public T next(){
        ConcurrentTree<T> node = stack.pop();
        pushLeft(node.right);
        return node.data;
    }
    public boolean hasNext(){
        return !stack.empty();
    }
    void pushLeft(ConcurrentTree<T> node){
        if (node != null && node.data != null){
            stack.push(node);
            pushLeft(node.left);
        }
    }
}
