public class P2 {
    public static void main(String[] args){
        //Object to hold word information for given web pages.
        WebPages webPage = new WebPages();
        webPage.addPage("test.txt");

        //Prints each word
        webPage.printTerms();

        //Removes *n* most common words
        //webPage.pruneStopWords(2);

        //Prints each word
        //webPage.printTerms();

        for(String appearance: webPage.whichPages("tiger")){
            System.out.println(appearance);
        }


        // --Pseudocode to implement--
        //for each word in input file,
        //  run whichPages method on webpages object
        //      if(found word)
        //          print “<word> in pages: <page1>, <page2>…”
        //      else
        //          print “<word> not found”
    }

    public void parseInputFile(){

    }
}