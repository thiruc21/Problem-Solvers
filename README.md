# L02_05
This is the repository for Problem Solvers. We have each introduced ourselves and agreed on our team's guidelines. We are on version 4 of our product backlog which consists of personas that represent future users of our software, user stories that reflect their user requirements, and tasks to implement the requirements of a userstory. We have completed six sprints and have their sprint backlogs below. We have also had a code review session of which you can view the results for below.

**Features implemented:** 

**1:** Creation of multiple choice questions of 2-6 possible options.

**2:** Assigning questions to an assignment.   

**3:** Menu to be able to select what tool the user wants to access.

**4:** Viewing assignment

**5:** Answering questions in an assignment 

**6:** Submitting assignment and receiving grade immediately

**7.** Logging in as a student user or an admin with only role specific features available.

**8.** Adding assignments

**How to Run:** 

**Windows**

Using Windows with JDK 1.7 or later installed with the environment path to the JDK already set.

1. Navigate to L02_05/Quizzer/src via a windows command-line interface.
2. Type "javac application/Login.java". If you receive " javac: command not found", your environment path to the JDK is probably not set correctly.
3. Type "java -classpath ".;../lib/sqlite-jdbc-3.20.0.jar" application/Login".
4. You will be shown the login menu but cannot login or signup until you click "Setup" to initialize your database.

Alternatively, you can run L02_05/Quizzer/run.bat.

**Linux**

1. Navigate to L02_05/Quizzer
2. Run the shellscript startup using the command ./startup.sh (root privledge not required)   
3. You will be shown the login menu but cannot login or signup until you click "Setup" to initialize your database.

**Logging in**
1. Default credentials to login as admin are username: admin, password: admin
2. Default credentials to login as student are username: student, password:student

**Testing**

1. Open eclipse and set the workspace to L02_05
2. Right click the package explorer and click New.. -> Java Project 
3. Type in "Quizzer" in the project name field then click "Finish".
4. Right click "Quizzer" in the package explorer  and click Build Path -> Configure Build Path.. 
5. Click Libraries which is one of the tabs at the top then click the "Add External JARs.." button
6. Add all jar files from L02_05/Quizzer/lib then click "OK".
7. Right click the "test" folder, click Build Path -> Use as source folder
8. To run both unittests and validation tests:
right click the "test" folder, click Run As -> JUnit Test

    To run only unittests:
 right click the package "backend", click Run As -> JUnit Test

    To run only validation tests
right click the package "application_validation", click Run As -> JUnit Test

Alternatively, on windows you can run L02_05/Quizzer/alltests.bat to run all tests.

**Log:**

[Team Introduction & Expectations Agreement](https://www.github.com/CSCC01F17/L02_05/blob/master/deliverables/week4/deliverable_1.pdf) - Here we introduce each of our team members and formulate the team's guidelines.

[Personas](https://www.github.com/CSCC01F17/L02_05/blob/master/deliverables/week12/Personas_v4.pdf) & [User Stories](https://www.github.com/CSCC01F17/L02_05/blob/master/deliverables/week12/UserStories_v4.pdf) - Here we create fictional users of our system, and how they will use our application. 

[Tasks](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week12/Tasks_v4.pdf) - Here we show how we decomposed some of the userstories into tasks. We also listed the expected number of story points to complete them and their dependencies with other tasks.

[Sprint Backlog 1](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week9/Sprint1_Backlog.pdf) , [2](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week9/Sprint2_Backlog.pdf) , [3](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week9/Sprint3_Backlog.pdf) , [4](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week11/Sprint4_Backlog.pdf) , [5](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week11/Sprint5_Backlog.pdf),[ 6](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week12/Sprint6_Backlog.pdf) - Here we show each of our sprint's backlog, consisting of the sprint plan, report, and provisional vs actual burndown chart.

[Code Review](https://github.com/CSCC01F17/L02_05/blob/master/deliverables/week11/CodeReview.pdf) - Here we show our code review strategy, code review summary, and code review debriefing.

11/27/2017 
-added deliverable/week12 for product backlog v4, and sprint backlogs for sprint 3-6.

11/20/2017 
-added deliverable/week11 for product backlog v3, sprint backlogs for sprint 4 and sprint 5, and code review.

11/06/2017 
-added deliverable/week9 for product backlog v2, and sprint backlogs for sprint 2 and sprint 3. updated sprint 1's backlog with sprint report and actual burndwon graph.

23/10/2017
-added deliverable/week7 for product backlog v1(consisting of personas, user stories, and tasks), and sprint backlog of the first sprint.

16/10/2017
-added deliverable/week6 for product backlog v0, consisting of personas and user stories.

25/09/2017
-added first deliverable with team introduction and expectations.