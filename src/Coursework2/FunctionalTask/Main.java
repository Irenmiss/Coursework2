package Coursework2.FunctionalTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByKey;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        String inputString = scanner.next();
        Stream<String> countWords = new ArrayList<>(List.of(inputString.split(" "))).stream();
        System.out.println("В тексте " + countWords.count() + " слов");
        System.out.println("TOP 10 слов:");
        Stream<String> inputStringStream = new ArrayList<>(List.of(inputString.split(" "))).stream();
        Map<String, Long> countWordsMap = inputStringStream
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        countWordsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(comparingByKey()))
                .limit(10)
                .forEach(System.out::println);
    }
}



