# ![cherry-logo](https://raw.githubusercontent.com/codereligion/cherry/master/small-cherry.png) Collect [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codereligion/codereligion-cherry-collect/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.codereligion/codereligion-cherry-collect) [![Build Status](https://ssl.webpack.de/secure-jenkins.codereligion.com/buildStatus/icon?job=codereligion-cherry-collect-master-build-flow)](http://jenkins.codereligion.com/view/codereligion-cherry-collect/job/codereligion-cherry-collect-master-build-flow/) [![SonarQube Coverage](https://img.shields.io/sonar/http/sonar.codereligion.com/com.codereligion:codereligion-cherry-collect/coverage.svg?style=plastic)](http://sonar.codereligion.com/dashboard/index/284)

This library is part of the [cherry project](https://github.com/codereligion/cherry), it contains utility classes for working with iterables and maps and their respective extensions and implementations. 

## Context of this library
It should be seen as an extension to existing libraries like the [apache commons collections](http://commons.apache.org/proper/commons-collections/) and [google guava](https://code.google.com/p/guava-libraries/). It is no way meant to replace any of the above libraries. In fact it uses guava interfaces like ```Function``` and ```Predicate``` in its public API. The current main responsibility of this library is to provide fast and convenient factory methods for common iterable and map classes which always takes an iterable as input and converts it to the target iterable or map by applying functions and predicates.

The initial trigger to create this library was the [performance problems and limitations](https://github.com/codereligion/cherry-collect#faq) when using ```Collections2``` from guava and it was open sourced because even after guava released the ```FluentIteable``` class we found that in performance critical environments like android using to much abstraction (e.g. fluent builders) can [slow down](https://github.com/codereligion/cherry-collect#performance-improvements) simple operations like filtering a list to a set considerably.

## Features
* fast filtering and transformation using guavas ```Predicate``` and ```Function``` interfaces or all commonly used iterable implementations
 * no "life views"
 * no unnecessary abstraction
 * optimized iteration for array lists (not using iterator)
* transformation can also be used to convert iterables to maps and guava multi maps, supported operations are:
 * using a key function to transform the value and map it to its original object
 * using a key and a value function to transform the object into two different objects to create the mapping
 * filtering the input iterable first and then apply either the first or second operation from above
* currently supported implementations
 * iterable: ```ArrayList```, ```LinkedList```, ```HashSet```, ```TreeSet```, ```ImmutableList```, ```ImmutableSet```, ```ImmutableSortedSet```
 * map: ```HashMap```, ```LinkedHashMap```, ```TreeMap```, ```ImmutableMap```, ```ImmutableSortedMap```
 * multi map: ```HashMultimap```, ```ArrayListMultimap```

## Usage
Assuming you want to filter an arbitrary iterable implementation and the result should be an ```ArrayList```, then you would do:
```java
Predicate<String> predicate = Predicates.alwaysTrue();
List<String> result = ArrayLists.createFrom(inputIterable, predicate);
```

alternatively you can also transform:
```java
Function<Object, String> function = Functions.toStringFunction();
List<String> result = ArrayLists.createFrom(inputIterable, function);
```

or do both:
```java
Predicate<Object> predicate = Predicates.alwaysTrue();
Function<Object, String> function = Functions.toStringFunction();
List<String> result = ArrayLists.createFrom(inputIterable, predicate, function);
```

For maps and multi maps the usage pattern is similar.

mapping a transformed version of the object to itself:
```java
Iterable<Integer> iterable = Lists.newArrayList(1, 2);
Function<Integer, String> keyFunction = toStringFunction();
Map<String, Integer> result = HashMaps.createFrom(iterable, keyFunction);
```
This would create a map like this:
```
"1" => 1
"2" => 2
```

mapping one transformed version of the object to another transformed version:
```java
Iterable<Integer> iterable = Lists.newArrayList(1, 2);
Function<Integer, String> keyFunction = toStringFunction();
Function<Integer, Integer> valueFunction = minusOneFunction();
Map<String, Integer> result = HashMaps.createFrom(iterable, keyFunction, valueFunction);
```
This would create a map like this:
```
"1" => 0
"2" => 1
```

Both operations described above can also be combined with a predicate, which would filter the incoming iterable first before transforming the objects.

filter and apply key function:
```java
Iterable<Integer> iterable = Lists.newArrayList(1, 2);
Predicate<Integer> predicate = Predicates.alwaysTrue();
Function<Integer, String> keyFunction = toStringFunction();
Map<String, Integer> result = HashMaps.createFrom(iterable, predicate, keyFunction);
```

filter and apply key and value function
```java
Iterable<Integer> iterable = Lists.newArrayList(1, 2);
Predicate<Integer> predicate = Predicates.alwaysTrue();
Function<Integer, String> keyFunction = toStringFunction();
Function<Integer, String> valueFunction = toStringFunction();
Map<String, String> result = HashMaps.createFrom(iterable, predicate, keyFunction, valueFunction);
```

## Performance improvements
The following table shows average improvements of functions of this library to comparable functions provided by guava. The source for the benchmark can be found [here](https://github.com/codereligion/cherry-collect-benchmark). The environments under which the benchmarks were conducted are: 
* MacBook Pro, Intel Core i7 2,8GHz CPU, 256KB L2 Cache, 6MB L3 Cache, OS X 10.9.5, Darwin 13.4.0, Oracle Java 64Bit 1.7.0_75
* OnePlus One, Qualcomm Snapdragon 801 2.5GHz Quad-core CPU, Android 4.4.4

Measuring was done on iterables holding 1.000.000 elements. Each operation was repeated 40 times and the fastest operation was considered in order to not let arbitrary garbage collections falsify the result. The actual implementations of ```Predicate```s and ```Function```s where chosen to be as efficient as possible to keep noise at a minimum. So for filter operations an "always true" filter was used and for transformations a "return input" functions was used.

| input type | operation          | output type | avg. improvement |
|------------|--------------------|-------------|------------------|
| ArrayList  | filter             | ArrayList   | 180%             |
| LinkedList | filter             | ArrayList   | 80%              |
| HashSet    | filter             | ArrayList   | 80%              |
| ArrayList  | transform          | ArrayList   | 60%              |
| LinkedList | transform          | ArrayList   | 35%              |
| HashSet    | transform          | ArrayList   | 28%              |
| ArrayList  | filter + transform | ArrayList   | 200%             |
| LinkedList | filter + transform | ArrayList   | 100%             |
| HashSet    | filter + transform | ArrayList   | 125%             |

## FAQ

### Why not using ```Collections2``` instead of this library?
The ```Collections2.filter``` and ```Collections2.transform``` methods return "live views". This means any operation on such a view causes the underlying collection to be filtered and/or transformed. The usual fix to this problem is to directly copy the result into a new collection.


```java
// bad: each call to size causes the filter (predicate) to be applied to all elements, which means the time complexity is O(n)
Collections2.filter(input, predicate).size();

// better: since this is a copy of the live view, the size call has the complexity of the implementation the elements where copied into, meaning O(1)
new ArrayList<String>(Collections2.filter(input, predicate)).size();

// best: this just iterates once over the input applying the given predicate to create a new collection
ArrayLists.createFrom(input, predicate).size();
```

In any case where you really do not need a live view, you should consider using other ways to filter/transform you collections.

Aside from the above mentioned performance problems caused by the life views, ```Collections2``` really just returns collections, which can be a little inconvenient when you want to use list features. There is no ```Lists.filter``` method and the ```Lists.transform``` method only takes a list as input.

### Why not using ```FluentIterable``` instead of this library?
```FluentIterable``` aside from its fluent interface is a major improvement to the initial methods provided by ```Collections2```, ```Lists```, ```Maps``` and ```Iterables```, however there are some problems:

1. The usage of builders creates additional objects
2. ```FluentIterable.to*``` methods always return immutable objects and the usage of ```FluentIterable.copyInto(Collection)``` seems a little clumsy
3. ```FluentIterable``` uses ```Iterables``` for iterating when filtering and transforming, which always uses an iterator of which the creation might be expensive

Under normal circumstances these problems may not seem to be to big, but in performance critical environments you might want reconsider the usage of the ```FluentIterable``` and replace those performance critical parts with more efficient code.

## Requirements
* Java 1.5 or higher
* dependencies see [maven pom](pom.xml)

## Attributions
The cherry icon originated from www.vector4free.com and is distributed under the [Creative Commons 4.0 License](http://creativecommons.org/licenses/by/4.0/)
