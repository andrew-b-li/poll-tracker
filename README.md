# poll-tracker

Description
Poll tracker is poll analysis program which allows for the grouping, sorting and aggregation of political poll data. The program in its current iteration works within the console and only preforms these manipulations on randomly generated poll data. Future versions will allow for the users to insert their own data and utilize the program using a graphical interface.

How to use
The program can be utilized by running the TextApplication java file. Make sure that this is done from an application package (folder) which contains the other four classes (Party, Poll, PollList, Factory). Once TextApplication is run, it will ask the user the following prompts, respond to them as instructed below:

1. How many seats are available in the election? 
Provide the total number of seats up for election within the poll(s) you wish to track.
2. Which parties are in the election (provide names, comma separated)?
Provide the name of all the parties which can be found in any of the polls (i.e includes party name even if they don’t appear in every poll). Enter the party names with comma separation but no spaces after commas (e.g party,party,party).
3. How many polls do you want to track with this application?
The number of polls which will be grouped and aggregated.
4. Would you like me to create a random set of polls? (Yes/No)
Current version of the program only allows for utilization of random poll data (so respond Yes).
5. How would you like me to visualize the data? (seat/vote)
Response determines the category by which the polls will be sorted.

A final prompt will ask if you wish to see the data from all the polls or simply the aggregate poll.

Enter quit in the console to exit the program.
