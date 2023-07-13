package com.example.memepicturesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.memepicturesapp.adapter.Rwadapter
import com.example.memepicturesapp.databinding.ActivityMainBinding
import com.example.memepicturesapp.databinding.RecviewBinding
import com.example.memepicturesapp.models.Meme
import com.example.memepicturesapp.utils.RetroInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rwadapter:Rwadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetroInstance.api.getMemes()
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_SHORT)
                    .show()

                return@launch
            }
           if (response.isSuccessful&&response.body()!=null){
               withContext(Dispatchers.Main){
                   val memeList:List<Meme> =response.body()!!.data.memes
                   binding.apply {
                       progressBar.visibility= View.GONE
                       rwadapter=Rwadapter(memeList)
                       recyclerView.adapter=rwadapter
                       recyclerView.layoutManager=StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)

                   }

               }
           }
        }
    }
}