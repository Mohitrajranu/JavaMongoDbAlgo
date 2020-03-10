package datastructure.linkedlist;

public class QueueBuiltWithTwoStacks<T> {

    private Stack<T> forwardStack = new Stack<>();
    private Stack<T> reverseStack = new Stack<>();


    public QueueBuiltWithTwoStacks() {

    }

    public static void main(String[] args)
            throws Queue.QueueOverflowException, Queue.QueueUnderflowException {
        QueueBuiltWithTwoStacks<Integer> queue = new QueueBuiltWithTwoStacks<Integer>();

        System.out.println("Queue full?: " + queue.isFull());
        System.out.println("Queue empty?: " + queue.isEmpty());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Queue full?: " + queue.isFull());
        System.out.println("Queue empty?: " + queue.isEmpty());

        queue.enqueue(4);
        System.out.println("Queue full?: " + queue.isFull());
        System.out.println("Queue empty?: " + queue.isEmpty());


        int data = queue.dequeue();
        System.out.println("Dequeued element: " + data);


        data = queue.dequeue();
        System.out.println("Dequeued element: " + data);

        try {
            queue.enqueue(4);
            queue.enqueue(5);
            queue.enqueue(6);
        } catch (Queue.QueueOverflowException soe) {
            System.out.println("Queue is full! No room available.");
        }

        try {
            queue.dequeue();
            queue.dequeue();
            queue.dequeue();
            queue.dequeue();
            queue.dequeue();
            queue.dequeue();
        } catch (Queue.QueueUnderflowException sue) {
            System.out.println("Queue is empty! No elements available.");
        }

    }

    public void enqueue(T data) throws Queue.QueueOverflowException {
        if (isFull()) {
            throw new Queue.QueueOverflowException();
        }

        try {
            if (forwardStack.isEmpty()) {
                while (!reverseStack.isEmpty()) {
                    forwardStack.push(reverseStack.pop());
                }
             }
            forwardStack.push(data);
        } catch (Stack.StackOverflowException | Stack.StackUnderflowException se) {
            throw new Queue.QueueOverflowException();
        }
    }

    public T dequeue() throws Queue.QueueUnderflowException {
        if (isEmpty()) {
            throw new Queue.QueueUnderflowException();
        }

        try {
            if (reverseStack.isEmpty()) {
                while (!forwardStack.isEmpty()) {
                    reverseStack.push(forwardStack.pop());
                }
            }

            return reverseStack.pop();
        } catch (Stack.StackOverflowException | Stack.StackUnderflowException se) {
            throw new Queue.QueueUnderflowException();
        }
    }

    public boolean isFull() {
        return forwardStack.isFull() || reverseStack.isFull();
    }

    public boolean isEmpty() {
        return forwardStack.isEmpty() && reverseStack.isEmpty();
    }

}
