package com.pra.interview.UI.fragment.ListScreen

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pra.interview.R
import com.pra.interview.UI.BaseFragment
import com.pra.interview.data.model.Content
import com.pra.interview.databinding.FragmentItemListBinding
import com.pra.myapplication.UI.Listener.OnItemClickListener
import com.pra.myapplication.UI.Util.ViewModelFactory
import com.pra.myapplication.UI.adapter.TitleAdapter

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

class ItemListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    private val unhandledKeyEventListenerCompat =
        ViewCompat.OnUnhandledKeyEventListenerCompat { v, event ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            false
        }

    private var mBinding: FragmentItemListBinding? = null

    private var mAdapter: TitleAdapter? = null
    private var mTitleList: MutableList<Content> = ArrayList()
    private lateinit var mViewModel: TitleViewModel
    private var itemDetailFragmentContainer: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)
        try {
            itemDetailFragmentContainer = view.findViewById(R.id.item_detail_nav_container)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: FragmentItemListBinding? = null

    override fun getLayoutResourceId(inflater: LayoutInflater): View {
        mBinding = FragmentItemListBinding.inflate(inflater)
        return mBinding?.root!!
    }

    override fun initComponents(view: View?) {
        val factory = ViewModelFactory(mActivity)
        mViewModel = ViewModelProvider(this, factory)[TitleViewModel::class.java]

    }

    override fun prepareView() {
        val linearLayoutManager = LinearLayoutManager(mActivity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mBinding?.rvtitleList?.layoutManager = linearLayoutManager
        observableViewModel()

        mViewModel.fetchtitle(true)
        // fetchCountry(true)
    }

    override fun setActionListeners() {
        mBinding?.swipeRefresh?.setOnRefreshListener(this)

    }


    override fun setToolBar() {
    }

    private fun observableViewModel() {
        mViewModel.titleObserver.observe(this, Observer {
            it.let {
                mTitleList = it as MutableList<Content>
                setadapter()
            }
        })

        mViewModel.countryLoadError.observe(this, Observer {
            it?.let {
                if (it) {
                    mBinding?.tvError?.visibility = View.VISIBLE
                    mBinding?.rvtitleList?.visibility = View.GONE
                } else {
                    mBinding?.tvError?.visibility = View.GONE
                    mBinding?.rvtitleList?.visibility = View.VISIBLE
                }
            }
        })

        mViewModel.loading.observe(this, Observer {
            it?.let {
                if (it) {
                    mBinding?.progressBar?.visibility = View.VISIBLE
                    //  _binding.rvUser.visibility = View.GONE
                } else {
                    mBinding?.progressBar?.visibility = View.GONE
                    mBinding?.rvtitleList?.visibility = View.VISIBLE
                    mBinding?.swipeRefresh?.isRefreshing = false
                }
            }
        })
    }

    private fun setadapter() {
        if (mAdapter == null) {
            mAdapter = TitleAdapter(
                mActivity, mTitleList as ArrayList<Content>,
                this
            )
            mBinding?.rvtitleList?.adapter = mAdapter
        } else {
            mAdapter?.UpdateCountry(mTitleList)
        }
    }


    /**
     * @description: pull refresh the shipment order list
     * call API for shipment order list
     */
    override fun onRefresh() {
        mViewModel.fetchtitle(false)
    }

    override fun onDelete(position: Int) {
        mTitleList.removeAt(position)
        setadapter()
        Toast.makeText(mActivity, "Data Deleted successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("object", mTitleList[position])
        if (itemDetailFragmentContainer != null) {
            itemDetailFragmentContainer?.findNavController()
                ?.navigate(R.id.fragment_item_detail, bundle)
        } else {
            val navController = Navigation.findNavController(mBinding?.root!!)
            navController.navigate(R.id.show_item_detail, bundle)
        }
    }

}