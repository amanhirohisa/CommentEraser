package org.computer.aman.metrics.comment;

import java.io.IOException;

import org.computer.aman.io.sourcecode.NotSupportedSourceFileExeption;
import org.computer.aman.io.sourcecode.SourceFile;

/**
 * A factory of a comment eraser object corresponding to the specified source file.
 * 
 * @author Hirohisa AMAN &lt;aman@computer.org&gt;
 */
public class CommentEraserFactory 
{
    public static CommentEraser create(final String aFilePath) 
    throws SecurityException, NotSupportedSourceFileExeption, IOException
    {
        SourceFile file = new SourceFile(aFilePath);
        
        if ( file.isJavaFile() ){
            return new CommentEraserForJava(file);
        }
        if ( file.isCFile() ){
            return new CommentEraserForC(file);
        }
        
        throw new NotSupportedSourceFileExeption("not supported file type: " + aFilePath);    
    }

}
