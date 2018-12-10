package models

import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

import scalikejdbc.WrappedResultSet

case class Article(id: UUID, lanid: UUID, timestamp: Timestamp, header: String, body: String)

object Article extends CRUDMapperWithUUID[Article] {
  override lazy val defaultAlias = createAlias("article")
  private[this] lazy val art = defaultAlias

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Article]) = new Article(
    rawValueToId(rs.any(n.id)),
    rawValueToId(rs.any(n.lanid)),
    rs.timestamp(n.timestamp),
    rs.string(n.header),
    rs.string(n.body)
  )

  def create(news: Article): UUID = {
    createWithAttributes(
      'lanid -> news.lanid,
      'timestamp -> news.timestamp,
      'header -> news.header,
      'body -> news.body
    )
  }

  def patch(article: Article)(implicit articleId: String): Int = {
    updateById(UUID.fromString(articleId)).withAttributes(
      'lanid -> article.lanid,
      'timestamp -> article.timestamp,
      'header -> article.header
    )

  }

}

