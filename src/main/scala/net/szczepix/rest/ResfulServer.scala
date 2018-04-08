package net.szczepix.rest

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.unmarshalling.PredefinedFromEntityUnmarshallers
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.native
import RestfulServerConfig._
import akka.http.scaladsl.Http

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

object RestfulServerConfig {

  val config = ConfigFactory.load()

  val httpInterface = config.getString("http.interface")
  val httpPort = config.getInt("http.port")
  val httpRest = config.getString("http.rest")
}

object RestfulServer extends RestfulServerRouting {

  override val logger = Logger(this.getClass.getSimpleName)

  implicit val serialization = native.Serialization
  implicit val stringUnmarshallers = PredefinedFromEntityUnmarshallers.stringUnmarshaller
  implicit val actorSystem = ActorSystem("BlockChainRestfulServer")
  implicit val materializer = ActorMaterializer()
  implicit val timeout = Timeout(30, TimeUnit.SECONDS)
  implicit val executionContext: ExecutionContext = actorSystem.dispatcher

  def start() = {
    Http().bindAndHandle(restfulRoutes, httpInterface, httpPort)

    logger.info(s"Server online: $httpInterface : $httpPort")

    Await.result(actorSystem.whenTerminated, Duration.Inf)
  }
}

trait RestfulServerRouting extends Json4sSupport {

  val logger: Logger

  val restfulRoutes =
    pathPrefix(httpRest / "get") {
      get {
        respondWithHeaders(RawHeader("Access-Control-Allow-Origin", "*")) {
          path("all") {
            logger.info("New request [GET] for the all blocks")
            complete("success")
          } ~
            path("peers") {
              logger.info("New request [GET] for the peers list")
              complete("success")
            } ~
            path("last") {
                logger.info("New request [GET] for the last block")
                complete("success")
            }
        }
      }
    } ~
      pathPrefix(httpRest / "create") {
        put {
          path("block") {
              logger.info("New request [PUT] to add new block")
              complete("success")
          }
        }
      } ~
    pathPrefix(httpRest / "add") {
      post {
        path("peer") {
          entity(as[String]) { data =>
            logger.info(s"New request [POST] to add new peer $data")
            complete("success")
          }
        }
      }
    }
}