package net.szczepix.rest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.typesafe.scalalogging.Logger
import org.scalatest.{Matchers, WordSpec}

class RestfulServerSpec extends WordSpec with Matchers with ScalatestRouteTest with RestfulServerRouting {

  override val logger = Logger(this.getClass.getSimpleName)

  lazy val routes = restfulRoutes

  "RestfulServer" should {
    "return success for (GET /blockchain/get/all)" in {
      Get("/blockchain/get/all") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
