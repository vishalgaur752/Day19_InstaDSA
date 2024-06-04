public class ReverseNodeKGroup {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void print() {
        if (head == null) {
            System.out.println("ll is empty.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static Node reverseKGroup(Node head, int k) {
        int count = 0;
        Node dummy = new Node(count);
        dummy.next = head;
        Node temp = dummy;
        while (temp.next != null) {
            temp = temp.next;
            count++;
        }
        temp = dummy;
        while (temp.next != null) {
            if (count < k) {
                break;
            }
            int nodes = k - 1;
            Node tempNext = temp.next;
            Node first = temp.next;
            Node second = first.next;
            while (nodes-- > 0) {
                Node next = second.next;
                second.next = first;
                first = second;
                second = next;
            }
            count -= k;
            temp.next = first;
            tempNext.next = second;
            temp = tempNext;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        int k = 2;
        Node result = reverseKGroup(head, k);
        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
        System.out.println();
    }
}
