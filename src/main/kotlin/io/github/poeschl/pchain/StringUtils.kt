package io.github.poeschl.pchain

import org.apache.commons.codec.digest.DigestUtils

fun String.asSha256Hex(): String {
    return DigestUtils.sha256Hex(this)
}
