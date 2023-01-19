package com.example.ustozshogird.model.repository

import android.util.Log
import com.example.ustozshogird.model.`interface`.RealtimeDBRepositoryInterface
import com.example.ustozshogird.model.data.DoneTask
import com.example.ustozshogird.model.data.Task
import com.example.ustozshogird.model.data.User
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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

    override fun signUp(user: User): Flow<Boolean> = callbackFlow {
        users.child(user.number).setValue(user.password).addOnSuccessListener {
            trySendBlocking(true)
        }
        awaitClose()
    }

    suspend fun sign(user: User) {
        withContext(Dispatchers.IO) {
            users.child(user.number).setValue(user).addOnSuccessListener {
                Log.d("TTT", "sign: ")
            }
        }
    }

    override fun checkUser(user: User): Flow<Boolean> = flow {

//        users.child(user.number).get().addOnSuccessListener {
//            if (!it.exists()) {
//                trySendBlocking(false)
//            } else {
//                trySendBlocking(it.child("password").value.toString().equals(user.password))
//            }
//        }.addOnFailureListener {
//
//        }
//        awaitClose()

        emit(users.child(user.number).get().await().exists())

    }

    suspend fun check(user: User): Boolean {
        val t = users.child(user.number).get().await().exists()
        return t
    }

    override fun getTasks(id: String): Flow<List<Task>> = callbackFlow {
        tasks.child(id).get().addOnSuccessListener { tasks ->
            taskList.clear()
            for (task in tasks.children) {
                taskList.add(task.getValue(Task::class.java)!!)
            }
            trySendBlocking(taskList)
        }
        awaitClose()
    }

    override fun getDoneTasks(id: String): Flow<List<DoneTask>> = callbackFlow {
        doneTasks.child(id).get().addOnSuccessListener { tasks ->
            doneTaskList.clear()
            for (task in tasks.children) {
                doneTaskList.add(task.getValue(DoneTask::class.java)!!)
            }
            trySendBlocking(doneTaskList)
        }
        awaitClose()
    }

    override fun doneTask(task: DoneTask): Flow<Boolean> = callbackFlow {
        tasks.child(task.id).removeValue()
        doneTasks.child(task.id).setValue(task).addOnSuccessListener {
            trySendBlocking(true)
        }
        awaitClose()
    }
}
// fun DatabaseReference.valueEventFlow(): Flow<Boolean> = callbackFlow {
//    val valueEventListener = object: ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot): Unit = sendBlocking(EventResponse.Changed(snapshot))
//        override fun onCancelled(error: DatabaseError): Unit = sendBlocking(EventResponse.Cancelled(error))
//    }
//    addValueEventListener(valueEventListener)
//    awaitClose {
//        removeEventListener(valueEventListener)
//    }
//}