package com.example.fragmentactivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * FileName: FragmentClass
 * Author: Naomi
 * Date: 2020/6/30 23:31
 * Description:
 */
class LeftFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.left_fragment, container, false)
    }
}

class RightFragment : Fragment() {

    companion object {
        const val TAG = "RightFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "OnAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "OnCreateView")
        return inflater.inflate(R.layout.right_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "OnActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "OnStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d(TAG, "OnPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, "OnResume")
        super.onResume()
    }


    override fun onStop() {
        Log.d(TAG, "OnStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "OnDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "OnDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "OnDetach")
        super.onDetach()
    }


}

class AnotherRightFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.another_right_fragment, container, false)
    }
}

