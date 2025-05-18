package org.shakirfattani.service.impl

import org.shakirfattani.service.FeeService

class FeeServiceImpl : FeeService {
    override fun calculateFee(type: String, amount: Double): Pair<Double, String> {
        val rate = when (type) {
            "Mobile Top Up" -> 0.0015
            else -> 0.002
        }
        val fee = amount * rate
        val description = "Standard fee rate of ${rate * 100}%"
        return fee to description
    }
}