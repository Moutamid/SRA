package com.moutamid.sra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moutamid.sra.databinding.ActivityDepositBinding;
import com.moutamid.sra.models.DepositRequestModel;
import com.moutamid.sra.utils.Constants;

import java.util.Date;
import java.util.UUID;

public class DepositActivity extends AppCompatActivity {
    ActivityDepositBinding binding;
    private static final int PICK_FROM_GALLERY = 1;
    Uri imageURI;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepositBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(DepositActivity.this);
        progressDialog.setMessage("Sending Your Request...");
        progressDialog.setCancelable(false);

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.hashKey.setText("TXLnyK6T7sDoagC293kKmaYYoGuvnUJxrV");

        binding.hashKey.setOnClickListener(v -> {
            String str = binding.hashKey.getText().toString();
            ((ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Copied Text", str));
            Toast.makeText(this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
        });

        binding.screenshotBtn.setOnClickListener(v -> {
            if (imageURI != null) {
                showDialog();
            } else {
                getImageFromGallery();
            }
        });

        binding.btnSend.setOnClickListener(v -> {
            if (validate()){
                String uid = UUID.randomUUID().toString();
                progressDialog.show();
                Constants.storageReference(Constants.auth().getCurrentUser().getUid()).child("depositRequest").child(uid).child("image")
                        .putFile(imageURI)
                        .addOnSuccessListener( taskSnapshot -> {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                                Date d = new Date();
                                DepositRequestModel model = new DepositRequestModel (
                                        uid,
                                        uri.toString(),
                                        Constants.auth().getCurrentUser().getUid(),
                                        Float.parseFloat(binding.amount.getEditText().getText().toString()), d.getTime(),
                                        "PEN", "DEP"
                                );
                                Constants.databaseReference().child("Request").child(Constants.auth().getCurrentUser().getUid())
                                        .child(uid).setValue(model).addOnSuccessListener(unused -> {
                                            progressDialog.dismiss();
                                            imageURI = null;
                                            binding.amount.getEditText().setText("");
                                            binding.addImage.setImageDrawable(getDrawable(R.drawable.add_image));
                                            Toast.makeText(this, "Request Sent Successfully", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(this, "You can go back now", Toast.LENGTH_SHORT).show();
                                        }).addOnFailureListener(e -> {
                                            progressDialog.dismiss();
                                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                            }).addOnFailureListener(e -> {
                                progressDialog.dismiss();
                                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        }).addOnFailureListener(e -> {
                            progressDialog.dismiss();
                            Toast.makeText(DepositActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.show_image_dialog);

        TextView delete = (TextView) dialog.findViewById(R.id.delete_image);
        TextView change = (TextView) dialog.findViewById(R.id.change_image);
        TextView close = (TextView) dialog.findViewById(R.id.close_image);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.preview_image);

        imageView.setImageURI(imageURI);

        delete.setOnClickListener(v -> {
            imageURI = null;
            binding.addImage.setImageDrawable(getDrawable(R.drawable.add_image));
            dialog.dismiss();
        });

        change.setOnClickListener(v -> {
            getImageFromGallery();
            dialog.dismiss();
        });

        close.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    private void getImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Continue with"), PICK_FROM_GALLERY);
    }

    private boolean validate() {
        if (imageURI == null){
            Toast.makeText(this, "Please add a screenshot", Toast.LENGTH_SHORT).show();
            return false;
        } if (binding.amount.getEditText().getText().toString().isEmpty()){
            Toast.makeText(this, "Please add the amount", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_FROM_GALLERY) {
                try {
                    if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                        imageURI = data.getData();
                        binding.addImage.setImageURI(imageURI);
                    }
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}