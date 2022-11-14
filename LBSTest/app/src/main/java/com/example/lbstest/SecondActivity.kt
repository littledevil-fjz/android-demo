package com.example.lbstest

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng
import com.example.lbstest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var mbinding: ActivitySecondBinding
    private lateinit var mapView: MapView
    private lateinit var mLocationClient: LocationClient
    private lateinit var baiduMap: BaiduMap
    private  var isFirstLocate = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SDKInitializer.setAgreePrivacy(applicationContext,true)
        SDKInitializer.initialize(applicationContext)
        mbinding = ActivitySecondBinding.inflate(layoutInflater)


        //百度地图API定位要求
        LocationClient.setAgreePrivacy(true)
        mLocationClient = LocationClient(applicationContext)
        val myLocationListener = MyLocationListener()
        //注册定位监听器
        mLocationClient.registerLocationListener(myLocationListener)
        setContentView(mbinding.root)
        mapView = mbinding.bmapView;
        baiduMap = mapView.map
        baiduMap.isMyLocationEnabled = true
        //权限申请
        val permissionList:MutableList<String> = ArrayList<String>()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!permissionList.isEmpty()) {
            val permissions :Array<String> = permissionList.toTypedArray()
            ActivityCompat.requestPermissions(this,permissions,1)
        }else{
            requestLocation()
        }
    }

    private fun navigateTo(location: BDLocation) {
        if (isFirstLocate) {
            val ll = LatLng(location.latitude,location.longitude)
            var update = MapStatusUpdateFactory.newLatLng(ll)
            baiduMap.animateMapStatus(update)
            update = MapStatusUpdateFactory.zoomTo(16f)
            baiduMap.animateMapStatus(update)
            isFirstLocate = false
        }

        val locationBuilder = MyLocationData.Builder()
        locationBuilder.latitude(location.latitude)
        locationBuilder.longitude(location.longitude)
        val locationData = locationBuilder.build()
        baiduMap.setMyLocationData(locationData)
    }
    private fun requestLocation(){
        initLocation()
        mLocationClient.start()
    }
    //位置的实时更新
    private fun initLocation(){
        val option = LocationClientOption()
        //更新间隔
        option.setScanSpan(5000)
        //使用GPS进行定位
//        option.locationMode = LocationClientOption.LocationMode.Device_Sensors
        //精确位置信息
        option.setIsNeedAddress(true)
        mLocationClient.locOption = option
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1-> if (grantResults.size>0){
                for (resulst in grantResults) {
                    if (resulst != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"必须同意所有权限菜鸟使用本程序", Toast.LENGTH_SHORT).show()
                    }
                }
                requestLocation()
            }else {
                Toast.makeText(this,"发生未知错误", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient.stop()
        mapView.onDestroy()
        baiduMap.isMyLocationEnabled = false
    }
    inner class MyLocationListener: BDLocationListener {
        override fun onReceiveLocation(location: BDLocation) {
           if (location.locType == BDLocation.TypeGpsLocation || location.locType == BDLocation.TypeNetWorkLocation) {
               navigateTo(location)
           }

        }
    }

}