package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._


object Application extends Controller {

  case class ShortUrl(id: Int, name: String, longUrl: String)

  implicit val urlWrites = Json.writes[ShortUrl]

  val form = Form(
    tuple(
      "Url" -> text,
      "Name" -> text
    )
  )

  var urls = Array[ShortUrl]()
  var idCounter = 1

  def shortenedURL = Action {
    var x:ShortUrl = new ShortUrl(1, "http://www.google.com", "Google")
    val json = Json.toJson(x)
    Ok(json)
  }

  def index = Action {
    Ok(views.html.main())
  }

  def create = Action { implicit request =>
    val params = form.bindFromRequest.get
    var x:ShortUrl = new ShortUrl(idCounter, params._1, params._2)
    urls = urls :+ x
    idCounter = idCounter + 1
    println(x.name)
    println(x.longUrl)
    println(urls)
    Ok(views.html.main())
  }

}
