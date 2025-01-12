package com.atiurin.ultron.core.compose.operation

import com.atiurin.ultron.core.common.DefaultOperationIterationResult
import com.atiurin.ultron.core.common.Operation
import com.atiurin.ultron.core.common.OperationIterationResult
import com.atiurin.ultron.core.common.UltronOperationType

class UltronComposeOperation(
    val operationBlock: () -> Unit,
    override val name: String,
    override val type: UltronOperationType,
    override val description: String,
    override val timeoutMs: Long
) : Operation {
    override fun execute(): OperationIterationResult {
        var success = true
        var exception: Throwable? = null
        try {
            operationBlock()
        }catch (error: Throwable){
            success = false
            exception = error
        }
        return DefaultOperationIterationResult(success, exception)
    }
}