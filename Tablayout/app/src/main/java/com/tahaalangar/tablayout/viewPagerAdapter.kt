package com.tahaalangar.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class viewPagerAdapter(fn:FragmentManager):FragmentStatePagerAdapter(fn) {

    private  val mFragmentList=ArrayList<Fragment>()
    private  val mFragmentTitleList=ArrayList<String>()

    override fun getCount()=mFragmentList.size

    override fun getItem(position: Int)=mFragmentList[position]

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }
    fun addFragment(fragment: Fragment,title:String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}