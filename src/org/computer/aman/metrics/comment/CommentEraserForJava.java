package org.computer.aman.metrics.comment;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.computer.aman.io.sourcecode.NotSupportedSourceFileExeption;
import org.computer.aman.io.sourcecode.SourceFile;
import org.computer.aman.metrics.util.CodeLineMap;
import org.computer.aman.metrics.util.java.CodeLineMapForJava;

public class CommentEraserForJava 
extends CommentEraser 
{
    public CommentEraserForJava(final SourceFile aSourceFile) 
    throws NotSupportedSourceFileExeption, IOException
    {
        super(aSourceFile);
    }
	
	public ArrayList<String> parse() 
	throws IOException 
	{
        ArrayList<String> lines = new ArrayList<String>();

        LineNumberReader reader = new LineNumberReader(new FileReader(getFilePath()));

        for ( Iterator<CodeLineMap> itr = getCodeMap().iterator(); itr.hasNext(); ){
        	String line = reader.readLine();
        	CodeLineMapForJava lineMap = (CodeLineMapForJava)itr.next();
            if ( lineMap.getCommentCount() == 0 ){
                lines.add(line);
            }
            else{
            	final int LENGTH = lineMap.getMap().length();
            	StringBuilder builder = new StringBuilder();
            	for ( int i = 0; i < LENGTH; i++ ){
            		builder.append( lineMap.isComment(i) ? ' ' : line.charAt(i) );
            	}
            	lines.add(new String(builder));
            }
        }
        
        reader.close();
        
        return lines;
	}
}
