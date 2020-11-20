package com.z.lib_core.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Creator:  chee
 * Date：2020/9/12 - 22:01
 * Desc：
 */
open class CoreFragmentPageAdapter(fm: FragmentManager, list: List<Fragment>) :
    FragmentStatePagerAdapter(fm) {

    var fragmentList: List<Fragment>? = null

    init {
        fragmentList = list
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList!!.get(position)
    }

}