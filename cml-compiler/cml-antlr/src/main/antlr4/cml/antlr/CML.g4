grammar CML;

model: modelElement*;
modelElement: concept | target;
concept: 'concept' NAME (';' | propertyList);
target: 'target' NAME propertyList;
propertyList: '{' (property ';')* '}';
property: NAME (':' type)? '=' STRING;
type: NAME;

NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
STRING: '"' .*? '"';

// Ignoring whitespace:
WS: ( ' ' | '\t' | '\f' | '\n' | '\r' )+ -> skip;

// Ignoring comments:
COMMENT: ('//' .*? '\n' | '(*' .*? '*)' ) -> skip;
