# WdSE-TTT
TicTacToe for university exam in "Tools of Software Engeering". 
The goal is to write a reasonably good styled and testable code. There is no aim on performance or completeness.

This project should integrated in a self hosted build toolchain consisting of Subversion and Jenkins. 

*The code of TicTacToe is very overbloated. It is intended to be only exemplary code for this specific continuous integration (test-)setup.*

# Why?
Maybe this repository is helpful for future groups of the same course.

*For reference: It is the third task in "Praktikum: Werkzeuge des Software Engeneering".* 

# Setup
You can use the (ugly) setup scripts if you want to run svn and jenkins inside a freshly installed Ubuntu server.

**Clone repository:**
```
sudo apt install git
git clone https://github.com/Maze7/WdSE-TTT/
cd WdSE-TTT
```

**For SVN Setup**
```
sudo bash setup-svn.sh
```
**For Tomcat Setup**
```
sudo bash setup-jenkins.sh
```

# Jenkins

There are a lot of build targets inside ant build.xml.
Target `jenkins` is specially designed for this task and runs junit tests (with coverage through jacoco), generates a jar with zipped javadoc inside and run checkstyle checks. 

But there are dependencies on different jar. An ant task for downloading them into your ant home is available.

Run it with

```
ant download.depends
ant jenkins
```

Otherwise copy following jars into `$HOME/.ant/lib`

- junit.jar
- org.hamcrest.core.jar
- jacoco.jar
- checkstyle-all.jar 

# Support
If you run through this course (Werkzeugpraktikum) and stuck in connection with this implementation, you can contact me for further help
