//package com.example.brandol.adaptor
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.brandol.ItemClickListener
//import com.example.brandol.R
//import com.example.brandol.connection.RetrofitClient2
//import com.example.brandol.searchCategory.UserData
//
//class UserCategoryAdapter(private val userList: MutableList<UserData>)
//    : RecyclerView.Adapter<UserCategoryAdapter.UserViewHolder>() {
//
//    private val newitems = mutableListOf<RetrofitClient2.SearchDetailUserDto>()
//
//    var itemClickListener: ItemClickListener? = null
//    private val items = mutableListOf<String>()
//
//    fun addItems(newUserList: List<RetrofitClient2.SearchDetailUserDto>) {
//        newitems.clear()
//        newitems.addAll(newUserList)
//        notifyDataSetChanged()
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCategoryAdapter.UserViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_category, parent, false)
//        return UserViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bind(newitems[position])
//    }
//    override fun getItemCount(): Int {
//        return userList.size
//    }
//
////    var itemClickListener: ItemClickListener? = null
////    private val items = mutableListOf<String>()
////
////
////    // 외부에서 더미 데이터를 설정하는 메서드
////    fun setUserList(newUserList: List<UserData>) {
////        userList.clear()
////        userList.addAll(newUserList)
////        notifyDataSetChanged()
////    }
////
////
////    // 뷰홀더 생성
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
////        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_category, parent, false)
////        return UserViewHolder(view)
////    }
////
////    // 뷰홀더에 데이터 바인딩
////    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
////        holder.bind(userList[position])
////    }
////
////
////    // 아이템 개수 반환
////    override fun getItemCount(): Int {
////        return userList.size
////    }
//
//    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val userName: TextView = itemView.findViewById(R.id.user_name_tv)
//        private val userAvatar: ImageView = itemView.findViewById(R.id.user_character_iv) // 이미지뷰 추가
//
//        fun bind(userData: RetrofitClient2.SearchDetailUserDto) {
//            userName.text = userData.userName
//            Glide.with(itemView.context)
//                .load(userData.userAvatar) // userImage 또는 해당 필드에 맞게 수정
//                .into(userAvatar)
//
//            // 필요한 경우 여기에 다른 위젯들에 대한 데이터 바인딩을 추가할 수 있습니다.
//        }
//
//        init {
//            itemView.setOnClickListener {
//                itemClickListener?.onItemClick(adapterPosition)
//            }
//        }
//    }
//
//}

package com.example.brandol.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.connection.RetrofitClient2

class UserCategoryAdapter
    : RecyclerView.Adapter<UserCategoryAdapter.UserViewHolder>() {

    var itemClickListener: ItemClickListener? = null
    //private val items = mutableListOf<String>()
    private val items = mutableListOf<RetrofitClient2.SearchDetailUserDto>()

    // 데이터 추가 메서드
    fun addItem(item: List<RetrofitClient2.SearchDetailUserDto>) {
        //items.add(item)
        items.addAll(item)
        notifyDataSetChanged()
    }

    // 외부에서 더미 데이터를 설정하는 메서드
//    fun setUserList(newUserList: List<UserData>) {
//        userList.clear()
//        userList.addAll(newUserList)
//        notifyDataSetChanged()
//    }

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_category, parent, false)
        return UserViewHolder(view)
    }

    // 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return items.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.user_name_tv)
        private val userAvatar: ImageView = itemView.findViewById(R.id.user_character_iv) // 이미지뷰 추가

        // 데이터 바인딩 메서드
        fun bind(userData: RetrofitClient2.SearchDetailUserDto) {
            userName.text = userData.userName
            if (userData.userAvatar != null) {
                Glide.with(itemView.context)
                    .load(userData.userAvatar)
                    .into(userAvatar)
            } else {
                // Handle case where avatar is null
                // You might want to set a default image or hide the ImageView
                //userAvatar.setImageResource(R.drawable.default_avatar)
                // or
                // userAvatar.visibility = View.GONE
            }
//            Glide.with(itemView.context)
//                .load(userData.userImageResourceId)
//                .into(userAvatar)
            // 필요한 경우 여기에 다른 위젯들에 대한 데이터 바인딩을 추가할 수 있습니다.
        }

        init {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }
}
