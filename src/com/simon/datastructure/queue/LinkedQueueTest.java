package com.simon.datastructure.queue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * LinkedQueue Test
 * Created by simon on 17-5-22.
 */
public class LinkedQueueTest {

    @Test
    public void testQueueEnterExit() {
        Queue queue = new LinkedQueue();
        queue.enterQueue("1");
        queue.enterQueue("2");
        queue.enterQueue("3");
        queue.enterQueue("4");
        queue.enterQueue("5");
        assertEquals(5, queue.size());
        assertEquals("1",queue.exitQueue());
        assertEquals("2",queue.exitQueue());
        assertEquals("3",queue.exitQueue());
        assertEquals("4",queue.exitQueue());
        assertEquals("5",queue.exitQueue());
    }


}
