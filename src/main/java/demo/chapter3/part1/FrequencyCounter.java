package demo.chapter3.part1;

import java.io.File;
import java.util.Scanner;

public class FrequencyCounter {
    public static void main(String[] args) throws Exception {
        ST<String, Integer> st = new ST<>();
        File file = new File("src/main/java/demo/data/tale.txt");
        Scanner scanner = new Scanner(file);
        int minlen = 8;
        while(scanner.hasNext()) {
            String word = scanner.next();
            if(word.length() < minlen) continue;
            if(!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        String max = "";
        st.put(max, 0);
        for(String key : st.keys()) {
            if(st.get(key) > st.get(max)) max = key;
        }
        System.out.println(max + " " + st.get(max));
    }
}
