<h1 align="center">RedSet</h1>

RedSet creates a beneficial environment for learners that includes topic wise problems with solution, notes section where user can keep notes or templates, notify about running and upcoming contest of groups where user is joined, leaderboard and activity, and the exclusive feature <b>LatticeLine</b>, that includes compiler, groups feature (Individual can create or join groups. The teachers of every group can make announce, assignment - like Google Classroom and contest - like an Online Judge.)

## Configuration

You can run the project in your environment through any IDE by cloning the project. Run `git clone https://github.com/Tamal267/RedSet` in your terminal.
Before run, delete `RedSet/target` if found and create a database named `lattice` in your localhost mysql server. Run `CREATE DATABASE lattice;`. Then run the given sql script for the database `lattice`.

```mysql
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE TABLE `announce` (
  `text` text,
  `date` varchar(50) DEFAULT NULL,
  `gp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `assignment` (
  `group_name` varchar(30) DEFAULT NULL,
  `text` text,
  `code` text,
  `input` longtext,
  `assignId` varchar(30) NOT NULL,
  `users` text,
  `timeLimit` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `conProb` (
  `problemid` varchar(30) NOT NULL,
  `statement` text,
  `code` text,
  `input` longtext,
  `users` text,
  `timeLimit` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contest` (
  `contestName` varchar(30) NOT NULL,
  `startTime` varchar(30) DEFAULT NULL,
  `duration` varchar(30) DEFAULT NULL,
  `problemsIds` text,
  `groupName` varchar(30) DEFAULT NULL,
  `ranking` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `gp` (
  `name` varchar(30) NOT NULL,
  `stdents` varchar(1000) DEFAULT NULL,
  `teachers` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `notes` (
  `title` text,
  `note` longtext,
  `user` varchar(30) DEFAULT NULL,
  `date` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `problems` (
  `problemid` varchar(50) NOT NULL,
  `statement` text,
  `code` text,
  `input` longtext,
  `users` text,
  `timeLimit` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `studyProblems` (
  `problemid` varchar(30) NOT NULL,
  `statement` text,
  `timelimit` varchar(5) DEFAULT NULL,
  `code` text,
  `input` longtext,
  `users` text,
  `solution` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `studyTopic` (
  `topicName` varchar(30) NOT NULL,
  `problemids` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `connect` text,
  `fullName` varchar(30) DEFAULT NULL,
  `studentId` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `institute` varchar(30) DEFAULT NULL,
  `time` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


ALTER TABLE `assignment`
  ADD UNIQUE KEY `assignId` (`assignId`);

ALTER TABLE `conProb`
  ADD PRIMARY KEY (`problemid`);

ALTER TABLE `contest`
  ADD PRIMARY KEY (`contestName`);

ALTER TABLE `gp`
  ADD PRIMARY KEY (`name`);

ALTER TABLE `problems`
  ADD PRIMARY KEY (`problemid`);

ALTER TABLE `studyProblems`
  ADD PRIMARY KEY (`problemid`);

ALTER TABLE `studyTopic`
  ADD PRIMARY KEY (`topicName`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
```

Then change username and passowrd in `RedSet/src/main/java/com/example/RedSet/DBconnect.java`.

## Dashboard

![Dasboard](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/dashboard.png?raw=true)

Dashboard gives a vivid idea of what can be done in RedSet. It gives important information to the users about how long he is in the application through graphical representation. Moreover, it also shows the number of different types of problems through a pie-chart. Besides it also provides features such as, 

### Notes: 
![notes](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/notes.png?raw=true)

  It is possible to keep necessary codes,templates and notes inside this section.

 ![addnotes](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/addnotes.png?raw=true)

A user can add/update and delete his desired template/code or notes.

### Study:
![studyproblems](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/studyproblems.png?raw=true)

This section gives the user a chance to challenge his current capability by solving various types of  problems.

![problemsofaparticulartopic](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/problemsofaparticulartopic.png?raw=true)

contains problems of a certain topic.

![solve](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/solve.png?raw=true)

User can attempt a problem and try solving it. If the solution provided by the user is correct, the judge will mark it accepted including required time.

![editorial](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/editorial.png?raw=true)

User can read editorials or get the sample accepted solution if he fails to solve the problem.

### Leaderboard:

![leaderboard](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/leaderboard.png?raw=true)

Shows the user his current standing on the basis of the amount of time spent in the application.

### Latticeline:

An exclusive part of RedSet which is discussed below:

  
### Features of Latticeline

* Solving problem from problem section
* Users can create group or join an existing group
* Teachers of a group can create assignments, announcements and contests
* Assignment of a student will accept when all the testcases for this assignment is passed, like an online judge
* Teachers can see the status of every assignment
* Users can see real-time ranking of a contest
* All the submissions of a contest will visible after the contest end
* Contests and assignments are editable/updatable
* Teachers can add a user as teacher from the group

### Problems Section

![problem_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/Problems1.png?raw=true)

It is global problem section. Any user can solve problem here. These problems are added by RedSet.

### Compiler Section
![compiler_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/Compiler1.png?raw=true)

It is a simple editor with highlighting syntax of C++ code feature that shows output including time and memory complexity. You can choose a file to compile from choose button. Only C++ is available right now.

### Groups Section
![group_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/Groups1.png?raw=true)
![group_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/CreateGrp.png?raw=true)
![group_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/JoinGrp.png?raw=true)

In the group section user can see the connected groups. User can create a group as a teacher or join a group as a student by clicking Create & Join button showing in the bottom section. While creating a group, the name of that group should be unique.

![eachgroup_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/EachGroup.png?raw=true)

Create Assignmente, like Google Classroom. Assignments are showing in each of the boxes.

![crtassign_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/CrtAssign.png?raw=true)

Only Teachers can create assignment.

![assign_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/Assign.png?raw=true)
![assign_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/EditAssign.png?raw=true)
![assign_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/ShowInputs.png?raw=true)

An assignment. Previous accepted solution remains visible with time & space limit. Only teachers can see status and edit the Assignment. Teachers can see inputs and update inputs.

![maketeacher](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/maketeacher.png?raw=true)

A teacher can promote a student as a teacher.

![createannounce](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/createannounce.png?raw=true)

Teachers can make necessary announcements .

![viewannounce](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/viewannounce.png?raw=true)

Users can view the announcements that were made up until the teacher doesn't delete it.

![contest_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/Contest.png?raw=true)

Students can enter to the contest if the contest is running or ended state. Teachers can enter and edit/update the contest anytime.
![contest_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/ContestPrbs.png?raw=true)

You can see a countdown at the bottom inside a contest page.

![contest_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/ContestPrb.png?raw=true)
![contest_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/Status.png?raw=true)

Students can only see the status after the end time of a contest. But teacher can see the status anytime.

![ranking_section](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/RedSet/Lattice/icons/Ranking.png?raw=true)

Ranking of every contest. 20 penalty will increase for a wrong submission.

![profile](https://github.com/Tamal267/RedSet/blob/main/src/main/resources/com/example/PIC/readmepic/editprofile.png?raw=true)

User can view his current given information and edit them anytime.

