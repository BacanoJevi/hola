package secondtest;

import java.util.Scanner;
import java.util.Vector;
import java.time.LocalDate;
import java.time.Period;
import java.time.DateTimeException;

public class UtilitiesTest
{
    private static Scanner input = new Scanner( System.in );

    public static void main( String[] args )
    {
        showMenu( );
        byte option = input.nextByte( );

        while( option !=  9 )
        {
            doOptions( option );
            showMenu( );
            option = input.nextByte( );
        }
    }

    private static void showMenu( )
    {
        System.out.println("--------------- PROGRAMA DE UTILIDADES por Emanuel Oviedo -------------------");
        System.out.println("Seleccione una opcion:\n1.\tMultiplicar matrices.\n" +
                "2.\tDeterminar la suma del cuadrado de los elementos de la diagonal principal de una matriz.\n3.\tHallar la matriz transpuesta de una matriz\n" +
                "4.\tHallar la moda de varios elementos.\n5.\tDeterminar la cantidad de dias, meses y años de forma exacta entre dos fechas.\n" +
                "6.\tDeterminar si una cédula suministrada es valida\n7.\tRecibir un monto y devuelva dicho monto expresado en letras.\n" +
                "8.\tDeterminar el Minimo Comun Multiplo de los numeros.\n9.\tSalir.\n");
        System.out.print("Opcion: ");
    }

    private static void doOptions( byte option )
    {
        try
        {
            switch (option)
            {
                case 1: //option 1
                {
                    System.out.println("---------------------MULTIPLICACION DE MATRICES----------------------");

                    double[][] matrix_1 = askForAMatrix((byte) 1);
                    double[][] matrix_2 = askForAMatrix((byte) 2);

                    double[][] result = Utilities.multiplyMatrix( matrix_1, matrix_2);

                    System.out.println("El resultado de la multiplicacion es: ");
                    showMatrix( result );
                }
                break;

                case 2 : //option 2
                {
                    double[][] matrix = askForAMatrix( );
                    double result = Utilities.calculateSumSquare( matrix );
                    System.out.println( "El resultado es: " + result );
                }
                break;

                case 3 : //option 3
                {
                    double[][] matrix = askForAMatrix( );
                    double[][] result = Utilities.findTransposedMatrix( matrix );
                    System.out.println( "La matrix transpuesta es: ");
                    showMatrix( result );
                }
                break;

                case 4 : //option 4
                {
                    int elementsCounter = 1;
                    Vector<String> elementsVector = new Vector<>( );

                    System.out.printf("Introduzca el elemento %d o escriba \"-1\" para terminar:  ", elementsCounter );
                    input.nextLine( ); //Consume salto de linea
                    String element = input.nextLine( );

                    while( !element.equals( "-1" ) )
                    {
                        elementsVector.add( element );
                        elementsCounter++;
                        System.out.printf("Introduzca el elemento %d o escriba \"-1\" para terminar:  ", elementsCounter );
                        element = input.nextLine( );
                    }

                    String[] vectorStore = new String[elementsVector.size( )];
                    String result = Utilities.findTrend( elementsVector.toArray( vectorStore ) );

                    if( result == null )
                        System.out.println( "No hay moda." );
                    else
                        System.out.println( "La moda es: " + result );
                }
                break;

                case 5 : //option 5
                {
                    System.out.println( "----- Fecha 1 ------" );
                    LocalDate date_1 = askForADate( );
                    System.out.println( "----- Fecha 2: -----" );
                    LocalDate date_2 = askForADate( );
                    Period result = Utilities.differenceDates( date_1, date_2 );

                    int differenceYears = Math.abs( result.getYears( ) );
                    int differenceMonth = Math.abs( result.getMonths( ) );
                    int differenceDays = Math.abs( result.getDays( ) );

                    System.out.printf( "La diferencia exacta entre las dos fechas es: " +
                            "%s anos, %s meses, %s dias%n%n", differenceYears, differenceMonth, differenceDays );
                }
                break;

                case 6 : //option 6
                {
                    System.out.println( "Introduzca la cedula a validar:");
                    input.nextLine( );
                    String idCard = input.nextLine( );

                    System.out.printf( "La cedula es %s%n ", ( Utilities.isValid( idCard ) ) ? "valida." : "invalida." );
                }
                break;

                case 7: //option 7
                {
                    String amount;
                    String amountInString = null;

                    System.out.print( "Ingrese el monto a convertir: " );
                    input.nextLine( );
                    amount = input.nextLine( );

                    try
                    {
                        int amountInteger = Integer.parseInt( amount );
                        amountInString = Utilities.amountToText( amountInteger );

                        System.out.println( amountInString + " PESOS");
                    }
                    catch ( NumberFormatException e )
                    {
                        double amountDouble = Double.parseDouble( amount );
                        amountInString = Utilities.amountToText( amountDouble );

                        System.out.println( amountInString );
                    }
                }
                break;
                case 8 : //Option 8
                {
                    Vector<Integer> numbersVector = new Vector<>();

                    System.out.println( "Introduzca un nuevo numero o escriba \"-1\" para calcular el MCM" );
                    System.out.print( "Opcion: ");
                    int number = input.nextInt( );

                    while( number != -1 )
                    {
                        if( ! numbersVector.contains( number ) )
                            numbersVector.add( number );
                        System.out.println( "Introduzca un nuevo numero o escriba \"-1\" para calcular el MCM" );
                        System.out.print( "Opcion: ");
                        number = input.nextInt( );
                    }

                    int result = Utilities.calculateMinimunCommonMultiple( numbersVector );
                    System.out.println( "El MCM es: " + result );
                }
                break;
                default:
                {
                    System.out.println( "Opcion invalida, por favor seleccione otra." );
                }
            }
        }
        catch( IllegalArgumentException e )
        {
            System.err.println( e.getMessage( ) );
        }
    }

