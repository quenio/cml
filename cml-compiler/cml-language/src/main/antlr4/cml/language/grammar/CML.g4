grammar CML;

@header
{
import cml.language.foundation.elements.*;
import cml.language.features.*;
}

modelNode returns [Model model]: modelElementNode*;
modelElementNode: conceptNode | targetNode;
conceptNode returns [Concept concept]: 'concept' NAME (';' | propertyListNode);
targetNode returns [Target target]: 'target' NAME propertyListNode;
propertyListNode: '{' (propertyNode ';')* '}';
propertyNode returns [Property property]: NAME (':' typeNode)? '=' STRING;
typeNode returns [Type type]: NAME;

NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
STRING: '"' .*? '"';

// Ignoring whitespace:
WS: ( ' ' | '\t' | '\f' | '\n' | '\r' )+ -> skip;

// Ignoring comments:
COMMENT: ('//' .*? '\n' | '(*' .*? '*)' ) -> skip;
