package T8LocalState

import T8LocalState.Book.BankAccount.makeWithdraw
import language.CommonFunctions.gcd

import scala.annotation.tailrec
import scala.util.Random
type Function = Int => Int
object Book:
  object BankAccount:
    var balance = 100
    def withdraw(amount: Int): Int = {
      balance -= amount
      balance
    }
    def deposit(amount: Int): Int = {
      balance += amount
      balance
    }
    def makeWithdraw(initialBalance: Int): Int => Int =
      var balance = initialBalance
      amount =>
        balance -= amount
        balance

    def makeAccount(initialBalance: Int, password: String): (String, String) => Function | Unit =
      var balance = initialBalance
      var badPasswordCount = 0
      def withdraw(amount: Int): Int = {
      balance -= amount
      balance
      }
      def deposit(amount: Int): Int = {
        balance += amount
        balance
      }
      def callTheCops(): Unit = throw new RuntimeException("Calling the cops on you!!!")

      (message, givenPassword) =>
        if password == givenPassword then
          if message == "withdraw" then withdraw
          else if message == "deposit" then deposit
          else println("Don't know what that is")
        else {
          if badPasswordCount > 7 then callTheCops()
          else {
            badPasswordCount += 1
            println(s"Try again: you have ${7 - badPasswordCount} tries left")
          }
        }

    def testBankAccount(): Unit = {
      println(withdraw(25)) // 75
      println(withdraw(25)) // 50
      // ^^^ this a new behaviour, the same function returning different values
      // it actually is spooky and I can imagine that it would be hard to debug
      val withdrawer = makeWithdraw(100)
      val w2 = makeWithdraw(100)
      println(withdrawer(25))
      println(withdrawer(25))
      println(w2(25))
      println("Test account")
//      val account = makeAccount(100, "123")
//      println(account("withdraw", "123")(25))
//      println(account("deposit", "122")(10))

    }

  // exercise 3.1
  def makeAccumulator(init: Int): Int => Int =
    var acc = init
    def accumulate(amount: Int): Int = {
      acc += amount
      acc
    }
    accumulate

  // exercise 3.2
  // /*Make a function that encapsulates a local variable "calls" that keeps track of how many calls the underlying function has been called. */

//  def makeMonitored(f: Function): Function =
//    var count = 0
//    def callF(m: Int): Int = {
//      count += 1
//      f(m)
//    }
//
//    message =>
//      if message == "how many calls?" then count
//      else if message == "reset" then {
//        count = 0
//        count
//      }
//      else callF(message)

  private val random = Random()
  // exercise 3.5
  def estimatePi(trials: Int): Double =
    def cesaroTest: Boolean =
      gcd(random.nextInt(), random.nextInt()) == 1
    math.sqrt(6 / monteCarlo(trials, cesaroTest))

  def monteCarlo(trials: Int, experiment: Boolean): Double =
    @tailrec
    def iter(trialsRemaining: Int, trialsPassed: Int): Double =
      if trialsRemaining == 0 then trialsPassed / trials
      else if experiment then iter(trialsRemaining - 1, trialsPassed + 1)
      else iter(trialsRemaining - 1, trialsPassed)

    iter(trials, 0)

  def main(args: Array[String]): Unit = {
    import BankAccount.testBankAccount
    testBankAccount()
    println("Accumulator")
    val A = makeAccumulator(5)
    println(A(10))
    println(estimatePi(1000))
    println(estimatePi(100000))

  }
