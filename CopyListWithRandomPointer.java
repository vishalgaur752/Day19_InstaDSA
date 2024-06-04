import java.util.HashMap;

public class CopyListWithRandomPointer {
    public static class Node {
        int data;
        Node next;
        Node random;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }
    }

    public static Node head;
    public static Node tail;

    public void addfirst(int data) {
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
        System.out.println();
    }

    public Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        Node newHead = new Node(0);
        Node new_curr = newHead;
        Node curr = head;
        HashMap<Node, Node> umap = new HashMap<>();
        while(curr != null) {
            Node temp = new Node(curr.data);
            umap.put(curr,temp);

            new_curr.next = temp;
            new_curr = new_curr.next;
            curr = curr.next;
        }
        curr = head;
        new_curr = newHead.next;
        while(curr != null) {
            Node random = curr.random;
            Node newNode = umap.get(random);
            new_curr.random = newNode;

            new_curr = new_curr.next;
            curr = curr.next;
        }
        return newHead.next;        
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer list = new CopyListWithRandomPointer();
    
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
    
        list.head.random = list.head.next.next; // 1 -> 3
        list.head.next.random = list.head; // 2 -> 1
        list.head.next.next.random = list.head.next.next.next; // 3 -> 4
        list.head.next.next.next.random = list.head.next; // 4 -> 2
        list.head.next.next.next.next.random = list.head; // 5 -> 1
    
        System.out.println("Original List:");
        list.print();
    
        Node copiedHead = list.copyRandomList(list.head);
    
        System.out.println("Copied List:");
        Node temp = copiedHead;
        while (temp!= null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    
        System.out.println("Random pointers in copied list:");
        temp = copiedHead;
        while (temp!= null) {
            if (temp.random!= null) {
                System.out.println(temp.data + " -> " + temp.random.data);
            } else {
                System.out.println(temp.data + " -> null");
            }
            temp = temp.next;
        }
    }
}
