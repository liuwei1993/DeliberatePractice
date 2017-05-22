package com.simon.datastructure.queue;

import com.simon.datastructure.list.LinkedList;

/**
 * queue
 * Created by simon on 17-5-22.
 */
public class LinkedQueue implements Queue {

    private LinkedList<String> linkedList = new LinkedList<>();

    public static Queue createQueue(String[] dataList) {
        Queue queue = new LinkedQueue();
        queue.enterDataList(dataList);
        return queue;
    }

    @Override
    public boolean enterDataList(String[] dataList) {
        boolean result = true;
        for (String s : dataList) {
            if (enterQueue(s)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean enterQueue(String data) {
        return linkedList.add(data);
    }

    @Override
    public String exitQueue() {
        if (linkedList.size() == 0) return null;
        return linkedList.remove(0);
    }

    @Override
    public int size() {
        return linkedList.size();
    }
}
