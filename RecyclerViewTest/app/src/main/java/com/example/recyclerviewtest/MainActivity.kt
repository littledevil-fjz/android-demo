package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruits()
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        binding.recyclerView.adapter = adapter
    }
    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit(getRandomLengthString("Apple"),R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthString("Banana"),R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthString("Orange"),R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthString("Cherry"),R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthString("Grape"),R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLengthString("Mango"),R.drawable.mango_pic))
            fruitList.add(Fruit(getRandomLengthString("Pear"),R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLengthString("Peanapple"),R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"),R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLengthString("WaterMelon"),R.drawable.watermelon_pic))
        }
    }
    private fun getRandomLengthString(str:String) :String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}