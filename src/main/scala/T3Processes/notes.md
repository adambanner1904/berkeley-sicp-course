To convert a normal recursive process function to an iterative process (but still a (tail) recursive function) you have to pass along some sort of state. 

This was made explicit when reading through "Functional programming in Scala" but is hinted at here. 

In most functions this is in the form of some sort of accumulator that "accumulates" the result as an argument to the function. 

It can also be other arguments such as the fibonacci inner function that must be defined if you want the actual function to have a signature of fib(n) (ie just one number)
The inner function then can be defined as a state transformation at each step from (f1, f2) -> (f2, f3=f1+f2) which creates an iterative function. 

As I said this is made more explicit in "Functional programming in Scala" by creating a State class that essentially is the result + some state that is passed forward where the state can be anything.  