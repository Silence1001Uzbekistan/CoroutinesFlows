package uz.artikov.coroutinesflows.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.artikov.coroutinesflows.models.GithubUser
import uz.artikov.coroutinesflows.networking.ApiClient
import uz.artikov.coroutinesflows.networking.ApiService
import uz.artikov.coroutinesflows.repository.UserRepository

class UserViewModel : ViewModel() {

    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
    private val userRepository = UserRepository(apiService)

    private val liveData = MutableLiveData<Result<List<GithubUser>?>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {

        viewModelScope.launch {

            userRepository.getGithubUsers().catch {

                liveData.postValue(Result.failure(it))

            }.collect {

                if (it.isSuccessful){
                    liveData.postValue(Result.success(it.body()))
                }else{
                    liveData.postValue(Result.failure(Throwable("Error")))
                }


            }

        }

    }

    fun getUserLiveData(): LiveData<Result<List<GithubUser>?>> {

        return liveData

    }

}