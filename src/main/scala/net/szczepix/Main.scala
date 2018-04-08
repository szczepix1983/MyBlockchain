package net.szczepix

import com.typesafe.scalalogging.Logger
import net.szczepix.blockchain.BlockChainConfig._
import net.szczepix.rest.RestfulServer

object Main extends App {

  val logger = Logger(this.getClass.getSimpleName)

  logger.info(s"Running node: $blockChainNode for host:  $blockChainHost")

  if (!blockChainHost.isEmpty) {
    logger.info(s"Attempting to connect to seed-host $blockChainHost")
    //peerToPeerActor ! AddPeer(seedHost)
  } else {
    logger.info("No seed host configured, waiting for messages.")
  }

  RestfulServer.start()
}
