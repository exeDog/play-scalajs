package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject._

@Singleton
class TaskListController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def taskList: Action[AnyContent] = Action {
    Ok("Works")
  }
}
