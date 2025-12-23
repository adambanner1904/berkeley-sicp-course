The reading is sections (2, 2.1, 2.2.1) focusing on the linear sequences first and then hiearchical data later on. 

The first chapter was about manipulating and creating generic procedures where we can begin to think about using functions as elements to manipulate.

This chapter now focuses on data abstraction ie creating data structures (and types) to further abstract away complexity. 

Procedural abstraction was a way of saying essentially given a function with this type signature i.e. Double -> Double you can define a new function that takes this as an argument and you can swap in any kind of function (that would have been implemented differently) and as long as it fulfills the same type signature the new function will work.

>Data abstraction is a methodology that enables us to isolate how a compound data object is used from the details of how it is constructed from more primitive data objects.

You can separate the implementation of a data structure like a Rational number with how it will be used by your program. 

This creates **modularity** in the program. 

In the book they start to introduce `pair` and also the `cons` constructor to implement a `pair` I am just wrapping a type definition as a tuple of types. So rational is merely a `Tuple2[Int, Int]`. 

When it comes to it I will try to implement the cons as ADT using `Enum`'s 

In `Lines` I have done a purely functional representation of `Point` `Line` and `Rectangle` in the `Shapes` package I am going to make the package more comprehensive and use case classes as representations instead of tuples. 