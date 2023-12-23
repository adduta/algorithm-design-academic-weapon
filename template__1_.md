# Feedback Greedy assignment

Legend:
 - (~) Point for improvement.
 - (=) Neutral comment
 - (+) Well done!

## Exercise 1
- (+) Good job!
- (+) You got the correct order!

## Exercise 2

- (+) Your pseudocode is easy to read and you properly introduce the notation you are going to use.
- (+) Good job on also including a small piece of text in just "natural language" to explain your pseudocode! This always helps the readers a great deal!
- (=) As practice, it is great that you compared your algorithm to other possible ones! However, on the exam, make sure not to waste time on things the question doesn't require you to do.

## Exercise 3

- (+) You explain all of the major parts of the algorithm well and deduce the correct run time complexity.
- (=) You could structure your answer a litle more, for instance by buletting the different operations ;) (Just a lay-out tip!)

## Exercise 4
- (~) Don't forget to mention the case in which G = O - you only need the rest of the proof if G != O.
- (=) Instead of saying "let O be..." the second time, it is better to say that you assume that O has the same order as G up until some point (this point can even be 0!).
- (~) Don't assume that G has the correct order - this is something that you need to prove. In other words, you know that O has a correct order and you want to derive from that the ordering used by G is also correct.
- (~) You need to analyze an inversion in the schedules - a pair (i, j) that is in a different order in O than in G. You also need to explain why it is adjacent.
- (~) Also do the math!
- (~) What about the finishing times for the other contestants?
- (~) You need to specify how many inversions need to be solved to reach the greedy schedule in the worst case.
- (+) It can be seen that you understand the idea behind why the greedy algorithm is optimal! You just need to work on the structure of the proof - maybe writing down the steps of an exchange proof would help.
- (=) The idea behind a proof by greedy exchange argument is to show that your greedy schedule is optimal, this is done by taking an optimal schedule. This schedule must differ in some way from your greedy schedule, if not we would already be done as the greedy schedule is now optimal as well, they are the same schedule after all. This difference in the schedule means that we have some inversion, which is when two contestants are ordered in the incorrect order (not the greedy schedule order). If we can show that reversing this inversion won't make the optimal schedule worse, we would have shown that there is a new optimal schedule with one less inversion. Now, we can repeat this process for a finite amount of time to remove all inversions, leaving us with the greedy schedule. Now we have shown that this process got us to the greedy schedule without removing the optimality of the schedule, so our greedy schedule must have been optimal!

## General remarks

- (=) Well done on handing in the assignment! You have done a first(?) proof of a greedy algorithm, which is very good preperation for the written exam of this course. I hope that this feedback gives you some tips on how to further improve your proofs in the future :) If you have any questions, feel free to tackle me during one of the lab sessions! (Ioana/Lisu)


