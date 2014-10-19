import java.text.DecimalFormat;
import java.util.Iterator;

public class BST implements Iterable<Term> {
    //initialize private count and root variables
    private int count;
    private BSTNode root; // middle branch node

    public BST() {
        this.count = 0;
        root = null;
    }

    public int size() {
        return count;
    }

    public void add(String documentName, String word) {
        //adds a new term or inc frequency if it already exists in BST
        Term term = new Term(documentName, word);
        BSTNode node = new BSTNode(term);
        BSTNode current = root;
        boolean added = false;
        System.out.println("\nAdding term: " + word);
        if (current == null) {
            System.out.println("Replaced root");
            root = node;
            count++;
            added= true;
        }

        while (!added) {
            if (current.getTerm().getName().compareTo(node.getTerm().getName()) == 0) {
                //Equal
                System.out.println("Equal terms, adding occurrence");
                current.getTerm().addNewOccurrence(documentName);
                added = true;
            } else if (current.getTerm().getName().compareTo(node.getTerm().getName()) > 0) {
                //go left
                if (current.getLeft() != null) {
                    current = current.getLeft();
                    System.out.println("Going left");
                }
                else {
                    System.out.println("Adding left node");
                    current.setLeft(node);
                    count++;
                    added = true;
                }
            } else if (current.getTerm().getName().compareTo(node.getTerm().getName()) < 0) {
                //go right
                if (current.getRight() != null) {
                    current = current.getRight();
                    System.out.println("Going right");
                }
                else {
                    System.out.println("Adding right node");
                    current.setRight(node);
                    count++;
                    added = true;
                }
            }
        }
    }

    public Term get(String word, Boolean printDepth) {
        // returns the name of the term
        // checks to see if printDepth is true and if so then checks to see how deep it exists in the tree
        String originalWord = word;
        word = word.trim().toLowerCase();
        String printOut = "NULL";
        DecimalFormat fmt = new DecimalFormat("0.00");
        BSTNode current = root;
        Term term = new Term(word.toLowerCase().trim());
        int depth = 1;
        boolean found = false;
        while (!found) {
            if (current.getTerm().getName().compareTo(word) == 0) {
                //Equal
                found = true;
                String docList = "";
                for(Occurrence doc: current.getTerm().getDocsList()) {
                    Query query = new Query(this);
                    double TFIDF = query.getTFIDF(current.getTerm().getName(), doc.getDocName());
                    docList = doc.getDocName() + ": " + fmt.format(TFIDF) +", " + docList;
                }
                printOut = originalWord + " in pages: " + docList.trim().substring(0, docList.length()-2);
            } else if (current.getTerm().getName().compareTo(word) > 0) {
                //go left
                if (current.getLeft() != null) {
                    current = current.getLeft();
                    depth++;
                } else {
                    depth++;
                    if(printDepth)
                        System.out.println("\tAt depth " + depth);
                    System.out.println(word + " not found");
                    return null;
                }

            } else if (current.getTerm().getName().compareTo(word) < 0) {
                //go right
                if (current.getRight() != null) {
                    current = current.getRight();
                    depth++;
                } else {
                    depth++;
                    if(printDepth)
                        System.out.println("\tAt depth " + depth);
                    System.out.println(word + " not found");
                    return null;
                }
            }
        }

        if (printDepth) {
            System.out.println("\tAt depth " + depth);
        }
        System.out.println(printOut);

        return term;
    }

    public BSTNode getRoot() {
        return root;
    }

    @Override
    public Iterator<Term> iterator() {
        return new BSTIterator<Term>(this);
    }

    public void print(){
        for(Term term: this){
            System.out.println("Iterator Found: " + term.getName());
        }
    }
}