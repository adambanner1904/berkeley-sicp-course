# Week 2 Functional Programming 2

Reading chapter 1.3 in preparation for the following lectures. 

Thoughts from this chapter
It actually feels like this time a lot of it has clicked. Thinking about the types algebraically was useful, 
coming up with different types like Function and Transformer etc to solidify what the functions were accepting 
and returning or using etc. 

I can understand now the use of currying. I used to think what was the point in returning and intermediary function 
say in the case of the derivative, you have f and then get the derivative(f) and this is a useful FUNCTION in its own 
right and so to always enforce say derivative(f, x) to get the value of the derivative of f at x isn't as intuitive as 
retrieving the derivative(f) and then applying that to x.

There seemed to be a set number of functions really. 
1. Normal functions that accept primitives and return primitives 
2. Functions that accept functions as arguments in order to be able to swap out a certain piece of computation
3. Functions that return functions as values. This without functions as parameters are rarer but the cubic function 
I defined that takes in the constants of a function (a, b, c) and returns the actual function is a key example
4. And finally those that take in functions and return functions I termed Transformer's that used the computation of an existing function
to define a new function

