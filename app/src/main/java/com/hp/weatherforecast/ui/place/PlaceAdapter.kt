package com.hp.weatherforecast.ui.place

import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hp.weatherforecast.R
import com.hp.weatherforecast.logic.model.Place
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
class PlaceAdapter(placeList: MutableList<Place?>?) :
    BaseQuickAdapter<Place?, BaseViewHolder>(R.layout.place_item, placeList) {
    override fun convert(holder: BaseViewHolder, item: Place?) {
        item?.let {
            holder.itemView.placeName.text = it.name
            holder.itemView.placeAddress.text = it.formatted_address
        }
    }
}