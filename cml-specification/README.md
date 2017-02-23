
# Language Specification Process

For each language feature, the specification process follows the following steps:
- Define production rules of the grammar.
- Define the AST mapping for each production rule.
- Define conceptual model supporting the grammar rules and AST mapping.
- Generate ModelValidator interface and contract scaffolding from grammar.
- Define contracts of ModelValidator (each having one or more validation errors).

TBD:
- describe how to generate ModelValidator interface from the CML model.

## Concrete Syntax

The concrete syntax tree defined below can represent a parsed CML program.
It will be transformed intro a CML model (the abstract syntax tree) by the ModelSynthesizer.

To transform the grammar into this syntax tree, for each production rule:
- define a concept with the same name appending the suffix "Node".
- for each non-terminal element in the production rule,
  define an association role with the same name in the production rule's concept,
  appending "Node",
  and its type corresponding to the concept of the referenced production rule.
- for each terminal element in the production rule,
- define an attribute with the same name with the String type.
- for any element participating in an alternative,
- define its association role or attribute as optional.
- for production rules containing a returns clause,
  the corresponding attributes or association roles will be added to the node concept.

## Syntax Tree Builder

In order to build the syntax tree, the buider will have methods called when traversing the production rules of the grammar during parsing.

The following annotations are used to determine when to call each builder method:
- @BeforeRule to call createXxxxxNode(...) methods before evaluating the specified grammar rule.
- @AfterRule to call includeXxxxxNode(...) methods after evaluating the specified rule.

The "include" methods of @AfterRule may have parameters corresponding to the terminal nodes parsed by the rule.

## Model Synthesizers

The ModelSynthesizer will synthesize the attributes of the syntax tree based on the node's terminals and the node's children.

To generate the ModelSynthesizer interface and the contracts scaffolding, for each production rule containing attributes, define a command "synthesize" containing the node type of the production rule as an argument.

## Invariants

Notice that we cannot add any invariants that prevent the instantiation of malformed models,
because the concrete syntax allows it.

The model (abstract syntax) is validated only after they have been synthesized.

## Collections

List = Order Set or Sequence?
