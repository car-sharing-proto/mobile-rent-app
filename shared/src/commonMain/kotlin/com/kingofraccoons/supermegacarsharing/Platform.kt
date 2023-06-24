package com.kingofraccoons.supermegacarsharing

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform