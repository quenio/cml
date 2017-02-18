# Dynamic Model Generation

Mapping:
- Directories map to packages.
- CML source files have no corresponding mapping on the target structure.
- Concepts map to classes.
- Interfaces map to interfaces and at a single implementation class.
- Interface commands are delegated to class commands.
- Interface queries are delegated to class queries.
- Individual commands may become:
    - class commands (based on visibility, coupling and cohesion) or be inlined
    - or be inline (if contains incomplete instances as parameters)
- Individual queries will become intermediate or basic queries.    

Steps:
- Start from interface contracts

Instance Creation:
- Create constructor first
- Then look for class/method where constructor will be called.

Queries:
- Implement intermidiate queries as derived attributes and associations in the classes that define the original attributes or has the visibility to the original associations.
- Consider the which argument of the query could have the query method based on the visibility.

Preconditions:
- May be generated as assertions, as exceptions, or removed.

Linking Objects:
- Required links should be provided to the class constructor.
- Commands that receive incomplete instances will have to be inlined on the constructors of the instance's classes.
