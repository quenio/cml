package cml.language.grammar;

import cml.language.features.model.Model;
import cml.language.grammar.CMLParser.ModelNodeContext;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.empty;

public class CMLLoader extends CMLBaseListener
{
    private final CMLSynthesizer synthesizer = new CMLSynthesizer();

    public Optional<Model> loadModel(String sourcePath)
    {
        
        try (final FileInputStream fileInputStream = new FileInputStream(sourcePath))
        {
            final ANTLRInputStream input = new ANTLRInputStream(fileInputStream);
            final CMLLexer lexer = new CMLLexer(input);
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final CMLParser parser = new CMLParser(tokens);
            final ModelNodeContext startContext = parser.modelNode();
            final ParseTreeWalker walker = new ParseTreeWalker();

            walker.walk(synthesizer, startContext);

            return empty();

//            return Optional.of(modelBuilder.getModel());
        }
        catch (final IOException ignored)
        {
//            console.println("I/O Error: %s", exception.getMessage());
            return empty();
        }
    }

}
