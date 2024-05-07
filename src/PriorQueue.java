import java.util.*;

class item {
    private String value;
    private int priority;

    item(String val, int prior) {
        this.value = val;
        this.priority = prior;
    }

    public String getValue() {
        return this.value;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setValue(String val) {
        this.value = val;
    }

    public void setPriority(int prior) {
        this.priority = prior;
    }
};

public class PriorQueue {
    private item[] pr;
    private int size;

    public PriorQueue() {
        this.pr = new item[100000];
        this.size = -1;
    }
    public void enqueue(String value, int priority)
    {
        size++;

        pr[size] = new item(value, priority);
    }

    public int peek()
    {
        int lowestPrior = Integer.MAX_VALUE;
        int ind = -1;

        for (int i = 0; i <= size; i++) {
            if (lowestPrior == pr[i].getPriority()
                    && ind > -1
                    && pr[ind].getValue().compareTo(pr[i].getValue()) < 0) {
                lowestPrior = pr[i].getPriority();
                ind = i;
            }
            else if (lowestPrior > pr[i].getPriority()) {
                lowestPrior = pr[i].getPriority();
                ind = i;
            }
        }
        return ind;
    }

    public void dequeue()
    {
        int ind = peek();

        for (int i = ind; i < size; i++) {
            pr[i] = pr[i + 1];
        }
        size--;
    }

    public void printTop() {
        int ind = peek();
        System.out.println(pr[ind].getValue());
    }

    public String getTopVal() {
        if (size < 0) {
            return null; // or throw an exception
        }
        int ind = peek();
        String top = pr[ind].getValue();
        dequeue();
        return top;
    }

    public void printAndEmptyQueue() {
        while (this.size >= 0) {
            int ind = peek();
            item it = pr[ind];
            System.out.println("Value: " + it.getValue() + ", Priority: " + it.getPriority());
            dequeue(); // Remove the item after printing
        }
    }

}
