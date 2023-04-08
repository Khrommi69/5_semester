'use strict';
class HorribleTestData
{
 constructor(x)
 {
 this.x = x;
 }
 isFirstQuestionAnswer()
 {
 return /^[\w]+(\s+[\w]+){24}$/gmu.test(this.x);
 }
}