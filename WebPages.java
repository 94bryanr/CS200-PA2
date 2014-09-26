import java.util.ArrayList;

public class WebPages {
    //Holds list of Term objects associated with each parsed word in web page
    private ArrayList<Term> termIndex;

    //Constructor
    public WebPages(){
        termIndex = new ArrayList<Term>();
    }

    //Passes filename to HTMLParser to get parsed array WITH duplicates
    //Calls adds terms to termIndex
    public void addPage(String document){
    //add to termIndex the parsed words from *document*
        HTMLParser pageParser = new HTMLParser(document);

        //For each word in our parsed array...
        for(String word: pageParser.getParsedArray()){

            if (termIndex.size() == 0)
                termIndex.add(new Term(word));

            else{
                boolean add = false;
                for(Term term: termIndex){
                    add = false;
                    //System.out.println("Checking: " + term.getName() + " against: " + word);
                    if(word.equals(term.getName())){
                        term.addNewOccurrence(document);
                        //System.out.println("Added: " + word +" Occurrence");
                    }
                    else{
                        add = true;
                    }
                }
                if(add) {
                    termIndex.add(new Term(word));
                    //System.out.println("Added: " + word);
                }
                //System.out.println("NEXT ITERATION\n");
            }
        }
    }

    //Iterates through the array of termIndex and prints each word
    public void printTerms(){
        for(Term word: termIndex){
            System.out.println(word.getName() + " " + word.getDocFrequency() + " " +
            word.getTotalFrequency());
        }
    }

       public ArrayList<Term> mergeSort (ArrayList<Term> list, int first, int last, int sortType){
        ArrayList<Term> leftSide = new ArrayList<Term>();
        ArrayList<Term> rightSide = new ArrayList<Term>();
        int mid =0;

        if (sortType =0){
            if (list.size()<=1){
                return list;
             }
              else {
                mid = list.size()/2;
                for (int i =0; i<mid;i++){
                    leftSide.add(list.get(i).getName());
                 }
                for (int j = mid; j<list.size(); j++){
                     rightSide.add(list.get(j).getName());
                }
            mergeSort(leftSide,0,mid,0);
            mergeSort(rightSide,mid+1,size,0);

            list = merge (leftSide,rightSide,list);
        }
    }
    else{
        if (list.size() <=1){
            return list;
        }
         else {
            mid = list.size()/2;
            for (int i =0; i<mid;i++){
                leftSide.add(list.get(i).getTotalFrequency());
            }
            for (int j = mid; j<list.size(); j++){
                rightSide.add(list.get(j).getTotalFrequency());
            }
            mergeSort(leftSide,0,mid,1);
            mergeSort(rightSide,mid+1,size,1);

            list = merge (leftSide,rightSide,list);
        }
    }
        return list;
    }

    private ArrayList<Term> merge (ArrayList<Term> leftS,ArrayList<Term> rightS,ArrayList<Term> wholeS, int sign){
        int left = 0;
        int right = 0;
        int total = 0;
        while (left <= leftS.size()-1 && right <= rightS.size()-1){
            if (sign = 0){
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
            else {
                if ((leftS.get(left) <= (rightS.get(right))){
                    wholeS.set(total,leftS.get(left));
                    left++;
                }
                else{
                    wholeS.set(total, rightS.get(right));
                    right++;
                 }
                }
        }
        ArrayList<Term> finished;
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

    //Prunes out *n* most common words
    public void pruneStopWords(int n){
        System.out.println("pruneStopWords not yet implemented");
        //Use mergesort
        termIndex = mergeSort(termIndex,0,termIndex.size(),1)
        for (int i = 0; i <= n; i++){
            termIndex.remove(termIndex.size()-1);
        }
        termIndex = mergeSort(termIndex,0,termIndex.size(),0);
        //Sort by term frequency
        //Remove most frequent terms
        //Sort by alphabetic order
        //Return array
    }

    //Prints which pages *word* exist on
    public String[] whichPages(String word) {
        ArrayList<String> pages = new ArrayList<String>();
        for(Term term: termIndex){
            if (term.getName().equals(word)){
                for(Occurrence occ: term.getDocsList()){
                    pages.add(occ.getDocName());
                }
            }
        }
        return pages.toArray(new String[pages.size()]);
    }
}