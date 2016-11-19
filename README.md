# logs-analysis
This program scans the folder for records, sorts them according to the user criteria and writes the result in output file.

Record - a line that is in the txt file. Each line has three fields:
1. User Name (example - Anastasia
2. The date in the format  d-MM-yyyy (for example - 25/02/2016)
3. Message
The delimiter is the character - '|';
Sample lines:
Mama|20-03-2015|How are you
Anastasia|25-02-2016|Hello man

After starting the program the user will see the menu:
1. Find the record by the name of the user.
2. Find the records by the period of time.
3. Find records by the message template.
4. Search of the records by several criteria.
5. Exit

The user must select one of the items and with the help of entering numbers.

After selecting the item, enter the required data. Enter the data you need in the same format as the original data in records (date must be in format  d-MM-yyyy (for example - 25/02/2016))
 At the end you need to fill in three essential components:
1. The path to the input file (example - /Users/user/input/)
2. The path to the output file (example - /Users/user/output/)
3. File Name (example - records)

To start you need to have a Java 8 on a computer.
Instructions:
1. Open the console
2. Enter:
java -cp /yourPathToFolderWithFiles/target/analysis-1.0-SNAPSHOT.jar Main