Goals
 * Fast startup - each unit test case should be able to start and stop cleanly in an amount of time that can be measured in milliseconds
 * Simple DSL - no weird exception catching, just a simple way to declare what should happen and finding out if it did
 * Expectations - ability to define what a client should do and ensuring it happened
 * Stubbing - ability to define what the server should respond with given requests that match a certain matcher

Design
 * Netty backend - fast and powerful
 * Populate a journal of requests and check journal against matchers
 * Populate a set of response/matcher pairs, first matcher that matches gets to formulate the response

Contact:

justin.m.boyd82@gmail.com
