# Topic 8 - Local State

## Description

## Actions
Reading: Section 3.1-3.2

Lectures: 21-23

Exercises:  3.3, 3.4, 3.7, 3.8, 3.10, 3.11 
& part 1 of programming project 3

## My notes

### Introducing state
Previously, in functional programming, there is no concept of state. Every function takes in an input and produces an output, the same output for the same input always. 

Because of this feature we could reason about programs using the substitution model which is substituting what the function returns with the function itself. 

This breaks when we introduce state in an object-oriented sense because a function can have side effects, it can potentially change a variable somewhere else and so substituting what the function returns with the function itself is not right as there is that side effect that would not have been done otherwise. 

This introduction also introduces the notion of effects vs values. Previously everything returned a value, now functions and code can have effects, the modification of some state somewhere without actually returning anything meaningful (denoted by Unit in Scala)

Functions / lambdas can encapsulate state and create a local environment.

A symbol or a name for a variable is no longer just the value that it is assigned but more of a pointer to a space in memory a value is stored but that can change. 

"Sameness" also breaks down when variables and functions can produce or have side effects. 
Referential transparency breaks. 

This idea of "sameness" is really philosophical. 

It's true that if we change the numerator or denominator of a rational number we do not consider it to be the same one. But if we change the balance in a bank account we do think that this is the same bank account with just a different balance but why are these two different in our minds. 

Using a loop imperatively forces a programmer to think about in which order assignment needs to happen. Passing the values as arguments to function calls do not require this. 

Functional programming is making a comeback now as a result of the highly concurrent and parallel systems that are dominating the landscape where its important that functions and computations can run in separate places without having to be concerned about some state elsewhere. 

Pure functions by definition can be run anywhere and return the same results. 

### Introducing environments

*Environments* are sequences of *frames*. A *frame* is a table of *bindings* where a *binding* associates a symbol to its value. 

A single frame may contain only one binding for any variable and also points to its parent/enclosing environment unless it is global. 

A procedure/function is a pair where one element is the definition (parameters and body) and the other value is the enclosing environment. Whichever environment you define a function also creates a pointer with the name you define the function with to this pair. 

I.e. `def square(x) = x * x` at the global level creates a binding in the global environment from the symbol `square -> ((parameters, body), parent environment)`

>Objects are just closures

This certainly makes the whole thing easier to understand than thinking classes and objects are some really obscure magical thing.

I don't think learning OOP in Python all those years ago would have been half as confusing with the built-up knowledge that classes and objects are just a way to encapsulate some state about a distinct entity.

Substitution model: 
1. EVAL subexpressions
2. APPLY proc to args
   1. substitute argument values for formal parameters
   2. EVAL modified body

Environment:
1. EVAL subexpressions
2. APPLY procedure to arguments
   1. Make a new environment with formal parameters bound to arguments
   2. EVAL the body IN the new environment

They work similarly, only different in how they apply the procedure to the arguments.

Substitution just subs in the evaluated argument expressions in as parameters and then evaluates this new expression. 

The environment model evaluates an expression in an environment so part of APPLY is to create a new environment for that procedure call.

I've always heard of the global environment in terms of python programs or even bash programs, but I didn't realise that they are crucial to understanding how programs are ran at all. 

It makes sense when you think that a program or procedure cannot run outside an environment. 

Apply at an object level is like the make-count procedure or instantiate procedure but is quite nice syntactically. Apply at the class or instance level describes the main API of an instance of that class. 

`Object.apply()` sets up an environment for each instance. 


There are a lot of let and lambdas (where lets are just syntactic sugar for creating a lambda and calling it). 

`make-count` is a function that can encapsulates a global counter in its environment and its empty apply method (calling it in Scheme) creates new procedures that encapsulate a more local scope. The procedure of each local scope updates its own local counter and the global counter but cannot see and does not know about any other 'instances' local scope.

`make-count` then is a **factory** for `counters`. 

3 pillars that are needed to implement OOP: 
1. A way to create local state
2. Message passing (to create multiple methods)
3. Inheritance

Local state is created by the above-mentioned encapsulation of variables within a procedure call.

Message passing is simply adding another layer of a lambda that takes a message and then returns the asked for procedure (ready to call with arguments, it doesn't actually call it itself)

Inheritance is created by following the same pattern but each instance has a local variable that is an instance of a parent, new methods can be defined by message passing and in the case of not matching any specific message in the child it delegates everything else to the parent which will either match there or fail completely (which is expected). 


### Rereading the chapters after the lectures

The monte carlo experiment sticks out to me especially the idea that when you break encapsulation some parts of the program bleeds out into the rest that could otherwise be hidden away. It's clear in that example that being able to encapsulate state into the random function itself allows the monte carlo program to be clearer about what its process is doing as opposed to the alternative where it is not clear what is part of the random number generation or part of the main purpose of the monte carlo program.
>There is a substantial difference between modeling this as
> ```scheme
>(define peter-acc (make-account 100))
> (define paul-acc (make-account 100))
>```
> and modeling it as
> ```
> (define peter-acc (make-account 100))
> (define paul-acc peter-acc)
> ```

The above was a massive source of confusion to me in Python. I thought that creating a new variable using a current one would copy it and the contents. But variables in Python are essentially just pointers/references to a thing and creating a new variable just points to the same place. I used to wonder why there were `copy()` methods on so many data types. 