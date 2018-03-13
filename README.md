[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/LesiaMiliutina.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/LesiaMiliutina)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/LesiaMiliutina/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/LesiaMiliutina)

# LesiaMiliutina

The project is intended for using by HR specialists.

1. Check base settings  
    
    $java -version 
    $ echo $JAVA_HOME (verification) 
    $export JAVA_HOME = ...
    $mvn -version
    
2. Build with Maven (https://maven.apache.org/)
    
    $mvn clean install
    
3. Preparing reports
  
    $mvn site  
    $mvn site:stage  
    check: <project>/target/stage/index.html

4. Travis CI integration https://travis-ci.org/Brest-Java-Course-2018/LesiaMiliutina/
