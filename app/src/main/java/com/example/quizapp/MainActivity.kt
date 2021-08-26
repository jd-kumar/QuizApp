package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN

        val btnStart=findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener{
            val editName=findViewById<TextView>(R.id.editName)

            if(editName.text.toString().isEmpty()){
                Toast.makeText(this,"Invalid entry",Toast.LENGTH_SHORT).show()
            }else{
                val intent=Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,editName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}