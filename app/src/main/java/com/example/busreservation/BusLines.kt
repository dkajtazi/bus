package com.example.busreservation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BusLines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_line)

        val recyclerView: RecyclerView = findViewById(R.id.busLinesRecyclerView)
        val noBusLinesTextView: TextView = findViewById(R.id.noBusLinesTextView)

        val fromCity = intent.getStringExtra("FromInputText") ?: ""
        val toCity = intent.getStringExtra("ToInputText") ?: ""
        val selectedDate = intent.getStringExtra("SelectedDate") ?: ""


        val busLines = fetchBusLines(fromCity, toCity, selectedDate)

        if (busLines.isEmpty()) {
            recyclerView.visibility = View.GONE
            noBusLinesTextView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            noBusLinesTextView.visibility = View.GONE

            val adapter = BusLineAdapter(busLines)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun fetchBusLines(fromCity: String, toCity: String, selectedDate: String): List<BusLine> {
        // Simulate fetching bus lines from city A to city B
        // Replace this with actual data retrieval logic
        val allBusLines = listOf(
            BusLine(1,"Endriti Tours","Ferizaj", "Prishtine","01/09/2023","08:00", "09:00", 1.5),
            BusLine(2,"Endriti Tours","Ferizaj", "Prishtine","01/09/2023","15:00", "16:00", 1.5),
            BusLine(3,"Dritoni Tours","Ferizaj", "Prishtine","01/09/2023","09:30", "10:30", 1.5),
            BusLine(4,"Dritoni Tours","Ferizaj", "Prishtine","01/09/2023","19:30", "20:30", 1.5),
            BusLine(5,"Mendimi Tours","Ferizaj", "Prishtine","01/09/2023","17:45", "18:45", 1.5),
            BusLine(6,"Zhabari Tours","Ferizaj", "Gjilan","01/09/2023","09:00", "10:00", 1.5),
            BusLine(7,"Gmica Tours","Prishtine", "Mitrovice","01/09/2023","12:00", "14:00", 3.0),
            BusLine(8,"Dritoni Tours","Prishtine", "Gjilan","01/09/2023","10:00", "11:00", 1.5),
            BusLine(9,"Medina Tours","Prishtine", "Gjakove","01/09/2023","09:00", "12:00", 4.0),
            BusLine(10,"Lindi Tours","Prishtine", "Peje","01/09/2023","15:00", "17:00", 2.0),
            BusLine(11,"Rrezoni Tours","Mitrovice", "Prishtine","01/09/2023","08:00", "10:00", 3.0),
            BusLine(12,"Drita Tours","Gjilan", "Prishtine","01/09/2023","08:00", "09:00", 1.5),
            BusLine(13,"Besimi Tours","Gjakove", "Prishtine","01/09/2023","09:00", "12:00", 4.0),
            BusLine(14,"Vala Tours","Peje", "Prishtine","01/09/2023","08:00", "10:00", 2.0),
        )
        return allBusLines.filter { busLine ->
            busLine.from == fromCity && busLine.to == toCity && busLine.departureDate == selectedDate
        }.sortedBy { busLine ->
            // Convert departureTime to a comparable format, such as minutes from midnight
            val parts = busLine.departureTime.split(":")
            val hours = parts[0].toInt()
            val minutes = parts[1].split(" ")[0].toInt()
            hours * 60 + minutes
        }
    }
}

