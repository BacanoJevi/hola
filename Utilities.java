package secondtest;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Vector;

public class Utilities
{
    private static HashMap<Integer, String> unitsMap;
    private static HashMap<Integer, String> tensMap;
    private static HashMap<Integer, String> hundredsMap;
    private static HashMap<Integer, String> specialCaseMap;

    public static double[][] multiplyMatrix(double[][] matrix_1, double[][] matrix_2) throws IllegalArgumentException {
        int rowsOfMatrix_1 = matrix_1.length;
        int rowsOfMatrix_2 = matrix_2.length;
        int columnOfMatrix_1 = matrix_1[0].length;
        int columnOfMatrix_2 = matrix_2[0].length;
        double[][] result = new double[rowsOfMatrix_1][columnOfMatrix_2];

        if (columnOfMatrix_1 != rowsOfMatrix_2)
            throw new IllegalArgumentException( "The number of rows in Matrix 1, must be equal to the number of column of Matrix 2." );

        for (int presentRow = 0; presentRow < result.length; presentRow++) {
            for (int presentColumn = 0; presentColumn < result[0].length; presentColumn++) {
                for (int presentColumnmatrix_1 = 0; presentColumnmatrix_1 < columnOfMatrix_1; presentColumnmatrix_1++) {
                    double presentResult = matrix_1[presentRow][presentColumnmatrix_1] * matrix_2[presentColumnmatrix_1][presentColumn];
                    result[presentRow][presentColumn] += presentResult;
                }
            }
        }
        return result;
    }

    public static double calculateSumSquare(double[][] matrix) throws IllegalArgumentException {
        int numberOfRows = matrix.length;
        int numberOfColumns = matrix[0].length;
        double result = 0;

        if (numberOfRows != numberOfColumns)
            throw new IllegalArgumentException( "The matrix must be square." );

        for (int indexPresentElement = 0; indexPresentElement < numberOfRows; indexPresentElement++) {
            double presentElement = matrix[indexPresentElement][indexPresentElement];
            result += Math.pow(presentElement, 2);
        }

        return result;
    }

    public static double[][] findTransposedMatrix(double[][] matrix) {
        int numberOfRows = matrix.length;
        int numberOfColumns = matrix[0].length;
        double[][] result = new double[numberOfColumns][numberOfRows];

        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }

    public static String findTrend(String[] elements)
    {
        String trend = null;
        int maxFrequency = 1;

        for (int i = 0; i < elements.length; i++) {
            int elementFrequency = 0;

            for (int j = 0; j < elements.length; j++) //Compara el elemento actual con todos los del arreglo
            {
                if (elements[i].equals(elements[j]))
                    elementFrequency++;
            }

            if (elementFrequency > maxFrequency) {
                trend = elements[i];
                maxFrequency = elementFrequency;
            }
        }
        return trend;
    }

    public static Period differenceDates(LocalDate date_1, LocalDate date_2) {
        return Period.between(date_1, date_2);
    }

    public static boolean isValid( String idCard )
    {
        if( idCard.length( ) != 11 )
            throw new IllegalArgumentException( "The number of digit must be 10." );

        for( int i = 0;  i < idCard.length( ); i++ )
        {
            if( !Character.isDigit( idCard.charAt( i ) ) )
            {
                throw new IllegalArgumentException( "The id card can only have digits." );
            }
        }

        int[] idCardToArrayInt = convertStringToArrayInt( idCard );
        final int[] CONSTANTS = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
        int result = 0;
        int mod;
        int lastDigit = idCardToArrayInt[ idCardToArrayInt.length - 1 ];

       for( int i = 0; i <= 9; i++ )
       {
           //result += CONSTANTS[i] * idCardToArrayInt[i];
           int mul = CONSTANTS[i] * idCardToArrayInt[i];
           if( CONSTANTS[i] * idCardToArrayInt[i] <= 9 )
               result += CONSTANTS[i] * idCardToArrayInt[i];
           else
               result += CONSTANTS[i] * idCardToArrayInt[i] - 9;
       }

       mod = result % 10;

       if( mod != 0)
           mod = 10 - mod;

       if( mod == lastDigit )
       {
           return true;
       }
       else
       {
           return false;
       }
    }
    
    private static int[] convertStringToArrayInt( String numberInString )
    {
        int[] result = new int[ numberInString.length( ) ];

        for( int i = 0; i < numberInString.length( ); i++ )
        {
            int elementToInt =  Integer.parseInt( Character.toString( numberInString.charAt( i ) ) );
            result[i] = elementToInt;
        }
        return result;
    }

