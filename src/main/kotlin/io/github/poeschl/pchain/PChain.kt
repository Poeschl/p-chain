package io.github.poeschl.pchain

class PChain {

    companion object {
        private const val MINE_DIFFICULTY = 4
        private val BLOCKCHAIN = mutableListOf<Block>()

    }

    fun isChainValid(): Boolean {
        var currentBlock: Block
        var previousBlock: Block

        for (i in 1 until BLOCKCHAIN.size) {
            currentBlock = BLOCKCHAIN[i]
            previousBlock = BLOCKCHAIN[i - 1]

            if (currentBlock.hash != currentBlock.calcHash()) {
                println("Hash Missmatch on $currentBlock")
                return false
            }

            if (currentBlock.previousHash != previousBlock.hash) {
                println("Invalid previous hash on $currentBlock")
                return false
            }

            if (!currentBlock.hash.matches(("^[0]{$MINE_DIFFICULTY}.*").toRegex())) {
                println("Invalid mining result on $currentBlock")
                return false
            }
        }
        return true
    }

    fun add(data: String) {
        val previousHash = BLOCKCHAIN.getOrNull(BLOCKCHAIN.size - 1)?.hash ?: "0"
        val newBlock = Block(previousHash, data)
        BLOCKCHAIN.add(newBlock)
        println("Mine block for $data")
        newBlock.mineBlock(MINE_DIFFICULTY)
    }

    override fun toString(): String {
        return "PChain: $BLOCKCHAIN"
    }


}

fun main(args: Array<String>) {

    val pChain = PChain()

    pChain.add("I'm the first")
    pChain.add("I'm the second")
    pChain.add("I'm the third")
    pChain.add("I'm the fourth")

    println(pChain)
    println("Valid Chain: ${pChain.isChainValid()}")
}
