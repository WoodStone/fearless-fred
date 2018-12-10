package json

import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

import models.Article
import play.api.libs.json._
import play.api.libs.functional.syntax._

trait JsonArticle {

  implicit val articleWrite = new Writes[Article] {
    override def writes(o: Article) = Json.obj(
      "id" -> o.id,
      "lanid" -> o.lanid,
      "timestamp" -> o.timestamp,
      "header" -> o.header,
      "body" -> o.body
    )
  }

  implicit val articleRead: Reads[Article] = (
    (JsPath \ "id").readWithDefault[UUID](UUID.fromString("aaabbbbb-0000-0000-0000-b2f4e744d023")) and
    (JsPath \ "lanid").readWithDefault[UUID](UUID.fromString("aaabbbbb-0000-0000-0000-b2f4e744d023")) and
      (JsPath \ "timestamp").readWithDefault[Timestamp](Timestamp.from(Instant.now())) and
      (JsPath \ "header").read[String] and
      (JsPath \ "body").read[String]
  )(Article.apply _)

  //implicit val uuidWrite: Writes[UUID] = Writes[UUID](v => JsString(v.toString))
  implicit val timestampWrite: Writes[Timestamp] = Writes[Timestamp](v => JsString(v.toString))

  implicit val uuidRead: Reads[UUID] = (JsPath \ "id").read[String].map(uuid => UUID.fromString(uuid))
  implicit val timestampRead: Reads[Timestamp] = (JsPath \ "timestamp").read[String].map(timestamp => Timestamp.valueOf(timestamp))




}
