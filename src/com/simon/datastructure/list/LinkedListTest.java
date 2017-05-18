package com.simon.datastructure.list;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * Created by simon on 17-5-17.
 */
public class LinkedListTest {


    @Test
    public void testAdd() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        assertEquals("1,2,3",linkedList.toString());

        linkedList.add(3,5);

        assertEquals("1,2,3,5",linkedList.toString());

        linkedList.set(3,4);

        assertEquals("1,2,3,4",linkedList.toString());

    }

    @Test
    public void testAddAll() {

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        LinkedList<Integer> listToAdd = new LinkedList<>();
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        linkedList.addAll(listToAdd);

        assertEquals("1,2,3,4,5,6",linkedList.toString());

        listToAdd.clear();

        listToAdd.add(11);
        listToAdd.add(12);
        listToAdd.add(13);
        listToAdd.add(14);

        linkedList.addAll(0,listToAdd);

        assertEquals("11,12,13,14,1,2,3,4,5,6",linkedList.toString());
    }

    @Test
    public void testSize() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        assertEquals(3,linkedList.size());
    }

    @Test
    public void testEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        assertEquals(true,linkedList.isEmpty());
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        assertEquals(false,linkedList.isEmpty());
    }

    @Test
    public void testIterator() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        StringBuilder sb = new StringBuilder();
        for (Integer integer : linkedList) {
            sb.append(integer).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        assertEquals("1,2,3",sb.toString());
    }


    @Test
    public void testContains() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        assertEquals(true, linkedList.contains(3));
        assertEquals(false, linkedList.contains(4));
    }

    @Test
    public void testIndexOf() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        linkedList.add("4");
        assertEquals(3,linkedList.indexOf("4"));
        assertEquals(7,linkedList.lastIndexOf("4"));

    }


    @Test
    public void testRemove() {

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        linkedList.remove(2);

        assertEquals("1,2",linkedList.toString());

        linkedList.add("3");

        linkedList.remove("1");

        assertEquals("2,3",linkedList.toString());

    }


    @Test
    public void testRemoveAll() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        LinkedList<String> listToRemove = new LinkedList<>();
        listToRemove.add("1");
        listToRemove.add("3");
        listToRemove.add("2");

        linkedList.removeAll(listToRemove);

        assertEquals("4,5,6,7",linkedList.toString());
    }

    @Test
    public void testRetainAll() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        LinkedList<String> listToRetain = new LinkedList<>();
        listToRetain.add("1");
        listToRetain.add("3");
        listToRetain.add("2");

        linkedList.retainAll(listToRetain);

        assertEquals("1,2,3",linkedList.toString());
    }

    @Test
    public void testToArray() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        assertEquals(Arrays.equals(linkedList.toArray(new String[3]),new String[]{"1","2","3"}), true);
    }

    @Test
    public void testSubList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        linkedList.add("8");
        linkedList.add("9");
        linkedList.add("10");

        List<String> subList = linkedList.subList(3, 8);

        assertEquals("4,5,6,7,8,9",subList.toString());

    }



}
