package hometask2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String correctString = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        String extraRowString = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0\n 45 67 78 98";
        String extraColumnString = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0 89";
        String missingRowString = "10 3 1 2\n2 3 2 2\n5 6 7 1";
        String missingColumnString = "10 3 1 2\n2 3 2 2\n5 6 1\n300 3 1 0";
        String notIntString = "10 3 1 2\n2 3 2 2\n5 6 S 1\n300 3 1 0";
        String emptyString = "";

        String[][] myArray;
        String s = correctString; // здесь можно менять строчку
        try {
            myArray = toArray(s);   // <-- сюда вводиться строка
            for (int i = 0; i < myArray.length; i++) {
                System.out.println(Arrays.toString(myArray[i]));
            }
            System.out.println("Result of operation is: " + calcMatrix(toIntMatrix(toArray(s)))); //<-- и сюда
        }
        catch(ArrayInvalidColumnCountException e){
            System.out.println(e.getMessage());
        }
        catch(ArrayInvalidRowCountException m){
            System.out.println(m.getMessage());
        }
        catch(ArrayParseToIntegerException i){
            System.out.println(i.getMessage());
        }
        catch(Throwable t){
            System.out.println("Какая то непредвиденная ошибка");
            System.out.println(t.getMessage());
            t.getStackTrace();

        }

    }


    public static String[][] toArray(String s) throws ArrayInvalidRowCountException, ArrayInvalidColumnCountException {
        String[] arr1 = s.split("\n");
        if(arr1.length !=4 ){
            throw new ArrayInvalidRowCountException("Wrong count of rows: "+ arr1.length + ". Correct row number: 4");
        }
        String[][] arr2 = new String[4][];
        for (int i = 0; i < arr1.length ; i++) {
            arr2[i] = arr1[i].split(" ");
        }
        for (int i = 0; i < arr2.length ; i++) {
            if(arr2[i].length != 4){
                throw new ArrayInvalidColumnCountException("Wrong count of columns in row " + i + ": " + arr2[i].length + ". Correct column number: 4");
            }
        }
        return (arr2);
    }

    public static int[][] toIntMatrix(String[][] StringMatrix) throws ArrayParseToIntegerException {

        int[][] intMatrix = new int[StringMatrix.length][StringMatrix.length]; // матрица всегда квадратная
        for (int i = 0; i < StringMatrix.length; i++) {
            for (int j = 0; j < StringMatrix[i].length; j++) {
                try {
                    intMatrix[i][j] = Integer.parseInt(StringMatrix[i][j]);
                }
                catch (Exception e){
                    throw new ArrayParseToIntegerException("Can not parse to integer element: " + StringMatrix[i][j]);
                }
            }
        }
        return intMatrix;
    }
    public static float calcMatrix(int[][] intMatrix){
        float sum =0;
        for (int i = 0; i < intMatrix.length; i++) {
            for (int j = 0; j < intMatrix[i].length; j++) {
                sum+=intMatrix[i][j];
            }
        }
        return (sum/2f);
    }
}