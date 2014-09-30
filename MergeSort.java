//Credit to Janet J. Prichard and Frank M. Carrano
//As the original authors of the code
//Seen in Java: Walls and Mirrors, Third Edition
//Slight modifications have been made

//TODO: Make this run with array
public class MergeSort{
    private static int counter = 0;

    public static <T extends Comparable<? super T>>
        void mergesort(T[] theArray){
        //Declare temporary array used for merge, must do unchecked cast from Comparable<?>[] to T[]
        T[] tempArray = (T[])new Comparable<?>[theArray.length];
        mergesort(theArray, tempArray, 0, theArray.length - 1);
        //System.out.println("Set counter to 0");
    } // end mergesort

    private static<T extends Comparable<? super T>>
        void merge(T[] theArray, T[] tempArray, int first, int mid, int last) {
        // initialize the local indexes to indicate the subarrays
        int first1 = first;          //beginning of first subarray
        int last1 = mid;             //end of first subarray
        int first2 = mid + 1;        //beginning of second subarray
        int last2 = last;            //end of second subarray
        // while both subarrays are not empty, copy the smaller item into the temporary array
        int index = first1;          // next available location in tempArray

        while((first1<= last1) && (first2 <= last2)) {
            // Invariant: tempArray:[first1..index-1] is in order
            //PA2: Set from less than to less than or equal to
            if(theArray[first1].compareTo(theArray[first2])<=0){
                tempArray[index] = theArray[first1];
                first1++;
                //PA2, Increment counter
                counter++;
            }
            else{
                tempArray[index] = theArray[first2];
                first2++;
            } //end if
            index++;
        } //end while

        // finish off the nonempty

        // finish off the first subarray, if necessary
        while(first1 <= last1) {
            // Invariant: tempArray[first1..index-1] is in order
            tempArray[index] = theArray[first1];
            first1++;
            index++;
        } // end while

        // finish off the second subarray, if necessary
        while (first2 <= last2) {
            // Invariant: tempArray[first1..index-1] is in order
            tempArray[index] = theArray[first2];
            first2++;
            index++;
        } // end while

        //copy results back into the original array
        for (index = first; index <= last; ++index){
            theArray[index] = tempArray[index];
        } // end for
    } // end merge

    public static <T extends Comparable<? super T>>
        void mergesort(T[] theArray, T[] tempArray, int first, int last) {
        if (first < last){
            //sort each half
            int mid = (first + last)/2; // index of midpoint
            //sort left half theArray[first...mid]
            mergesort(theArray, tempArray, first, mid);
            //sort right half theArray[mid+1..last]
            mergesort(theArray, tempArray, mid+1, last);
            //merge the two halves
            merge(theArray, tempArray, first, mid, last);
        } // end if
    } // end mergesort

    //PA2: Return counter

    public static int getCounter() {
        int temp = counter;
        counter = 0;
        return temp;
    }
}
