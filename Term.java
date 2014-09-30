import java.util.ArrayList;

//TEst comment
public class Term implements Comparable {
    //Set 0 to compare alphabetically, set 1 to compare by index
    public static int compareType = 0;
    //Word parsed from web page
    private String name;
    //Number of documents the word appears in. NOT a word count
    private int docFrequency;
    //Array of occurrences, each contain the number of times the word was in a specified document
    private LinkedOccurrence docsList = new LinkedOccurrence();

    //Constructor
    public Term(String name) {
        this.name = name;
        docFrequency = 0;
    }

    //Increases words document frequency by 1
    public void incFrequency(String document) {
        docFrequency++;
    }

    //Updates docsList by one term occurring in *document*
    public void addNewOccurrence(String document) {
        boolean add = true;
        for (Occurrence occ : docsList.toArray()) {
            if(P2.getDebugState() >= 1) {
                System.out.printf("Checking occ repeat for %s between %s and %s%n",
                        name, occ.getDocName(), document);
            }
            if (occ.getDocName().equals(document)) {
                occ.incFrequency();
                add = false;
            }
        }
        if (add) {
            docsList.insert(document);
            docFrequency++;
            if(P2.getDebugState() >= 1)
                System.out.printf("Increased: %s docFrequency%n", name);
        }
    }

    //Returns the word the term is associated with
    public String getName() {
        return name;
    }

    //Returns the document frequency of the word
    public int getDocFrequency() {
        return docFrequency;
    }

    //Returns total count of the word in all documents
    public int getTotalFrequency() {
        int total = 0;
        for (Occurrence occ : docsList.toArray()) {
            total += occ.getTermFrequency();
        }
        return total;
    }

    public ArrayList<Occurrence> getDocsList() {
        return docsList.toArray();
    }

    @Override
    public int compareTo(Object o) {
        if(compareType == 0) {
            String name1 = this.name;
            String name2 = ((Term) o).name;
            return name1.compareTo(name2);
        }
        if(compareType == 1){
            Integer count1 = this.getTotalFrequency();
            Integer count2 = ((Term) o).getTotalFrequency();
            return count1.compareTo(count2);
        }
        System.out.println("ERROR: NO SORTING DEFINED");
        return 0;
    }

    public String getStringList(){
        return docsList.toString().trim();
    }
}