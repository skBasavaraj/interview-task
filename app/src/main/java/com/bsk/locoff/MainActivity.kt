package com.bsk.locoff

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bsk.locoff.model.ApiResponse
import com.bsk.locoff.model.RetrofitInstance
import com.bsk.locoff.model.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
   lateinit   var adapter: Adapter
    lateinit var mRecyclerView: RecyclerView
    lateinit var myViewModel: MyViewModel
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setViewModel()
    }

    private fun initView() {
        mRecyclerView = findViewById(R.id.rv)
        adapter = Adapter(this)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)
        progressDialog.show()
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter
    }

    /**
     *  this lines of code for MVVM
     */
    private fun setViewModel() {
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        myViewModel.getViewModel().observe(this, Observer {
            if(it==null){
                progressDialog.setMessage("Please check internet..")
            }else {
                adapter.setAdapterList(it)
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }
        })
        myViewModel.makeApiCall()
    }
        /**
         *  this below lines codes are without MVVM
         */
        //   myViewModel.getViewModel().obv
        /*  val retrofitService = RetrofitInstance.getInstance().create(RetrofitService::class.java)
        val data = retrofitService.getDataFromAPI()
        data.enqueue(object : Callback<ArrayList<ApiResponse>?> {
            override fun onResponse(
                call: Call<ArrayList<ApiResponse>?>,
                response: Response<ArrayList<ApiResponse>?>
            ) {
                val response = response.body()
                 adapter =Adapter(response!!)
                rv.layoutManager =LinearLayoutManager(this@MainActivity)
                rv.adapter =adapter
                Log.d(";;","$response")
            }

            override fun onFailure(call: Call<ArrayList<ApiResponse>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })*/
        /**
         *  below list code used for In ApiResponse class fill use Call<List<ApiRsponse>>
         * */
        /* data.enqueue(object : Callback<List<ApiResponse>?> {
             override fun onResponse(
                 call: Call<List<ApiResponse>?>,
                 response: Response<List<ApiResponse>?>
             ) {
                 val response = response.body()
             //    adapter = Adapter(response as ArrayList<ApiResponse>)
                 adapter =Adapter(response!!)
                 rv.layoutManager =LinearLayoutManager(this@MainActivity)
                 rv.adapter =adapter
                 Log.d(";;","$response")

                  if (response != null) {
                    for (items in response){
                        rv.text = items.name
                    }

                }
            }

            override fun onFailure(call: Call<List<ApiResponse>?>, t: Throwable) {
                Log.d(";;", " ${t.message}")
            }
        })
*/
}



