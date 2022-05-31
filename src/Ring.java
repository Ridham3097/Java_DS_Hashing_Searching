//I,Ridham Patel, 000831171 certify that this material is my original work.
//No other person’s work has been used without due acknowledgement.
//I have not made my work available to anyone else.
public class Ring {
    String name;              // to store name
    int closeCount=0;         // to store count number
    int appearCount=0;        // to store apper count
    private double closenessFactor;

    /**
     * constructor of class
     * @param name
     */
    public Ring(String name) {
        this.name = name;
    }

    /**
     * to get closenessfactor
     * @return
     */
    public double getClosenessFactor(){

        return (double) closeCount/appearCount;
    }

    /**
     *
     * @return increase one number
      */
    public int getCloseCount() {
        return closeCount++;
    }

    /**
     * @return increase one  umber
     */
    public int getAppearCount() {
        return appearCount++;
    }

    /**
     * print result
     * @return
     */
    @Override
    public String toString() {
        return "["+ name +", " + appearCount + "]"+
                " Close to Ring " + closeCount +
                ", Closeness factor " + String.format("%.4f",getClosenessFactor());

    }
}
