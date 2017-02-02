grammar CML;

start: concept*;
concept: 'concept' NAME ';';

NAME: 'A'..'Z' ( 'A'..'Z' | 'a'..'z' | '0'..'9' )*;

// Ignoring whitespace:
WS: ( ' ' | '\t' | '\f' | '\n' | '\r' )+ -> skip;

// Ignoring comments:
COMMENT: ('//' .*? '\n' | '(*' .*? '*)' ) -> skip;
