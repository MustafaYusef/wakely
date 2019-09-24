package com.mustafayusef.wakely.ui.auth.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.data.profile.profile
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.auth.AuthLesener
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.ui.auth.AuthViewModel
import com.mustafayusef.wakely.ui.auth.AuthViewModelFactory
import kotlinx.android.synthetic.main.fragment_update_profile.*
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.regester_shops_fragment_tow.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class UpdateProfile : Fragment(),AuthLesener {
    override fun OnSuccessUpdate(response: loginResponse) {
        progLoading?.visibility=View.GONE
         context?.toast(response.message)
        view?.findNavController()?.navigate(R.id.profile_fragment)
    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: loginResponse) {

    }

    override fun onFailer(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun OnSuccessProfile(message: profile) {

    }

    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=123
   var imagePath=""
    var imagesBodyRequest:MultipartBody.Part?=null
    var imageFile:RequestBody?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_profile, container, false)
    }
      var viewModel:AuthViewModel?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.updateProfile) {
                // bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
                //  bottomNav?.visibility = View.VISIBLE

                toolbar?.visibility = View.VISIBLE
            }

        }
        val itemComp= activity?.findViewById<View>(R.id.ordersCompany)
        val item= activity?.findViewById<View>(R.id.orders_fragment)
        if(MainActivity.cacheObj.role!=2){
            item?.visibility=View.VISIBLE
            itemComp?.visibility=View.GONE
        }else{
            item?.visibility=View.GONE
            itemComp?.visibility=View.VISIBLE
        }


        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= AuthRepostary(api!!)
        val factory= AuthViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        viewModel?.Auth=this

        UpdateBtn?.setOnClickListener {
            if(!nameShopU.text.toString().isNullOrEmpty()&&
            !titleShopsU.text.toString().isNullOrEmpty()&&
            !phoneU.text.toString().isNullOrEmpty()){
                if(imagesBodyRequest!=null){
                viewModel?.Update(MainActivity.cacheObj.token
                    ,nameShopU.text.toString()
                    ,titleShopsU.text.toString()
                    ,phoneU.text.toString(),imagesBodyRequest!!)
            }else{
                    context?.toast("أختر صورة رجاء")
                }
            }
            else{
                context?.toast("أكمل جميع الحقول")
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       var name=arguments!!.getString("name")
        var title=arguments!!.getString("title")
        var phone=arguments!!.getString("phone")
        var image=arguments!!.getString("image")

        circleImageViewU?.let {it1->
            context?.let {
                Glide.with(it).load("http://api.alwakiel.com/storage/images/"+image)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                    .into( it1)
            }
        }
        SelectPhotoU.alpha=0f

        titleShopsU?.setText(title)
        nameShopU?.setText(name)
        phoneU?.setText(phone)

        if(MainActivity.cacheObj.role==2){
            titleShopsU?.hint="عنوان الشركة"
            nameShopU?.hint="أسم مدير المبيعات"
            phoneU?.hint="رقم مدير المبيعات"
        }


        SelectPhotoU?.setOnClickListener {
            if (Build.VERSION.SDK_INT < 19) {
                var intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture")
                    , 0
                )


            } else {
                if (Build.VERSION.SDK_INT >=23) {
                    if(checkPermissionREAD_EXTERNAL_STORAGE(context!!)){
                        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                        intent.action= Intent.ACTION_GET_CONTENT
                        // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                        intent.addCategory(Intent.CATEGORY_OPENABLE)
                        intent.type = "image/*"
                        startActivityForResult(intent, 0);
                    }else{
                        context?.toast("you can not pick images")
                    }
                }else{
                    //  var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                    var intent = Intent()

                    intent.action= Intent.ACTION_GET_CONTENT
                    intent.addCategory(Intent.CATEGORY_OPENABLE)
                    intent.type = "image/*"
                    startActivityForResult(intent, 0);
                }


            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        // When an Image is picked
        if (requestCode == 0 && resultCode == Activity.RESULT_OK
            && null != data
        ) {
            var imageUri: Uri? = data.data
            val bitmap= MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)
            SelectPhotoU.text=""
            circleImageViewU.setImageBitmap(bitmap)
            SelectPhotoU.alpha=0f
            getPathFromURI(imageUri!!)
            var oregnal = File(imagePath)
//  var oregnal = File(getPathFromURI(imageUri))
            imageFile = RequestBody.create(
                MediaType.parse(context?.contentResolver?.getType(imageUri)!!),
                oregnal
            )
            imagesBodyRequest= MultipartBody.Part.createFormData("image", oregnal.name, imageFile)

        }

    }

    @SuppressLint("NewApi")
    fun getPathFromURI(uri: Uri) {
        var path: String = uri.path!! // uri = any content Uri

        val databaseUri: Uri
        val selection: String?
        val selectionArgs: Array<String>?
        if (path.contains("/document/image:")) { // files selected from "Documents"
            databaseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            selection = "_id=?"
            selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":")[1])
        } else { // files selected from all other sources, especially on Samsung devices
            databaseUri = uri
            selection = null
            selectionArgs = null
        }
        try {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.DATE_TAKEN
            ) // some example data you can query
            val cursor = context?.contentResolver ?.query(
                databaseUri,
                projection, selection, selectionArgs, null
            )
            if(cursor==null){
                imagePath = path
            }else
                if (cursor!!.moveToFirst()) {
                    val columnIndex = cursor!!.getColumnIndex(projection[0])
                    //  if (cursor.getType(columnIndex) == FIELD_TYPE_STRING) {
                    imagePath = cursor!!.getString(columnIndex)
                    // }

                    // Log.e("path", imagePath)
                }


            cursor?.close()
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, e.message, e)
        }
    }

    fun checkPermissionREAD_EXTERNAL_STORAGE(
        context: Context
    ):Boolean {
        var currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                showDialog("External storage", context,
//                    Manifest.permission.READ_EXTERNAL_STORAGE);
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                        .requestPermissions(
                            context as Activity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) ,
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    fun showDialog(msg:String, context: Context,
                   permission:String) {
        var alertBuilder: AlertDialog.Builder = AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
            DialogInterface.OnClickListener(
                fun(dialog: DialogInterface, which:Int) {
                    ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(permission ),
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
            )



        )
        val  alert: AlertDialog = alertBuilder.create();
        alert.show();
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
//                    var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//
//                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//                    intent.action= Intent.ACTION_GET_CONTENT
//                    // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//                    intent.addCategory(Intent.CATEGORY_OPENABLE)
//                    intent.type = "image/*"
//                    startActivityForResult(intent, PICK_IMAGE_MULTIPLE);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    context?.toast("tou can not pick image")
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
    }

