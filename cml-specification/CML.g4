grammar CML;

model returns [model: Model; scope: Scope;]
    : modelElement*;

modelElement
    : concept | target;

concept returns [concept: Concept; scope: Scope;]
    : 'concept' NAME ';';

target returns [target: Target; scope: Scope;]
    : 'target' NAME '{' propertyList '}';

propertyList
    : (property ';')*;

property returns [property: Property; scope: Scope;]
    : NAME '=' STRING;

NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
STRING: '"' .*? '"';
