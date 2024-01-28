package com.example.brandol

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
        //클릭했을때 로직 구현
        clickStuffListener()
        //꾹 눌렀을 때 로직 구현
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
                //터치리스너를 중간에 가져와서 기능 구현
                when (e.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        handler.postDelayed({
                            val context = context
                            val dialog = CustomDetailInfoDialog(context!!)
                            dialog.setContentView(R.layout.dialog_detail_info)
                            //다이얼로그 위치 조정
                            val params = dialog.window?.attributes
                            params?.gravity = Gravity.TOP or Gravity.LEFT// 원하는 위치로 변경합니다.
                            params?.x = 100 // x 위치 조정
                            params?.y = 100 // y 위치 조정
                            dialog.window?.setAttributes(params);
                            dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                            dialog.show()


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
                stuff.image
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