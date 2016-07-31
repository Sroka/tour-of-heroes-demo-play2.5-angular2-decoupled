package models.database

import models.Hero
import slick.driver.H2Driver.api._
import slick.lifted.ProvenShape


/**
  * Created by maciek on 14.06.16.
  *
  */
class HeroTableDef(tag: Tag) extends Table[Hero](tag, "HEROES") {

  def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)

  def name = column[String]("NAME")

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[Hero] = (name, id.?) <>((Hero.apply _).tupled, Hero.unapply)
}
