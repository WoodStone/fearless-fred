package models

import java.util.UUID

import skinny.orm.SkinnyJoinTable

case class LanArticle(lanId: UUID, articleId: UUID)

object LanArticle extends SkinnyJoinTable[LanArticle] {
  override lazy val tableName = "lanarticle"
  override lazy val defaultAlias = createAlias("la")
}
