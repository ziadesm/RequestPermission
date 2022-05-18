package com.app.permission_utils.view_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PermissionImagesViewPager(fm: FragmentManager?, fragments: ArrayList<Fragment>) :
    FragmentStatePagerAdapter(fm!!) {
    private var mFragments: ArrayList<Fragment> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    init {
        mFragments = fragments
    }
}