package com.example.brandol.avatar

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.brandol.ItemClickListener
import com.example.brandol.chatting.MessageFragment
import com.example.brandol.R
import com.example.brandol.adaptor.VP.AvatarVPAdapter
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentAvatarBinding
import com.example.brandol.dialog.CustomAnnonceInfoDialog
import com.example.brandol.dialog.CustomSaveDialog
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Callback
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class AvatarFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentAvatarBinding
    var idList: MutableList<Long> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarBinding.inflate(inflater, container, false)
        //아바타 캡쳐해서 이미지 저장
        saveAvatarLogic()
        //짧은 다이얼로그 보여주기
        showShortDialog()
        //아이템리스트 보여주기
        showtablayout()
        //메시지 화면으로 전환
        goMessage()
        //채팅 온 갯수 앞으로 보내기
        binding.avatarChattingQuantity.bringToFront()
        //아바타에 옷힙히기 로직
        wearitem()

        return binding.root
    }

    private fun wearitem() {
        childFragmentManager.setFragmentResultListener(
            "requestKey",
            viewLifecycleOwner
        ) { key, bundle ->
            val itemId = bundle.getLong("id")
            val itemPart = bundle.getString("part")
            val itemImage = bundle.getString("image")
            val check = bundle.getBoolean("check")
            Log.d("LhJ",check.toString())
            if (check) {
                idList.add(itemId)
                Log.d("LHJ",idList.toString())
                when (itemPart) {
                    "TOP" -> Glide.with(binding.avatarBaseAvatarShirts.context).load(itemImage)
                        .into(binding.avatarBaseAvatarShirts)

                    "BOTTOM" -> Glide.with(binding.avatarBaseAvatarPants.context).load(itemImage)
                        .into(binding.avatarBaseAvatarPants)

                    "SHOES" -> Glide.with(binding.avatarBaseAvatarPants.context).load(itemImage)
                        .into(binding.avatarBaseAvatarShoes)

                    "HAIR" -> Glide.with(binding.avatarBaseAvatarPants.context).load(itemImage)
                        .into(binding.avatarBaseAvatarHair)

                    "SKIN" -> Glide.with(binding.avatarBaseAvatarPants.context).load(itemImage)
                        .into(binding.avatarBaseAvatarSkin)
                }
            } else {
                idList.remove(itemId)
                Log.d("LHJ",idList.toString())
                when (itemPart) {
                    "TOP" -> binding.avatarBaseAvatarShirts.setImageResource(R.drawable.base_item_shirts)
                    "BOTTOM" -> binding.avatarBaseAvatarPants.setImageResource(R.drawable.base_item_pants)
                    "SHOES" -> binding.avatarBaseAvatarShoes.setImageResource(R.drawable.base_item_shoes)
                    "HAIR" -> binding.avatarBaseAvatarHair.setImageResource(R.drawable.base_item_hair)
                    "SKIN" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no1_item_skin)
                }
            }
        }
    }
    private fun saveAvatarLogic() {
        //저장하기 버튼 클릭시
        binding.avatarSaveBtn.setOnClickListener {
            //아바타 이미지 갤러리에 저장
            val avatarImage = binding.avatarBaseAvatarFl
            val bitmap = drawBitmap(avatarImage)
            saveImage(bitmap)
            //다이얼로그 띄우기
            val dialog = CustomSaveDialog(binding.avatarChattingQuantityTv.context)
            dialog.show()


            val imageString = bitmapToBase64(bitmap)
            val token = getCurrentToken(requireContext())

            val call = RetrofitObject.getRetrofitService.wearMyItem("Bearer $token",RetrofitClient2.ReequestWear(idList,imageString))
            //call.enqueue(object : Callback<RetrofitClient2.ResponseLogin>

        }
    }
    fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    private fun goMessage() {
        binding.avatarChattingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MessageFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }
    }

    private fun showShortDialog() {
        val dialog = CustomAnnonceInfoDialog(binding.avatarChattingQuantityTv.context)
        dialog.setContentView(R.layout.dialog_announce_info)

        val params = dialog.window?.attributes
        params?.gravity = Gravity.TOP or Gravity.LEFT // 원하는 위치로 변경합니다.
        params?.x = 100 // x 위치 조정
        params?.y = 450 // y 위치 조정
        dialog.window?.setAttributes(params);
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.show()
    }


    private fun showtablayout() {
        val tabElement = arrayOf("전체", "피부", "헤어", "상의", "하의", "신발")

        //뷰페이저 어댑터 연결
        val pagerAdapter = AvatarVPAdapter(childFragmentManager, lifecycle)
        binding.avatarItemlistVp.adapter = pagerAdapter

        //탭 레이아웃과 뷰페이저 연동
        TabLayoutMediator(binding.avatarCategoryTl, binding.avatarItemlistVp) { tab, position ->
            // 탭의 텍스트 설정
            tab.text = tabElement[position]
        }.attach()
    }


    fun drawBitmap(view: View): Bitmap {
        //View의 너비와 높이를 측정하여 비트맵 생성
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bitmap에 뷰를 그린다.
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        return bitmap
    }
    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    fun saveImage(bitmap: Bitmap) {
        //파일이름
        val fileName = System.currentTimeMillis().toString() + ".png" // 파일이름 현재시간.png

        /*
        * ContentValues() 객체 생성.
        * ContentValues는 ContentResolver가 처리할 수 있는 값을 저장해둘 목적으로 사용된다.
        * */
        val contentValues = ContentValues()
        contentValues.apply {
            put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/ImageSave") // 경로 설정
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName) // 파일이름을 put해준다.
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            put(MediaStore.Images.Media.IS_PENDING, 1) // 현재 is_pending 상태임을 만들어준다.
            // 다른 곳에서 이 데이터를 요구하면 무시하라는 의미로, 해당 저장소를 독점할 수 있다.
        }
        val contentResolver = activity?.contentResolver
        // 이미지를 저장할 uri를 미리 설정해놓는다.
        val uri =
            contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {
            if (uri != null) {
                val image = contentResolver.openFileDescriptor(uri, "w", null)
                // write 모드로 file을 open한다.

                if (image != null) {
                    val fos = FileOutputStream(image.fileDescriptor)
                    val a = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos).toString()

                    //비트맵을 FileOutputStream를 통해 compress한다.
                    fos.close()
                    Log.d("LHJ", a)
                    contentValues.clear()
                    contentValues.put(MediaStore.Images.Media.IS_PENDING, 0) // 저장소 독점을 해제한다.
                    contentResolver.update(uri, contentValues, null, null)
                    parentFragmentManager.setFragmentResult("avatarImage", bundleOf("bundlekey" to uri.toString()))
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

