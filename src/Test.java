public class Test {

    public static void main(String[] args){
        PriorQueue pq = new PriorQueue();
        // Function Call to insert elements
        // as per the priority
        pq.enqueue("asw", 2);
        pq.enqueue("bebek", 8);
        pq.enqueue("pepek", 4);
        pq.enqueue("sempuk", 3);

        // Stores the top element
        // at the moment
//        int ind = peek();
//        System.out.println(pr[ind].value);
        pq.printTop();

        // Dequeue the top element
        pq.dequeue();

        // Check the top element
//        ind = peek();
//        System.out.println(pr[ind].value);
        pq.printTop();

        // Dequeue the top element
        pq.dequeue();

        // Check the top element
//        ind = peek();
//        System.out.println(pr[ind].value);
        pq.printTop();
        pq.dequeue();

        pq.printTop();

    }
}