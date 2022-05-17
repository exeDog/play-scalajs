package controllers

import models.TaskList
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject._

@Singleton
class TaskListController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def taskList: Action[AnyContent] = Action { req =>
    req.session.get("username").map {
      case v =>
        val tasks = TaskList.getTasks(v)
        Ok(views.html.tasklist(tasks))
      case _ => InternalServerError
    }.getOrElse(Redirect(routes.LoginController.login))
  }

  def product(productType: String, prodNum: Int): Action[AnyContent] = Action {
    Ok(s"Product type is ${productType} & product number is ${prodNum}")
  }
}
