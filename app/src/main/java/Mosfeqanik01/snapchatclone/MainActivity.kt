package Mosfeqanik01.snapchatclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    var emailEditText:EditText? = null
    var passwordEditText:EditText? = null
    val mAuth=FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.EmailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        if(mAuth.currentUser != null){
            logIn()
        }

    }
    fun goclick(view:View){
        //check if we can log in the user
        mAuth.signInWithEmailAndPassword(emailEditText.toString(), passwordEditText.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   logIn()
                } else {
                    //else sign up the User
                    mAuth.createUserWithEmailAndPassword(emailEditText.toString(), passwordEditText.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                logIn()
                            } else {
                                Toast.makeText(baseContext, "Log in failed. Try Again",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

    }
    fun logIn(){
        //Move to the next Activity
        val intent = Intent(this,snapActivity::class.java)
        startActivity(intent)

    }
}
