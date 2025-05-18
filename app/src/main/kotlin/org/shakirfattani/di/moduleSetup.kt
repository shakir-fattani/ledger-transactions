package org.shakirfattani.di

import org.koin.dsl.module
import org.shakirfattani.service.FeeService
import org.shakirfattani.service.impl.FeeServiceImpl
import org.shakirfattani.workflow.FeeWorkflow

val appModule = module {
    single<FeeService> { FeeServiceImpl() }
    single { FeeWorkflow(get()) }
}