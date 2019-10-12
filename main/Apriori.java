import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Apriori{

    public static void main(String[] args) throws Exception{

        String file = new String("/Users/gegb1994/Documents/workspace/Apriori-Algorithm/resources/database_5.txt");

        int support = 4;

        //items read from file
        List<String> items = readFileInList(file);

        //items after first iteration

        Map<String, Integer> itemsetsOfOne = generateItemsetsOfOne(items);
        Set<String> nonFrequent = removeNonFrequentItemSets(itemsetsOfOne, support);
        itemsetsOfOne.keySet().removeAll(nonFrequent);
//        itemsetsOfOne.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        Map<String, Integer> frequentItemSets = new HashMap<>();

//        while(true) {

            int i=2;
            List<String> candidates = generateCandidates(new ArrayList<>(itemsetsOfOne.keySet()), i++);
            generateFrequentItemsets(candidates, items, frequentItemSets);
            nonFrequent = removeNonFrequentItemSets(frequentItemSets, support);
            frequentItemSets.keySet().removeAll(nonFrequent);
            frequentItemSets.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));


//        }




    }

    public static void generateFrequentItemsets(List<String> candidates, List<String> items, Map<String,Integer> frequentItemSets) {

        for(String st : items){

            //getting lines from database
            List<String> separatedLines = new ArrayList<>(Arrays.asList(st.split(",")));
            for(String c : candidates) {
                List<String> separatedCandidates = new ArrayList<>(Arrays.asList(c.split(",")));
                if (separatedLines.containsAll(separatedCandidates)) {
                    if(!frequentItemSets.containsKey(c)){
                        frequentItemSets.put(c, 1);
                    }
                    else {
                        frequentItemSets.put(c, frequentItemSets.get(c) + 1);
                    }

                }
                else {
                    continue;
                }
            }
        }

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
        else{


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

    public static Map<String, Integer> generateItemsetsOfOne(List<String> items) throws Exception{

        Map<String, Integer> itemsMap = new HashMap<>();
        for(String st : items){
            String[] transaction = st.split(",");
            for(String t : transaction){
                if(!itemsMap.containsKey(t)){
                    itemsMap.put(t, 1);
                }
                else {
                    itemsMap.put(t, itemsMap.get(t) + 1);
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