package controllers

import javax.inject.Inject

import models.Hero
import models.database.HeroTableDef
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application"s home page.
  */
class HomeController @Inject()(val dbConfigProvider: DatabaseConfigProvider) extends Controller {

  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import scala.concurrent.ExecutionContext.Implicits.global

  val heroes = TableQuery[HeroTableDef]

  val heroesJSON =
    """[{ "id": 11, "name": "Mr. Nice" },{ "id": 12, "name": "Narco" },{ "id": 13, "name": "Bombasto" },{ "id": 14, "name": "Celeritas" },{ "id": 15, "name": "Magneta" },{ "id": 16, "name": "RubberMan" },{ "id": 17, "name": "Dynama" },{ "id": 18, "name": "Dr IQ" },{ "id": 19, "name": "Magma" },{ "id": 20, "name": "Tornado" }]"""

  def getHeroes = Action.async { implicit request =>
    implicit val userImplicitWrites = Json.writes[Hero]
    val resultFuture: Future[Seq[Hero]] = dbConfig.db.run(heroes.result)
    resultFuture.map(heroesList => Ok(Json.toJson(heroesList)))
  }

  def getHero(id: Long) = Action.async {
    implicit val userImplicitWrites = Json.writes[Hero]
    val resultFuture: Future[Option[Hero]] = dbConfig.db.run(heroes.filter(_.id === id).result.headOption)
    resultFuture.map(heroesList => heroesList.fold(NoContent)(hero => Ok(Json.toJson(hero))))
  }

  def addHero() = Action.async { implicit request => {
    val body: Option[JsValue] = request.body.asJson
    body.fold(Future(BadRequest("Not a Json really")))(json => {
      implicit val userImplicitWrites = Json.reads[Hero]
      val hero: Hero = Json.fromJson[Hero](json).get
      val databaseInsertionResult: Future[Int] = dbConfig.db.run(heroes += hero)
      databaseInsertionResult.map(resultFuture => Ok("Successfully inserted hero"))
    }
    )
  }
  }

  def deleteHero(id: Long) = Action.async { implicit request =>
    val resultFuture: Future[Int] = dbConfig.db.run(heroes.filter(_.id === id).delete)
    resultFuture.map(rowsDeleted => Ok(s"Deleted $rowsDeleted rows"))
  }

  def updateHero(id: Long) = Action.async { implicit request =>
    val body: Option[JsValue] = request.body.asJson
    body.fold(Future(BadRequest("Not a Json really")))(json => {
      println(json)
      implicit val userImplicitWrites = Json.reads[Hero]
      val hero: Hero = Json.fromJson[Hero](json).get
      val databaseInsertionResult: Future[Int] = dbConfig.db.run(heroes.filter(_.id === id).update(hero))
      databaseInsertionResult.map(resultFuture => Ok("Successfully inserted hero"))
    }
    )
  }

  def mockCors(id: Long) = Action { implicit request =>
    Ok("Mock CORS pre-flight response")
  }

}
