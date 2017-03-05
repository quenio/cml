root Model: ModelItem*
{
    elements = Concept* + Target*;
}

node ModelItem: Concept | Target;

node Concept: 'concept' NAME (';' | PropertyList)
{
    name = NAME;

    properties = Property*;
    properties.typeRequired = false;
    properties.typeAllowed = true;
}

node Target: 'target' NAME PropertyList
{
    name = NAME;

    properties = Property*;
    properties.typeRequired = false;
    properties.typeAllowed = false;
}

node PropertyList: '{' (Property ';')* '}';

node Property: NAME (':' Type)? '=' STRING
{
    name = NAME;
    value = unwrap(STRING);
    type = Type?;
}

node Type: NAME
{
    name = NAME;
}

token NAME: ('A'..'Z' | 'a'..'z') ( 'A'..'Z' | 'a'..'z' | '0'..'9' | '_' )*;
token STRING: '"' .*? '"';

function unwrap(str: String): String
{
    let /"?(<value>.*?)"?/ = str;

    return value or else str;
}
