package com.veronika.fitconnectveronika.model


abstract class session() {
   companion object {
       var sessionActive: Boolean = false
       var user:User? = null
   }

}