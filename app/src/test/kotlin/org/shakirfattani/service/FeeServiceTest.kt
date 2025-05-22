package org.shakirfattani.service

import dev.restate.sdk.kotlin.Context
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.shakirfattani.service.impl.FeeServiceImpl

class FeeServiceTest : FeatureSpec({

    feature("Calculating transaction fees") {

        val service = FeeServiceImpl() // or mock if needed

        scenario("Calculating Transaction Fee for Mobile Top up and fee should be 15.0") {
            val result = runBlocking {
                service.calculateFee(null, TransactionAmountAndType(1000.0, "Mobile Top Up"))
            }

            result.fee shouldBe 15.0
        }

        scenario("Calculating Transaction Fee for Fuel Top up and fee should be 20.0") {
            val result = runBlocking {
                service.calculateFee(null, TransactionAmountAndType(1000.0, "Fuel Top Up"))
            }

            result.fee shouldBe 20.0
        }
    }
})