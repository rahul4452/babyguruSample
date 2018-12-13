package com.example.poplify.baby_guru_sample.child_profile


import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.poplify.baby_guru_sample.R
import com.example.poplify.baby_guru_sample.adapter.FullDesc
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest.ChildProfileResponse
import kotlinx.android.synthetic.main.fragment_show__choose__method.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Show_Choose_Method : Fragment() {


    private var recyclerViewForOpenMethod:RecyclerView? = null
    private var regular = Typeface.createFromAsset(context!!.assets, "Comfortaa_Regular.ttf")
    private var regularMon= Typeface.createFromAsset(context!!.assets, "Montserrat-Regular.otf")

    lateinit var listenToSamArgs: String

    lateinit var toolbarHeading: String

    private lateinit var methodheading: String


    private lateinit var fullDescription: ArrayList<FullDesc>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getString("listenToSam")?.let {
            listenToSamArgs = it
        }

        arguments?.getString("header")?.let {
            toolbarHeading = it
        }

        arguments?.getString("methodName")?.let { 
            methodheading = it
        }

//        arguments?.getParcelableArrayList<FullDesc>("parceList")?.let {
//            fullDescription = it
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View =inflater.inflate(R.layout.fragment_show__choose__method, container, false)


        initlizeActivity(view)

        setupActivity()

        return view

    }

    private fun setupActivity() {


        chooseSam!!.typeface = regularMon


    }


    private var selectMethodTitle: TextView?=null
    private var chooseSam: TextView? = null

    private fun initlizeActivity(view: View) {

        chooseSam = view.findViewById(R.id.listenSamChoose)

        selectMethodTitle = view.findViewById(R.id.blueHeaderChoose)

        recyclerViewForOpenMethod = view.findViewById(R.id.open_choose_recycler)

        open_choose_recycler.layoutManager = LinearLayoutManager(context)


    }




}
