# String-Matching-Algorithms
String matching algorithms

Written in Java.
Uncomment the code between the horizontal dotted lines for corresponding solutions in Java file.
 
Change the length variable to change the number of input text, pattern size. 
All solutions are written in seperate functions. To execute each uncomment the correponding section.

Raw data contains excel file that has 3 sheets with empirical stuides of three questions.

                 ------------------------------------------------------------------------

Folders -

Raw Data - Inputs used for testing all the algorithms

Java Code - Code used for the algos

Graphs - Graphs with different parameters to measure the efficiency of the algorithm

Report - Detailed information about graphs and observations.

                 ------------------------------------------------------------------------

Implemented three string matching algorithms: naïve, KML and BM and compared their performance over artificial and natural strings. For all runs implemented the “all matches” version where the code goes to the end of the larger string.

1) Implemented the three string-match algorithms and tested them on some simple problems to verify they work correctly by returning the same answers. 

2) Download the text file of the complete works of Shakespeare and some DNA sequences from the links listed below.

3) Written an artificial string problem generator that can generate a string of a given length from the alphabet {0, 1}, with a specific regularity. Used the following Markov decision process to create a string, given p (the probability of generating the same character again).
Note that this technique can be generalized to larger vocabularies by adding more states (nodes) and modifying the probability of the edges that change the character output. 
In all graphs the sum of the leaving edges must be 1.0.

4) Designed experiments to answer a series of questions concerning algorithm performance. First defined three questions that you are interested in answering, 
    for instance “How does the regularity of the string affect the performance of the algorithms for large string sizes?” or “How does the alphabet size affect performance for the algorithms on natural strings (e.g., DNA and Shakespeare).”

5) Designed experiments to answer them. Where the dependent variable is cpu time, used a log-log plot to display the results and a doubling scheme to increase problem sizes. The graph is a result of at least 10 distinct runs.


Complete works of Shakespeare:

http://www.gutenberg.org/files/100/ (Links to an external site.)

Example DNA sequences: (chromosome 1, from position 1 to position 248,956,422)

http://www.ncbi.nlm.nih.gov/mapview/seq_reg.cgi?taxid=9606&chr=1&from=1&to=248956422

Some lecture notes that provide pseudo code for the algorithms:

http://www.cs.uku.fi/~kilpelai/BSA05/lectures/slides03.pdf (Links to an external site.)

http://cs.indstate.edu/~kmandumula/presentation.pdf (Links to an external site.)
