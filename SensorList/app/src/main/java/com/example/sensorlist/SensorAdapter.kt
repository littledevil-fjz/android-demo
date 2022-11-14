package com.example.sensorlist

import android.content.Context
import android.widget.ArrayAdapter
import android.view.ViewGroup
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.widget.TextView
import com.example.sensorlist.R
import com.example.sensorlist.SensorAdapter
import android.os.Build
import android.text.Html
import android.view.View

class SensorAdapter(context: Context?, private val resource: Int, items: List<MySensor?>?) :
    ArrayAdapter<MySensor?>(
        context!!, resource, items!!
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val newView: LinearLayout
        if (convertView == null) {
            newView = LinearLayout(context)
            val inflater = Context.LAYOUT_INFLATER_SERVICE
            val li: LayoutInflater
            li = context.getSystemService(inflater) as LayoutInflater
            li.inflate(resource, newView, true)
        } else {
            newView = convertView as LinearLayout
        }
        val nameView = newView.findViewById<View>(R.id.nameView) as TextView
        val vendorView = newView.findViewById<View>(R.id.vendorView) as TextView
        val typeView = newView.findViewById<View>(R.id.typeView) as TextView
        val versionView = newView
            .findViewById<View>(R.id.versionView) as TextView
        val maximumRangeView = newView
            .findViewById<View>(R.id.maximumRangeView) as TextView
        val minDelayView = newView
            .findViewById<View>(R.id.minDelayView) as TextView
        val powerView = newView.findViewById<View>(R.id.powerView) as TextView
        val resolutionView = newView
            .findViewById<View>(R.id.resolutionView) as TextView
        val unitsRangeView = newView
            .findViewById<View>(R.id.unitsRangeView) as TextView
        val unitsResolutionView = newView
            .findViewById<View>(R.id.unitsResolutionView) as TextView
        val unitsDelayView = newView
            .findViewById<View>(R.id.unitsDelayView) as TextView
        val unitsPowerView = newView
            .findViewById<View>(R.id.unitsPowerView) as TextView
        if (SDK < Build.VERSION_CODES.GINGERBREAD) {
            val minDelayLabel = newView
                .findViewById<View>(R.id.minDelayLabel) as TextView
            minDelayLabel.visibility = View.GONE
            minDelayView.visibility = View.GONE
            unitsDelayView.visibility = View.GONE
        }
        val mySensor = getItem(position)
        nameView.text = mySensor!!.name
        vendorView.text = mySensor.vendor
        typeView.text = mySensor.typeDescription
        versionView.text = mySensor.version.toString()
        maximumRangeView.text = mySensor.maximumRange.toString()
        if (SDK >= Build.VERSION_CODES.GINGERBREAD) minDelayView.text =
            mySensor.minDelay.toString()
        powerView.text = mySensor.power.toString()
        resolutionView.text = String.format("%f", mySensor.resolution)
        unitsRangeView.text = Html.fromHtml(mySensor.units)
        unitsResolutionView.text = Html.fromHtml(mySensor.units)
        if (SDK >= Build.VERSION_CODES.GINGERBREAD) unitsDelayView.text = Html.fromHtml(
            mySensor.delayUnits
        )
        unitsPowerView.text = Html.fromHtml(mySensor.powerUnits)
        return newView
    }

    companion object {
        private val SDK = Build.VERSION.SDK_INT
    }
}