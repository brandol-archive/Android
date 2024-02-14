package com.example.brandol.avatar

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import com.example.brandol.R
import com.example.brandol.adaptor.VP.AvatarVPAdapter
import com.example.brandol.chatting.MessageFragment
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentAvatarBinding
import com.example.brandol.dialog.CustomAnnonceInfoDialog
import com.example.brandol.dialog.CustomSaveDialog
import com.example.brandol.searchCategory.AvatarStoreCategoryFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.user.UserApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class AvatarFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentAvatarBinding
    var idList = ArrayList<Long>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarBinding.inflate(inflater, container, false)
        //아바타 캡쳐해서 이미지 저장
        saveBtnLogic()
        //아이템리스트 보여주기
        showtablayout()
        //메시지 화면으로 전환
        goMessage()
        //채팅 온 갯수 앞으로 보내기
        binding.avatarChattingQuantity.bringToFront()
        binding.avatarShopBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AvatarStoreCategoryFragment())
                .addToBackStack(null)
                .commit()
        }
        //앱 실행 후 한번만
        executeFunctionOnce()
        loadMyAvatar()
        //아바타에 옷힙히기 로직
        wearitem()
        return binding.root
    }

    private fun executeFunctionOnce() {
        val sharedPreferences =
            context?.getSharedPreferences("oneTime", AppCompatActivity.MODE_PRIVATE)
        val pref = sharedPreferences?.getBoolean("bool", false)
        if (pref == true) {
            showShortDialog()
        }
        sharedPreferences?.edit()?.putBoolean("bool", false)

    }

    //전에 저장했던 아바타 불러오기
    private fun loadMyAvatar() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getMyItemData("Bearer $token")
        call.enqueue(object : Callback<RetrofitClient2.ResponseItem> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseItem>,
                response: Response<RetrofitClient2.ResponseItem>
            ) {
                Log.d("LHJ", response.toString())
                if (response.isSuccessful) {
                    var response = response.body()
                    //Log.d("LHJ", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            if (response.result.size != 0) {
                                binding.avatarNoItemTv.text = ""
                            }
                            for (i in 0..response.result.size - 1) {
                                val itemId: Long = response.result[i].itemId
                                val part: String = response.result[i].part
                                val image: String = response.result[i].image
                                val wearingImage: String = response.result[i].wearingImage
                                val wearing: Boolean = response.result[i].wearing
                                if (wearing) {
                                    idList.add(itemId)
                                    when (part) {
                                        "TOP" -> Glide.with(binding.avatarBaseAvatarShirts.context)
                                            .load(image)
                                            .into(binding.avatarBaseAvatarShirts)

                                        "BOTTOM" -> Glide.with(binding.avatarBaseAvatarPants.context)
                                            .load(image)
                                            .into(binding.avatarBaseAvatarPants)

                                        "SHOES" -> Glide.with(binding.avatarBaseAvatarPants.context)
                                            .load(image)
                                            .into(binding.avatarBaseAvatarShoes)

                                        "HAIR" -> Glide.with(binding.avatarBaseAvatarPants.context)
                                            .load(image)
                                            .into(binding.avatarBaseAvatarHair)

                                        "SKIN" -> Glide.with(binding.avatarBaseAvatarPants.context)
                                            .load(image)
                                            .into(binding.avatarBaseAvatarSkin)
                                    }
                                }
                            }
                        }
                    }

                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseItem>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("LHJ", errorMessage)
            }

        })
    }

    private fun wearitem() {
        childFragmentManager.setFragmentResultListener(
            "requestKey",
            viewLifecycleOwner
        ) { key, bundle ->
            val itemId = bundle.getLong("id")
            val itemPart = bundle.getString("part")
            val itemWearingImage = bundle.getString("wearingImage")
            val check = bundle.getBoolean("check")
            val dup = bundle.getLong("dupPosition")

            Log.d("LHJ", check.toString())
            if(dup<900) {
                if (check == true) {
                    idList.add(itemId)
                    idList.remove(dup)
                    Log.d("LHJ", idList.toString())
                    when (itemPart) {
                        "TOP" -> Glide.with(binding.avatarBaseAvatarShirts.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarShirts)

                        "BOTTOM" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarPants)

                        "SHOES" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarShoes)

                        "HAIR" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarHair)

                        "SKIN" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarSkin)
                    }
                } else {
                    idList.remove(itemId)
                    Log.d("LHJ", idList.toString())
                    when (itemPart) {
                        "TOP" -> binding.avatarBaseAvatarShirts.setImageResource(R.drawable.base_item_shirts)
                        "BOTTOM" -> binding.avatarBaseAvatarPants.setImageResource(R.drawable.base_item_pants)
                        "SHOES" -> binding.avatarBaseAvatarShoes.setImageResource(R.drawable.no1_item_shoes)
                        "HAIR" -> binding.avatarBaseAvatarHair.setImageResource(R.drawable.base_item_hair)
                        "SKIN" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no1_item_skin)
                    }
                }

            }else{
                if (check == true) {
                    idList.add(itemId)
                    Log.d("LHJ", idList.toString())
                    when (itemPart) {
                        "TOP" -> Glide.with(binding.avatarBaseAvatarShirts.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarShirts)

                        "BOTTOM" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarPants)

                        "SHOES" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarShoes)

                        "HAIR" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarHair)

                        "SKIN" -> Glide.with(binding.avatarBaseAvatarPants.context)
                            .load(itemWearingImage)
                            .into(binding.avatarBaseAvatarSkin)
                    }
                } else {
                    idList.remove(itemId)
                    Log.d("LHJ", idList.toString())
                    when (itemPart) {
                        "TOP" -> binding.avatarBaseAvatarShirts.setImageResource(R.drawable.base_item_shirts)
                        "BOTTOM" -> binding.avatarBaseAvatarPants.setImageResource(R.drawable.base_item_pants)
                        "SHOES" -> binding.avatarBaseAvatarShoes.setImageResource(R.drawable.no1_item_shoes)
                        "HAIR" -> binding.avatarBaseAvatarHair.setImageResource(R.drawable.base_item_hair)
                        "SKIN" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no1_item_skin)
                    }
                }
            }
        }
    }

    private fun saveBtnLogic() {
        //저장하기 버튼 클릭시
        binding.avatarSaveBtn.setOnClickListener {
            //아바타 이미지 갤러리에 저장
            val avatarImage = binding.avatarBaseAvatarFl
            val bitmap = drawBitmap(avatarImage)
            val uri = saveImage(bitmap)
            //다이얼로그 띄우기
            val dialog = CustomSaveDialog(binding.avatarChattingQuantityTv.context)
            dialog.show()

            //착용한 아이템 아이디와 이미지 보ㄱ
            val filePath = getPathFromUri(binding.avatarBaseAvatarPants.context, uri)
            val file = File(filePath)
            val requestFile = file.asRequestBody("image/png".toMediaTypeOrNull())
            val avatarImagePart =
                MultipartBody.Part.createFormData("avatarImage", file.name, requestFile)

            //배열 중복 제거
            val token = getCurrentToken(requireContext())
            val call = RetrofitObject.getRetrofitService.updateAvatar(
                "Bearer $token", idList, avatarImagePart
            )
            Log.d("LHJ", "idList : " + idList.toString())

            call.enqueue(object : Callback<RetrofitClient2.ResponseWear> {
                override fun onResponse(
                    call: Call<RetrofitClient2.ResponseWear>,
                    response: Response<RetrofitClient2.ResponseWear>
                ) {
                    Log.d("LHJ", response.toString())
                    if (response.isSuccessful) {
                        val response = response.body()
                        Log.d("LHJ", response.toString())
                        if (response != null) {
                            if (response.isSuccess) Log.d("LHJ", response.result)
                        }
                    }
                }

                override fun onFailure(call: Call<RetrofitClient2.ResponseWear>, t: Throwable) {
                    val errorMessage = "Call Failed: ${t.message}"
                    Log.d("LHJ", errorMessage)
                }

            })
        }
    }

    fun getPathFromUri(context: Context, uri: Uri): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val columnIndex = cursor.getColumnIndex(projection[0])
            filePath = cursor.getString(columnIndex)
            cursor.close()
        }
        return filePath
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

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    fun saveImage(bitmap: Bitmap): Uri {
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
                    parentFragmentManager.setFragmentResult(
                        "avatarImage",
                        bundleOf("bundlekey" to uri.toString())
                    )
                }
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return uri!!
    }
}
