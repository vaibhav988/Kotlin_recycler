package com.example.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class fragadapter(fragmentmanager : FragmentManager , lifecycle: Lifecycle ,context : Context) :
    FragmentStateAdapter(fragmentmanager , lifecycle) {

    val context : Context = context
    override fun getItemCount(): Int {
        return 6

    }

    override fun createFragment(position: Int): Fragment {
       return when(position)
        {
            1->news_frag("in" , context )
            2-> news_frag("jp" , context)
            3-> news_frag("ng" , context)
            4-> news_frag("ie" ,context)
            5-> news_frag("il" , context)
           else ->{
               news_frag("id"  ,context)
            }
       }


    }


}