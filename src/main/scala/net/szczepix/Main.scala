package net.szczepix

import net.szczepix.blockchain.BlockChainConfig._
import net.szczepix.blockchain.BlockChainEngine
import net.szczepix.rest.RestfulServer

object Main extends App {

  val engine = new BlockChainEngine
  engine.setup(blockChainNode, blockChainHost)

  // TODO - move it to the callback after engine will start properly
  RestfulServer.start()
}
