import java.util.ArrayList;
import java.util.Iterator;

public class BSTIterator <Term> implements Iterator<Term> {
    BST tree;
    ArrayList<Term> queue;

    public BSTIterator(BST tree){
        this.tree = tree;
        queue = new ArrayList<Term>();
        queueInorder(tree.getRoot());
    }

    @Override
    public boolean hasNext() {
        return (!queue.isEmpty());
    }

    @Override
    public Term next() {
        Term term = queue.get(0);
        queue.remove(0);
        return term;
    }

    @Override
    public void remove() {

    }

    private void queueInorder(BSTNode node){
        //Fill queue inorder
        if(node.getLeft() != null) {
            queueInorder(node.getLeft());
        }

        Term t = (Term)node.getTerm();
        queue.add(t);

        if(node.getRight() != null){
            queueInorder(node.getRight());
        }
    }
}
