root Model: ModelItem*
{
    elements = Concept* + Target*;
}

node ModelItem: Concept | Target;

node Concept: 'abstract'? 'concept' NAME (':' AncestorList)? (';' | PropertyList)
{
    name = NAME;
    abstract = 'abstract'?;

    properties = Property*;
    properties.typeRequired = false;
    properties.typeAllowed = true;

    ancestors = for name in AncestorList.NAME* | map Model.concept[name];
    missingAncestors = AncestorList.NAME* - ancestors.name;
}

node Target: 'target' NAME PropertyList
{
    name = NAME;

    properties = Property*;
    properties.typeRequired = false;
    properties.typeAllowed = false;
}

node PropertyList: '{' (Property ';')* '}';

node Property: NAME (':' Type)? ('=' STRING)?
{
    name = NAME;
    value = unwrap(STRING?);
    type = Type?;
}

node ancestorListNode: NAME (',' NAME)*;

node Type: NAME CARDINALITY?
{
    name = NAME;
    cardinality = CARDINALITY?;
}

token NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
token STRING: '"' .*? '"';
token CARDINALITY: ('?' | '*');

function unwrap(str: String): String
{
    let /"?(<value>.*?)"?/ = str;

    return value or else str;
}
