# Coin Collecting Robot
It is the implementation for the modified version of coin collecting robot problem with dynamic programming approach. It is assumed that each cell has an integer between -5 and 5. If the value of a cell is positive, then the robot gains that amount of coins when it visits that cell. If the value of a cell is negative and the robot has enough coins, then it can pay that amount and visit the cell. Cells that are marked with an 'X' are unreachable. Aim is to have the maximum number of coins when the robot reaches to the end of the board.<br/>
There is a certain format that the input file should have and you can check it from the test input file which is in the test folder. As the output, total number of coins that is collected by the robot, and the path it follows to get that amount is written to a file.
## How to Use It?
* Clone the repo.
* Run 'javac CoinCollectingRobot.java' command.
* Run 'java CoinCollectingRobot *input-file-name*' command.
* See the results which is in the 'output.dat'.
