package controllers

import javax.inject.Inject
import org.pac4j.core.profile.{CommonProfile, ProfileManager}
import org.pac4j.play.PlayWebContext
import org.pac4j.play.scala.{Security, SecurityComponents}
import play.api.Configuration
import play.api.libs.json.Json

import scala.collection.JavaConverters

class LoginController @Inject()(val controllerComponents: SecurityComponents, configuration: Configuration) extends Security[CommonProfile] {

  def check = Secure("") { request =>
    val context = new PlayWebContext(request, playSessionStore)
    val profileManager = new ProfileManager[CommonProfile](context)
    val profiles = profileManager.getAll(true)

    Ok(Json.toJson(JavaConverters.asScalaBuffer(profiles).toList.toString()))
  }

  def login(r: String) = Secure("KeycloakOidcClient") { implicit request =>
    val cors = configuration.get[Seq[String]]("play.filters.cors.allowedOrigins")

    if (cors.filter(r.startsWith(_)).isEmpty) {
      Redirect("/")
    } else {
      Redirect(r)
    }
  }

}
