package com.pra.interview.UI.fragment

import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pra.interview.UI.placeholder.PlaceholderContent
import com.pra.interview.data.model.Content
import com.pra.interview.databinding.FragmentItemDetailBinding
import java.util.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The placeholder content this fragment is presenting.
     */

    private var mContent: Content? = null
    private var toolbarLayout: CollapsingToolbarLayout? = null
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
          //  item = mContent
            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("object")) {
                 mContent = it.getParcelable("object")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout

        updateContent()
        rootView.setOnDragListener(dragListener)

        _binding?.btnPdf?.setOnClickListener {
            val format = "https://drive.google.com/viewerng/viewer?embedded=true&url="+mContent?.mediaUrl
            val fullPath: String = java.lang.String.format(Locale.ENGLISH, format, "PDF_URL_HERE")
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(fullPath))
            startActivity(browserIntent)
        }

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = mContent?.mediaTitleCustom

        // Show the placeholder content as text in a TextView.
        mContent?.let {
            _binding?.tvUrl?.movementMethod = LinkMovementMethod.getInstance();
            _binding?.tvTitle?.text = mContent?.mediaTitleCustom
            _binding?.tvDate?.text = mContent?.mediaDate?.dateString
            _binding?.tvUrl?.text = mContent?.mediaUrl

            if (mContent?.mediaUrl?.isNotEmpty()!!) {
                _binding?.btnPdf?.visibility = View.VISIBLE
            } else {
                _binding?.btnPdf?.visibility = View.GONE
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}