package fr.epsi.projetexamenv2

import android.content.Context
import java.lang.Exception

public class User (var firstName: String, var lastName: String, var email: String, var address: String, var zipcode: String, var city: String, var cardRef: String){


    public fun Save(context: Context) : Boolean {
        val saver : IOProgram = IOProgram()
        return saver.save(context, this, "userobj.conf")
    }

    public fun Load(context: Context) : Boolean {
        val reader : IOProgram = IOProgram()
        val tempObj = reader.load(context, "userobj.conf") as User

        if (tempObj == null) {
            return false
        } else {

            this.firstName = tempObj.firstName
            this.lastName = tempObj.lastName
            this.email = tempObj.email
            this.address = tempObj.address
            this.zipcode = tempObj.zipcode
            this.city = tempObj.city
            this.cardRef = tempObj.cardRef

            return true
        }

    }

}