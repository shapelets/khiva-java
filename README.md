# Khiva-Java

[![License: MPL 2.0](https://img.shields.io/badge/License-MPL%202.0-brightgreen.svg)](https://github.com/shapelets/khiva-java/blob/master/LICENSE.txt)  

| Build Linux and Mac OS                                                                                                               |  Build Windows                                                                                                                                                              | Code Coverage                                                                                                                                            |
|:------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------:|
| [![Build Status](https://travis-ci.org/shapelets/khiva-java.svg?branch=master)](https://travis-ci.org/shapelets/khiva-java/branches) | [![Build status](https://ci.appveyor.com/api/projects/status/qm5swdme1fb99d5m/branch/master?svg=true)](https://ci.appveyor.com/project/shapelets/khiva-java/branch/master)  |[![Coverage Status](https://codecov.io/gh/shapelets/khiva-java/branch/master/graph/badge.svg)](https://codecov.io/gh/shapelets/khiva-java/branch/master)  |

# README #
This is the Khiva binding for Java, it allows the usage of Khiva library from Java.

## License
This project is licensed under [MPL-v2](https://www.mozilla.org/en-US/MPL/2.0/).
 
## Quick Summary
This Java package called 'khiva' provides all the functionalities of the Khiva library for time series analytics.

## Requirements
* Arrayfire
* Khiva library

## Set up

It is just needed to execute the next command in the root directory of the project:
```bash
mvn install
```
## Executing the tests:
Execute the next command in the root directory of the project:
```bash
 mvn test
```
 
Note: The tests are executed automatically when the package is installed.

## Documentation
This Java package follows the standard way of writing documentation of Java code using Javadoc.

In order to generate the documentation, execute the next command in the root directory of the project: 
```bash
mvn javadoc:javadoc
```

## Contributing
The rules to contribute to this project are described [here](CONTRIBUTING.md)

[![Powered by Shapelets](https://img.shields.io/badge/powered%20by-Shapelets-orange.svg?style=flat&colorA=E1523D&colorB=007D8A)](https://shapelets.io)