    private static double[][] askForAMatrix( )
    {
        System.out.print("Cantidad de filas de la matriz: ");
        int numberOfRows = input.nextInt( );
        System.out.print("Cantidad de columnas de la matriz: ");
        int numberOfColumn = input.nextInt( );
        double[][] matrix = new double[numberOfRows][numberOfColumn];

        System.out.println( "Ingrese los datos de la matriz" );

        for( int i= 0; i < numberOfRows; i++ )
        {
            for( int j = 0; j< numberOfColumn; j++ )
            {
                System.out.printf("( %d, %d ) = ", i, j );
                matrix[i][j] = input.nextDouble( );
            }
        }
        return matrix;
    }

    private static double[][] askForAMatrix( byte numberOfMatrix )
    {
        System.out.printf("Cantidad de filas de la matriz %d: ", numberOfMatrix );
        int numberOfRows = input.nextInt( );
        System.out.printf("Cantidad de columnas de la matriz %d: ", numberOfMatrix );
        int numberOfColumn = input.nextInt( );
        double[][] matrix = new double[numberOfRows][numberOfColumn];

        System.out.printf( "Ingrese los datos de la matriz %d%n", numberOfMatrix );

        for( int i = 0; i < numberOfRows; i++ )
        {
            for( int j = 0; j< numberOfColumn; j++ )
            {
                System.out.printf("( %d, %d ) = ", i, j);
                matrix[i][j] = input.nextDouble( );
            }
        }
        return matrix;
    }

    private static void showMatrix( double[][] matrix )
    {
        for( double[] i : matrix )
        {
            for( double j : i )
            {
                System.out.printf("%.2f, ", j );
            }
            System.out.println( );
        }
    }

    private static LocalDate askForADate( )
    {
        System.out.print( "Introduzca el año: " );
        int year = input.nextInt( );
        System.out.print( "Introduzca el numero del mes: " );
        byte month = input.nextByte( );
        System.out.print( "Introduzca el dia: " );
        byte day = input.nextByte( );

        LocalDate date = null;

        try
        {
            date = LocalDate.of( year, month, day );
        }
        catch ( DateTimeException e )
        {
            System.err.println( e.getMessage( ) );
        }

        return date;
    }
}
