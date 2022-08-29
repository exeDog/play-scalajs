package controllers

import models.TaskList
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject._

@Singleton
class LoginController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def login: Action[AnyContent] = Action { implicit req =>
    Ok(views.html.login())
  }

  def signup: Action[AnyContent] = Action { implicit req =>
    Ok(views.html.signup())
  }

  def validateLogin: Action[AnyContent] = Action { req =>
    req.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password =  args("password").head

      TaskList.validateUser(username, password) match {
        case true => Redirect(routes.TaskListController.taskList).withSession("username" -> username).flashing("success" -> "Welcome")
        case _ => Redirect(routes.LoginController.login).flashing("error" -> "Invalid username or password")
      }
    }.getOrElse(InternalServerError)
  }

  def createUser: Action[AnyContent] = Action { req =>
    req.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password =  args("password").head

      TaskList.createUser(username, password) match {
        case true => Ok("")
        case _ => InternalServerError
      }
    }.getOrElse(InternalServerError)
  }

  def validateSignUp: Action[AnyContent] =  Action { req =>
    req.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password =  args("password").head

      TaskList.createUser(username, password) match {
        case true => Redirect(routes.TaskListController.taskList).withSession("username" -> username)
        case _ => InternalServerError
      }
    }.getOrElse(InternalServerError)
  }

  def logout: Action[AnyContent] = Action {
    Redirect(routes.LoginController.login).withNewSession
  }
}
