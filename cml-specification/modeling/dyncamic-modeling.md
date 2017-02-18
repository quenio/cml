# Rules

## 9.2 Responsibility Distribution

### 9.2.1 Self

- a) knowledge: attribute getter
- b) update: attribute setter

### 9.2.2 Neighbors

- a) knowledge: association role query
- b) update: association role links: (setRole, addRole, removeRole, replaceRole)

### 9.2.3 Others

- a) knowledge: derived attributes/roles, general queries (see 9.5)
- b) update: commands (see 9.4)

## 9.4 Postconditions

### 9.4.1 Instance Creation

When a contract establishes that some instance must be created, an object has to be chosen to create it.

The choice should be based on the following rules:
- a) An object that is the aggregate of the instance to be created.
- b) An object of a class that has one-to-many association to the class of the instance to be created.
- c) An object that has the initialization information for the object to be created.

## 9.5 Queries

Three types of queries:
- system queries: implemented on the interfaces.
- basic queries: correspond to getters of attributes and links.
- intermidiate queries: correspond to derived atributes and associations.
