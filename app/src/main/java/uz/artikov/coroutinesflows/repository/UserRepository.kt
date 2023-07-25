package uz.artikov.coroutinesflows.repository

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import uz.artikov.coroutinesflows.networking.ApiService

class UserRepository(private val apiService: ApiService) {

    fun getGithubUsers() = flow { emit(apiService.getUsers()) }

}