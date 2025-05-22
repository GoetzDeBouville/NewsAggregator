package com.example.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounceFun(
    coroutineScope: CoroutineScope,
    delayMillis: Long = 2000L,
    useLastParam: Boolean = true,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (useLastParam) {
            debounceJob?.cancel()
        }
        if (debounceJob?.isCompleted == true || useLastParam) {
            debounceJob = coroutineScope.launch {
                if (useLastParam) {
                    delay(delayMillis)
                }
                action(param)
                if (!useLastParam) {
                    delay(delayMillis)
                }
            }
        }
    }
}