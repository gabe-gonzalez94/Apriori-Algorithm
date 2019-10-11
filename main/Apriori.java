import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Apriori{

    public static void main(String[] args) throws Exception{

        String file = new String("/Users/gegb1994/Documents/workspace/Apriori-Algorithm/resources/database_1.txt");
        List<String> items = readFileInList(file);
        Map<String, Integer> itemset = generateAllFrequentItemSets(items, 4);
        itemset.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

    }

    public static Map<String, Integer> generateAllFrequentItemSets(List<String> items, int support) throws Exception{

        Map<String, Integer> itemsMap = new HashMap<>();
        for(String st : items){
            String[] transaction = st.split(",");
            for(String t : transaction){
                if(!itemsMap.containsKey(t.trim())){
                    itemsMap.put(t.trim(), 1);
                }
                else {
                    itemsMap.put(t.trim(), itemsMap.get(t.trim()) + 1);
                }
            }
        }

        itemsMap.entrySet().removeIf(entry -> entry.getValue().compareTo(support) < 0);

        return itemsMap;
    }

    public static List<String> readFileInList(String fileName)throws IOException {

        List<String> lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        return lines;

    }

}