package com.example.mod4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.example.mod4.room.PostEntity
import com.example.mod4.room.PostViewModel
import com.example.mod4.room.PostViewModelFactory

class Popup(private val postEntity: PostEntity) : DialogFragment() {

    private lateinit var appViewModel: PostViewModel

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onStart() {
        super.onStart()
        requireDialog().window?.apply {
            setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        view?.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(16, 16, 16, 16)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_popup, container, false)
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = PostViewModelFactory.getInstance(requireContext())
        appViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        val btnUbah: MaterialButton = view.findViewById(R.id.btn_ubah)
        val btnHapus: MaterialButton = view.findViewById(R.id.btn_hapus)

       btnUbah.setOnClickListener {
            val intent = Intent(requireContext(), Update_Feed::class.java)
            intent.putExtra("post", postEntity)
            startActivity(intent)
            dismiss()
        }

        btnHapus.setOnClickListener {
            appViewModel.deletePost(postEntity)
            dismiss()
        }
    }

    companion object {
        const val TAG = "Popup"
    }
}