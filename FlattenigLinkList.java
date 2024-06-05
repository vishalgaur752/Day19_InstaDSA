public class FlattenigLinkList {
    public static class Node {
        int data;
        Node next;
        Node bottom;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
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

    public Node flattningLL(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = flattningLL(head.next);
        head = merge(head, head.next);
        return head;
    }

    public Node merge(Node l1, Node l2) {
        Node res = new Node(0);
        Node temp = res;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                temp.bottom = l1;
                temp = temp.bottom;
                l1 = l1.bottom;
            } else {
                temp.bottom = l2;
                temp = temp.bottom;
                l2 = l2.bottom;
            }
        }
        if (l1 != null) {
            temp.bottom = l1;
        } else {
            temp.bottom = l2;
        }
        return res.bottom;
    }

    public static void main(String[] args) {
        FlattenigLinkList ll = new FlattenigLinkList();
        ll.addLast(5);
        ll.addLast(10);
        ll.addLast(19);
        ll.addLast(28);

        ll.head.bottom = new Node(7);
        ll.head.bottom.bottom = new Node(8);
        ll.head.bottom.bottom.bottom = new Node(30);
        ll.head.next.bottom = new Node(20);
        ll.head.next.next.bottom = new Node(22);
        ll.head.next.next.bottom.bottom = new Node(50);
        ll.head.next.next.next.bottom = new Node(35);
        ll.head.next.next.next.bottom.bottom = new Node(40);
        ll.head.next.next.next.bottom.bottom.bottom = new Node(45);
        Node temp = ll.flattningLL(ll.head);
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.bottom;
        }
    }
}
