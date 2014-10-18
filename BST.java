
public class BST {
    // intialize private count and root variables
    private int count;
    private BST root; // middle branch node
    private BST leftchild; // left and right branches
    private BST rightchild;

    public BST(){
        this.count =0;
        root = null;
    }
    public int size(){
        return count;
    }

    public void add(String documentName, String word){
        //adds a new term or inc frequency if it already exists in BST

    }
    public Term get(String word, Boolean printDepth){
        // returns the name of the term
        // checks to see if printDepth is true and if so then checks to see how deep it exists in the tree
        return null;
    }
}
