package deque;

/** This is the first implementation of Deque, using Doubly Linked List, with circular sentinel node*/
public class LinkedListDeque<Item> {
    public class ListNode {
        private Item item;
        private ListNode prev;
        private ListNode next;

        /* Constructor for regular node with item i */
        public ListNode(Item i, ListNode p, ListNode n) {
            item = i;
            prev = p;
            next = n;
        }

        /* Constructor for sentinel node */
        public ListNode(ListNode p, ListNode n) {
            prev = p;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private ListNode sentinel;
    private int size;

    /* Initialize an empty DLList */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel; // circular linked list structure
        size = 0;
    }

    /* Add an item of type Item to the front of the deque.
    * Tips: add a node of item before the first element of the linked list, after insertion,
    * the new node will be the first node */
    public void addFirst(Item item) {
        ListNode newNode = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /* Add an item of type Item to the back of the deque*/
    public void addLast(Item item) {
        ListNode newNode = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /* Check if the deque is empty*/
    public boolean isEmpty() {
        return size == 0;
    }

    /* Return the size of the deque*/
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by space*/
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("empty deque");
        }
        ListNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    /* Removes and returns the item at the front of the deque */
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item removedFirst = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size -= 1;
        return removedFirst;
    }

    /* Removes and returns the item at the back of the deque */
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item removedLast = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size -= 1;
        return removedLast;
    }

    /* Gets the index-th item, where 0 is the front, 1 is the next item.
    If no such item exists, return null.*/
    public Item get(int index) {
        if (size < index) {
            return null;
        }
        ListNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
        }
        return p.item;
    }

    /* Using recursion, to get the item at a given index.
    * Tips: A helper method would be nice, just to track the current node. */
    public Item getRecursive(int index) {
        if (size < index) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private Item getRecursiveHelper(ListNode node, int i) {
        if (i == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, i - 1);
    }

    public static void main(String[] args) {
        /* Create a list of one integer, namely 10*/
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(30);
        L.addLast(20);
        L.addLast(10);
        L.printDeque();
    }
}