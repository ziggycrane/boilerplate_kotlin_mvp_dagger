package com.ziggycrane.boilerplate.data.network.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class GetAccountListResponse {

    @Expose
    @SerializedName("get_tasks")
    val getTasksInterval: Int? = null

    @Expose
    @SerializedName("check_version")
    val checkVersionInterval: Int? = null
}
