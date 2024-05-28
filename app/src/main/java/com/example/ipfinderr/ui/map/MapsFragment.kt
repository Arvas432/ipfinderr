package com.example.ipfinderr.ui.map

import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.ipfinderr.R
import com.example.ipfinderr.domain.search.IpResult
import com.google.gson.Gson


class MapsFragment : Fragment() {

    private lateinit var worldMap: ImageView
    private lateinit var pin: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        worldMap = view.findViewById(com.example.ipfinderr.R.id.world_map)
        pin = view.findViewById(R.id.pin)
        val input = Gson().fromJson<IpResult>(requireArguments().getString(FRAGMENT_KEY), IpResult::class.java)
        // Example latitude and longitude
        val latitude = input.latitude.toDouble()
        val longitude = input.longitude.toDouble()

        placePinOnMap(latitude, longitude)
    }

    private fun placePinOnMap(latitude: Double, longitude: Double) {
        // Convert latitude and longitude to x, y coordinates
        val point = latLngToPoint(latitude, longitude)

        // Get the dimensions of the ImageView
        worldMap.post {
            val values = FloatArray(9)
            worldMap.imageMatrix.getValues(values)

            // Image dimensions
            val imageWidth = values[Matrix.MSCALE_X] * worldMap.drawable.intrinsicWidth
            val imageHeight = values[Matrix.MSCALE_Y] * worldMap.drawable.intrinsicHeight

            // Position in ImageView
            val scaledX = point.x * imageWidth
            val scaledY = point.y * imageHeight

            // Offset for pin
            val pinX = scaledX + values[Matrix.MTRANS_X] - pin.width / 2
            val pinY = scaledY + values[Matrix.MTRANS_Y] - pin.height

            // Set pin position
            pin.x = pinX
            pin.y = pinY
            pin.visibility = View.VISIBLE
        }
    }

    private fun latLngToPoint(latitude: Double, longitude: Double): PointF {
        // Convert latitude and longitude to x, y coordinates on the image
        val x = ((longitude + 180) / 360).toFloat()
        val y = ((90 - latitude) / 180).toFloat()
        return PointF(x, y)
    }
    companion object{
        const val FRAGMENT_KEY = "FRAGMENT_KEY"
        fun newInstance(ipInput: IpResult) = MapsFragment().apply {
            arguments = bundleOf(FRAGMENT_KEY to Gson().toJson(ipInput))
        }
    }
}