package com.example.quizapp

object Constants{

    const val USER_NAME: String = "user_name"
    const val TOTAL_Q:String ="total_questions"
    const val CORRECT_ANSWERS:String ="correct_answers"

    fun getQuestions(): ArrayList<Questions>{
        val questionList= ArrayList<Questions>()

        val que1=Questions(
            1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_australia,
            "Australia",
            "USA",
            "India",
            "Germany",
            1)
        questionList.add(que1)

        val que2=Questions(
            2,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_india,
            "Australia",
            "USA",
            "India",
            "Germany",
            3)
        questionList.add(que2)

        val que3=Questions(
            3,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_fiji,
            "Fiji",
            "Belgium",
            "Denmark",
            "Germany",
            1)
        questionList.add(que3)

        val que4=Questions(
            4,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_brazil,
            "Denmark",
            "Brazil",
            "Kuwait",
            "Argentina",
            2)
        questionList.add(que4)

        return questionList
    }
}