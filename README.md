## Abhinav Srivastava

### Programming Assignment 1 / Lab 2

- Describe any design decisions you made. These may be minimal for pa1.
- Discuss and justify any changes you made to the API.
- Describe any missing or incomplete elements of your code.
- Describe how long you spent on the assignment, and whether there was anything you found particularly difficult or confusing.
- If you collaborate with someone else, you have to discribe how you split the workload.

## Design Decisions

The approach was to first read the documents, read the tests and reverse engineer a few ideas from there. I also used UML diagrams to grok the architecture of the codebase - and from there decided to start with TupleDesc and work my way up the HeapFile. I did previously read a few implementations of the problem, so it was pretty easy to get started.

## Changes to the API

I made one significant change to the API which was use a new class called `catalogItem` to store `tableName, tableFile, tablepKey` - some housekeeping on the code. I read about this previously and thought it was neat.

## Currently missing

It seems the heapPage constructor is not giving a valid response in some cases, which results in two failed test cases: 

```shell
testIteratorClose and testIteratorBasic: 
Cannot invoke "simpledb.HeapPage.iterator()" because "heapPage" is null
```

## Time spent on assignment and the difficult parts

I spent about 2-3 days grokking the overall architecture and the requirements and reverse-engineering the tests. I also rewrote a simpleDB implementation from scratch without good code practice and very minimal features to understand what’s going on. The most difficult part for me was understanding heapFiles/heapPage and correctly getting the bufferPool to work as expected. Once that worked, I could get past the other (much simpler) implementations. It was mostly a mental hurdle. It was not till very late (about 3 hours before submission!) that I stumbled upon exercise 2.7 - when I tried to give it a go, I ran into a bunch of errors (that is suspect is relating to my earlier issues with iterators). I got the following error `Error: method HeapFile.iterator(TransactionId tid) not yet implemented!` - despite the fact that iterator is implemented. Seems `table.iterator(tid)` is returning null. However it converted with no problem!

## Instructions

```
ant runtest -Dtest=TupleTest
ant runtest -Dtest=TupleDescTest
ant runtest -Dtest=CatalogTest
ant runtest -Dtest=HeapPageIdTest
ant runtest -Dtest=RecordIdTest
ant runtest -Dtest=HeapFileReadTest
ant runtest -Dtest=HeapPageReadTest
```

CatalogTest.java        HeapPageIdTest.java     RecordIdTest.java       TupleDescTest.java      systemtest
HeapFileReadTest.java   HeapPageReadTest.java   TestUtil.java           TupleTest.java