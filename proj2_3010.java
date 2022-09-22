public class proj2_3010
{
    private int n;
    private double[][] coefficients = new double [n][n + 1];
    
    public void setN(int n)
    {
        this.n = n;
    }
    public int getN()
    {
        return this.n;
    }
    public void setCoefficients(double [][] coefficients)
    {
        this.coefficients = coefficients;
    }
    public double [][] getCoefficients()
    {
        return this.coefficients;
    }
    public void printArray(double[][] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            for(int k = 0; k < array[0].length; k++)
            {
                System.out.printf("%.0f", array[i][k]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public double[][] copyArray(double [][] array)
    {
        return array;
    }

    public void jacobiMethod(int n, double [][] coefficients, double[] startingSolution, double error)
    {
        double [] biggestCoefficient = new double [n]; //to get the biggest coefficient for each equation
        for(int i = 0; i < coefficients.length; i++) //filling biggestCoefficient
        {
            double biggest = 0;
            for (int k = 0; k < coefficients.length; k++)
            {
                if(coefficients[i][k] > biggest)
                {
                    biggest = coefficients[i][k];
                }
            }
            biggestCoefficient[i] = biggest;
        }
        
        double [][] xyz = new double [n][50];


        for (int i = 0; i < startingSolution.length; i++) //filling with the starting solution.
        {
            xyz[i][0] = startingSolution[i];
        }

        for(int i = 0; i < xyz.length; i++) //making sure array is initialized to zero
        {
            for(int k = 1; k < xyz[0].length; k++)
            {
                xyz[i][k] = 0;
            }
        }

        int xyzColumn = 1; //equals one because index 0 already has starting solution
        double errorForIteration = 1; //start off having an error of 1 for while loop to work

        while(xyzColumn < xyz[0].length && errorForIteration > error) //doing the calculations
        {
            int previousIterationColumn = xyzColumn - 1; 
            for (int i = 0; i < coefficients.length; i++) 
            {
                //int previousIterationColumn = xyzColumn - 1; 
    
                for(int k = 0; k < coefficients[0].length; k++)
                {
                    int skip = 0; // we don't want x1 to be included for the equation for x1
                    if(k < coefficients[0].length) // equations for x, y, and z
                    {
                        if(k == i)
                            skip = 1; //where skip happens
                        
                        else if( (k + skip) < coefficients.length )
                        {    
                            xyz[i][xyzColumn] -= (coefficients[i][k + skip] * xyz[k + skip][previousIterationColumn]) / biggestCoefficient[i];
                        }
                        
                        else if (k == coefficients[0].length - 1) //finishing calculations for x, y, and z
                            xyz[i][xyzColumn] += coefficients[i][k] / biggestCoefficient[i];
                        
                    }
                }
            }
            errorForIteration = 0; //the error to compare stop
            double x1Minusx0 = 0; //current iteration minus previous iteration
            double x1 = 0; //current iteration

            for(int errorRowIndex = 0; errorRowIndex < xyz.length; errorRowIndex++) //caluculating error
            {
                x1Minusx0 += Math.pow( ( xyz[errorRowIndex][xyzColumn] - xyz[errorRowIndex][previousIterationColumn] ), 2);
                x1 += Math.pow( ( xyz[errorRowIndex][xyzColumn] ), 2);

                if (errorRowIndex == xyz.length - 1)
                    errorForIteration = Math.sqrt(x1Minusx0) / Math.sqrt(x1); //calculated the error
            }
            xyzColumn++;
        } //end of while loop

        int printColumnPattern = 0; // to have correct pattern for printing
        for (; printColumnPattern < xyzColumn; printColumnPattern++)   
        { 
            for(int i = 0; i < xyz.length; i++) //printing jacobi iterations 
            {
                System.out.printf("%.4f", xyz[i][printColumnPattern]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }

        
    public void gaussSeidelMethod(int n, double [][] coefficients, double[] startingSolution, double error)
    {
        double [] biggestCoefficient = new double [n]; //to get the biggest coefficient for each equation
        for(int i = 0; i < coefficients.length; i++) //filling biggestCoefficient
        {
            double biggest = 0;
            for (int k = 0; k < coefficients.length; k++)
            {
                if(coefficients[i][k] > biggest)
                {
                    biggest = coefficients[i][k];
                }
            }
            biggestCoefficient[i] = biggest;
        }
        
        double [][] xyz = new double [n][50];


        for (int i = 0; i < startingSolution.length; i++) //filling with the starting solution.
        {
            xyz[i][0] = startingSolution[i];
        }

        for(int i = 0; i < xyz.length; i++) //making sure array is initialized to zero
        {
            for(int k = 1; k < xyz[0].length; k++)
            {
                xyz[i][k] = 0;
            }
        }

        int xyzColumn = 1; //equals one because index 0 already has starting solution
        double errorForIteration = 1; //start off having an error of 1 for while loop to work

        while(xyzColumn < xyz[0].length && errorForIteration > error) //doing the calculations
        {
            int previousIterationColumn = xyzColumn - 1; 
            for (int i = 0; i < coefficients.length; i++) 
            {
                //int skip = 0;
                for(int k = 0; k < coefficients[0].length; k++) //doing the calculations
                {
                    if (i == 0) //at first it behaves like Jacobi
                    {
                        int skip = 0; // we don't want x1 to be included for the equation for x1
                        if(k < coefficients[0].length) // equations for x, y, and z
                        {
                            if(k == i)
                                skip = 1; //where skip happens
                            
                            else if( (k + skip) < coefficients.length )
                            {    
                                xyz[i][xyzColumn] -= (coefficients[i][k + skip] * xyz[k + skip][previousIterationColumn]) / biggestCoefficient[i];
                            }
                            
                            else if (k == coefficients[0].length - 1) //finishing calculations for x, y, and z
                                xyz[i][xyzColumn] += coefficients[i][k] / biggestCoefficient[i];
                            
                        }
                    }
                    else //then it behaves like Gauss-Seidel
                    {
                        if (k < i) //the magic
                            xyz[i][xyzColumn] -= (coefficients[i][k] * xyz[k][xyzColumn]) / biggestCoefficient[i];
                        else //then it behaves like Jacobi
                        {
                            int skip = 0; // we don't want x1 to be included for the equation for x1
                            if(k < coefficients[0].length) // equations for x, y, and z
                            {
                                if(k == i)
                                    skip = 1; //where skip happens
                                
                                else if( (k + skip) < coefficients.length )
                                {    
                                    xyz[i][xyzColumn] -= (coefficients[i][k + skip] * xyz[k + skip][previousIterationColumn]) / biggestCoefficient[i];
                                }
                                
                                else if (k == coefficients[0].length - 1) //finishing calculations for x, y, and z
                                    xyz[i][xyzColumn] += coefficients[i][k] / biggestCoefficient[i];
                                
                            }
                        }
                    }
                }
            }
            errorForIteration = 0; //the error to compare stop
            double x1Minusx0 = 0; //current iteration minus previous iteration
            double x1 = 0; //current iteration

            for(int errorRowIndex = 0; errorRowIndex < xyz.length; errorRowIndex++) //caluculating error
            {
                x1Minusx0 += Math.pow( ( xyz[errorRowIndex][xyzColumn] - xyz[errorRowIndex][previousIterationColumn] ), 2);
                x1 += Math.pow( ( xyz[errorRowIndex][xyzColumn] ), 2);

                if (errorRowIndex == xyz.length - 1)
                    errorForIteration = Math.sqrt(x1Minusx0) / Math.sqrt(x1); //calculated the error
            }
            xyzColumn++;
        } //end of while loop
        int printColumnPattern = 0; // to have correct pattern for printing
        for (; printColumnPattern < xyzColumn; printColumnPattern++)   
        { 
            for(int i = 0; i < xyz.length; i++) //printing iterations 
            {
                System.out.printf("%.4f", xyz[i][printColumnPattern]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}