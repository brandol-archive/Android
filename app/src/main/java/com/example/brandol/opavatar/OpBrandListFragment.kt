package com.example.brandol.opavatar

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.example.brandol.adaptor.RV.OpBrandRVAdapter
import com.example.brandol.collection.BrandData
import com.example.brandol.collection.OpBrand
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentOpBrandListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OpBrandListFragment : Fragment() {

    lateinit var binding: FragmentOpBrandListBinding
    private var opBrandList = ArrayList<OpBrand>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpBrandListBinding.inflate(layoutInflater,container,false)
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getOtherBrand("Bearer $token",1)
        call.enqueue(object : Callback<RetrofitClient2.ResponseBrand> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseBrand>,
                response: Response<RetrofitClient2.ResponseBrand>
            ) {
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("LHJ", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            for(i in 0..response.result.size-1) {
                                val brandId = response.result[i].brandId
                                val brandProfile = response.result[i].profileImage
                                val brandName = response.result[i].brandName
                                val description= response.result[i].description
                                val sequence = response.result[i].sequence
                                opBrandList.add(OpBrand(brandId,brandName,description,brandProfile,sequence))
                            }
                            var brandAdapter = OpBrandRVAdapter(opBrandList)
                            binding.brandListRv.adapter =  brandAdapter
                            binding.brandListRv.layoutManager = LinearLayoutManager(activity)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseBrand>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return binding.root
    }

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }
}