package com.example.brandol.avatar


import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.adaptor.RV.AvatarRVAdapter
import com.example.brandol.collection.ItemModel
import com.example.brandol.databinding.FragmentAvatarTabBinding
import com.example.brandol.dialog.CustomDetailInfoDialog


class AvatarTabFragment : Fragment(), ItemClickListener {


    lateinit var binding: FragmentAvatarTabBinding
    private var itemList = ArrayList<ItemModel>()
    private var category: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarTabBinding.inflate(inflater, container, false)
        //카테고리에 따라 탭레이아웃 분배
        splitByCategory()

        //리사이클러뷰 연결
        binding.avatartabItemlistRv.layoutManager = GridLayoutManager(context, 4)


        return binding.root
    }

    private fun splitByCategory() {
        category = arguments?.getString("category")
        when (category) {
            "전체" -> setAllItemTab()
            "피부" -> setTabByCategory("피부")
            "헤어" -> setTabByCategory("헤어")
            "상의" -> setTabByCategory("상의")
            "하의" -> setTabByCategory("하의")
            "신발" -> setTabByCategory("신발")
        }
    }

    //아바타 옷입히는 로직 구현
    override fun onItemClick(position: Int) {
        //클릭시 이름을 보내서 이름을 매개로 정보를 받게
        val itemobject = itemList.get(position)
        val itemName = itemobject.itemName
        // Use the Kotlin extension in the fragment-ktx artifact.
        parentFragmentManager.setFragmentResult("requestKey", bundleOf("bundleKey" to itemName))

    }
    override fun showCustomDialog(position: Int) {
        val itemobject = itemList.get(position)
        val context = context
        val dialog = CustomDetailInfoDialog(context!!,itemobject.itemName,itemobject.brandLogoRes,)
        //다이얼로그 위치 조정
        val params = dialog.window?.attributes
        params?.gravity = Gravity.TOP or Gravity.LEFT// 원하는 위치로 변경합니다.
        params?.x = 100 // x 위치 조정
        params?.y = 100 // y 위치 조정
        dialog.window?.setAttributes(params);
        //배경 어두워지는 효과 제거
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.show()
    }

    private fun setAllItemTab() {
        itemList.clear()
        itemList.apply {
            add(ItemModel(R.drawable.demo_shoes, "Brand1", "신발", "신발", "item1_info", "100p"))
            add(ItemModel(R.drawable.demo_pants, "Brand1", "바지", "하의", "item1_info", "100p"))
            add(ItemModel(R.drawable.demo_hair, "Brand1", "헤어", "헤어", "item1_info", "100p"))
            add(ItemModel(R.drawable.demo_skin, "Brand1", "피부", "피부", "item1_info", "100p"))
            add(ItemModel(R.drawable.demo_shirts, "Brand1", "피부", "상의", "item1_info", "100p"))
            add(ItemModel(R.drawable.no1_item_skin, "Brand1", "피부", "피부1", "item1_info", "100p"))
            add(ItemModel(R.drawable.no2_item_skin, "Brand1", "피부", "피부2", "item1_info", "100p"))
            add(ItemModel(R.drawable.no3_item_skin, "Brand1", "피부", "피부3", "item1_info", "100p"))
            add(ItemModel(R.drawable.no4_item_skin, "Brand1", "피부", "피부4", "item1_info", "100p"))
            add(ItemModel(R.drawable.no5_item_skin, "Brand1", "상의", "피부5", "item1_info", "100p"))
        }
        var avatarAdapter = AvatarRVAdapter(itemList, this)
        binding.avatartabItemlistRv.adapter =avatarAdapter
        avatarAdapter.notifyDataSetChanged()
    }
    private fun setTabByCategory(category: String) {
        itemList.clear()
        when (category) { //여기에 나중에 category와 같은 분류를 같고 있는 데이터를 가져오는 로직 구현하면 될듯
            "피부"-> itemList.add(ItemModel(R.drawable.demo_hair, "Brand1", "피부", "Item1", "item1_info", "100p"))
            "헤어"->itemList.add(ItemModel(R.drawable.demo_skin, "Brand1", "헤어", "Item1", "item1_info", "100p"))
            "상의"->itemList.add(ItemModel(R.drawable.demo_shirts, "Brand1", "상의", "Item1", "item1_info", "100p"))
            "하의"->itemList.add(ItemModel(R.drawable.demo_pants, "Brand1", "하의", "Item1", "item1_info", "100p"))
            "신발"->itemList.add(ItemModel(R.drawable.demo_shoes, "Brand1", "신발", "Item1", "item1_info", "100p"))
        }
        var avatarAdapter = AvatarRVAdapter(itemList, this)
        binding.avatartabItemlistRv.adapter =avatarAdapter
        avatarAdapter.notifyDataSetChanged()
    }

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("userToken", null)
    }
    companion object {
        fun newInstance(category: String): AvatarTabFragment {
            var fragment = AvatarTabFragment()
            var args = Bundle()
            args.putString("category", category)
            fragment.arguments = args

            return fragment
        }
    }
}