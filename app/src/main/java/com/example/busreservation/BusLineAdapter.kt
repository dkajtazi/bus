package com.example.busreservation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BusLineAdapter(private val busLines: List<BusLine>) : RecyclerView.Adapter<BusLineAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lineNameTextView: TextView = itemView.findViewById(R.id.lineNameTextView)
        val price : TextView = itemView.findViewById(R.id.priceTextView)
        val fromCity: TextView = itemView.findViewById(R.id.fromCityTextView)
        val toCity: TextView = itemView.findViewById(R.id.toCityTextView)
        val departureTimeTextView: TextView = itemView.findViewById(R.id.departureTimeTextView)
        val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_bus_line, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val busLine = busLines[position]
        holder.lineNameTextView.text = busLine.busLineName
        holder.price.text = "${busLine.price} \u20AC"
        holder.fromCity.text = "From: ${busLine.from}"
        holder.toCity.text = "To: ${busLine.to}"
        holder.departureTimeTextView.text = "Departure: ${busLine.departureTime}"
        holder.arrivalTimeTextView.text = "Arrival: ${busLine.arrivalTime}"

    }

    override fun getItemCount(): Int {
        return busLines.size
    }
}