package json

import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

import models.{Article, Lan}
import play.api.libs.json._
import play.api.libs.functional.syntax._

trait JsonLan extends JsonArticle {

  implicit val lanWrite = new Writes[Lan] {
    override def writes(o: Lan): JsValue = Json.obj(
      "id" -> o.id,
      "alias" -> o.alias,
      "name" -> o.name
    )
  }

  implicit val lanRead: Reads[Lan] = (
    (JsPath \ "id").readWithDefault[UUID](UUID.fromString("aaabbbbb-0000-0000-0000-b2f4e744d023")) and
      (JsPath \ "alias").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "articles").readNullable[Seq[Article]]
  )(Lan.apply _)

}
