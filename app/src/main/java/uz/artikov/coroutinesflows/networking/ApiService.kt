package uz.artikov.coroutinesflows.networking

import retrofit2.Response
import retrofit2.http.GET
import uz.artikov.coroutinesflows.models.GithubUser

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<GithubUser>>

}