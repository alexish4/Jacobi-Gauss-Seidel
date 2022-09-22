import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//PLEASE FOLLOW test.txt FORMAT, NO COMMAS ONLY SPACES, YOU WILL BE ASKED TO ENTER NUMBER OF EQUATIONS BEFORE CHOOSING FILE, THIS IS HOW IT IS INTEDED TO WORK

public class proj2Demo3010    
{
    public static void main(String[] args) throws FileNotFoundException
    {
        proj2_3010 demo = new proj2_3010();
        Read2DArray fileDemo = new Read2DArray();
        
        
        Scanner keyboard = new Scanner(System.in);
        
        do //getting number of equations and making sure it is in the right range
        {
            System.out.print("Please enter number of equations: ");
            demo.setN(keyboard.nextInt());
            keyboard.nextLine();
            if (demo.getN() > 10 || demo.getN() < 0)
                System.out.println("Invalid input, please print number from 0 - 10");
        }while(demo.getN() > 10 || demo.getN() < 0);

        System.out.print("Would you like to use a file for your matrix, if so enter Y, if not then enter any other character: ");
        String fileOrNot = keyboard.nextLine();
        System.out.println();

        if (fileOrNot.equals("Y") )
        {
            demo.setCoefficients(fileDemo.readFile(demo.getN(), demo.getN() + 1));
        }
        else
        {
            double [][] tempArray = new double [demo.getN()][demo.getN() + 1];
            for(int i = 0; i < demo.getN(); i++) // enter values of coefficients of the equations
            {
                System.out.println("Please enter the coefficients for equation " + (i + 1));
                for(int k = 0; k < demo.getN() + 1; k++)
                {
                    tempArray[i][k] = keyboard.nextInt(); //Needs to be doubles
                }
            }
            demo.setCoefficients(tempArray); //coefficients will be stored in coefficients array from tempArray
        }
        System.out.print("Please print the error coefficient: "); //getting error
        double error = keyboard.nextDouble();
        System.out.println();

        System.out.println("You entered a matrix of: ");
        demo.printArray(demo.getCoefficients());
    
        double[] startingSolution = new double[demo.getN()];        
        System.out.print("Please enter the starting solution for the problem: "); //receiving starting solution
        for (int i = 0; i < demo.getN(); i++) 
        {
            startingSolution[i] = keyboard.nextDouble();
        }
        System.out.println();

        keyboard.close();
        System.out.println("Jacobi Iteration");
        demo.jacobiMethod(demo.getN(), demo.getCoefficients(), startingSolution, error);
        System.out.println("Gauss Seidel Iteration");
        demo.gaussSeidelMethod(demo.getN(), demo.getCoefficients(), startingSolution, error);

    }   
}