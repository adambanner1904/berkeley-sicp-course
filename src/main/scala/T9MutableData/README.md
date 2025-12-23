# Topic 9 - Mutable Data and Vectors (Arrays)

## Description

## Actions
Reading: Section 3.3.1-3 & Optional 3.3.4

Lectures: 24-26

Exercises: 3.16, 3.17, 3.21, 3.25, 3.27

## My notes

In Chapter 2 we had compound data objects where we defined constructors and selectors. Now with the addition of mutability we also have to factor in the fact that we may want to modify parts of the object. These are called mutators. 

Functional data types then probably only have constructors and selectors. Mutable data types also have mutators. 

Thinking about it they should still be able to have "setters" only functional objects would need to return a new instance with a new value that the setter specifies whereas mutable objects can make use of mutators to modify the object in-place and return that same object. 

Being able to modify elements in a compound data structure like a list in Python introduces problems in identity. You have to think about what is actually be shared

```scheme
x = list(1, 2)
z = cons(x, x)
```
compare this with 

```scheme
z = cons(list(1, 2), list(1, 2))
```
in the first example `cons(x, x)` is referencing the same list and so any modification to the `car` of the list will also change the list that is being used as the `cdr` whereas in the second example modifying the `car` of `z` won't affect the `cdr` due to the two elements being distinct elements that were constructed separately. 

In the book Fluent Python he talks about this in an example called the ghost bus that always stuck in my mind, it was around the mutability of Python lists and the dangers that the flexibility that Python gives can lead to if you aren't aware of how it actually works. It originally went over my head but now with this understanding I get it. 

The main difference is be wary of using references and make sure that is what you mean as opposed to when you should be using constructors instead. 

Above is actually the perfect example of the substitution model breaking as well. 