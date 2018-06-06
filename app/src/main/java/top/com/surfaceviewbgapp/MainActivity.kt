package top.com.surfaceviewbgapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //隐藏状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        initViewAndData()

    }

    private fun initViewAndData() {
        surface1.holder.setKeepScreenOn(true)
        surface1.holder.addCallback(object:SurfaceHolder.Callback{
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {

            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                play()
            }

        })
        button1.setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
        }
    }

    private val mediaPlayer: MediaPlayer
        get() {
            val player = MediaPlayer()
            return player
        }

    private fun play() {
        val player = mediaPlayer
        player.reset()
        //通过raw文件目录获取响应文件
        val fd = resources.openRawResourceFd(R.raw.intro)
        player.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        player.isLooping=true
        player.setScreenOnWhilePlaying(true)
        player.setDisplay(surface1.holder)
        player.prepareAsync()
        player.setOnPreparedListener {
            //最好在prepareListener调用
            player.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }


}
