package org.computer.aman.metrics.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.computer.aman.io.sourcecode.NotSupportedSourceFileExeption;

public class CommentEraserCUI
{
    public static void main(String[] args) 
    throws SecurityException, NotSupportedSourceFileExeption, IOException
    {
        CommentEraserCUI ui = new CommentEraserCUI();

        ui.printCopyright();
        if ( args.length != 1 ){
            ui.printUsage();
            return;
        }
        printSeparator();
        
        CommentEraser eraser = CommentEraserFactory.create(args[0]);
        ArrayList<String> lines = eraser.parse();
        for (Iterator<String> iterator = lines.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());			
		}

        printSeparator();
    }
    
    
    private static void printSeparator()
    {
        final int LENGTH = 64;
        for ( int i = 0; i < LENGTH; i++ ){
            System.err.print("-");
        }
        System.err.println();
    }
    
    
    private void printCopyright()
    {
        try {
            printResource(COPYRIGHT);
        }
        catch (IOException e) {
            System.err.println("[Internal error]" + COPYRIGHT + " not found.");
        }
    }
    
    private void printResource(final String aFileName) 
    throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(aFileName)));
        String line = null;
        while ( (line = reader.readLine()) != null){
            System.err.println(line);
        }
        reader.close();
    }
    

    private void printUsage()
    {
        try {
            printResource(USAGE);
        }
        catch (IOException e) {
            System.err.println("[Internal error]" + USAGE + " not found.");
        }    
    }
    
    private final String COPYRIGHT = "copyright.txt";
    private final String USAGE = "usage.txt";
}
