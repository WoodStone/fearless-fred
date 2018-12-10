package models

import java.util.UUID

import scalikejdbc._

case class Lan(id: UUID, alias: String, name: String, articles: Option[Seq[Article]] = None)

object Lan extends CRUDMapperWithUUID[Lan] {
  override lazy val defaultAlias = createAlias("Lan")
  private[this] lazy val l = defaultAlias

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Lan]): Lan =  new Lan(
    rawValueToId(rs.any(n.id)),
    rs.string(n.alias),
    rs.string(n.name)
  )

  def create(lan: Lan): UUID = {
    createWithNamedValues(
      column.alias -> lan.alias,
      column.name -> lan.name
    )
  }

  def patch(alias: String, lan: Lan): UUID = {
    updateBy(sqls.eq(l.alias, alias)).withAttributes(
      'alias -> lan.alias,
      'name -> lan.name
    )
    lan.id
  }

  def findByAlias(implicit alias: String): Option[Lan] = {
    findBy(sqls.eq(l.alias, alias))
  }

  lazy val articleRef = hasMany[Article](
    many = Article -> Article.defaultAlias,
    on = (l, a) => sqls.eq(l.id, a.lanid),
    merge = (lan, articles) => lan.copy(articles = Some(articles))
  )

  def findByAliasWithArticles(implicit alias: String): Option[Lan] = {
    joins(articleRef).findBy(sqls.eq(l.alias, alias))
  }

}
