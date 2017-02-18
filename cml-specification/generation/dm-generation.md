# Dynamic Model Generation

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
