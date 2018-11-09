package com.neusoft

class Person {
  private var _age = 2
  def age_=(num: Int) = this._age = num
  def age = _age
  def printObj { println(s"I can see ${Person.obj}") }
}
object Person {
  // access the private class field 'age'
  def double(p: Person) = p._age * 2

  private val obj = "Person's object"
  print()
}
object Driver extends App {
  //伴生对象可以引用类的私有变量
  val p = new Person
  println(p.age)
  p.age = 10
  println(Person.double(p)) // prints 20

  //类可以引用伴生对象的私有变量
  p.printObj    //prints I can see Person's object
}