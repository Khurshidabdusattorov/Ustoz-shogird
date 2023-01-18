package com.example.ustozshogird.model.repository

import android.util.Log
import com.example.ustozshogird.model.`interface`.RealtimeDBRepositoryInterface
import com.example.ustozshogird.model.data.DoneTask
import com.example.ustozshogird.model.data.Task
import com.example.ustozshogird.model.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RealtimeDBRepository : RealtimeDBRepositoryInterface {
    private val database = FirebaseDatabase.getInstance()
    private val users = database.getReference("User")
    private val tasks = database.getReference("Task")
    private val doneTasks = database.getReference("DoneTask")
    private val taskList: ArrayList<Task> = ArrayList()
    private val doneTaskList: ArrayList<DoneTask> = ArrayList()

    companion object {
        var inst: RealtimeDBRepository? = null
        fun getInstance(): RealtimeDBRepository {
            if (inst == null) {
                inst = RealtimeDBRepository()
            }
            return inst!!
        }
    }

    override suspend fun signUp(user: User): Flow<Boolean> = callbackFlow {
        users.child(user.number).setValue(user.password).addOnSuccessListener {
            trySendBlocking(true)
        }
        awaitClose()
    }

    override suspend fun checkUser(user: User): Flow<Boolean> = callbackFlow {
        users.child(user.number).get().addOnSuccessListener {
            trySendBlocking(it.exists())
        }
        awaitClose()
    }

    override suspend fun getTasks(id: String): Flow<List<Task>> = callbackFlow {
        tasks.child(id).get().addOnSuccessListener { tasks ->
            taskList.clear()
            for (task in tasks.children) {
                taskList.add(task.getValue(Task::class.java)!!)
            }
            trySendBlocking(taskList)
        }
        awaitClose()
    }

    override suspend fun getDoneTasks(id: String): Flow<List<DoneTask>> = callbackFlow {
        doneTasks.child(id).get().addOnSuccessListener { tasks ->
            doneTaskList.clear()
            for (task in tasks.children) {
                doneTaskList.add(task.getValue(DoneTask::class.java)!!)
            }
            trySendBlocking(doneTaskList)
        }
        awaitClose()
    }

    override suspend fun doneTask(task: DoneTask): Flow<Boolean> = callbackFlow {
        tasks.child(task.id).removeValue()
        doneTasks.child(task.id).setValue(task).addOnSuccessListener {
            trySendBlocking(true)
        }
        awaitClose()
    }
}
//suspend fun DatabaseReference.valueEventFlow(): Flow<Boolean> = callbackFlow {
//    val valueEventListener = object: ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot): Unit = sendBlocking(EventResponse.Changed(snapshot))
//        override fun onCancelled(error: DatabaseError): Unit = sendBlocking(EventResponse.Cancelled(error))
//    }
//    addValueEventListener(valueEventListener)
//    awaitClose {
//        removeEventListener(valueEventListener)
//    }
//}