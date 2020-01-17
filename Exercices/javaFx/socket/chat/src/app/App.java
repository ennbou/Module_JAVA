package app;

import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(7);
        list.add(10);
        list.add(11);
        list.add(12);

        List<Integer> newList = getList(list);
        for (Integer integer : newList) {
            System.out.println(integer);
        }
    }

    public static List<Integer> getList(List<Integer> list) {
        List<Integer> l = new ArrayList<>();
        int i;
        int last = list.get(list.size() - 1);
        
        // int len = list.size();
        // for(i = 0 ; i<len; i++ )

        for (i = list.get(0); i < last; i++) {
            l.add(i);
        }
        for (Integer integer : list) {
            l.remove(integer);
        }
        return l;
    }
}