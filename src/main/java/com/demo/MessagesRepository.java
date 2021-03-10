package com.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessagesRepository {

    private static final MessagesRepository singleton = new MessagesRepository();
    private final List<String> list = new ArrayList<>();

    public static MessagesRepository getInstance() {
        return singleton;
    }

    private MessagesRepository() {
    }

    public void add(String s) {
        list.add(s);
    }

    public Integer size(){
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Collections.reverse(list);
        for (String s : list)
            sb.append("<br>").append(s);

        return sb.toString();
    }
}
