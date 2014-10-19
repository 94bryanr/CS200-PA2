import java.text.DecimalFormat;

public class Query{
    private double termFrequency, documentFrequency, totalDocs;
    //              TF              DF                  D

    private BST tree;
    private int totalDocuments;

    public Query(BST tree){
        this.tree = tree;
        totalDocuments = WebPages.getDocCount();
    }

    public double getTFIDF(String termName, String document){
        Term searchTerm = null;

        for(Term term: tree){
            if(term.getName().compareTo(termName.toLowerCase().trim()) == 0){
                searchTerm = term;
            }
        }

        if(searchTerm != null) {
            termFrequency = searchTerm.getInDocumentFrequency(document);
            //System.out.println("termFrequency: " + termFrequency);
            totalDocs = totalDocuments;
            //System.out.println("totalDocs: " + totalDocs);
            documentFrequency = searchTerm.getDocFrequency();
            //System.out.println("documentFrequency: " + documentFrequency);
            double IDF = Math.log(totalDocs / documentFrequency);
            return (termFrequency * IDF);
        }

        return -1;
    }
}
