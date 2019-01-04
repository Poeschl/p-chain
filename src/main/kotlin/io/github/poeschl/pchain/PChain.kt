package io.github.poeschl.pchain

class PChain {

    internal val blockchain = mutableListOf<Block>()

    fun isChainValid(): Boolean {
        var currentBlock: Block
        var previousBlock: Block

        for (i in 1 until blockchain.size) {
            currentBlock = blockchain[i]
            previousBlock = blockchain[i - 1]

            if (currentBlock.hash != currentBlock.calcHash()) {
                return false
            }

            if (currentBlock.previousHash != previousBlock.hash) {
                return false
            }
        }
        return true
    }
}

fun main(args: Array<String>) {

    val pChain = PChain()

    pChain.blockchain.add(Block("0", "I'm the first"))
    pChain.blockchain.add(Block(pChain.blockchain[pChain.blockchain.size - 1].hash, "I'm the second"))
    pChain.blockchain.add(Block(pChain.blockchain[pChain.blockchain.size - 1].hash, "I'm the third"))
    pChain.blockchain.add(Block(pChain.blockchain[pChain.blockchain.size - 1].hash, "I'm the fourth"))

    println(pChain.blockchain)
    println(pChain.isChainValid())
}
