package java_lang;

import cml.language.features.Concept;
import cml.language.foundation.Property;
import cml.language.foundation.Type;
import generic.TemplateTest;
import org.junit.Test;
import org.stringtemplate.v4.ST;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;

public class ClassTest extends TemplateTest
{
    @Override
    protected String getTemplateFileName()
    {
        return "/java_lang/class.stg";
    }

    @Test
    public void class_emptyConcept() throws IOException
    {
        final Concept concept = Concept.create("Book");

        testClassTemplateWithConcept(concept, "emptyConcept.txt");
    }

    @Test
    public void class_simpleConcept() throws IOException
    {
        final Concept concept = Concept.create("Book");

        concept.addElement(Property.create("title", null, Type.create("String", null)));

        testClassTemplateWithConcept(concept, "simpleConcept.txt");
    }

    @Test
    public void class_conceptWithOptionalProperty() throws IOException
    {
        final Concept concept = Concept.create("Book");

        concept.addElement(Property.create("title", null, Type.create("String", null)));
        concept.addElement(Property.create("sequel", null, Type.create("Book", "?")));

        testClassTemplateWithConcept(concept, "conceptWithOptionalProperty.txt");
    }

    @Test
    public void class_conceptWithSetProperty() throws IOException
    {
        final Concept concept = Concept.create("Book");

        concept.addElement(Property.create("title", null, Type.create("String", null)));
        concept.addElement(Property.create("sequel", null, Type.create("Book", "?")));
        concept.addElement(Property.create("categories", null, Type.create("Category", "*")));

        testClassTemplateWithConcept(concept, "conceptWithSetProperty.txt");
    }

    @Test
    public void class_conceptWithAncestor() throws IOException
    {
        final Concept productConcept = Concept.create("Product");
        productConcept.addElement(Property.create("description", null, Type.create("String", null)));

        final Concept bookConcept = Concept.create("Book");
        bookConcept.addElement(Property.create("title", null, Type.create("String", null)));
        bookConcept.addDirectAncestor(productConcept);

        testClassTemplateWithConcept(bookConcept, "conceptWithAncestor.txt");
    }

    @Test
    public void class2_conceptWithClassNameSuffixAndMultipleAncestors() throws IOException
    {
        final Concept baseConcept = Concept.create("Base");
        baseConcept.addElement(Property.create("baseProperty", null, Type.create("String", null)));

        final Concept productConcept = Concept.create("Product");
        productConcept.addElement(Property.create("description", null, Type.create("String", null)));
        productConcept.addDirectAncestor(baseConcept);

        final Concept stockItemConcept = Concept.create("StockItem");
        stockItemConcept.addElement(Property.create("quantity", null, Type.create("Integer", null)));
        stockItemConcept.addDirectAncestor(baseConcept);

        final Concept concept = Concept.create("Book");
        concept.addElement(Property.create("title", null, Type.create("String", null)));
        concept.addDirectAncestor(productConcept);
        concept.addDirectAncestor(stockItemConcept);

        testClassTemplateWithSuffix(concept, "conceptWithClassNameSuffixAndMultipleAncestors.txt");
    }

    private void testClassTemplateWithConcept(Concept concept, String expectedOutputFileName) throws IOException
    {
        testTemplateWithConcept(
            "class",
            concept,
            "/java_lang/class/" + expectedOutputFileName);
    }

    private void testClassTemplateWithSuffix(Concept concept, String expectedOutputFileName) throws IOException
    {
        final String templateName = "class2";

        final ST template = getTemplate(templateName);
        assertNotNull("Expected template: " + templateName, template);

        template.add("concept", concept);
        template.add("classNameSuffix", "Impl");

        final String result = template.render();
        assertThatOutputMatches("/java_lang/class/" + expectedOutputFileName, result);
    }
}
