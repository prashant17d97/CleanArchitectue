package com.prashant.cleanarchitecture.domain.usecases.interfaces

interface UseCase<in Param, out Result> {
    suspend operator fun invoke(param: Param): Result
}