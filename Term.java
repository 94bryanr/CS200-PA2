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

    public ArrayList<Occurrence> getDocsList() {
        return docsList;
    }
    public ArrayList<Term> mergeSort (ArrayList<Term> list, int first, int last, int sortType){
        ArrayList<String> leftSide = new ArrayList<String>();
        ArrayList<String> rightSide = new ArrayList<String>();
        int size = list.size();
        int mid =0;
        if (list.size()<=1){
            return list;
        }
        else {
            mid = list.size()/2;
            for (int i =0; i<mid;i++){
                leftSide.add(list.get(i));
            }
            for (int j = mid; j<list.size(); j++){
                rightSide.add(list.get(j));
            }
            mergeSort(leftSide,0,mid);
            mergeSort(rightSide,mid+1,size);

            list = merge (leftSide,rightSide,list);
        }
        return list;
    }

    private ArrayList<String> merge (ArrayList<String> leftS,ArrayList<String> rightS,ArrayList<String> wholeS){
        int left = 0;
        int right = 0;
        int total = 0;
        while (left <= leftS.size()-1 && right <= rightS.size()-1){
            if ((leftS.get(left).compareTo(rightS.get(right)) <= 0)){
                wholeS.set(total,leftS.get(left));
                left++;
            }
            else{
                wholeS.set(total, rightS.get(right));
                right++;
            }
            total++;
        }
        ArrayList<String> finished;
        int last=0;
        if (left>=leftS.size()){
            finished = rightS;
            last = right;
        }

        else{
            finished = leftS;
            last = left;
        }
        for (int i = last; i<finished.size();i++){
            wholeS.set(total,finished.get(i));
            total++;
        }
        System.out.println(wholeS);
        return wholeS;
    }
}