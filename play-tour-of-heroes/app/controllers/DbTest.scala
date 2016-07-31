package controllers

import models.Hero
import models.database.HeroTableDef
import slick.dbio.Effect.Read
import slick.driver.H2Driver.api._
import slick.profile.FixedSqlStreamingAction

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by maciek on 05.07.16.
  */
object DbTest extends App {
  val db = Database.forConfig("h2mem1")
  try {

    // The query interface for the Suppliers table
    val heroesQuery: TableQuery[HeroTableDef] = TableQuery[HeroTableDef]

    val setupAction: DBIO[Unit] = DBIO.seq(
      // Create the schema by combining the DDLs for the Suppliers and Coffees
      // tables using the query interfaces
      heroesQuery.schema.create,

      // Insert some suppliers
      heroesQuery += Hero("asd"),
      heroesQuery += Hero("asd"),
      heroesQuery += Hero("asd")
    )

    val setupFuture: Future[Unit] = db.run(setupAction)

    val f: Future[Seq[String]] = setupFuture.flatMap { _ =>
      val query: Query[Rep[String], String, Seq] = heroesQuery.map(_.name)
      println("Generated SQL for filter query:\n" + query.result.statements)
      val po: Future[Seq[String]] = db.run(query.result)
      po
    }
    val asyncResult: Seq[String] = Await.result(f, Duration.Inf)
    println(asyncResult)
    asyncResult
  } finally db.close
}
