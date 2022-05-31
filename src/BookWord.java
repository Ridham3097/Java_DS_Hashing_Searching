//I,Ridham Patel, 000831171 certify that this material is my original work.
//No other person’s work has been used without due acknowledgement.
//I have not made my work available to anyone else.
public class BookWord {
    private String text;        // text variable to store word
    private Integer count =1;       // count number of world

    /**
     * construstor of class
     * @param wordText
     */
    public BookWord(String wordText) {
        text = wordText;
    }

    /**
     * getter method to get text
     * @return  text
     */
    public String getText() {
        return text;
    }

    /**
     * getter method for count
     * @return count
     */
    public Integer getCount() {
        return count;
    }


    /**
     * increamenr method to add one num
     */
    public void incrementCount(){
           count++;
    }

    /**
     * ovverride equal method in object class
     * @param wordToCompare
     * @return
     */
    @Override
    public boolean equals(Object wordToCompare){

        if(wordToCompare == null){
            return false;
        }

        if(wordToCompare instanceof BookWord){
            BookWord obj =(BookWord) wordToCompare;
            if(obj.text.equals(this.text)){
                return true;
            }
        }
        return  false;

    }

    /**
     * hashcode override object method
     * @return
     */
    @Override
    public int hashCode(){
        return text.hashCode();
    }

    /**
     * retrun string print result
     * @return
     */
    @Override
    public String toString() {
        return "[" + getText() +", " + getCount()+"]";
    }
}
