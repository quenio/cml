grammar CML;

ModelNode: ModelElementNode*
-> Model { concepts = <ConceptNode*>; targets = <TargetNode*>; }

ModelElementNode: ConceptNode | TargetNode;

ConceptNode: 'concept' NAME (';' | PropertyListNode)
-> Concept { name = <NAME>; properties = <PropertyNode*>; }

TargetNode: 'target' NAME PropertyListNode
-> Target { name = <NAME>; properties = <PropertyNode*>); }

PropertyListNode: '{' (PropertyNode ';')* '}';

PropertyNode: NAME (':' TypeNode)? '=' STRING
-> Property { name = <NAME>; value = <STRING>; type = <TypeNode?>; }

TypeNode: NAME
-> Type { name = <NAME>; }

NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
STRING: '"' .*? '"';
