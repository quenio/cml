grammar CML;

start: (concept | target)*;
concept: 'concept' NAME ';';
target: 'target' NAME '{' propertyList '}';
propertyList: (property ';')*;
property: NAME '=' STRING;

NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' )*;
STRING: '"' .*? '"';

// Ignoring whitespace:
WS: ( ' ' | '\t' | '\f' | '\n' | '\r' )+ -> skip;

// Ignoring comments:
COMMENT: ('//' .*? '\n' | '(*' .*? '*)' ) -> skip;
