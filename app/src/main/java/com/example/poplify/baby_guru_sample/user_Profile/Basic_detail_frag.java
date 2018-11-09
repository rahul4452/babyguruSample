package com.example.poplify.baby_guru_sample.user_Profile;


import android.Manifest;

import android.app.Activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;

import android.os.Bundle;
import android.os.Environment;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;

import com.bumptech.glide.Glide;
import com.example.poplify.baby_guru_sample.R;

import com.example.poplify.baby_guru_sample.adapter.SaveData;

import com.example.poplify.baby_guru_sample.pojo.response.userResponse.UserDetailAddResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Basic_detail_frag extends Fragment implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private Spinner edRelationParent;
    String[] relation = new String[]{"mother", "father"};
    Typeface regular, regularMon;
    TextView parentNameTv, parentRelationChildTv, parentEmailAddress, tb_title_parentAdd;
    Button addParentBtn;
    Toolbar toolbar;
    SaveData saveData;
    ImageView parentProfileBtn;
    CircleImageView parentProfileShow;
    EditText edParentName, edParentEmail;
    ScrollView scrollViewbg;
    FragmentManager fragmentManager;
    String relation_id;
    public static final int RC_PHOTO_PICKER_PERM = 123;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int MY_REQUEST_CODE = 1;
    private UserDetailAddResponse serverresponse;
    private ArrayList<String> photoPaths = new ArrayList<>();

    public Basic_detail_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.basic_detail_frag, container, false);

        saveData = new SaveData(getContext());
        fragmentManager = getFragmentManager();


        init(view);
        setupUi(view);
        spinnerAdapter();


        return view;
    }

    private void init(final View view) {
        //Setting Up the toolbar  title
        toolbar = view.findViewById(R.id.toolbar_basic);
        tb_title_parentAdd = view.findViewById(R.id.toolbar_title);
        tb_title_parentAdd.setText(getResources().getString(R.string.basic_detail));
        tb_title_parentAdd.setTypeface(regular);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //*******************************************************************//
        //*******************************************************************//
        scrollViewbg = view.findViewById(R.id.scrollView2);
        scrollViewbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
            }
        });
    }


    private void setupUi(View view) {

        //Setting TextView
        parentNameTv = view.findViewById(R.id.parent_name_txt); //textView Name
        parentNameTv.setTypeface(regularMon);

        parentRelationChildTv = view.findViewById(R.id.relation_parent_txt); //textView Bday
        parentRelationChildTv.setTypeface(regularMon);

        parentEmailAddress = view.findViewById(R.id.parent_mail_txt); //textView Gender
        parentEmailAddress.setTypeface(regularMon);

        //Setting edittext
        edParentName = view.findViewById(R.id.ed_parent_name); //EditTxt Name
        edParentName.setTypeface(regularMon);

        edParentEmail = view.findViewById(R.id.parent_email); //EditTxt email
        edParentEmail.setTypeface(regularMon);
        edParentEmail.setText(saveData.get("login_email"));
        //edParentEmail.setEnabled(false);


        //Button to add User


        addParentBtn = view.findViewById(R.id.add_parent_btn_id);
        addParentBtn.setTypeface(regular);
        addParentBtn.setOnClickListener(this);


        //--------------------********************setting up the Profile Pic**********--------------------------//

        parentProfileShow = view.findViewById(R.id.parent_profile_pic);


        parentProfileBtn = view.findViewById(R.id.user_select_profile);
        parentProfileBtn.setOnClickListener(this);


        edRelationParent = view.findViewById(R.id.spinner);

    }


    private void spinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.gender_layout, relation);

        //adapter.setDropDownViewResource(R.layout.gender_layout,gender);
        edRelationParent.setAdapter(adapter);

        edRelationParent.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.spinner_draw));

        edRelationParent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {


                Log.v("item", (String) parent.getItemAtPosition(position));
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_parent_btn_id:
                //new UserDetailAysn().execute();
                callApi(view);
                break;
            case R.id.user_select_profile:
                //verifyStoragePermissions(getActivity());
                //change_profile_pic();

                changeProfilePic();
                break;
            default:
                Toast.makeText(getContext(), "Another Button", Toast.LENGTH_LONG).show();

        }
    }

    private void changeProfilePic() {
        if (EasyPermissions.hasPermissions(getContext(), FilePickerConst.PERMISSIONS_FILE_PICKER)) {
            onPickPhoto();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    "Asking for permission",
                    RC_PHOTO_PICKER_PERM,
                    FilePickerConst.PERMISSIONS_FILE_PICKER);
        }
    }

    private void onPickPhoto() {
        FilePickerBuilder.getInstance()
                .setSelectedFiles(photoPaths)
                .setActivityTheme(R.style.LibAppTheme)
                .pickPhoto(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    //photoPaths = data.getStringExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                }
                break;
            default:
                Toast.makeText(getContext(), " adsf adsf adfb dflmnaf", Toast.LENGTH_LONG).show();
        }

        addThemToView(photoPaths);
    }

    private void addThemToView(ArrayList<String> imagePaths) {
        ArrayList<String> filePaths = new ArrayList<>();
        if (imagePaths != null) {
            filePaths.addAll(imagePaths);
        }

        String path = filePaths.get(0);
        String compressImage = compressImage(path);

        Bitmap bitmap = (BitmapFactory.decodeFile(compressImage));
        parentProfileShow.setImageBitmap(bitmap);


    }


    private void callApi(final View view) {

        //UserDetailRequest userDetailRequest = new UserDetailRequest();
        //UserDetailRequest.User user = new UserDetailRequest().new User();


        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");


        if (edRelationParent.getSelectedItem().equals("father")) {
            relation_id = "1";
        } else {
            relation_id = "2";
        }

        RequestBody requestName = RequestBody.create(MediaType.parse("multipart/form-data"), edParentName.getText().toString());
        RequestBody requestRelation = RequestBody.create(MediaType.parse("multipart/form-data"), relation_id);
        RequestBody requestlanguage = RequestBody.create(MediaType.parse("multipart/form-data"), "1");


        String path2 = photoPaths.get(0);



        File file = new File(path2);

        /*for (File temp : file.listFiles()) {
            if (temp.getName().equals("temp.jpg")) {
                file = temp;
                break;
            }
        }*/

        //=======+++++++++++++++++++++++++===========
        //+++++++=========================+++++++++
        //set Image in Api
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("user[image]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        Call<UserDetailAddResponse> responseCall = service.uploadMulFile(token_header, email_header, requestName, requestRelation, requestlanguage, filePart);
        responseCall.enqueue(new Callback<UserDetailAddResponse>() {
            @Override
            public void onResponse(Call<UserDetailAddResponse> call, Response<UserDetailAddResponse> response) {

                serverresponse = response.body();
                // String userToken = response.body().getUser().getAuthenticationToken();
                boolean success = response.isSuccessful();

                if (!success) {
                    switch (response.code()) {
                        case 400:
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                TSnackbar snackbar = TSnackbar.make(view.findViewById(android.R.id.content), jObjError.getString("message"), TSnackbar.LENGTH_LONG);
                                snackbar.setActionTextColor(Color.WHITE);
                                View snackbarView = snackbar.getView();
                                snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                                TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);
                                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                                snackbar.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            Toast.makeText(getContext(), "Wrong Response", Toast.LENGTH_SHORT).show();

                    }


                    Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                } else {

                    saveData.save("userAdd", serverresponse.getMessage());
                    Toast.makeText(getContext(), serverresponse.getUser().getImageUrl(), Toast.LENGTH_LONG).show();

                    replacementFragment(new User_Profile_frag());
                }
            }

            @Override
            public void onFailure(Call<UserDetailAddResponse> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                Toast.makeText(getContext(), "Please Check User InterNet Connection", Toast.LENGTH_SHORT).show();
            }

        });


    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );

        }
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity, new String[]{Manifest.permission.CAMERA},
                    MY_REQUEST_CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            } else {

                Toast.makeText(getContext(), "permission not granted", Toast.LENGTH_LONG).show();
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
            }
        }
    }


    private void change_profile_pic() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, 1);
                    }

                } else if (options[item].equals("Choose from Gallery")) {

                    // Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    //startActivityForResult(intent, FilePickerConst.REQUEST_CODE_PHOTO);

                    FilePickerBuilder.getInstance()
                            //.setSelectedFiles(photoPaths)
                            .setActivityTheme(R.style.LibAppTheme)
                            .pickPhoto((Activity) getContext());
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();

    }





    /*

    @SuppressLint("LongLogTag")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        File f;
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {


                f = new File(Environment.getExternalStorageDirectory().toString());

                for (File temp : f.listFiles()) {

                    if (temp.getName().equals("temp.jpg")) {

                        f = temp;
                        break;

                    }

                }

                String clickedImage = f.getAbsolutePath();
                String compressImage = compressImage(clickedImage);

                Bitmap bitmap = (BitmapFactory.decodeFile(compressImage));
                parentProfileShow.setImageBitmap(bitmap);



            } else if (requestCode == FilePickerConst.REQUEST_CODE_DOC) {


                if(data!=null)
                {
                    photoPaths = new ArrayList<>();
                    photoPaths.add(data.getStringExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                    //photoPaths.addAll(data.getStringArrayListExtra());
                }

               /* Uri selectedImage = data.getData();
                String stringUri = selectedImage.toString();


                String filepath = compressImage(stringUri);

                Bitmap thumbnail = (BitmapFactory.decodeFile(filepath));

               // Log.w("dsfaafdsasdfasdfasfasdfadsfa", filepath + "");


                //parentProfileShow.setImageBitmap(thumbnail);

                String path = photoPaths.get(1);
                Glide.with(getContext())
                        .load(new File(path))
                        .apply(RequestOptions.centerCropTransform()
                                .dontAnimate()
                                .placeholder(droidninja.filepicker.R.drawable.image_placeholder))
                        .thumbnail(0.5f)
                        .into(parentProfileShow);
                // parentProfileShow.setBackgroundResource(android.R.color.transparent);

            }

        }


    }
*/


    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }


    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }


    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContext().getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }


    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }





    private void replacementFragment(Fragment fragment) {
        String backstack = null;
        String fragmentTag = null;


        FragmentTransaction ft = fragmentManager.beginTransaction();

        backstack = fragment.getClass().getName();
        fragmentTag = backstack;
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backstack, 0);

        Log.d("", "replacementFragment: fragmentPopped" + fragmentPopped);
        try {
            if (fragmentPopped != true) {
                ft.replace(R.id.fragment_container_navbar, fragment, fragmentTag);
            }
            ft.addToBackStack(backstack).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

}
