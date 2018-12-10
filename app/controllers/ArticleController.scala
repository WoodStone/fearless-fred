package controllers

import java.util.UUID

import javax.inject.Inject
import json.{JsonArticle, JsonLan}
import models.{Article, Lan}
import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.{Security, SecurityComponents}
import play.api.Configuration
import play.api.libs.json._
import play.api.mvc.Accepting

class ArticleController @Inject()(val controllerComponents: SecurityComponents, configuration: Configuration) extends Security[CommonProfile] with JsonArticle with JsonLan {

  def fetchAll(implicit alias: String) = Action { request =>
    Lan.findByAliasWithArticles match {
      case Some(lan) => Ok(Json.toJson(lan.articles))
      case None => NotFound
    }
  }

  def create(implicit alias: String) = Action { request =>
    request.body.asJson.map { json =>
      json.validate[Article] match {
        case s: JsSuccess[Article] => {
          Lan.findByAlias match {
            case Some(lan) => {
              Ok(Json.toJson(Article.create(s.get.copy(lanid = lan.id))))
            }
            case None => NotFound
          }
        }
        case e: JsError => {
          BadRequest("JSON Error")
        }
      }
    } getOrElse {
      BadRequest
    }
  }

  def fetch(implicit lanAlias: String, articleId: String) = Action { request =>
    Article.findById(UUID.fromString(articleId)) match {
      case Some(article) => Ok(Json.toJson(article))
      case None => NotFound
    }
  }

  //val AcceptJSONPatch = Accepting("json-patch+json")
  def patch(implicit lanAlias: String, articleId: String) = Action { request =>
    NotAcceptable
  }

  def delete(implicit lanAlias: String, articleId: String) = Action { request =>
    Article.deleteById(UUID.fromString(articleId))
    NoContent
  }

}
