package com.simon.datastructure.list;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * Created by simon on 17-5-17.
 */

public class LinkedList<E> implements List<E> {

    private Node<E> head;

    private Node<E> tail;

    private int size;

    private Node<E> cacheHead;

    public LinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> pointer = head;

            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public E next() {
                Node<E> thisNode = pointer;
                pointer = thisNode.next;
                return thisNode.item;
            }
        };
    }

    @Override
    public Object[] toArray() {
        final Object[] array = new Object[size];
        Node<E> pointer = head;
        for (int i = 0; i < size; i++) {
            array[i] = pointer.item;
            pointer = pointer.next;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int i = 0;
        Object[] result = a;
        for (Node<E> x = head; x != null; x = x.next)
            result[i++] = x.item;

        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        addNewNode(obtainNode(null, e, null));
        size++;
        return true;
    }

    private void addFirstNode(Node<E> node) {
        head = node;
        tail = head;
    }

    private void addNodeToEnd(Node<E> node) {
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    private void addNewNode(Node<E> node) {
        if(head == null) {
            addFirstNode(node);
        } else {
            addNodeToEnd(node);
        }
    }

    @Override
    public boolean remove(Object o) {
        if(head == null) return false;
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.item == null){
                    removeNode(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.item)){
                    removeNode(x);
                    return true;
                }
            }
        }
        return false;
    }


    private void removeNode(Node<E> node) {
        if(node == head) {
            head = node.next;
            head.prev = null;
        } else if(node == tail) {
            tail = node.prev;
            tail.next = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
        }
        recyclerNode(node);
        size--;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if(!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int sizeBefore = size;
        for (E e : c) {
            add(e);
        }
        return sizeBefore != size;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);
        Node<E> targetNode = node(index);
        int sizeBefore = size;
        for (E e : c) {
            Node<E> newNode = obtainNode(null, e, null);
            insertBeforeNode(targetNode, newNode);
        }
        return sizeBefore != size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int sizeBefore = size;
        for (Object o : c) {
            remove(o);
        }
        return sizeBefore != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(head == null) return false;
        Node<E> pointer = head;
        int sizeBefore = size;
        while(pointer != null) {
            Node<E> next = pointer.next;
            if(!c.contains(pointer.item)) {
                removeNode(pointer);
            }
            pointer = next;
        }
        return sizeBefore != size;
    }

    @Override
    public void clear() {
        Node<E> pointer = head;
        while (pointer != null) {
            Node<E> next = pointer.next;
            recyclerNode(pointer);
            pointer = next;
        }
        size = 0;
    }

    @Override
    public E get(final int index) {
        checkElementIndex(index);
        Node<E> node = node(index);
        return node != null ? node.item : null;
    }

    @Override
    public E set(final int index, E element) {
        checkPositionIndex(index);
        Node<E> node = node(index);
        E itemOld = node.item;
        node.item = element;
        return itemOld;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if(index == 0) {
            addFirst(element);
        } else if(index == size) {
            add(element);
        } else {
            Node<E> node = node(index);
            Node<E> prev = node.prev;
            Node<E> newNode = obtainNode(prev, element, node);
            node.prev = newNode;
            prev.next = newNode;
        }

    }

    public void addFirst(E element) {
        Node<E> node = obtainNode(null, element, null);
        if(head == null) {
            head = node;
            tail = head;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        Node<E> node = node(index);
        E item = node.item;
        removeNode(node);
        return item;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if (o == null) {
            for (Node<E> x = tail; x != null; x = x.prev) {
                if (x.item == null)
                    return index;
                index--;
            }
        } else {
            for (Node<E> x = tail; x != null; x = x.prev) {
                if (o.equals(x.item))
                    return index;
                index--;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new RuntimeException("not supported");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new RuntimeException("not supported");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        checkElementIndex(fromIndex);
        checkElementIndex(toIndex);
        if(fromIndex >= toIndex) {
            throw new IllegalStateException("fromIndex must be less than toIndex");
        }
        LinkedList<E> subList = new LinkedList<>();
        Node<E> pointer = node(fromIndex);
        int subListSize = toIndex - fromIndex + 1;
        for (int i = 0; i < subListSize; i++) {
            subList.add(pointer.item);
            pointer = pointer.next;
        }
        return subList;
    }


    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    public void forEach(OnEach<E> onEach) {
        forEach(onEach, false);
    }

    public void forEach(OnEach<E> onEach, boolean reverseOrder) {
        if(head == null) return;
        if(!reverseOrder) {
            int index = 0;
            Node<E> pointer = head;
            while (pointer!= null) {
                onEach.onEach(index++, pointer);
                pointer = pointer.next;
            }
        } else {
            int index = size - 1;
            Node<E> pointer = tail;
            while (pointer!= null) {
                onEach.onEach(index--, pointer);
                pointer = pointer.prev;
            }
        }
    }

    private void insertAfterNode(Node<E> targetNode, Node<E> newNode) {
        if(targetNode == tail) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<E> next = targetNode.next;
            targetNode.next = newNode;
            newNode.prev = targetNode;
            newNode.next = next;
            next.prev = newNode;
        }
        size ++;
    }

    private void insertBeforeNode(Node<E> targetNode, Node<E> newNode) {
        if(targetNode == head) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> prev = targetNode.prev;
            prev.next = newNode;
            newNode.next = targetNode;
            targetNode.prev = newNode;
            newNode.prev = prev;
        }
        size ++;
    }

    private void deleteAfterNode(Node<E> targetNode) {
        if(tail == targetNode) return;
        Node<E> nodeToDelete = targetNode.next;
        if(nodeToDelete == tail) {
            targetNode.next = null;
        } else {
            Node<E> next = nodeToDelete.next;
            targetNode.next = null;
            next.prev = null;
        }
        recyclerNode(nodeToDelete);
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }


    private Node<E> obtainNode(Node<E> prev,E item,Node<E> next) {
        if(cacheHead != null) {
            Node<E> node = cacheHead;
            cacheHead = node.next;
            node.item = item;
            node.next = next;
            return node;
        } else {
            return new Node<>(prev, item, next);
        }
    }

    private void recyclerNode(Node<E> node) {
        node.clear();
        if(cacheHead == null) {
            cacheHead = node;
        } else {
            node.next = head;
            cacheHead = node;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }

        void clear() {
            this.prev = null;
            this.item = null;
            this.next = null;
        }

    }

    public interface OnEach<E> {
        void onEach(int index, Node<E> node);
    }

    @Override
    public String toString() {
        if(head == null) return "empty";
        final StringBuilder sb = new StringBuilder();
        Node<E> pointer = head;
        while (pointer != null) {
            sb.append(pointer.item).append(",");
            pointer = pointer.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
