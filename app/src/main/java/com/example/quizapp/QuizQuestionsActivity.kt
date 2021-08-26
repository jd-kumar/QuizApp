package com.example.quizapp

import android.content.Intent
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrPosition : Int =1
    private var mQuestionsList : ArrayList<Questions>?=null
    private var mSelectedPosition: Int = 0
    private var mCorrectAnswers: Int =0
    private var mUserName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName=intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList =Constants.getQuestions()

        setQuestions()

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)

        submitButton.setOnClickListener(this)

    }
    private fun setQuestions(){

        val questions=mQuestionsList!![mCurrPosition-1]

        defaultOView()

        if(mCurrPosition==mQuestionsList!!.size){
            submitButton.text="FINISH"
        }else{
            submitButton.text="SUBMIT"
        }
        progressBar.max=mQuestionsList!!.size
        progressBar.progress=mCurrPosition

        progress.text="$mCurrPosition"+"/"+mQuestionsList!!.size

        question.text=questions!!.question

        questionImage.setImageResource(questions.image)

        option1.text=questions.option1

        option2.text=questions.option2

        option3.text=questions.option3

        option4.text=questions.option4
    }

    private fun defaultOView(){
        val options=ArrayList<TextView>()

        options.add(0,option1)
        options.add(1,option2)
        options.add(2,option3)
        options.add(3,option4)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.option1->{
                selectedOView(option1,1)
            }
            R.id.option2->{
                selectedOView(option2,2)
            }
            R.id.option3->{
                selectedOView(option3,3)
            }
            R.id.option4->{
                selectedOView(option4,4)
            }
            R.id.submitButton->{
                if(mSelectedPosition==0){
                    mCurrPosition++

                    when{
                        mCurrPosition<=mQuestionsList!!.size->{
                            setQuestions()
                        }else ->{
                            val intent=Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_Q,mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question =mQuestionsList?.get(mCurrPosition-1)
                    if(question!!.coption!=mSelectedPosition){
                        answerView(mSelectedPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.coption,R.drawable.correct_option_border_bg)

                    if(mCurrPosition==mQuestionsList!!.size){
                        submitButton.text="FINISH"
                    }else{
                        submitButton.text="Go to next question"
                    }

                    mSelectedPosition=0
                }
            }
        }
    }

    private fun answerView (answer:Int,drawableView:Int ){
        when(answer){
            1->{
                option1.background=ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2->{
                option2.background=ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3->{
                option3.background=ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4->{
                option4.background=ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }

    private fun selectedOView(tv:TextView,selectedONumber:Int){
        defaultOView()
        mSelectedPosition=selectedONumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }
}