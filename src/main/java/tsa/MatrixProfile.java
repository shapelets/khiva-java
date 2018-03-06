package tsa;

/**
 * Matrix profile class
 */
public class MatrixProfile {


    private double[] profile;

    private int[] index;

    /**
     * MatrixProfile constructor.
     * @param p Array of double with the distances.
     * @param i Array of integers with the indexes.
     */
    public MatrixProfile(double[] p, int[] i) {
        profile = p;
        index = i;
    }

    /**
     * Gets the distance profile.
     * @return Array of doubles with the distance profile.
     */
    public double[] getProfile() {
        return profile;
    }

    /**
     *  Sets the distance profile.
     * @param profile
     */
    public void setProfile(double[] profile) {
        this.profile = profile;
    }

    /**
     * Gets the index profile.
     * @return Array of integers with the index profile.
     */
    public int[] getIndex() {
        return index;
    }

    /**
     * Sets the index profile.
     * @param index
     */
    public void setIndex(int[] index) {
        this.index = index;
    }
}
