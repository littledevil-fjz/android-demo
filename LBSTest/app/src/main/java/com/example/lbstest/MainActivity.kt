package com.example.lbstest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.example.lbstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var positionText: TextView
    private lateinit var mLocationClient: LocationClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val startSecond = binding.startSecond
        startSecond.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, SecondActivity::class.java)
            startActivity(intent)
        }
        //百度地图API定位要求
        LocationClient.setAgreePrivacy(true)
        mLocationClient = LocationClient(this.applicationContext)
        val myLocationListener = MyLocationListener()
        //注册定位监听器
        mLocationClient.registerLocationListener(myLocationListener)
        setContentView(binding.root)
        positionText = binding.positionTextView;

        //权限申请
        val permissionList: MutableList<String> = ArrayList<String>()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!permissionList.isEmpty()) {
            val permissions: Array<String> = permissionList.toTypedArray()
            ActivityCompat.requestPermissions(this, permissions, 1)
        } else {
            requestLocation()
        }
    }

    private fun requestLocation() {
        initLocation()
        mLocationClient.start()
    }

    //位置的实时更新
    private fun initLocation() {
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
        when (requestCode) {
            1 -> if (grantResults.size > 0) {
                for (resulst in grantResults) {
                    if (resulst != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "必须同意所有权限菜鸟使用本程序", Toast.LENGTH_SHORT).show()
                    }
                }
                requestLocation()
            } else {
                Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    inner class MyLocationListener : BDLocationListener {
        override fun onReceiveLocation(location: BDLocation) {
            val currentPostion = StringBuffer()
            currentPostion.append("纬度：").append(location.latitude).append("\n")
            currentPostion.append("经度：").append(location.longitude).append("\n")
            currentPostion.append("国家：").append(location.country).append("\n")
            currentPostion.append("省：").append(location.province).append("\n")
            currentPostion.append("市：").append(location.city).append("\n")
            currentPostion.append("区：").append(location.district).append("\n")
            currentPostion.append("街道：").append(location.street).append("\n")
            currentPostion.append("定位方式：")
            if (location.locType == BDLocation.TypeGpsLocation) {
                currentPostion.append("GPS")
            } else if (location.locType == BDLocation.TypeNetWorkLocation) {
                currentPostion.append("网络")
            }
            positionText.setText(currentPostion)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient.stop()
    }
}