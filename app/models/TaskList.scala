package models

import collection.mutable

object TaskList {
  private val users = mutable.Map[String, String]("priyank" -> "foo")
  private val tasks = mutable.Map[String, List[String]]("priyank" -> List("Eat", "Sleep", "Scala", "Repeat"))

  def validateUser(username: String, password: String): Boolean = users.get(username).contains(password)

  def createUser(username: String, password: String): Boolean = {
    username match {
      case _ if users.contains(username) => false
      case _ =>
        users(username) = password
        true
    }
  }

  def getTasks(user: String): Seq[String] = tasks.getOrElse(user, Nil)

  def addTask(task: String, user: String): Unit = ???

  def removeTask(task: String, user: String): Unit = ???

}
