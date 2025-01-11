package com.michel.unit.turbine

import app.cash.turbine.turbineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

fun runTurbineTest(
    testBody: suspend TestScope.() -> Unit,
) = runTest(testBody = { turbineScope { testBody() } })
