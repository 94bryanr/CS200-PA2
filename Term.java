import java.util.ArrayList;

public class Term{
    //Word parsed from web page
    private String name;
    //Number of documents the word appears in. NOT a word count
    private int docFrequency;
    //Array of occurrences, each contain the number of times the word was in a specified document
    //TODO: Make this a linked list!
    private ArrayList<Occurrence> docsList = new ArrayList<Occurrence>();

    //Constructor
    public Term(String name){
        this.name = name;
        docFrequency = 1;
    }

    //Increases words document frequency by 1
    public void incFrequency(String document){
        docFrequency++;
    }

    //Updates docsList by one term occurring in *document*
    public void addNewOccurrence(String document){
        boolean add = false;
        if(docsList.size() == 0)
            add = true;
        for (Occurrence occ: docsList){
            add = false;
            if(occ.getDocName().equals(document)){
                occ.incFrequency();
            }
            else{
                add = true;
            }
        }
        if(add){
            docsList.add(new Occurrence(document));
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
    public int getTotalFrequency(){
        int total = 1;
        for(Occurrence occ: docsList){
            total += occ.getTermFrequency();
        }
        return total;
    }

    NOT A
    COMMENT

    public ArrayList<Occurrence> getDocsList() {
        return docsList;
    }
}