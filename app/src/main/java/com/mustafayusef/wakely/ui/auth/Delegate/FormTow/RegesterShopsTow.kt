package com.mustafayusef.wakely.ui.auth.Delegate.FormTow

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.wakely.MainActivity

import com.mustafayusef.wakely.R
import com.mustafayusef.wakely.data.AddUserRes
import com.mustafayusef.wakely.data.ProvData
import com.mustafayusef.wakely.data.provRes
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.ui.auth.AuthRepostary
import com.mustafayusef.wakely.ui.auth.Delegate.DelegateLesener
import com.mustafayusef.wakely.ui.auth.Delegate.RegShopsViewModelFactory
import com.mustafayusef.wakely.ui.auth.Delegate.RegesterShopsViewModel
import kotlinx.android.synthetic.main.filters_dilog1.view.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.regester_shops_fragment_tow.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class RegesterShopsTow : Fragment(),DelegateLesener {
    override fun OnSuccessProv(response: provRes) {
        var ind=0
        province.clear()

        progLoading?.visibility=View.GONE
        resProv=response.data
        println("djccccccccjcjjcjj "+resProv)
        context?.toast("success  "+resProv.toString())
          for(i in response.data){
              province.add(ind,i.name)
              context?.toast("success  "+i.name)
              ind++
          }
        if(!province.isNullOrEmpty()){
            ShowProv(province as ArrayList<String>)
        }else{
            //context?.toast("nullllllllllllll")
        }
    }

    override fun OnSuccessCity(response: provRes) {
        var ind=0
        cities.clear()
        progLoading?.visibility=View.GONE
        resCity=response.data
        for(i in response.data){
            cities.add(ind,i.name)
            ind++
        }
        ShowCity(cities as ArrayList<String>)
    }

    override fun OnStart() {
        progLoading?.visibility=View.VISIBLE
    }

    override fun OnSuccess(response: AddUserRes) {
        context?.toast("تم تسجيل المحل بنجاح")
        progLoading?.visibility=View.GONE
        view?.findNavController()?.navigate(R.id.regesterShops)
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }

    override fun onFailerNet(message: String) {
        context?.toast(message)
        progLoading?.visibility=View.GONE
    }


    companion object {
        fun newInstance() = RegesterShopsTow()
    }
    var resProv:List<ProvData>?=null
    var resCity:List<ProvData>?=null
    var imagesBodyRequest:MultipartBody.Part?=null
    var imageFile:RequestBody?=null
    var imagePath:String?=null
    private lateinit var viewModel: RegesterShopsViewModel
    var title=""
    var phone=""
    var provinceId=""
    var cityId=""
    var nearLocation=""
    var cities:MutableList<String> = arrayListOf()
    var province:MutableList<String> = arrayListOf()
    var PICK_IMAGE_MULTIPLE = 1
    var index2=0
    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=123
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.regester_shops_fragment_tow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var id=arguments!!.getString("UserId")
        context?.toast("tow "+id)
        val bottomNav = activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView> (R.id.bottomNav)
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.regesterShopsTow) {
                bottomNav?.visibility = View.GONE
                toolbar?.visibility = View.GONE
            } else {
                bottomNav?.visibility = View.VISIBLE

                toolbar?.visibility = View.VISIBLE
            }

        }

        val networkIntercepter= context?.let { networkIntercepter(it) }
        val api= networkIntercepter?.let { myApi(it) }
        val repostary= AuthRepostary(api!!)
        val factory= RegShopsViewModelFactory(repostary)

        viewModel = ViewModelProviders.of(this,factory).get(RegesterShopsViewModel::class.java)
        viewModel?.Auth=this
        //viewModel.GetProv()


        locationReg?.setOnClickListener {
            context?.toast("click  jfnjfdnknkjdnd")
            viewModel.GetProv()
        }

        loginBtnRegTow?.setOnClickListener {
            title=titleShops?.text.toString()
            phone= phoneRegTow?.text.toString()
            nearLocation=NearLoc?.text.toString()
            if(!title.isNullOrEmpty()&&
                !phone.isNullOrEmpty()&&
               !nearLocation.isNullOrEmpty()&& imagesBodyRequest!=null){
                viewModel?.AddShops(MainActivity.cacheObj.token,title,phone,provinceId,cityId,nearLocation,imagesBodyRequest!!,id!!)
            }else{
                context?.toast("أكمل جميع الحقول")
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SelectPhoto?.setOnClickListener {
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

    fun ShowProv(
        array: ArrayList<String>
    ){

        val dview: View = layoutInflater.inflate(R.layout.filters_dilog1, null)
        val builder = context?.let { AlertDialog.Builder(it).setView(dview) }
        val malert= builder?.show()
        malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
       // dview.filterTitle.text="Add"
        dview.filterPicker.minValue = 0
        dview.filterPicker.maxValue = array.size-1
        dview.filterPicker.wrapSelectorWheel = true
        dview.filterPicker.displayedValues = array.toTypedArray()
        var index=0

        dview.filterPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //
            //Display the newly selected number to text view
            index=newVal
            // println(country +"   cooodkl,dl")
        }
        dview.applayFilter.setOnClickListener {

            //mileFilter.text=selectMile

            index2=index
            locationReg?.setText(array.get(index2).toString())
               provinceId=resProv?.get(index)!!._id!!
                viewModel.GetCity(provinceId)


            malert?.dismiss()
        }
//        dview.closeDf.setOnClickListener {
//            malert?.dismiss()
//            index2=0
//        }

    }
    fun ShowCity(
        array: ArrayList<String>
    ){

        val dview: View = layoutInflater.inflate(R.layout.filters_dilog1, null)
        val builder = context?.let { AlertDialog.Builder(it).setView(dview) }
        val malert= builder?.show()
        malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dview.filterTitle.text="Add"
        dview.filterPicker.minValue = 0
        dview.filterPicker.maxValue = array.size-1
        dview.filterPicker.wrapSelectorWheel = true
        dview.filterPicker.displayedValues = array.toTypedArray()
        var index=0

        dview.filterPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //
            //Display the newly selected number to text view
            index=newVal
            // println(country +"   cooodkl,dl")
        }
        dview.applayFilter.setOnClickListener {

            //mileFilter.text=selectMile

             cityId= resCity?.get(index)?._id!!
            locationReg?.append("/"+array.get(index).toString())
            malert?.dismiss()
        }
//        dview.closeDf.setOnClickListener {
//            malert?.dismiss()
//
//        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        // When an Image is picked
        if (requestCode == 0 && resultCode == Activity.RESULT_OK
            && null != data
        ) {
                    var imageUri: Uri? = data.data
            val bitmap=MediaStore.Images.Media.getBitmap(context?.contentResolver,imageUri)
            // val bitmapDrawable= BitmapDrawable(bitmap)
            SelectPhoto.text=""
            circleImageView.setImageBitmap(bitmap)
            SelectPhoto.alpha=0f
            getPathFromURI(imageUri!!)
            var oregnal = File(imagePath)
//                        var oregnal = File(getPathFromURI(imageUri))
            imageFile = RequestBody.create(
                MediaType.parse(context?.contentResolver?.getType(imageUri)!!),
                oregnal
            )
            imagesBodyRequest=MultipartBody.Part.createFormData("image", oregnal.name, imageFile)

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

                    // Log.e("path", imagePath);

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
