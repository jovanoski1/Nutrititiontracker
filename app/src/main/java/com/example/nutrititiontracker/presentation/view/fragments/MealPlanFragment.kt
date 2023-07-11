package com.example.nutrititiontracker.presentation.view.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealType
import com.example.nutrititiontracker.databinding.FragmentMealPlanBinding
import com.example.nutrititiontracker.presentation.view.dialogs.MealPlanDialog
import com.example.nutrititiontracker.presentation.view.recycler.adapter.PlanGridAdapter
import com.example.nutrititiontracker.presentation.view.recycler.listeners.GridPlanClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealPlanMealClickListener
import org.koin.android.ext.android.inject
import java.util.*

class MealPlanFragment : Fragment() {

    private var _binding: FragmentMealPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PlanGridAdapter
    private var mealEntitiesOrg = listOf<MealEntity>()
    private val sharedPreferences: SharedPreferences by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        binding.sendBtn.setOnClickListener {
            val recipient = binding.emailEt.text.toString().trim()
            val subject = "Meal Plan".toString().trim()
            val message = formatEmail().toString().trim()

            sendEmail(recipient, subject, message)
            println(formatEmail())
        }
    }

    private fun initUi() {
        binding.listRv.layoutManager = GridLayoutManager(context, 4)

        adapter = PlanGridAdapter(object : GridPlanClickListener {
            override fun onItemClick(index: Int) {
                MealPlanDialog(object : MealPlanMealClickListener{
                    override fun onMyMealClick(mealEntity: MealEntity) {
                        val mealListCopy = mealEntitiesOrg.toMutableList()
                        val mealTypeValues:Array<MealType> = MealType.values()
                        val mealCopy = mealEntity.copy()
                        mealCopy.mealType = mealTypeValues[index%4]
                        mealListCopy[index] = mealCopy
                        mealEntitiesOrg = mealListCopy
                        adapter.submitList(mealEntitiesOrg)
                        println("USAOOOO")
                    }

                    override fun onMealClick(mealResponse: MealResponse) {
                        val mealTypeValues:Array<MealType> = MealType.values()

                        val mealListCopy = mealEntitiesOrg.toMutableList()
                        mealListCopy[index] = mealResponse.toMealEntity(sharedPreferences.getLong("userId", -1), mealTypeValues[index%4])
                        mealEntitiesOrg = mealListCopy
                        adapter.submitList(mealEntitiesOrg)
                    }

                }).show(parentFragmentManager, "Select meal dialog")
            }

        })

        binding.listRv.adapter = adapter

        val mealEntities = mutableListOf<MealEntity>()
        for (i in 0 until 28) {
            val mealEntity = MealEntity(
                id = 3352,
                name = i.toString(),
                category = null,
                plannedDate = Date(),
                mealType = MealType.BREAKFAST, // Replace with the appropriate meal type
                image = "https://www.themealdb.com/images/media/meals/sypxpx1515365095.jpg",
                instructions = null,
                urlToVideo = null,
                strIngredient1 = null,
                strIngredient2 = null,
                strIngredient3 = null,
                strIngredient4 = null,
                strIngredient5 = null,
                strIngredient6 = null,
                strIngredient7 = null,
                strIngredient8 = null,
                strIngredient9 = null,
                strIngredient10 = null,
                strIngredient11 = null,
                strIngredient12 = null,
                strIngredient13 = null,
                strIngredient14 = null,
                strIngredient15 = null,
                strIngredient16 = null,
                strIngredient17 = null,
                strIngredient18 = null,
                strIngredient19 = null,
                strIngredient20 = null,
                strMeasure1 = null,
                strMeasure2 = null,
                strMeasure3 = null,
                strMeasure4 = null,
                strMeasure5 = null,
                strMeasure6 = null,
                strMeasure7 = null,
                strMeasure8 = null,
                strMeasure9 = null,
                strMeasure10 = null,
                strMeasure11 = null,
                strMeasure12 = null,
                strMeasure13 = null,
                strMeasure14 = null,
                strMeasure15 = null,
                strMeasure16 = null,
                strMeasure17 = null,
                strMeasure18 = null,
                strMeasure19 = null,
                strMeasure20 = null,
                userId = 0 // Replace with the appropriate user ID
            )
            mealEntities.add(mealEntity)
        }

        mealEntitiesOrg = mealEntities
        adapter.submitList(mealEntitiesOrg)
    }
    fun MealResponse.toMealEntity(userId: Long, mealType: MealType): MealEntity {
        return MealEntity(
            name = strMeal,
            category = strCategory,
            plannedDate = Date(),
            mealType = mealType,
            image = strMealThumb,
            instructions = strInstructions,
            urlToVideo = strYoutube,
            strIngredient1 = strIngredient1,
            strIngredient2 = strIngredient2,
            strIngredient3 = strIngredient3,
            strIngredient4 = strIngredient4,
            strIngredient5 = strIngredient5,
            strIngredient6 = strIngredient6,
            strIngredient7 = strIngredient7,
            strIngredient8 = strIngredient8,
            strIngredient9 = strIngredient9,
            strIngredient10 = strIngredient10,
            strIngredient11 = strIngredient11,
            strIngredient12 = strIngredient12,
            strIngredient13 = strIngredient13,
            strIngredient14 = strIngredient14,
            strIngredient15 = strIngredient15,
            strIngredient16 = strIngredient16,
            strIngredient17 = strIngredient17,
            strIngredient18 = strIngredient18,
            strIngredient19 = strIngredient19,
            strIngredient20 = strIngredient20,
            strMeasure1 = strMeasure1,
            strMeasure2 = strMeasure2,
            strMeasure3 = strMeasure3,
            strMeasure4 = strMeasure4,
            strMeasure5 = strMeasure5,
            strMeasure6 = strMeasure6,
            strMeasure7 = strMeasure7,
            strMeasure8 = strMeasure8,
            strMeasure9 = strMeasure9,
            strMeasure10 = strMeasure10,
            strMeasure11 = strMeasure11,
            strMeasure12 = strMeasure12,
            strMeasure13 = strMeasure13,
            strMeasure14 = strMeasure14,
            strMeasure15 = strMeasure15,
            strMeasure16 = strMeasure16,
            strMeasure17 = strMeasure17,
            strMeasure18 = strMeasure18,
            strMeasure19 = strMeasure19,
            strMeasure20 = strMeasure20,
            userId = userId
        )
    }

    private fun formatEmail(): String{
        val builder = StringBuilder()
        val dayList = listOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        val groups = mealEntitiesOrg.chunked(4)
        var cnt = 0
        for (group in groups) {
            builder.append(dayList[cnt] +": \n")

            for (element in group) {
                if (element.id!=3352L){
                    builder.append(element.toString())
                }
            }
            cnt++
            builder.append("-----------\n")
        }
        return builder.toString()
    }

    private fun sendEmail(recipient:String, subject:String, message:String){

//        val intent = Intent(Intent.ACTION_SENDTO).apply {
//            data = Uri.parse("mailto:") // Specify the "mailto:" scheme
//            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient)) // Set the recipient email address
//            putExtra(Intent.EXTRA_SUBJECT, subject) // Set the email subject
//            putExtra(Intent.EXTRA_TEXT, message) // Set thmihae email body
//        }
//
//        if (context?.let { intent.resolveActivity(it.packageManager) } != null) {
//            context?.startActivity(intent) // Start the activity for sending email
//        } else {
//            // No email client app is available
//            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
//        }

//        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$recipient"))
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
//        emailIntent.putExtra(Intent.EXTRA_TEXT, message)
//        startActivity(Intent.createChooser(emailIntent, "Chooser Title"))

        val mIntent = Intent(Intent.ACTION_SENDTO)
        mIntent.type = "text/plain"
        mIntent.data = Uri.parse("mailto:")
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent,"Choose Email Client"))
        }catch (e:java.lang.Exception){
            println(e.message)
        }
    }
}