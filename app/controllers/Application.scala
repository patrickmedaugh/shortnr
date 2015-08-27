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
      "Name" -> text,
      "Url" -> text
    )
  )

  var urls = Array[ShortUrl]()

  var idCounter = 1

  val validUrl ="^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".r;

  def shortenedURL = Action {
    val json = Json.toJson(urls)
    Ok(json)
  }

  def index = Action {
    Ok(views.html.main())
  }

  def create = Action { implicit request =>
    val params = form.bindFromRequest.get
    val url = params._2 match {
      case validUrl(_*) => params._2
      case _ => ""
    }
    var x:ShortUrl = new ShortUrl(idCounter, params._1, url)
    urls = urls :+ x
    idCounter = idCounter + 1
    println(urls(urls.length - 1))
    Ok(views.html.main())
  }

}