    public static String amountToText( int amount )
    {
        String amountInString = Integer.toString( amount );
        int numberOfDigits = amountInString.length( );
        int[] amountInArray = convertStringToArrayInt(amountInString);
        int firstPart;
        int secondPart;
        String firstPartText;
        String secondPartText;
        String amountInText = null;

        switch ( numberOfDigits )
        {
            case 1:
            case 2:
            case 3:
                amountInText = getNumberInText(amount);
                break;
            case 4:
                {
                firstPart = amountInArray[0];
                secondPart = amountInArray[1] * 100 + amountInArray[2] * 10 + amountInArray[3];

                if( secondPart == 0 )
                    secondPartText ="";
                else
                {
                    secondPartText = getNumberInText( secondPart );
                }

                if (firstPart == 1)
                    firstPartText = "MIL ";
                else
                {
                    firstPartText = getNumberInText( firstPart ) + " MIL ";
                }

                amountInText = firstPartText + secondPartText;
            }
            break;
            case 5 :
            {
                firstPart = amountInArray[0] * 10 + amountInArray[1];
                secondPart = amountInArray[2] * 100 + amountInArray[3] * 10 + amountInArray[4];
                firstPartText = getNumberInText( firstPart );

                if( secondPart == 0 )
                    secondPartText ="";
                else
                {
                    secondPartText = getNumberInText( secondPart );
                }

                amountInText = firstPartText + " MIL " + secondPartText;
            }
            break;
            case 6 :
            {
                firstPart = amountInArray[0] * 100  + amountInArray[1] * 10 + amountInArray[2];
                secondPart = amountInArray[3] * 100 + amountInArray[4] * 10 + amountInArray[5];
                firstPartText = getNumberInText( firstPart );

                if( secondPart == 0 )
                    secondPartText = "";
                else
                {
                    secondPartText = getNumberInText( secondPart );
                }

                amountInText = firstPartText + " MIL " + secondPartText;
            }
            break;
            case 7 :
            {
                firstPart = amountInArray[0];
                secondPart = amountInArray[1] * 100 + amountInArray[2] * 10 + amountInArray[3];
                int thirdPart = amountInArray[4] * 100 + amountInArray[5] * 10 + amountInArray[6];
                String thirdPartText;

                if( firstPart == 1 )
                    firstPartText = " UN MILLON ";
                else
                {
                    firstPartText = getNumberInText( firstPart ) + " MILLONES ";
                }

                if( thirdPart == 0 )
                    thirdPartText = "";
                else
                {
                    thirdPartText = getNumberInText( thirdPart );
                }

                if( secondPart == 0 )
                    secondPartText = "";
                else if ( secondPart == 1 )
                    secondPartText = " MIL ";
                else
                {
                    secondPartText = getNumberInText( secondPart ) + " MIL ";
                }

                amountInText = firstPartText + secondPartText + thirdPartText;
            }
            break;
            default:
            {
                throw new IllegalArgumentException( "The amount must have a maximum of 9 positive digits." );
            }
        }
        return amountInText;
    }

    public static String amountToText( double amount )
    {

        String amountInString = Double.toString( amount );
        String[] partsOfAmount = amountInString.split( "\\." );
        int integerPart = Integer.parseInt( partsOfAmount[0] );
        int decimalPart = Integer.parseInt( partsOfAmount[1] );
        String amountInText = amountToText( integerPart) + " PESOS CON " + amountToText( decimalPart ) + " CENTAVOS";
        return amountInText;
    }

    private static void initUnitsMap( )
    {
        unitsMap = new HashMap<>();

        unitsMap.put( 0, "CERO" );
        unitsMap.put( 1, "UNO" );
        unitsMap.put( 2, "DOS" );
        unitsMap.put( 3, "TRES" );
        unitsMap.put( 4, "CUATRO" );
        unitsMap.put( 5, "CINCO" );
        unitsMap.put( 6, "SEIS" );
        unitsMap.put( 7, "SIETE" );
        unitsMap.put( 8, "OCHO" );
        unitsMap.put( 9, "NUEVE" );
    }

    private static void initTensMap( )
    {
        tensMap = new HashMap<>();

        tensMap.put( 10, "DIEZ" );
        tensMap.put( 20, "VEINTE" );
        tensMap.put( 30, "TREINTA");
        tensMap.put( 40, "CUARENTA" );
        tensMap.put( 50, "CINCUENTA" );
        tensMap.put( 60, "SESENTA" );
        tensMap.put( 70, "SETENTA" );
        tensMap.put( 80, "OCHENTA" );
        tensMap.put( 90, "NOVENTA" );
    }

    private static void initHundredsMap( )
    {
        hundredsMap = new HashMap<>();

        hundredsMap.put( 100, "CIEN" );
        hundredsMap.put( 200, "DOSCIENTOS" );
        hundredsMap.put( 300, "TRESCIENTOS" );
        hundredsMap.put( 400, "CUATROCIENTOS" );
        hundredsMap.put( 500, "QUINIENTOS" );
        hundredsMap.put( 600, "SEISCIENTOS" );
        hundredsMap.put( 700, "SETECIENTOS" );
        hundredsMap.put( 800, "OCHOCIENTOS" );
        hundredsMap.put( 900, "NOVECIENTOS" );
    }

