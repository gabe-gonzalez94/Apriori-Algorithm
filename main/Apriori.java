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

        String file = new String("C:\\Users\\gegb1\\IdeaProjects\\Apriori-Algorithm\\resources\\database_1.txt");

        //items read from file
        List<String> items = readFileInList(file);
        //items after first iteration

        Map<String, Integer> itemset = generateAllFrequentItemSets(items);
        Set<String> nonFrequent = removeNonFrequentItemSets(itemset, 4);
        itemset.keySet().removeAll(nonFrequent);
        itemset.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
        List<String> candidates = generateCandidates(new ArrayList<>(itemset.keySet()), 2);
//        while(itemset.size() > 1){
//        }
    }

    public static List<String> generateCandidates(List<String> items, int candidateSize){

        List<String> candidates = new ArrayList<>();
        if(candidateSize == 2) {
            for (int i=0; i<items.size(); i++){
                String itemToCombine = items.get(i);
                for(int j=1; j<items.size(); j++){
                    if(itemToCombine.equalsIgnoreCase(items.get(j)))
                        break;
                    StringBuffer sb = new StringBuffer();
                    sb.append(itemToCombine);
                    sb.append(",");
                    sb.append(items.get(j));
                    candidates.add(sb.toString());
                }

            }

        }
        return candidates;

    }

    public static Set<String> removeNonFrequentItemSets(Map<String,Integer> itemset, int support){

        Set<String> itemsToReturn = new HashSet<>();
        for(Map.Entry<String, Integer> entry : itemset.entrySet()){
            if(entry.getValue().compareTo(support) < 0){
                itemsToReturn.add(entry.getKey());
            }
        }
        return itemsToReturn;
    }

    public static Map<String, Integer> generateAllFrequentItemSets(List<String> items) throws Exception{

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

        return itemsMap;
    }

    public static List<String> readFileInList(String fileName)throws IOException {

        List<String> lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        return lines;

    }

}