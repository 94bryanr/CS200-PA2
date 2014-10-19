public class BST {
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

        if (current == null) {
            root = node;
            count++;
            added= true;
        }

        while (!added) {
            if (current.getTerm().compareTo(node.getTerm()) == 0) {
                //Equal
                current.getTerm().addNewOccurrence(documentName);
                added = true;
            } else if (current.getTerm().compareTo(node.getTerm()) > 0) {
                //go left
                if (current.getLeft() != null)
                    current = current.getLeft();
                else {
                    current.setLeft(node);
                    count++;
                    added = true;
                }
            } else if (current.getTerm().compareTo(node.getTerm()) < 0) {
                //go right
                if (current.getRight() != null)
                    current = current.getRight();
                else {
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
        BSTNode current = root;
        Term term = new Term(word);
        int depth = 0;
        boolean found = false;
        while (!found) {
            if (current.getTerm().getName().compareTo(word) == 0) {
                //Equal
                found = true;
                String docList = "";
                for(Occurrence doc: current.getTerm().getDocsList())
                    docList += doc.getDocName();
                System.out.println("Found: " + word + " in: " + docList);
            } else if (current.getTerm().getName().compareTo(word) > 0) {
                //go left
                if (current.getLeft() != null) {
                    current = current.getLeft();
                    depth++;
                } else {
                    System.out.println(word + " not found");
                    return null;
                }

            } else if (current.getTerm().getName().compareTo(word) < 0) {
                //go right
                if (current.getRight() != null) {
                    current = current.getRight();
                    depth++;
                } else {
                    System.out.println(word + " not found");
                    return null;
                }
            }
        }

        if (printDepth) {
            System.out.println("At depth " + depth);
        }

        return term;
    }
}