    private static void initSpecialCaseMap( )
    {
        specialCaseMap = new HashMap<>();

        specialCaseMap.put( 11, "ONCE" );
        specialCaseMap.put( 12, "DOCE" );
        specialCaseMap.put( 13, "TRECE" );
        specialCaseMap.put( 14, "CATORCE" );
        specialCaseMap.put( 15, "QUINCE" );
    }

    private static String getNumberInText( int number ) // 0 - 999
    {
        int numberOfDigits = Integer.toString( number ).length();
        String numberText = null ;

        initUnitsMap( );
        initTensMap( );
        initHundredsMap( );
        initSpecialCaseMap( );

        switch ( numberOfDigits )
        {
            case 1 :
            {
                numberText = unitsMap.get( number );
            }
            break;
            case 2 :
            {
                int units = number % 10;
                int tensInUnits = ( ( number / 10 ) % 10) * 10;

                if( specialCaseMap.containsKey( number ) )
                {
                    numberText = specialCaseMap.get( number );
                }
                else if( tensMap.containsKey( number ) )
                {
                    numberText = tensMap.get( number );
                }
                else
                {
                    String tensInText = tensMap.get( tensInUnits );
                    String unitsInText = unitsMap.get( units );

                    if( number > 15 && number < 20 )
                    {
                        numberText = "DIECI" + unitsInText;
                    }
                    else if ( number > 20 && number < 30)
                    {
                        numberText = "VEINTI" + unitsInText;
                    }
                    else
                    {
                        numberText = tensInText + " Y " + unitsInText;
                    }
                }
            }
            break;
            case 3 :
            {
                int units = number % 10;
                int tensInUnits = ( ( number / 10 ) % 10 ) * 10;
                int hundredsInUnits = ( ( number / 100 ) % 10 ) * 100;
                String tensInText = tensMap.get( tensInUnits );
                String unitsInText = unitsMap.get( units );
                String hundredsInText = hundredsMap.get( hundredsInUnits );

                if( number > 100 && number < 200)
                    hundredsInText = "CIENTO";

                if( hundredsMap.containsKey( number ) )
                {
                    numberText = hundredsMap.get( number );
                }
                else if( specialCaseMap.containsKey( tensInUnits + units ) )
                {
                    String tensSpecialCase = specialCaseMap.get( tensInUnits + units );
                    numberText = hundredsInText + " " + tensSpecialCase;
                }
                else if( unitsMap.containsKey( tensInUnits + units ) )
                {
                    numberText = hundredsInText + " " + unitsInText;
                }
                else if( tensMap.containsKey( tensInUnits + units ) )
                {
                    numberText = hundredsInText + " " + tensInText;
                }
                else
                {
                    if( tensInUnits + units > 15 && tensInUnits + units < 20)
                        numberText = hundredsInText + " DIECI" + unitsInText;
                    else if( tensInUnits + units > 20 && tensInUnits + units < 30 )
                        numberText = hundredsInText + " VEINTI" + unitsInText;
                    else
                    {
                        numberText = hundredsInText + " " + tensInText + " Y " + unitsInText;
                    }
                }
            }
            break;
        }
        return numberText;
    }

    public static int calculateMinimunCommonMultiple( Vector<Integer> numbersVector )
    {
        final int INITIAL_VALUE = 2;
        int counter = INITIAL_VALUE;
        int result;

        if( numbersVector.contains( 0 ) )
            result = 0;
        else
        {
            result = 1;

            while( ! areAllUnit( numbersVector ) )
            {
                if( existsMultiply( numbersVector, counter ) )
                {
                    numbersVector = divideNumbers( numbersVector, counter );
                    result *= counter;
                    counter = INITIAL_VALUE;
                }
                else
                {
                    counter++;
                }
            }
        }

        return result;
    }

    private static boolean areAllUnit( Vector<Integer> numbersVector )
    {
        var iterator = numbersVector.iterator( );

        while( iterator.hasNext( ) )
        {
            if( iterator.next( ) != 1 )
                return false;
        }
        return true;
    }

    private static boolean existsMultiply( Vector<Integer> dividends, int divider )
    {
        var iterator = dividends.iterator( );

        while( iterator.hasNext( ) )
        {
            int presentDividend = iterator.next( );

            if( presentDividend % divider == 0)
                return true;
        }
        return false;
    }

    private static Vector<Integer> divideNumbers( Vector<Integer> dividends, int divider)
    {
        for( int i = 0; i < dividends.size( ); i++ )
        {
            int presentNumber = dividends.get( i );

            if( presentNumber % divider == 0 )
                dividends.set( i, presentNumber / divider );
        }
        return dividends;
    }
}