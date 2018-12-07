package com.example.poplify.baby_guru_sample.add_New_Baby_tab;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.example.poplify.baby_guru_sample.MainActivity;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest.ChildProfileResponse;
import com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage.CryingScaleResponse;
import com.example.poplify.baby_guru_sample.rest.ApiClient;
import com.example.poplify.baby_guru_sample.rest.ApiInterface;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Add_child_tab_frag extends Fragment implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private Spinner spinner;
    private Unbinder unbinder;
    String[] gender = new String[]{"Boy", "Girl"};
    Typeface regular, regularMon;
    TextView txt_name, txt_bday, txt_gen, tb_title_add;
    Button add_4_child_btn;
    Toolbar toolbar;
    ImageView profile_btn;
    CircleImageView profile_show;
    EditText ent_name, ent_bday;
    SaveData saveData;
    String SERIALIZED_KEY = "updateChildData";
    public static final int RC_PHOTO_PICKER_PERM = 123;
    private ArrayList<String> photoPaths ;
    private String childName;
    private String childDob;
    private String childImageUrl;
    private int childId;
    private ChildProfileResponse.ChildProfileLabels childLabels;
    private int genderId;
    private String updatedGender;


    public Add_child_tab_frag() {
        // Required empty public constructor
    }

    Bundle addChildBundle = new Bundle();

    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            addChildBundle = getArguments();
            childName = addChildBundle.getString("childName");
            childDob = addChildBundle.getString("childDob");
            childImageUrl = addChildBundle.getString("childimageUrl");
            childId = addChildBundle.getInt("childIdforUpdate");
            genderId = addChildBundle.getInt("genderId");
            childLabels = (ChildProfileResponse.ChildProfileLabels) addChildBundle.getSerializable("labels");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_child_tab_frag, container, false);

        initChild(view);
        setupChild(view);
        spinnerAdapter(view);

        return view;

    }

    private void initChild(View view) {
        //Setting fonts
        regular = Typeface.createFromAsset(getActivity().getAssets(), "Comfortaa_Regular.ttf");
        regularMon = Typeface.createFromAsset(getActivity().getAssets(), "Montserrat-Regular.otf");

        saveData = new SaveData(getContext());

        //butterknife
        unbinder = ButterKnife.bind(this, view);

        //Setting Up the toolbar  title
        toolbar = view.findViewById(R.id.toolbar_add);
        tb_title_add = view.findViewById(R.id.toolbar_title);
        if(childLabels!=null)
        {
            tb_title_add.setText("edit child");
        }
        else {
            tb_title_add.setText(saveData.get("addChild"));
        }
        tb_title_add.setTypeface(regular);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupChild(View view) {
        //Setting TextView
        txt_name = view.findViewById(R.id.child_name_txt); //textView Name
        txt_name.setTypeface(regularMon);


        txt_bday = view.findViewById(R.id.child_birt_txt); //textView Bday
        txt_bday.setTypeface(regularMon);

        txt_gen = view.findViewById(R.id.child_gender_txt); //textView Gender
        txt_gen.setTypeface(regularMon);

        //Setting edittext
        ent_name = view.findViewById(R.id.ed_ch_name); //EditTxt Name
        ent_name.setTypeface(regularMon);
        if (childName != null) {
            ent_name.setText(childName);
        }

        ent_bday = view.findViewById(R.id.ed_ch_bday); //EditTxt bday
        ent_bday.setTypeface(regularMon);
        if (childDob != null) {
            ent_bday.setText(childDob);
        }

        //Button
        add_4_child_btn = view.findViewById(R.id.add_child_btn_id);
        add_4_child_btn.setTypeface(regular);
        if (childName != null) {
            add_4_child_btn.setText("update");
        }

        add_4_child_btn.setOnClickListener(this);


        //--------------------********************setting up the Profile Pic**********--------------------------//

        profile_show = view.findViewById(R.id.profile_pic);


        profile_btn = view.findViewById(R.id.select_profile);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeProfilePic();
            }
        });

        if (childImageUrl != null) {
            try {
                Glide.with(this)
                        .load(childImageUrl)
                        .into(profile_show);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_child_btn_id:
                if (getArguments() != null) {
                    updatedChildDataCall(view);
                } else {
                    //addNewChildCall(view);
                }
            default:
                Toast.makeText(getContext(), "another Button", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatedChildDataCall(final View view) {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        String token_header = saveData.get("login_token");
        String email_header = saveData.get("login_email");

        if(spinner.getSelectedItem().equals("Boy"))
        {
            updatedGender = "1";
        }
        else
        {
            updatedGender="2";
        }

        RequestBody requestName = RequestBody.create(MediaType.parse("multipart/form-data"), ent_name.getText().toString().trim());
        RequestBody requestGender = RequestBody.create(MediaType.parse("multipart/form-data"), updatedGender);
        RequestBody requestDateBirth = RequestBody.create(MediaType.parse("multipart/form-data"), ent_bday.getText().toString().trim());
        File file;

        String childImage;
        if(photoPaths!=null) {
            childImage = photoPaths.get(0);
             file = new File(childImage);
        }
        else
        {
             file = new File("");
        }
        //+++++++=========================+++++++++
        //set Image in Api
        MultipartBody.Part childImageMultipart = MultipartBody.Part.createFormData("child[image]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        Call<ChildProfileResponse.Child> responseCall = service.updateChild(token_header, email_header, childId,requestName,requestDateBirth,requestGender,childImageMultipart);
        responseCall.enqueue(new Callback<ChildProfileResponse.Child>() {
            @Override
            public void onResponse(Call<ChildProfileResponse.Child> call, Response<ChildProfileResponse.Child> response) {
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
                                Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                snackbar.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 500:
                            try {
                                //JSONObject jObjError = new JSONObject(response.errorBody().string());
                                TSnackbar snackbar = TSnackbar.make(view.findViewById(android.R.id.content)," Something went Wrong !! ", TSnackbar.LENGTH_LONG);
                                snackbar.setActionTextColor(Color.WHITE);
                                View snackbarView = snackbar.getView();
                                snackbarView.setBackgroundColor(getResources().getColor(R.color.light_pink));
                                TextView textView = snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
                                textView.setTextColor(Color.WHITE);
                                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                                Toast.makeText(getContext(), response.errorBody() + "" + response.message(), Toast.LENGTH_LONG).show();
                                snackbar.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                    }
                } else {
                    FragmentManager fm = getActivity()
                            .getSupportFragmentManager();

                    String fragmentTag = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName();
                    fm.popBackStack(fragmentTag,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }

            @Override
            public void onFailure(Call<ChildProfileResponse.Child> call, Throwable t) {
                call.cancel();
            }
        });


    }

    private void spinnerAdapter(View view) {

        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.gender_layout, gender);

        //adapter.setDropDownViewResource(R.layout.gender_layout,gender);
        spinner.setAdapter(adapter);
        if (childName != null) {
            spinner.setSelection(genderId-1);
        }
        spinner.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.spinner_draw));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        FilePickerBuilder.getInstance().setMaxCount(1)
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
                    photoPaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                }
                break;
            default:
                Toast.makeText(getContext(), "asdadasda", Toast.LENGTH_LONG).show();
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

        try {
            Glide.with(this)
                    .load(bitmap)
                    .into(profile_show);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

    @OnClick(R.id.add_child_layout)
    public void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
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
