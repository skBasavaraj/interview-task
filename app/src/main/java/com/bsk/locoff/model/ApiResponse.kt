package com.bsk.locoff.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(

    var id: Int? = null,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: Address? = Address(),
    var phone: String? = null,
    var website: String? = null,
    var company: Company? = Company()
)

data class Geo(

     var lat: String? = null,
     var lng: String? = null

)


data class Address(

      var street: String? = null,
     var suite: String? = null,
 var city: String? = null,
   var zipcode: String? = null,
   var geo: Geo? = Geo()

)


data class Company(

 var name: String? = null,
  var catchPhrase: String? = null,
      var bs: String? = null

)