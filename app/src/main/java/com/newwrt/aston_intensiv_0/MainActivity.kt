package com.newwrt.aston_intensiv_0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val catName = Cat("Marsik", 5)

        catName.let { p ->
            val result = when (p.age) {
                in 1..9 -> Result.Young(p.name)
                in 10..20 -> Result.MiddleAged(p.name)
                else -> Result.Old(p.name)
            }

            run {
                val message = checkAge(result)

                message.also { msg ->
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                }
            }
        }

        //finish()
    }



    private fun checkAge(result: Result): String {
        return with(result) {
            when (this) {
                is Result.Young -> "$name is young"
                is Result.MiddleAged -> "$name is middle-aged"
                is Result.Old -> "$name is old"
            }
        }
    }

    sealed class Result {
        data class Young(val name: String) : Result()
        data class MiddleAged(val name: String) : Result()
        data class Old(val name: String) : Result()
    }

    data class Cat(val name: String, val age: Int)
}