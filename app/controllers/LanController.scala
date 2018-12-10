package controllers

import javax.inject.Inject
import json.JsonLan
import models.Lan
import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.{Security, SecurityComponents}
import play.api.Configuration
import play.api.libs.json._

class LanController @Inject() (val controllerComponents: SecurityComponents, configuration: Configuration) extends Security[CommonProfile] with JsonLan {

  def fetchAll = Action { request =>
    Ok(Json.toJson(Lan.findAll()))
  }

  def create = Action { request =>
    request.body.asJson.map { json =>
      json.validate[Lan] match {
        case s: JsSuccess[Lan] => {
          val rsjs = JsObject(Seq(
            "id" -> JsString(Lan.create(s.value).toString)
          ))
          Ok(rsjs)
        }
        case e: JsError => {
          BadRequest("JSON Error")
        }
      }
    } getOrElse {
      BadRequest
    }
  }

  def fetch(implicit alias: String) = Action { request =>
    Lan.findByAlias match {
      case Some(lan) => Ok(Json.toJson(lan))
      case None => NotFound
    }
  }

  def patch(id: String) = Action { request =>
    request.body.asJson.map { json =>
      json.validate[Lan] match {
        case s: JsSuccess[Lan] => {
          Lan.patch(id, s.value)
          Ok
        }
        case e: JsError => {
          BadRequest("JSON Error")
        }
      }
    } getOrElse {
      BadRequest
    }
  }

  def delete(id: String) = Action { request =>
    Ok
  }

}
