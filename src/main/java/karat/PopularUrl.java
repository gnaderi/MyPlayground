package karat;/*
For this question, you are given a log file from a simple web server. Each line in the log file contains a URL and nothing else. Your job is to write code that will download the log file from the internet, process it, and output the most popular URL in the file. You do not need to normalize the URLs in the log files.

You can find the sample log files at: 

https://public.karat.io/content/q015/urls.txt
https://public.karat.io/content/q015/single_url.txt


Let's say we wanted to extract the top N URLs instead of the single top URL. Can you change your code to make N a configurable parameter?
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class PopularUrl {
    public static void main(String[] argv) throws Exception {

        URL fileUrl = new URL("https://public.karat.io/content/q015/urls.txt");
        findTopPopularUrls1(fileUrl,10);
        System.out.println(" ===============================================================================================");
        findTopPopularUrls2(fileUrl,10);

    }

    private static void findTopPopularUrls1(URL fileUrl, Integer topN) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
        Map<String, Integer> urlMap = new HashMap<>();
        String url;
        while ((url = br.readLine()) != null) {
            urlMap.merge(url, 1, Integer::sum);

        }
        urlMap = urlMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(topN).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        urlMap.keySet().stream().toList().forEach(System.out::println);
    }

    private static void findTopPopularUrls2(URL fileUrl, Integer topN) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(fileUrl.openStream()));
        List<String> urls = new ArrayList<>();
        String url;
        while ((url = br.readLine()) != null) urls.add(url);
        urls.stream().collect(groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(topN).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new))
                .keySet().stream().toList().forEach(System.out::println);
    }
}
