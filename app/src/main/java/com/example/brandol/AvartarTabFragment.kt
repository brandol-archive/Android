package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brandol.databinding.FragmentAvartarTabBinding

class AvartarTabFragment : Fragment() {

    lateinit var binding: FragmentAvartarTabBinding
    private var items = ArrayList<Stuff>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAvartarTabBinding.inflate(inflater,container,false)
        var view : View = binding.root
        items.apply {
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
            add(Stuff("데모",R.drawable.justexp,"데모"))
        }
        binding.avartartabItemlistRv.layoutManager = GridLayoutManager(activity,4)
        binding.avartartabItemlistRv.adapter = AvartarRVAdapter(items)

        return view
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