package net.szczepix.blockchain

import com.typesafe.config.ConfigFactory

object BlockChainConfig {

  val config = ConfigFactory.load()

  val blockChainHost = config.getString("blockChain.host")
  val blockChainNode = config.getString("blockChain.node")
}
