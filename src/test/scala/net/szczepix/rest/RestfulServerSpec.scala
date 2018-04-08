package net.szczepix.rest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.typesafe.scalalogging.Logger
import org.scalatest.{Matchers, WordSpec}

class RestfulServerSpec extends WordSpec with Matchers with ScalatestRouteTest with RestfulServerRouting {

  override val logger = Logger(this.getClass.getSimpleName)

  "RestfulServer GET" should {
    "success for (/blockchain/get/all)" in {
      Get("/blockchain/get/all") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }

  "RestfulServer GET" should {
    "success for (/blockchain/get/last)" in {
      Get("/blockchain/get/last") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }

  "RestfulServer GET" should {
    "success for (/blockchain/get/peers)" in {
      Get("/blockchain/get/peers") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }

  "RestfulServer POST" should {
    "success for (/blockchain/add/peer)" in {
      Post("/blockchain/add/peer") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }

  "RestfulServer PUT" should {
    "success for (/blockchain/create/block)" in {
      Put("/blockchain/create/block") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
