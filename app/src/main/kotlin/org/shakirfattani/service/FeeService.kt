package org.shakirfattani.service

interface FeeService {
    fun calculateFee(type: String, amount: Double): Pair<Double, String>
}