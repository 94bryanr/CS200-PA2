public class LinkedOccurrence {
    private Occurrence head;

    public LinkedOccurrence(){
        head = null;
    }

    //Inserts a new Link at the first of the list
    public void insert(String name){
        Occurrence newOcc = new Occurrence(name);
        newOcc.next = head;
        head = newOcc;
    }

    public void printList(){
        Occurrence temp = head;
        while(head != null){
            head.printOcc();
            head = head.next;
        }
        head = temp;
    }
}