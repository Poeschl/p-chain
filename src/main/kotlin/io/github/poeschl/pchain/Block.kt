package io.github.poeschl.pchain

import java.time.LocalDateTime
import java.time.ZoneOffset

data class Block(
    val previousHash: String, val data: String
) {
    private val timestamp: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

    internal val hash = calcHash()

    internal fun calcHash(): String {
        return (previousHash + timestamp.toString() + data).asSha256Hex()
    }

    override fun toString(): String {
        return "Block(previousHash='$previousHash', data='$data', timestamp=$timestamp, hash='$hash')"
    }


}
