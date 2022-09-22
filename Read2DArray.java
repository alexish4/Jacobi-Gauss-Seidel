/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package read2darray;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author DevMecha
 */
public class Read2DArray {

    proj2_3010 getNDemo = new proj2_3010();
    //private static final int ROWS = 4;
    //private static final int COLUMNS = 5;

    public static void main(String[] args) throws FileNotFoundException {
        Read2DArray demo = new Read2DArray();
        proj2_3010 getNDemo = new proj2_3010();
        demo.readFile(getNDemo.getN(), getNDemo.getN() + 1); //because those values are 0
    }

    public double[][] readFile(int ROWS, int COLUMNS) throws FileNotFoundException {
        double[][] numArray = new double[ROWS][COLUMNS];
        Scanner sc = new Scanner(choseTextFile());
        while (sc.hasNextLine()) {
            for (int i = 0; i < numArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    numArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        System.out.println(Arrays.deepToString(numArray));
        return numArray;
    }

    private static File choseTextFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Select File To Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        File[] file = dialog.getFiles();
        return file[0];
    }

}