package com.example.brandol

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.databinding.FragmentAvartarTabBinding

class AvartarTabFragment : Fragment() {

    lateinit var binding: FragmentAvartarTabBinding
    private var avartarList = ArrayList<Avartar>()
    private var avartarAdapter = AvartarRVAdapter(avartarList)
    private var handler = Handler(Looper.getMainLooper())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAvartarTabBinding.inflate(inflater,container,false)
        var view : View = binding.root

        initStuffList()
        clickStuffListener()
        touchStuffListener(view)
        //리사이클러뷰 연결
        binding.avartartabItemlistRv.layoutManager = GridLayoutManager(activity,4)
        binding.avartartabItemlistRv.adapter = avartarAdapter


        return view
    }

    private fun touchStuffListener(view: View) {
        binding.avartartabItemlistRv.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val childView: View? = rv.findChildViewUnder(e.x, e.y)
                val position = rv.getChildAdapterPosition(childView ?: view)
                when (e.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        handler.postDelayed({
                            AlertDialog.Builder(context)
                                .setTitle("${avartarList[position].title}")
                                .setMessage("${avartarList[position].descrp}")
                                .show()
                        }, 1000)
                        true
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        handler.removeCallbacksAndMessages(null)
                        true
                    }

                    else -> false
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }

        })
    }

    private fun clickStuffListener() {
        avartarAdapter.itemClickListener = object : ItemClickListener {
            override fun onItemClick(position: Int) {
                val stuff = avartarList[position]
                val avartarTabFragment = AvartarTabFragment()
                val bundle = Bundle()
                bundle.putInt("profile", stuff.image)
                avartarTabFragment.arguments = bundle
                Toast.makeText(activity, "${stuff.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initStuffList() {
        avartarList.apply {
            add(Avartar("신발", R.drawable.demo_shoes, "멋진 신발이다"))
            add(Avartar("데모", R.drawable.demo_pants, "데모"))
            add(Avartar("데모", R.drawable.demo_hair, "데모"))
            add(Avartar("데모", R.drawable.demo_skin, "데모"))
            add(Avartar("데모", R.drawable.demo_shirts, "데모"))
        }
    }

    companion object {
        fun newInstance(category: String):AvartarTabFragment{
            var fragment = AvartarTabFragment()
            var args = Bundle()
            args.putString("category", category)
            fragment.setArguments(args)

            return fragment
        }
    }
}