package edu.unapec.ejercicio_c;

import static javax.swing.JOptionPane.*;
import static javax.swing.JFileChooser.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.String.*;
import java.io.IOException;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.*;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

class Test {
    public static void main( String[] args ) {
        showMessageDialog(null,"Elaborado por: (Pon tu nombre aqui)\nMatricula: (Tu matricula)",
                "Ejercicio 6 - Examen Final POO",INFORMATION_MESSAGE);
        var rows = parseInt( showInputDialog( null,"Introduzca la cantidad de filas de la matriz" ) );
        var cols = parseInt( showInputDialog( null,"Introduzca la cantidad de columnas de la matriz" ) );
        int[][] integerArray = new int[rows][cols];

        for( int i = 0; i < integerArray.length; i++ ) {
            for( int j = 0; j < integerArray[0].length; j++ ) {
                integerArray[i][j] = parseInt( showInputDialog(null,format("Fila: %d\nColumna: %d", i, j ),"Introduzca el valor", PLAIN_MESSAGE ) );
            }
        }

        showFileChooser( sortMatrix( integerArray ) );
    }

    private static void printArray( int[][] integerArray ) {
        for( int[] row : integerArray ) {
            for( int number : row ) {
                System.out.printf( "%s, ", number );
            }
            System.out.println();
        }
    }

    private static int[][] sortMatrix( int[][] integerArray ) {
        var transposedMatrix = transposeArray( integerArray );
        for( int[] row : transposedMatrix ) {
            sort( row );
        }
        return transposeArray( transposedMatrix );
    }

    private static int[][] transposeArray( int[][] integerArray ) {

        int[][] transposedArray = new int[integerArray[0].length][integerArray.length];
        for( int i = 0; i < transposedArray.length; i++ ) {
            for( int j = 0; j < transposedArray[0].length; j++ ) {
                transposedArray[i][j] = integerArray[j][i];
            }
        }
        return transposedArray;
    }

    private static void showFileChooser( int[][] integerArray ) {
        var fileChooser = new JFileChooser( );
        fileChooser.setFileSelectionMode( FILES_ONLY );
        fileChooser.setFileFilter( new FileNameExtensionFilter("Archivo de texto","txt" ));
        if( fileChooser.showSaveDialog( null ) == APPROVE_OPTION ) {
            final var EXTENSION_FILE = ".txt";
            writeArrayInFile( get(fileChooser.getSelectedFile( ).getAbsolutePath( ) + EXTENSION_FILE ), integerArray );

        }
        else {
            showMessageDialog( null,"No seleccionó un directorio para guardar el archivo",
                    "Error",ERROR_MESSAGE);
        }
    }

    private static void writeArrayInFile( Path path, int[][] integerArray ) {
        try
        {
            var writer = exists( path ) ? newBufferedWriter( path, StandardOpenOption.TRUNCATE_EXISTING ) : newBufferedWriter( path, StandardOpenOption.CREATE );

            for( int[] row : integerArray ) {
                for( int number : row ) {
                    writer.write( format( "%s, ", number ) );
                }
                writer.newLine( );
            }

            showMessageDialog(null,"El archivo se ha creado satisfactoriamente", "Aviso", INFORMATION_MESSAGE );
            writer.close();
        }
        catch ( IOException exception ) {
            showMessageDialog( null,exception.getMessage(),"Error",ERROR_MESSAGE );
        }
    }
}
