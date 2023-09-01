package com.example.busreservation

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set which file to use for UI
        setContentView(R.layout.activity_main)
        setAutoCompleteValuesForCities()
        setSwapButtonFunctionality()
        setDatePickerButtonFunctionality()
        setSearchButtonFunctionality()
    }

    private fun setAutoCompleteValuesForCities(){
        // Get a reference to the AutoCompleteTextView in the layout.
        val fromTextInput : AutoCompleteTextView = getFromAutoCompleteTextView()
        val toTextInput : AutoCompleteTextView = getToAutoCompleteTextView()

        // Get the string array.
        // List<Cities> cities = query...
        // List<String> citiesInString = cities...
        val cities: Array<out String> = resources.getStringArray(R.array.cities)
        // Create the adapter and set it to the AutoCompleteTextView.
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cities)

        fromTextInput.setAdapter(adapter)
        toTextInput.setAdapter(adapter)

    }

    private fun setSwapButtonFunctionality() {
        val swapButton: ImageButton = findViewById(R.id.switchButton)

        swapButton.setOnClickListener {
            val textInsideTextFromInput = getFromAutoCompleteTextView().text
            val testInsideToTextInput = getToAutoCompleteTextView().text
            getFromAutoCompleteTextView().text = testInsideToTextInput
            getToAutoCompleteTextView().text = textInsideTextFromInput
        }
    }

    private fun setDatePickerButtonFunctionality(){
        val datePickerButton: TextView = getDateTextView()

        datePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val todaysYear = calendar.get(Calendar.YEAR)
            val todaysMonth = calendar.get(Calendar.MONTH)
            val todaysDay = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val formattedDay = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
                    val formattedMonth = if (monthOfYear + 1 < 10) "0${monthOfYear + 1}" else "${monthOfYear + 1}"
                    val selectedDate = "$formattedDay/$formattedMonth/$year"
                    datePickerButton.text = selectedDate
                },
                todaysYear, todaysMonth, todaysDay
            )

            datePickerDialog.show()
        }
    }

    private fun setSearchButtonFunctionality() {
        val searchButton: Button = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            val intent = Intent(this, BusLines::class.java)
            intent.putExtra("FromInputText", getFromAutoCompleteTextView().text.toString())
            intent.putExtra("ToInputText", getToAutoCompleteTextView().text.toString())
            intent.putExtra("SelectedDate", getDateTextView().text.toString())
            startActivity(intent)
        }
    }

    private fun getFromAutoCompleteTextView(): AutoCompleteTextView {
        val fromAutoCompleteTextView : AutoCompleteTextView = findViewById(R.id.fromAutoCompleteTextView)
        return fromAutoCompleteTextView;
    }

    private fun getToAutoCompleteTextView(): AutoCompleteTextView {
        val toAutoCompleteTextView : AutoCompleteTextView = findViewById(R.id.toAutoCompleteTextView)
        return toAutoCompleteTextView
    }

    private fun getDateTextView(): TextView {
        val dateTextView : TextView = findViewById(R.id.editTextDate)
        return dateTextView
    }
}