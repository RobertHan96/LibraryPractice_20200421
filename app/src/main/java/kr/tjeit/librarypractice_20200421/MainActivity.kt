package kr.tjeit.librarypractice_20200421

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValue()
    }

    override fun setupEvents() {
        callBtn.setOnClickListener {
            val permissionListener = object  : PermissionListener {
                override fun onPermissionGranted() {
//                    사용자가 앱권한을 허용한 경우
                    val uri = Uri.parse("tel:01022345619")
                    val myIntent = Intent(Intent.ACTION_CALL, uri)
                    startActivity(myIntent)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                    사용자가 앱권한을 거부한 경우
                    Toast.makeText(mContext, "권한 허용 필요", Toast.LENGTH_SHORT).show()
                }

            }
//            위에 지정한 권한 허용/거부에 대한 케이스 코드를 실제 화면에 출력하고, 권한을 체크하는 코드
            TedPermission.with(mContext).setPermissionListener(permissionListener).setDeniedMessage("설정에서 권한을 허용해주세요.")
                .setPermissions(Manifest.permission.CALL_PHONE).check()

        }

    }

    override fun setValue() {

        Glide.with(mContext).load("http://pds.joins.com/news/component/htmlphoto_mmdata/201810/29/htm_2018102914510995660.jpeg").into(profileImg)

    }

}
