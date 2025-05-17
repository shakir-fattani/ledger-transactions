package org.shakirfattani.di

import org.koin.dsl.module
import org.shakirfattani.service.FeeService
import org.shakirfattani.service.impl.FeeServiceImpl

val appModule = module {
    single<FeeService> { FeeServiceImpl() }
}