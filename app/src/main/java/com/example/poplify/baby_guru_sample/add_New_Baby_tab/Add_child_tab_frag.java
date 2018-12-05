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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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

import com.example.poplify.baby_guru_sample.MainActivity;
import com.example.poplify.baby_guru_sample.R;
import com.example.poplify.baby_guru_sample.adapter.SaveData;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Add_child_tab_frag extends Fragment implements View.OnClickListener{

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
    public static final int RC_PHOTO_PICKER_PERM = 123;
    private ArrayList<String> photoPaths = new ArrayList<>();


    public Add_child_tab_frag() {
        // Required empty public constructor
    }

    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_child_tab_frag, container, false);


        initChild(view);
        setupChild(view);
        spinnerAdapter(view);




        profile_btn = view.findViewById(R.id.select_profile);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeProfilePic();
            }
        });

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
        tb_title_add.setText(saveData.get("addChild"));
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

        ent_bday = view.findViewById(R.id.ed_ch_bday); //EditTxt bday
        ent_bday.setTypeface(regularMon);

        //Button
        add_4_child_btn = view.findViewById(R.id.add_child_btn_id);
        add_4_child_btn.setTypeface(regular);

        add_4_child_btn.setOnClickListener(this);


        //--------------------********************setting up the Profile Pic**********--------------------------//

        profile_show = view.findViewById(R.id.profile_pic);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add_child_btn_id:

                default:
                    Toast.makeText(getContext(),"another Buttuon",Toast.LENGTH_SHORT).show();
        }
    }

    private void spinnerAdapter(View view) {
        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.gender_layout, gender);

        //adapter.setDropDownViewResource(R.layout.gender_layout,gender);
        spinner.setAdapter(adapter);

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
        //parentProfileShow.setImageBitmap(bitmap);


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

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }



}
