//I,Ridham Patel, 000831171 certify that this material is my original work.
//No other person’s work has been used without due acknowledgement.
//I have not made my work available to anyone else.


/** DISCUSSION :
 *  In below assignment misspelled word find from dictionary.there are 3 different method are used
 *  1. Liner search- in which each word from bookWord list are find by word by word into dictionary then get out put it
 *                  is time consuming method it will take most time to get out put
 *  2. BinarySearch:  binary search follow divide and concur method, find mid point are found then compare it with string
 *                   then again mid point are found and compare until string does not match if string doesn't match then it will
 *                   give negative output and this method is faster then liner search nut still time consuming
 *  3. Hashing :  in hashing word from list are stored into bucket by first alphabet of word and then it will take first word of string(searching)
 *                and match with appropriate bucket and this is fastest method in all three method
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Assignment3_Start {

      static  String[] wordFind = {"frodo", "sam", "bilbo", "gandalf", "boromir",
                                   "aragorn", "legolas", "gollum", "pippin", "merry",
                                   "gimli", "sauron","saruman", "faramir", "denethor","treebeard" ,
                                   "elrond", "galadriel"};


    /**
     * The starting point of the application
     */
    public static void main(String[] args)
    {
        ArrayList<BookWord> bk1 =new ArrayList<>();
        ArrayList<BookWord> bk =new ArrayList<>();
        SimpleHashSet<String> hashSet = new SimpleHashSet<>();
        // File is stored in a resources folder in the project
        final String filename = "src/TheLordOfTheRings.txt";
        int countTotal = 0;
        int countUnique=0;

        try {
            Scanner fin = new Scanner(new File(filename));
            fin.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE
            while (fin.hasNext()) {
                String fileWord = fin.next().toLowerCase();

               if (fileWord.length() > 0)
                {
                    bk1.add(new BookWord(fileWord));
                    /// store word in arrayList
                    BookWord w=new BookWord(fileWord);
                    if(bk.contains(w)) {
                        bk.get(bk.indexOf(w)).incrementCount();
                    }else{
                        bk.add(new BookWord(fileWord));
                        countUnique++;
                    }
                    countTotal++;
                }
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        ArrayList<String> list = readLines("src/US.txt");

        // ADD other code after here

        // add word in hashset arraylist
        for(String word:list){
            hashSet.insert(word);
        }


        System.out.println("Word Analysis/Spell Checker");
        System.out.println("==================================\n");


        System.out.println("There are " + countTotal + " words in thew file ");
        System.out.println("There are a total of " + countUnique + " different words in the file.");
        System.out.println("\n");
        System.out.println("The list of the 10 most frequent words and counts ");

            sort1(bk);
           int size = 1;
            for(int i = 0;i<10;i++){
                System.out.println("\t" + size + " " + bk.get(i));
                size++;
            }

        System.out.println("\n");
        System.out.println("The words that occur exactly 64 times in the file " );
            for(int i = 0;i<bk.size();i++){
                if(bk.get(i).getCount() == 64) {
                    System.out.println("\t" + bk.get(i));
                }
            }


        System.out.println("\n");
        System.out.println("LINEAR SEARCH - " + linerSearch(bk,list) + " misspelled words -");

       Collections.sort(list);

        System.out.println("BINARY SEARCH - " +  binary_Search(list,bk) + " misspelled words -");
        System.out.println("HASHSET SEARCH - " + hashSearch(hashSet,bk) + " misspelled words - \n");

        System.out.println("Number of Buckets :" + hashSet.getNumberofBuckets());
        System.out.println("Biggest Buckets :" + hashSet.getLargestBucketSize());
        System.out.println("# of Empty Buckets :" + hashSet.getNumberofEmptyBuckets());
        System.out.println("% of Empty Bucket :" + hashSet.getNumberofEmptyBuckets()*100 / hashSet.getNumberofBuckets() + "% \n");


        System.out.println("ORDER of WHO REALLY WANTS the RING");
        System.out.println("==================================\n");

        long start =System.nanoTime();
        Proximity_Search(bk1);
        long stop = System.nanoTime();
        System.out.println("\n\t Time: " + (stop-start));

    }

    /**
     * to read any line
     * @param filename
     * @return
     */
    public static ArrayList<String> readLines(String filename) {
        ArrayList<String> words = new ArrayList<String>();
        try {
            Scanner fin = new Scanner(new File(filename));
            while (fin.hasNextLine()) {
                String w = fin.nextLine().toLowerCase();
                words.add(w);
            }
            fin.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());}return words;
    }

    /**
     * liner search method to find unfind text
     * @param bk
     * @param list
     * @return
     */
    public static int linerSearch(ArrayList<BookWord> bk,ArrayList<String> list){
        int a= bk.size();
        for(BookWord bok: bk){
            if(list.contains(bok.getText())){
                a--;
            }
        }
        return a;
    }

    public static int binary_Search(ArrayList<String> list,ArrayList<BookWord> bk){
        int count4 =0 ;

        for(BookWord b:bk){
            int i =  Collections.binarySearch(list,b.getText());
            if(i<0){
                count4++;
            }
        }
        return count4;
    }
    /**
     * hashset method to search find notsound word
     * @param hashSet
     * @param bk
     * @return
     */
    public static int hashSearch(SimpleHashSet<String> hashSet,ArrayList<BookWord> bk ){
        int notfoundword=bk.size();
        for(BookWord bok: bk){
            if(hashSet.contains(bok.getText())){
                notfoundword--;
            }
        }
        return notfoundword;
    }

    /**
     * sort method to sort arraylist
     * @param bk
     */
    public static void sort1(ArrayList<BookWord> bk){
        Collections.sort(bk, (BookWord o1, BookWord o2) -> {
            if(o1.getCount()<o2.getCount()){
                    return 1;
            }else if(o1.getCount()>o2.getCount()){
                return -1;
            }else{
                return 0;
            }
        });

    }





    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** PART-B
    /**
     * this method search element
     * @param bk
     */
    public  static void Proximity_Search(ArrayList<BookWord> bk){

        int start = 0;
        int end = 0;
        ArrayList<Ring> r =new ArrayList<>();

        for (int i=0;i<wordFind.length;i++) {

            r.add(new Ring(wordFind[i]));
            for(int x=0;x< bk.size();x++){

                if(bk.get(x).getText().equals(wordFind[i])) {

                    r.get(i).getAppearCount();          // count number of times occurance in ArrayList

                    // chnage starting and ending point for certain range and for border
                    if (x - 42 < 0) {
                        start = 0;
                        end = x + 42;
                    } else if (x + 42 > bk.size()) {
                        start = x - 42;
                        end = bk.size();
                    } else {
                        start = x-42;
                        end = x+42;
                    }

                    //find ring in certain range of ArrayList
                    for (int j = start; j < end; j++) {
                        if (bk.get(j).getText().equals("ring")) {
                            r.get(i).getCloseCount();
                        }
                    }
                }
            }
        }
        //print result of part B bu invoking toString method

        Collections.sort(r,(Ring r1,Ring r2) ->{
            if(r1.getClosenessFactor()<r2.getClosenessFactor()){
                return 1;
            }else if(r1.getClosenessFactor()>r2.getClosenessFactor()){
                return -1;
            }else{
                return  0;
            }
        });

        for (Ring ring: r) {
            System.out.println(ring);
        }
    }

}

