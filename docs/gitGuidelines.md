# Common Playground - Software Requirements Specification 

## Table of contents
- [Table of contents](#table-of-contents)
- [Commit Guidelines](#1-commit-guidelines)
    - [Commit size](#11-commit-size)
    - [Commit timing](#12-commit-timing)
    - [Testing](#13-testing)
    - [Commit Messages](#14-commit-messages)
    - [Branches](#15-branches)
- [Git Workflow](#2-git-workflow)

## 1. Commit Guidelines

### 1.1 Commit size
A commit is a wrapper for related changes regarding one task. So commits should be as small as possible and as big as necessary.

### 1.2 Commit timing
Only commit if a task is completed.
  
### 1.3 Testing
Test before committing.

### 1.4 Commit Messages
Commit messages should describe the changes clearly. Furthermore it should be visible to which task a commit belongs to.

### 1.5 Branches and Pull Requests
Each new feature is developed on his own branch. For merging this feature Branch back into the development branch you create a Pull Request. Another developer checks the changes and then accepts or dismisses them with a comment.
Details about our workflow are described in the next chapter.
    
## 2. Git Workflow
![Git Workflow](https://blog.seibert-media.net/wp-content/uploads/2014/03/Gitflow-Workflow-3.png)
Our git workflow can be seen in the picture above.
There are two branches permanently:
* master:  
This Branch always represents a working and deployable state of the project.
* dev:  
This branch is for development. From this branch all feature Branches diverge.

Other branches:
* Feature Branches:  
Each feature is implemented on his own branch. After finishing the development of the festure the feature Branch is merged into the dev Branch.
* Release Branch:  
For releasing the current state of our Application a new branch is diverged from dev. After testing the release branch is merged into the master and the dev branch.