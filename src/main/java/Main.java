//git status                                                                          -> checks status of git in my folder
//git checkout changes                                                                -> switches branches to 'changes' branch
//git add .                                                                           -> adds everything to my branch
//git commit -m "Add my-folder to the changes branch" -m "this is a new line"         -> commits everything to the branch and adds a message
//git push origin changes                                                             -> pushes 'changes' to GitHub

/*
to push to github in terminal:

1. cd into the folder
2. run 'git add .'
3. run 'git commit -m "message to github here"'
4. run 'git push origin changes'
5. check I'm on the '*main' branch using 'git branch'. If not, run 'git checkout main'
6. git pull origin 'changes'
*/

//to run the java file in terminal (from directory RevTacToe folder):
//DOES NOT WORK WITH INTELLIJ. ONLY VS CODE TERMINAL, POWERSHELL, AND CMD
//java -cp target\classes Main

public class Main {
    public static void main(String[] args) {
        new myFrame();
    }
}