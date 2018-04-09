package net.szczepix.blockchain

import com.typesafe.scalalogging.Logger

class BlockChainEngine {

  val logger = Logger("# - " + this.getClass.getSimpleName)

  def setup(node: String, host: String): Unit = {
    logger.info(s"Running engine with node: [$node] and host: [$host]")
    if (!host.isEmpty) {
      logger.info(s"Attempting to connect to seed-host $host")
      //peerToPeerActor ! AddPeer(seedHost)
    } else {
      logger.info("No seed host configured, waiting for messages.")
    }
  }
}
