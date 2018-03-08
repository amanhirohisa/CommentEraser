package org.computer.aman.metrics.comment;

import java.io.IOException;
import java.util.ArrayList;

import org.computer.aman.io.sourcecode.NotSupportedSourceFileExeption;
import org.computer.aman.io.sourcecode.SourceFile;
import org.computer.aman.metrics.util.CodeMap;
import org.computer.aman.metrics.util.CodeMapFactory;

/**
 * A comment eraser for a source file.
 * 
 * @author Hirohisa AMAN &lt;aman@computer.org&gt;
 */
public abstract class CommentEraser 
{
    /**
     * Create an instance of CommentEraser class.
     * Because this class is an abstract class, this constructor is called by only its sub class.
     * 
     * @param aSourceFile target source file
     * @throws IOException 
     * @throws NotSupportedSourceFileExeption 
     */
	public CommentEraser(final SourceFile aSourceFile)
	throws NotSupportedSourceFileExeption, IOException
	{
		sourceFile = aSourceFile;
		codeMap = CodeMapFactory.create(aSourceFile);
	}

    public String getFilePath()
    {
        return sourceFile.getPath();
    }
    
	/**
	 * Parse the source file and return a list of source lines in which all comments are erased.
	 * 
	 * @return a list of source lines in which all comments are erased
	 */
	public abstract ArrayList<String> parse() throws IOException;	
    
    protected CodeMap getCodeMap()
    {
        return codeMap;
    }
    private CodeMap codeMap;
    
    private SourceFile sourceFile;
}
