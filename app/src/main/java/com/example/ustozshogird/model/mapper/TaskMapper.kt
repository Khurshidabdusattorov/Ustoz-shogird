package com.example.ustozshogird.model.mapper

import com.example.ustozshogird.model.data.DoneTask
import com.example.ustozshogird.model.data.Task

class TaskMapper : Mapper<Task, DoneTask> {
    override fun map(input: Task): DoneTask {
        return DoneTask(input.id, input.title, input.description, 0, "",input.timeStamp,input.imageUrl)
    }
}