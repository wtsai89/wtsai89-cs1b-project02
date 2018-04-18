package cellularData;

/**
 *
 */
public class CellularData {
    private double table[][];
    private double year[];
    private String countries[];
    private int numRows, numCols, startingYear;
    private int count = 0; //used for adding data to arrays

    CellularData(int rows, int cols, int sYear)
    {
        table = new double[rows][cols];
        countries = new String[rows];

        year = new double[cols];
        for(int i = 0; i < year.length; i++)
        {
            year[i] = sYear + i;
        }
    }

    public void addCountry(String country, double[] data)
    {
        countries[count] = country;
        table[count] = data;
        count++;
    }
}