# ![cherry-logo](https://raw.githubusercontent.com/codereligion/cherry/master/small-cherry.png) Collect [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codereligion/codereligion-cherry-collect/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.codereligion/codereligion-cherry-collect) [![Build Status](https://ssl.webpack.de/secure-jenkins.codereligion.com/buildStatus/icon?job=codereligion-cherry-collect-master-build-flow)](http://jenkins.codereligion.com/view/codereligion-cherry-collect/job/codereligion-cherry-collect-master-build-flow/) [![SonarQube Coverage](https://img.shields.io/sonar/http/sonar.codereligion.com/com.codereligion:codereligion-cherry-collect/coverage.svg?style=plastic)](http://sonar.codereligion.com/dashboard/index/284)

This library is part of the [cherry project](https://github.com/codereligion/cherry), it contains utility classes for working with iterables and maps and their respective extensions and implementations. 

## Context of this library
It should be seen as an extension to existing libraries like the [apache commons collections](http://commons.apache.org/proper/commons-collections/) and [google guava](https://code.google.com/p/guava-libraries/). It is no way meant to replace any of the above libraries. In fact it uses guava interfaces like ```Function``` and ```Predicate``` in its public API. The current main responsibility of this library is to provide fast and convenient factory methods for common iterable and map classes which always takes an iterable as input and converts it to the target iterable or map by applying functions and predicates.

The initial trigger to create this library was the [performance problems and limitations](https://github.com/codereligion/cherry-collect/wiki/FAQ#why-not-using-collections2-instead-of-this-library) when using ```Collections2``` from guava and it was open sourced because even after guava released the ```FluentIteable``` class we found that in performance critical environments like android using to much abstraction (e.g. fluent builders) can [slow down](https://github.com/codereligion/cherry-collect/wiki/Performance-comparison-to-alternative-guava-classes) simple operations like filtering a list to a set considerably.

## Features
TODO

## Usage
TODO


## Requirements
* Java 1.5 or higher
* dependencies see [maven pom](pom.xml)

## Attributions
The cherry icon originated from www.vector4free.com and is distributed under the [Creative Commons 4.0 License](http://creativecommons.org/licenses/by/4.0/)
