import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Apriori{

    public static void main(String[] args) throws Exception{

        File filePath = new File("C:\\Users\\gegb1\\IdeaProjects\\Apriori-Algorithm\\resources\\database_1.txt");
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        Map<String, Integer> itemset = generateAllFrequentItemSets(br);
        itemset.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

    }

    public static Map<String, Integer> generateAllFrequentItemSets(BufferedReader file) throws Exception{

        Map<String, Integer> itemsMap = new HashMap<>();
        String row;
        while((row = file.readLine()) != null){
            String[] transaction = row.split(",");
            for(String st : transaction){
                if(!itemsMap.containsKey(st.trim())){
                    itemsMap.put(st.trim(), 1);
                }
                else {
                    itemsMap.put(st.trim(), itemsMap.get(st.trim()) + 1);
                }
            }
        }

        return itemsMap;
    }

}