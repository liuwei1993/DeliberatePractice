package com.simon.datastructure.queue;

/**
 * queue interface
 * Created by simon on 17-5-22.
 */
public interface Queue {

    boolean enterDataList(String[] dataList);

    boolean enterQueue(String data);

    String exitQueue();

    int size();


}
