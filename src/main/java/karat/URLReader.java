package karat;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLReader {
    public static void main(String[] args) throws Exception {

        URL bookUrl = new URL("https://public.karat.io/content/test/test_file.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(bookUrl.openStream()));
       List<String> lines=new ArrayList<>();
        String line;
        while (( line = in.readLine()) != null)
            lines.add(line);
        in.close();
        Map<String,Integer> map=new HashMap<>(lines.size());
        final Integer[] dub = {0};
        lines.forEach(l->{
            String[] parts = l.split(",");
            if(parts.length==4){
                if(map.containsKey(parts[0])){
                    dub[0]++;
                }
                map.putIfAbsent(parts[0],Integer.valueOf(parts[3]));
            }else {
                System.out.printf("Invalid line:%s%n",l);
            }
        });
     Long pageCount=  map.values().stream().mapToLong(Integer::intValue).sum();
     System.out.printf("duplicated= %s%n",dub[0]);
     System.out.printf("Lines= %s%n",lines.size());
     System.out.printf("BooksCounted= %s%n",map.size());
     System.out.printf("PagesCounted= %s%n",pageCount);
    }
}