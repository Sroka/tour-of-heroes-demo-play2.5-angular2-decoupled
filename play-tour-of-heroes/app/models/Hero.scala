package models

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._


/**
  * Created by maciek on 14.06.16.
  */
case class Hero(name: String, id: Option[Long] = None)

object Hero {
//
//  implicit object HeroFormat extends Format[Hero] {
//
//    // convert from Tweet object to JSON (serializing to JSON)
//    def writes(hero: Hero): JsValue = {
//      //  tweetSeq == Seq[(String, play.api.libs.json.JsString)]
//      val heroSeq = Seq(
//        "name" -> JsString(hero.name),
//        "id" -> JsNumber(hero.id match {
//          case Some(i) => i
//          case None => 0
//        })
//      )
//      JsObject(heroSeq)
//    }
//
//    // convert from JSON string to a Tweet object (de-serializing from JSON)
//    // (i don't need this method; just here to satisfy the api)
//    def reads(json: JsValue): JsResult[Hero] = {
//      JsSuccess(Hero((JsPath \ "name").read[String), Option(0)))
//    }
//
//  }

}

//
//case class HeroFormData(name: String)
//
//object Heroes {
//  var heroes: Seq[Hero] = Seq()
//
//  def add(hero: Hero): String = {
//    heroes = heroes :+ hero.copy(id = heroes.length) // manual id increment
//    "User successfully added"
//  }
//
//  def delete(id: Long): Option[Int] = {
//    val originalSize = heroes.length
//    heroes = heroes.filterNot(_.id == id)
//    Some(originalSize - heroes.length) // returning the number of deleted users
//  }
//
//  def get(id: Long): Option[Hero] = heroes.find(_.id == id)
//
//  def listAll: Seq[Hero] = heroes
//}
//
//
//object HeroForm {
//  val form = Form(
//    mapping(
//      "name" -> nonEmptyText
//    )(HeroFormData.apply)(HeroFormData.unapply)
//  )
//}

