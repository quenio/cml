package cml.language.foundation.elements;

public class SourceLocation
{
    private int line, column;
    private String sourcePath;
    
    public SourceLocation(int line, int column, String sourcePath)
    {
        this.line = line;
        this.column = column;
        this.sourcePath = sourcePath;
    }
}
