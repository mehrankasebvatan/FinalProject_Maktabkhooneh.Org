package app.mkv.finalproject.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mkv.finalproject.api.ApiInstance
import app.mkv.finalproject.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel : ViewModel() {

    private var liveData = MutableLiveData<List<Data>>()

    fun getTodos(context: Context) {


        ApiInstance.api.getTodos()
            .enqueue(object : Callback<List<Data>> {
                override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                    if (response.body() != null) {
                        liveData.value = response.body()
                    } else return


                }

                override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                    Toast.makeText(context, t.message.toString(), Toast.LENGTH_LONG).show()


                }

            })

    }

    fun observeLiveData(): LiveData<List<Data>> {
        return liveData
    }
}