package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._


object Application extends Controller {

  case class Place(id: Int, name: String)


  implicit val placesWrites = Json.writes[Place]

  def shortenedURL = Action {
    var x:Place = new Place(1, "Laguna")
    val json = Json.toJson(x)
    Ok(json)
  }

  def index = Action {
    Ok(views.html.main())
  }

}
