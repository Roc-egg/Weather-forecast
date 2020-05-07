package com.hp.weatherforecast.ui.place

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hp.weatherforecast.R
import com.hp.weatherforecast.logic.model.Place
import com.hp.weatherforecast.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.place_item.view.*

/**
 * 项目：    WeatherForecast
 * 类描述：
 * 创建人：  hp
 * 创建时间：2020/4/16 16:27
 * 修改人：  hp
 * 修改时间：2020/4/16 16:27
 * 修改备注：
 */
class PlaceAdapter(
    private val viewModel: PlaceViewModel,
    private val placeFragment: PlaceFragment
) :
    BaseQuickAdapter<Place?, BaseViewHolder>(R.layout.place_item, viewModel.placeList) {
    override fun convert(holder: BaseViewHolder, item: Place?) {
        item?.let {
            holder.itemView.placeName.text = it.name
            holder.itemView.placeAddress.text = it.formatted_address
        }
        holder.itemView.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            val place = viewModel.placeList[adapterPosition]
            place?.let {
                val activity = placeFragment.activity
                if (activity is WeatherActivity) {
                    activity.drawerLayout.closeDrawers()
                    activity.viewModel.locationLng = it.location.lng
                    activity.viewModel.locationLat = it.location.lat
                    activity.viewModel.placeName = it.name
                    activity.refreshWeather()
                } else {
                    val intent = Intent(context, WeatherActivity::class.java).apply {
                        putExtra("location_lng", it.location.lng)
                        putExtra("location_lat", it.location.lat)
                        putExtra("place_name", it.name)
                    }
                    placeFragment.startActivity(intent)
                    activity?.finish()
                }
                viewModel.savePlace(it)
            }

        }
    }
}