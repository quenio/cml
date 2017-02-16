grammar CML;

model returns [model: Model]
    : modelElement*;

modelElement
    : concept | target;

concept returns [concept: Concept]
    : 'concept' NAME (';' | propertyList);

target returns [target: Target]
    : 'target' NAME propertyList;

propertyList returns [propertyList: PropertyList]
    : '{' (property ';')* '}';

property returns [property: Property]
    : NAME (':' type)? '=' STRING;

type returns [type: Type]
    : NAME;

NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
STRING: '"' .*? '"';
