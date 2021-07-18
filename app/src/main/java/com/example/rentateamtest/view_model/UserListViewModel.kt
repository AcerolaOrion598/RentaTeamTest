package com.example.rentateamtest.view_model

import androidx.lifecycle.ViewModel
import com.example.rentateamtest.model.User
import com.example.rentateamtest.repository.IUserListRepository
import io.reactivex.Observable

class UserListViewModel : ViewModel() {
    private val userList = arrayListOf<User>()

    fun userListSource(userListRepository: IUserListRepository): Observable<ArrayList<User>> {
        return Observable.create { subscriber ->
            val receivedList = userListRepository.getUserList() ?: throw Exception()
            userList.clear()
            userList.addAll(receivedList)
            subscriber.onNext(userList)
        }
    }
}