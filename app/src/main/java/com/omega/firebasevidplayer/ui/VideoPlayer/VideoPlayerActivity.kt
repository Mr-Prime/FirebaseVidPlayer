package com.omega.firebasevidplayer.ui.VideoPlayer

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util
import com.omega.firebasevidplayer.R
import com.omega.firebasevidplayer.model.response.FirebaseResponse
import com.omega.firebasevidplayer.model.response.HomeResponse
import com.omega.firebasevidplayer.ui.VideoPlayer.presenter.VideoPresenter
import com.omega.firebasevidplayer.ui.VideosHome.HomeFragment
import kotlinx.android.synthetic.main.activity_video_player.*


class VideoPlayerActivity : AppCompatActivity(),LoadInterface, VideoView.View {

    private lateinit var player: SimpleExoPlayer
    private var playWhenReady = true
    private var currentWindow = 0



    var mediaSource : MediaSource? = null
    private val TAG: String = VideoPlayerActivity::class.java.getName()
    private var playbackPosition: Long = 0
    private var playbackStateListener: PlaybackStateListener? = null
    var dataList: MutableList<HomeResponse> = arrayListOf()
    var mediaArray = arrayOfNulls<MediaSource>(0)
    var id :Int = 0
    var count :Int = 1
    var pos :Int = 0
    var url :String = ""
    var Desc :String = ""
    var Title :String = ""

    var SeekTo :Long =0;
    private lateinit var presenter: VideoPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        id =intent.getIntExtra("id",0)
        pos =intent.getIntExtra("pos",0)
        url =intent.getStringExtra("url")
        Desc =intent.getStringExtra("desc")
        Title =intent.getStringExtra("title")
        titles.text=Title
        desc.text=Desc

        presenter = VideoPresenter(this)
        presenter.getVideoInfo(id.toString())
        // setExpenseVisitDetails(roots);

        showHomeFragment()
    }


    private fun initializePlayer() {

        Log.e("initializePlayer","initialised")
        player = ExoPlayerFactory.newSimpleInstance(this)
        playerView.player = player

        if(mediaSource!=null) {
            playbackStateListener = PlaybackStateListener()
            player.addListener(playbackStateListener);

            with(player) {
                seekTo(currentWindow, playbackPosition)
                prepare(mediaSource, false, false)
                playWhenReady = true
            }
            playerView.setShutterBackgroundColor(Color.TRANSPARENT)
            playerView.requestFocus()
        }


    }

    private class PlaybackStateListener : Player.EventListener {
        override fun onPlayerStateChanged(
            playWhenReady: Boolean,
            playbackState: Int) {
            val stateString: String
            stateString = when (playbackState) {
                ExoPlayer.STATE_IDLE -> {
                    "ExoPlayer.STATE_IDLE      -"
                }
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            Log.d(
                TAG, "changed state to " + stateString
                        + " playWhenReady: " + playWhenReady
            )
        }




    }

    private fun buildMediaSource(uri: Uri, id: Int): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this, "exoplayer-codelab")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
                .setTag(id)
                .createMediaSource(uri)
    }
    private fun releasePlayer() {
        playWhenReady = player.getPlayWhenReady();
        playbackPosition = player.getCurrentPosition();
        currentWindow = player.getCurrentWindowIndex();

        Log.e(ContentValues.TAG, "getCurrentPosition" + player.getCurrentPosition())
        Log.e(ContentValues.TAG, "getCurrentWindowIndex" + player.getCurrentWindowIndex())
        Log.e(ContentValues.TAG, "id" + player.currentTag)

        if(!player.currentTag.toString().equals("null"))
        presenter.StoreData(player.currentTag.toString(), player.getCurrentPosition())


        player.removeListener(playbackStateListener);
        player.release();
        //player = null
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23)
            initializePlayer()
    }

    public override fun onResume() {
        super.onResume()

        if (Util.SDK_INT <= 23) initializePlayer()
    }

    public override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) releasePlayer()
    }

    public override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) releasePlayer()
    }

    private fun showHomeFragment() {

        var fragment: HomeFragment = HomeFragment.newInstance(this,pos)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                fragment
            )
       // fragmentTransaction.addToBackStack(fragment)
        fragmentTransaction.commit()
    }

    override fun OnLoad(
        videos: MutableList<HomeResponse>,
        posi: Int
    ) {
        Log.e("OnLoad","size "+ videos.size)
        dataList=videos
        dataList.removeAt(posi)
        releasePlayer()


        var list: List<HomeResponse> = arrayListOf()
        list=dataList

        var  mediaSources = arrayOfNulls<MediaSource>(list.size)
        if(list.size>0) {
            mediaSources[0] = buildMediaSource(Uri.parse(url),id)
            Log.e("ForLoop ", "Added 0 " + Title)
        }

        for (i in 0 until list.size-1) {

                mediaSources[count] = buildMediaSource(Uri.parse(list.get(i).url),list.get(i).id)
                Log.e("ForLoop ", "Added " + i + list.get(i).title)
                count++
        }
        mediaArray=mediaSources

        mediaSource =
            if (mediaSources.size == 1)
                mediaSources[0]!!
            else
                ConcatenatingMediaSource(*mediaSources)


        initializePlayer()
    }

    override fun responeUserInfo(dataUser: FirebaseResponse) {
        SeekTo=dataUser.seekto
        playbackPosition=SeekTo
        player.seekTo(playbackPosition)
        Log.e("ForLoop ", "SeekTo " + SeekTo)
    }


}