package io.github.poeschl.pchain

import java.time.LocalDateTime
import java.time.ZoneOffset

data class Block(
    val previousHash: String, val data: String
) {
    internal var hash: String

    private val timestamp: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    private var nonce: Int = 0

    init {
        hash = calcHash()
    }

    internal fun calcHash(): String {
        return (previousHash + timestamp.toString() + data + nonce).asSha256Hex()
    }

    internal fun mineBlock(difficulty: Int) {
        val target = "".padStart(difficulty, '0')
        while (hash.substring(0, difficulty) != target) {
            nonce++
            hash = calcHash()
        }
        println("Block Mined: $hash")
    }

    override fun toString(): String {
        return "Block(previousHash='$previousHash', data='$data', hash='$hash', timestamp=$timestamp, nonce=$nonce)"
    }


}
