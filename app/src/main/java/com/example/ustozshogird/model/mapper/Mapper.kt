package com.example.ustozshogird.model.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}