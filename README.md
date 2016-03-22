# gogo message properties synchronizer

A tool for synchronizing message.message properties files.

# Introduction

When working with multiple developers on a software product that supports multiple languages,
synchronizing translations in several message properties files can be tedious.
Choose one message properties file as source (for example the file containing the English messages) and use this
tool to synchronize this file with the properties files for the other languages.

The tool compares the source file with another properties file, the destination file. The destination file wil be
updated so that it contains the same properties as the source file. Messages that were already present in 
the destination are not changed. New messages are prefixed with a todo message so that translates can easily
identify lines to be translated. Comments can be translated as long as the number of comment lines does not change.

# Build instructions

After cloning this project type `gradlew install` from the command prompt in the directory containing the file
build.gradle. After a couple of seconds build/libs/gogomessagepropertiessynchronizer-1.0.jar is created.

# Usage

```
usage: java -jar gogomessagepropertiessynchronizer-1.0.jar -d <destination
       file> [-h] -s <source file> [-t <arg>]
 -d,--destination <destination file>   destination message properties
 -h,--help                             prints this message
 -s,--source <source file>             source message properties
 -t,--todo <arg>                       todo message to be inserted in
                                       added lines
```
                                       
# Example

If messages.properties looks like this:

```
# Group 1
line=Line
square=Square
circle=Circle

# Group 2
red=Red
green=Green
blue=Blue
```

and messages_nl.properties looks like this:

```
# Groep 1
line=Lijn
circle=Cirkel

# Groep 2
green = Groen
blue = Blauw
```

Then after executing the command 
`java -jar gogomessagepropertiessynchronizer-1.0.jar -s messages.properties  -d messages_nl.properties`
message_nl.properties looks like this:

```
# Groep 1
line=Lijn
square=<TODO TRANSLATE>Square
circle=Cirkel

# Groep 2
red=<TODO TRANSLATE>Red
green = Groen
blue = Blauw
```