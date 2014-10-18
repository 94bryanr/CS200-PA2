public class BSTNode {
    private Term term;
    private BSTNode left, right;

    public BSTNode(Term term){
        this.term = term;
    }

    public BSTNode getLeft() {
        return left;
    }

    public BSTNode getRight() {
        return right;
    }

    public Term getTerm() {
        return term;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}

