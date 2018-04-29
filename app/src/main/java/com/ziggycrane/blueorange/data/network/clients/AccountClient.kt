package com.ziggycrane.blueorange.data.network.clients

import com.google.gson.JsonObject
import com.ziggycrane.blueorange.data.network.model.account.GetAccountListResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AccountClient {

    @GET("accounts")
    fun getAccounts(): Observable<GetAccountListResponse>
}