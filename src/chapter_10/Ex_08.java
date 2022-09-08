package chapter_10;

public class Ex_08 {
    public static void main(String[] agrs) {
        int[][] brackets_2001 = {
                {27050, 65550, 136750, 297350},
                {45200, 109250, 166500, 297350},
                {22600, 54625, 83250, 148675},
                {36250, 93650, 151650, 297350}
        };
        double[] rates_2001 = {0.15, 0.275, 0.305, 0.355, 0.391};
                
        int[][] brackets_2009 = {
                {8350, 33950, 82250, 171550, 372950},
                {16700, 67900, 137050, 208850, 372950},
                {8350, 33950, 68525, 104425, 186475},
                {11950, 45500, 117450, 190200, 372950}
        };
        double[] rates_2009 = {0.1, 0.15, 0.25, 0.28, 0.33, 0.35};
        
        System.out.println("Table for 2001 Tax Rates");
        printHeader();
        for (double income = 50000; income <= 60000; income+= 1000) {
            System.out.printf("%-20.1f%-30.2f%-30.2f%-30.2f%-30.2f\n", 
                    income,
                    new Tax(Tax.SINGLE_FILER, brackets_2001, rates_2001, income).getTax(),
                    new Tax(Tax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOW, brackets_2001, rates_2001, income).getTax(),
                    new Tax(Tax.MARRIED_SEPARATELY, brackets_2001, rates_2001, income).getTax(),
                    new Tax(Tax.HEAD_OF_HOUSEHOLD, brackets_2001, rates_2001, income).getTax());
        }
        
        System.out.println();
        
        System.out.println("Table for 2009 Tax Rates");
        printHeader();
        for (double income = 50000; income <= 60000; income+= 1000) {
            System.out.printf("%-20.1f%-30.2f%-30.2f%-30.2f%-30.2f\n", 
                    income,
                    new Tax(Tax.SINGLE_FILER, brackets_2009, rates_2009, income).getTax(),
                    new Tax(Tax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOW, brackets_2009, rates_2009, income).getTax(),
                    new Tax(Tax.MARRIED_SEPARATELY, brackets_2009, rates_2009, income).getTax(),
                    new Tax(Tax.HEAD_OF_HOUSEHOLD, brackets_2009, rates_2009, income).getTax());
        }
    }
    
    public static void printHeader() {
        String taxIncome = "Taxable Income", 
               single = "Single filers", marriedJointly = "Married filing jointly",
               marriedSeparately = " Married filing separately", head = "Head of Household";
        System.out.printf("%-20s%-30s%-30s%-30s%-30s\n", taxIncome, single, marriedJointly, marriedSeparately, head);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public static void printLine(int income, Tax tax) {
        
        System.out.printf("%-20d%-30f%-30f%-30f%-30f\n");
    }

}
