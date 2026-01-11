/*
to push to github in terminal:

1. cd into the folder
2. run 'git status'
3. run 'git checkout changes'   -> switches branches to 'changes' branch
4. run 'git add .'
5. run 'git commit -m "message to github here" -m "this is a new line"'
6. run 'git push origin changes'
7. check I'm on the '*main' branch using 'git branch'. If not, run 'git checkout main'
8. git pull origin 'changes'
*/

//TO RUN TESTS FROM TERMINAL RUN THE FOLLOWING:
//1. cd to RevTacToe
//2. run 'mvn test'

//TO RUN FROM TERMINAL RUN THE FOLLOWING:
//mvn clean compile
//mvn exec:java


public class Main {
    public static void main(String[] args) {
        new myFrame();
    }
}