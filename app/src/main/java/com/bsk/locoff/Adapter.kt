package com.bsk.locoff

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bsk.locoff.model.ApiResponse

class Adapter(var activity:Activity) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var list: List<ApiResponse>?=null
    fun setAdapterList( list:List<ApiResponse>) {
        this.list=list
    }

    /**   below line for using List collection*/
    //class Adapter(val list: List<ApiResponse>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bind(list?.get(position)!!)

    }

    override fun getItemCount(): Int {
        if(list==null)return 0
        else return list?.size!!
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val email: TextView = view.findViewById(R.id.tvEmail)
        val address: TextView = view.findViewById(R.id.tvLastName)
        val id: TextView = view.findViewById(R.id.tvID)
        fun bind(data:ApiResponse){
              name.text =data.username
            email.text=data.email
            address.text= data.address.toString()
            id.text= data.id.toString()
        }
    }
}