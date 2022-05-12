package controllers

import models.TaskList
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject._

@Singleton
class LoginController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def login: Action[AnyContent] = Action {
    Ok(views.html.login())
  }

  def validateLogin: Action[AnyContent] = Action { req =>
    req.body.asFormUrlEncoded.map { args =>
      val username = args("username").head
      val password =  args("password").head

      TaskList.validateUser(username, password) match {
        case true => Redirect(routes.TaskListController.taskList)
        case _ => Redirect(routes.LoginController.login)
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
}
