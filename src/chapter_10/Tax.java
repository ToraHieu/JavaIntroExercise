package chapter_10;

public class Tax {
    private int fillingStatus;
    public static final int SINGLE_FILER = 0;
    public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
    public static final int MARRIED_SEPARATELY = 2;
    public static final int HEAD_OF_HOUSEHOLD = 3;
    private int[][] brackets;
    private double[] rates;
    private double taxableIncome;
    
    public Tax() {
        
    }
    public Tax(int fillingStatus, int[][] brackets, double[] rates, double taxableIncome) {
        this.fillingStatus = fillingStatus;
        this.brackets = brackets;
        this.rates = rates;
        this.taxableIncome = taxableIncome;
    }
    
    public int getFillingStatus() {
        return fillingStatus;
    }
    
    public void setFillingStatus(int fillingStatus) {
        this.fillingStatus = fillingStatus;
    }
    
    public String getBrackets() {
        return brackets.toString();
    }
    
    public void setBrackets(int[][] brackets) {
        this.brackets = brackets;
    }
    
    public String getRates() {
        return rates.toString();
    }
    
    public void setRates(double[] rates) {
        this.rates = rates;
    }
    
    public double getTaxableIncome() {
        return taxableIncome;
    }
    
    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }
    
    public double getTax() {
        double tax = 0.0;
        int currentTier = 0;
        if (taxableIncome <= brackets[fillingStatus][currentTier]) {
            tax = taxableIncome * rates[currentTier];
            return tax;
        }
        else {
        tax+= brackets[fillingStatus][currentTier] * rates[currentTier];
        double remainder = taxableIncome - brackets[fillingStatus][currentTier];
        currentTier++;
        while (currentTier < brackets[fillingStatus].length && taxableIncome >= brackets[fillingStatus][currentTier]) {
            tax+= (brackets[fillingStatus][currentTier] - brackets[fillingStatus][currentTier-1]) * rates[currentTier];
            remainder -= (brackets[fillingStatus][currentTier]- brackets[fillingStatus][currentTier-1]);
            currentTier++;
        }
        if (remainder > 0) 
            tax+= remainder * rates[currentTier];
        
        return tax;
        }
    }

}
