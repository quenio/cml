grammar CML;

model returns [model: Model]
    : modelElement*;

modelElement
    : concept | target;

concept returns [concept: Concept]
    : 'concept' NAME (';' | propertyList);

target returns [target: Target]
    : 'target' NAME propertyList;

propertyList
    : '{' (property ';')* '}';

property returns [property: Property]
    : NAME '=' STRING;

NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
STRING: '"' .*? '"';
