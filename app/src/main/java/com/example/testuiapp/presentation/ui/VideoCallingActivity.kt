package com.example.testuiapp.presentation.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.testuiapp.R
import com.example.testuiapp.databinding.ActivityVideoCallingBinding
import io.agora.rtc2.*
import io.agora.rtc2.video.VideoCanvas
import java.util.*

class VideoCallingActivity : AppCompatActivity() {

    private var _binding: ActivityVideoCallingBinding? = null
    private val binding: ActivityVideoCallingBinding
        get() = _binding!!

    private lateinit var appId: String
    private val channelName = "testChannel1"
    private lateinit var token: String
//    private val userId = (0..999999).random()
    private val userId = UUID.randomUUID().hashCode()
    private var agoraEngine: RtcEngine? = null
    private var isJoined: Boolean = false
    private var remoteSurfaceView: SurfaceView? = null
    private var localSurfaceView: SurfaceView? = null

    private val mRtcEventHandler = object : IRtcEngineEventHandler() {
        override fun onUserJoined(uid: Int, elapsed: Int) {
            showMessage("Remote user: ($uid) joined")
            runOnUiThread { setupRemoteVideo(uid) }
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            isJoined = true
            showMessage("Joined channel $channel")
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            showMessage("Remote user: $uid is offline $reason")
            runOnUiThread { remoteSurfaceView?.visibility = View.GONE }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_video_calling)
        binding.lifecycleOwner = this

        appId = getString(R.string.agora_app_id)
        token = getString(R.string.agora_app_token)

        if (!checkSelfPermission()) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSION, PERMISSION_REQ_ID)
        }
        setupVideoSDKEngine()

        binding.btnJoin.setOnClickListener {
            joinChannel()
        }

        binding.btnLeave.setOnClickListener {
            leaveChannel()
        }
    }

    override fun onDestroy() {
        leaveChannel()
        RtcEngine.destroy()
        agoraEngine = null
        remoteSurfaceView = null
        localSurfaceView = null
        super.onDestroy()
    }


    private fun checkSelfPermission(): Boolean {
        return !(ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSION[0]) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSION[1]) != PackageManager.PERMISSION_GRANTED)
    }

    private fun setupVideoSDKEngine() {
        try {
            val config = RtcEngineConfig()
            config.mContext = baseContext
            config.mAppId = appId
            config.mEventHandler = mRtcEventHandler
            agoraEngine = RtcEngine.create(config)
            agoraEngine?.enableVideo()
        } catch (e: Exception) {
            showMessage(e.toString())
        }
    }

    private fun setupRemoteVideo(uid: Int) {
        remoteSurfaceView = SurfaceView(baseContext)
        remoteSurfaceView?.setZOrderMediaOverlay(true)
        binding.remoteVideoViewContent.addView(remoteSurfaceView)
        agoraEngine?.setupRemoteVideo(VideoCanvas(remoteSurfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid))
        remoteSurfaceView?.visibility = View.VISIBLE
    }

    private fun setupLocalVideo() {
        localSurfaceView = SurfaceView(baseContext)
        binding.localVideoViewContent.addView(localSurfaceView)
        agoraEngine?.setupLocalVideo(VideoCanvas(localSurfaceView, VideoCanvas.RENDER_MODE_HIDDEN, 0))
    }

    private fun joinChannel() {
        if (checkSelfPermission()) {
            val options = ChannelMediaOptions()
            options.channelProfile = Constants.CHANNEL_PROFILE_COMMUNICATION
            options.clientRoleType = Constants.CLIENT_ROLE_BROADCASTER
            setupLocalVideo()
            localSurfaceView?.visibility = View.VISIBLE
            agoraEngine?.startPreview()
            agoraEngine?.joinChannel(token, channelName, userId, options)
            Log.d("TAG", "joinChannel: audioTrackCount = ${agoraEngine?.audioTrackCount}")
        } else {
            showMessage("Permission was not granted")
        }
    }

    private fun leaveChannel() {
        if (!isJoined) {
            showMessage("Doesn't have joined channel")
        } else {
            agoraEngine?.stopPreview()
            agoraEngine?.leaveChannel()
            showMessage("you left the channel")
            if (remoteSurfaceView != null) remoteSurfaceView?.visibility = View.GONE
            if (localSurfaceView != null) localSurfaceView?.visibility = View.GONE
            isJoined = false
        }
    }

    private fun showMessage(message: String) {
        this.runOnUiThread { Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show() }
    }

    companion object {
        private const val PERMISSION_REQ_ID = 23
        private val REQUESTED_PERMISSION = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
        )
    }
}