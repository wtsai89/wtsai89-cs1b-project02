package cellularData;

/**
 *
 */
public class CellularData {
    private double table[][];
    private int years[];
    private String countries[];
    private int numberOfCountries, numberOfSubs, startingYear, endingYear;
    private int lastFilledRow = 0;

    public CellularData(int rows, int cols, int sYear)
    {
        table = new double[rows][cols];
        countries = new String[rows];
        numberOfCountries = rows;
        numberOfSubs = cols;
        startingYear = sYear;
        endingYear = sYear + cols - 1;

        years = new int[cols];
        for(int i = 0; i < years.length; i++)
        {
            years[i] = sYear + i;
        }
    }

    public void addCountry(String country, double[] data)
    {
        countries[lastFilledRow] = country;
        table[lastFilledRow] = data;
        lastFilledRow++;
    }

    public double getNumSubscriptionsInCountryForPeriod(String country, int start, int end)
    {
        double sum = 0;
        int countryIndex = getCountryIndex(country);

        if(countryIndex == -1)
        {
            System.out.println("Country not found. Returning -1");
            return -1;
        }
        else if(start > end || start > endingYear || end < startingYear)
        {
            System.out.println("Illegal arguments. The range of years is outside the valid period. Returning -1.");
            return -1;
        }
        else {
            int flag = 0;  //only display corrected range if there is a discrepancy
            if (start < startingYear) {
                System.out.printf("Illegal Argument Request of start year %d. ", start);
                start = startingYear;
                flag = 1;
            }
            if (end > endingYear) {
                System.out.printf("Illegal Argument Request of end year %d. ", end);
                end = endingYear;
                flag = 1;
            }
            if (flag == 1)
                System.out.printf("Valid period for %s is %d to %d. \n", country, start, end);

            int startIndex = start - startingYear;
            int endIndex = end - startingYear;
            for (int i = startIndex; i <= endIndex; i++)
                sum += table[countryIndex][i];

            return sum;
        }
    }

    public int getCountryIndex(String country)
    {
        for(int i = 0; i < countries.length; i++)
            if(countries[i].equals(country))
                return i;

        return -1;
    }

    public String toString()
    {
        String s = "Country       | Year";
        for(int i = 0; i < years.length; i++)
            s += String.format("%7d", years[i]);
        s += "\n";

        for(int i = 0; i < numberOfCountries; i++)
        {
            s += String.format("%-20s", countries[i]);
            for(int j = 0; j < numberOfSubs; j++)
                s += String.format("%7.2f", table[i][j]);
            s += "\n";
        }

        return s;
    }
}