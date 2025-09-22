Task 1: Java Backend and REST API Example
Overview

This project implements a Java backend application providing a REST API to manage Task objects. Each Task represents a shell command that can be executed.
The application uses Spring Boot and stores Task objects in MongoDB.

Task Object

Each Task has the following properties:

id: Task ID (String)

name: Task name (String)

owner: Task owner (String)

command: Shell command to execute (String)

taskExecutions: List of executions

Each TaskExecution has:

startTime: Execution start date/time

endTime: Execution end date/time

output: Command output

API Endpoints
1. Create Task
Invoke-WebRequest -Uri "http://localhost:8080/api/tasks" -Method PUT -Headers @{ "Content-Type" = "application/json" } -Body '{"id":"t1","name":"hello","owner":"Nitu","command":"echo Hello Kaiburr"}'

2. Execute Task
(Invoke-WebRequest -Uri "http://localhost:8080/api/tasks/t1/execute" -Method PUT).Content

Note: If execution fails with 500 Internal Server Error, check your Spring Boot application logs.

3. List All Tasks
Invoke-WebRequest -Uri "http://localhost:8080/api/tasks" -Method GET


4. Search Task by Name
Invoke-WebRequest -Uri "http://localhost:8080/api/tasks?name=hello" -Method GET


5. Delete Task
Invoke-WebRequest -Uri "http://localhost:8080/api/tasks/t1" -Method DELETE

Git Commands Used:-

All code and screenshots are pushed to GitHub using:

git init
git add screenshots/
git commit -m "Add screenshots for Task 1"
git remote add origin <your-github-repo-url>
git branch -M main
git push -u origin main

All screenshots are available in the screenshots folder
