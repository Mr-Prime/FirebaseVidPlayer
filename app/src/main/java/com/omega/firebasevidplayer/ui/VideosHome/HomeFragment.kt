package com.omega.firebasevidplayer.ui.VideosHome


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.omega.firebasevidplayer.R
import com.omega.firebasevidplayer.listener.RecyclerViewCallBack
import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.ui.VideoPlayer.LoadInterface
import com.omega.firebasevidplayer.ui.VideoPlayer.VideoPlayerActivity
import com.omega.firebasevidplayer.ui.VideosHome.adapter.HomeAdapter
import com.omega.firebasevidplayer.ui.VideosHome.presenter.homePresenter
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(),HomeView.View {
    companion object {
        val FRAGMENT_NAME: String = HomeFragment::class.java.name
        var dataList: List<HomeResponse> = arrayListOf()
        lateinit var interfcaeLoad:LoadInterface

        fun newInstance(load : LoadInterface):HomeFragment{
            val args: Bundle = Bundle()
            interfcaeLoad=load
            val fragment = HomeFragment()
           // fragment.arguments = args
            return fragment
        }
    }

    private lateinit var presenter: homePresenter
    private lateinit var adapter: HomeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        presenter = homePresenter(this)
        presenter.getListImage()

        adapter = HomeAdapter(requireContext())

        setupRecyclerView(view)

        onEvent(view)

        return view
    }

    fun setupRecyclerView(view: View?) {

        view!!.mainRecyclerViewHome.setHasFixedSize(true)
        view.mainRecyclerViewHome.layoutManager =LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        view.mainRecyclerViewHome.adapter = adapter


    }

    private fun onEvent(view: View?) {
        adapter.setOnDataRecyclerViewListener(object : RecyclerViewCallBack {
            override fun onClickItem(position: Int) {
                val intent = Intent(context,VideoPlayerActivity::class.java)
                intent.putExtra("id",dataList[position].id)
                intent.putExtra("thumb",dataList[position].thumb)
                intent.putExtra("title",dataList[position].title)
                intent.putExtra("url",dataList[position].url)
                intent.putExtra("desc",dataList[position].description)
                intent.putExtra("pos",position)
                startActivity(intent)
            }
        })
    }

    override fun onVideosLoadedSuccess(videos: List<HomeResponse>) {
        dataList = videos
        adapter.itemList = dataList
        Log.e("frag","size "+ adapter.itemList.size)
        adapter.notifyDataSetChanged()

        interfcaeLoad.OnLoad(dataList)


    }

    override fun onnVideosLoadedError() {

    }


    override fun showeLoding() {

    }

    override fun hideLoding() {

    }

    override fun onError(message: String) {

    }

}








