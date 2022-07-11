package com.example.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Articles
import com.example.example.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [news_frag.newInstance] factory method to
 * create an instance of this fragment.
 */
class news_frag(country :String , context : Context) : Fragment() {
    // TODO: Rename and change types of parameters



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    val country : String = country
    val context1 : Context = context
    var list_of_news : ArrayList<Articles> = ArrayList<Articles>()
    val customAdapter: CustomAdapter = CustomAdapter(list_of_news ,context1)
    val filterd_list : ArrayList<Articles> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_news_frag, container, false)
        val progrssbar : ProgressBar = view.findViewById(R.id.progress)
        progrssbar.visibility = View.INVISIBLE



        //Recyclerview and adapter declaration
        val recycler: RecyclerView = view.findViewById(R.id.new_recycler)
        recycler.layoutManager = LinearLayoutManager(context1)
        recycler.adapter = customAdapter
        fetch_the_news( country , progrssbar , recycler )



        return view

    }

    fun filter_the_list(text :String)
    {
        filterd_list.clear()
        for(article in list_of_news)
        {
            if(article.title?.contains(text) == true || article.description?.contains(text) == true)
            {
                filterd_list.add(article)
            }
        }
        list_of_news = filterd_list;
        customAdapter.notifyDataSetChanged()
    }


    fun fetch_the_news(country:String, progrss : ProgressBar, recycler : RecyclerView)
    {
        recycler.visibility = View.INVISIBLE
        progrss.visibility = View.VISIBLE
        val retrofit_instance = Retrofit.Builder().baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val data  = retrofit_instance.create( getdata :: class.java)
        val news = data.getnews(country , API.API_KEY)

//        GlobalScope.launch {
//            val result = data.getnews(str , Api_key)
//            recycler.visibility = View.VISIBLE
//            progrss.visibility = View.INVISIBLE
//            val fetched_list = result.body()?.articles
//            list_of_news.clear()
//            if (fetched_list != null) {
//                list_of_news.addAll(fetched_list)
//            }
//
////            customAdapter.notifyDataSetChanged()
//
//        }



        news.enqueue(object : Callback<Model?> {
            override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                progrss.visibility = View.INVISIBLE
                val model : Model? = response.body()
                val getarticles :ArrayList<Articles>? = model?.articles
                if (getarticles != null) {
                    list_of_news.clear()
                    list_of_news.addAll(getarticles)
                    Log.d("API1" , list_of_news.toString())
                    recycler.visibility = View.VISIBLE
                    customAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Model?>, t: Throwable) {
                progrss.visibility = View.INVISIBLE

                Log.d("API" , "something went wrong")
            }

        })


    }



}