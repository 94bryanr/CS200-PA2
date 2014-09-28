import java.util.ArrayList;

//TEst comment
public class Term implements Comparable {
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
            System.out.printf("Checking occ repeat for %s between %s and %s%n",
                    name, occ.getDocName(), document);
            if (occ.getDocName().equals(document)) {
                occ.incFrequency();
                add = false;
            }
        }
        if (add) {
            docsList.insert(document);
            docFrequency++;
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
        int total = 1;
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
        String name1 = this.name;
        String name2 = ((Term)o).name;
        if (name1.compareTo(name2) < 0){
            return -1;
        }
        if (name1.compareTo(name2) == 0){
            return 0;
        }
        return 1;
    }

    public int compareToOcc(Object o) {
        int total1 = this.getTotalFrequency();
        int total2 = ((Term)o).getTotalFrequency();
        if (total1 > total2){
            return 1;
        }
        if (total1 == total2){
            return 0;
        }
        return -1;
    }

    public void printList(){
        docsList.printList();
    }
}