# Common Playground - Git Guidelines 

## Table of contents
- [Table of contents](#table-of-contents)
- [Commit Guidelines](#1-commit-guidelines)
    - [Commit size](#11-commit-size)
    - [Commit timing](#12-commit-timing)
    - [Testing](#13-testing)
    - [Commit Messages](#14-commit-messages)
    - [Files naming convention](#15-files-naming-convention)
- [Branches and Pull Requests](#1-branches-and-pull-requests)
    - [Usage](#11-usage)
    - [Branches naming convention](#12-branches-naming-convention)
- [Git Workflow](#2-git-workflow)

## 1. Commit Guidelines

### 1.1 Commit size
A commit is a wrapper for related changes regarding one task. So commits should be as small as possible and as big as necessary.

### 1.2 Commit timing
Only commit if a task is completed. Completed also includes successful testing (the new test cases for this task are green) and successful regression testing (all former test cases should be green). Don´t commit half done work.
  
### 1.3 Testing
Test before committing.

### 1.4 Commit Messages
Commit messages should describe the changes clearly. Furthermore it should be visible to which task a commit belongs to.

### 1.5. Files naming convention
Each file and directory in our repo should have a self-explanatory name. 
Furthermore there should be no blanks in a file or directory name.

## 2. Branches and Pull Requests

### 2.1. Usage
Each new feature is developed on it´s own branch. For merging this feature branch back into the development branch you create a Pull Request. Another developer checks the changes and then accepts or dismisses them with a comment.
Details about our workflow are described in the next chapter.

### 2.2. Branches naming convention
Each branch for the development of features is named with feature_branchname.
Each branch for the development of other tasks (e.g. project management tasks) is named with task_branchname. 
    
## 3. Git Workflow
![Git Workflow](https://blog.seibert-media.net/wp-content/uploads/2014/03/Gitflow-Workflow-3.png)  
Our git workflow can be seen in the picture above.
There are two branches permanently:
* master:  
This Branch always represents a working and deployable state of the project.
* dev:  
This branch is for development. From this branch all feature and task branches diverge and will be merged into this branch.

Other branches:
* Feature Branches:  
Each feature is implemented on his own branch. After finishing the development of the festure the feature Branch is merged into the dev Branch.
* Release Branch:  
For releasing the current state of our Application a new branch is diverged from dev. After testing the release branch is merged into the master and the dev branch.    

Further information about git can be found here: https://git-scm.com/book/en/v2