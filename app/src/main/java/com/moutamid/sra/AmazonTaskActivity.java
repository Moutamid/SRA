package com.moutamid.sra;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.card.MaterialCardView;
import com.moutamid.sra.databinding.ActivityAmazonTaskBinding;
import com.moutamid.sra.models.OrdersModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

public class AmazonTaskActivity extends AppCompatActivity {
    ActivityAmazonTaskBinding binding;
    int i = 0, assets;
    double total;
    ProgressDialog progressDialog;
    String ID;
    ArrayList<OrdersModel> ordersList;
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAmazonTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");

        assets = getIntent().getIntExtra("assets", 0);

        ordersList = new ArrayList<>();

        getData();

        binding.back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.btnGrab.setOnClickListener(v -> {
            binding.grabbingText.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInDown)
                    .duration(1000)
                    .repeat(20)
                    .delay(100)
                    .playOn(findViewById(R.id.q1));

            YoYo.with(Techniques.SlideInDown)
                    .duration(1000)
                    .repeat(20)
                    .delay(200)
                    .playOn(findViewById(R.id.q2));

            YoYo.with(Techniques.SlideInDown)
                    .duration(1000)
                    .repeat(20)
                    .delay(400)
                    .onEnd(animator -> {
                        binding.grabbingText.setVisibility(View.GONE);
                        Collections.shuffle(ordersList);
                        random = new Random().nextInt(ordersList.size());
                        showProductDialog(random);
                    })
                    .playOn(findViewById(R.id.q3));
        });
    }

    private void showProductDialog(int random) {
        OrdersModel model = ordersList.get(random);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.product_dialog);
        dialog.setCancelable(true);

        ImageView productImage = dialog.findViewById(R.id.productImage);
        TextView name = dialog.findViewById(R.id.dialog_name);
        TextView price = dialog.findViewById(R.id.dialog_price);
        TextView quantity = dialog.findViewById(R.id.dialog_quanity);
        TextView id = dialog.findViewById(R.id.dialog_id);
        TextView time = dialog.findViewById(R.id.dialog_time);
        TextView total = dialog.findViewById(R.id.dialog_total);
        TextView commission = dialog.findViewById(R.id.dialog_commission);
        MaterialCardView submit = dialog.findViewById(R.id.btn_submit);
        MaterialCardView close = dialog.findViewById(R.id.btn_close);

        productImage.setImageResource(model.getImage());
        name.setText(model.getName());
        price.setText("$"+model.getPrice());
        quantity.setText("x"+model.getQuantity());
        id.setText(model.getID());
        time.setText(model.getOrderTime());
        total.setText("$"+model.getTotal());
        commission.setText("$"+model.getCommission());

        close.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);

    }

    private void getData() {
        ID = UUID.randomUUID().toString();
        OrdersModel model1 = new OrdersModel();
        model1.setID(ID);
        model1.setName("Amazon Basics 36 Pack AAA High-Performance Alkaline Batteries, 10-Year Shelf Life, Easy to Open Value Pack");
        model1.setQuantity(3);
        model1.setPrice(12.14);
        model1.setOrderTime("22/12/2022 11:23:22");
        model1.setImage(R.drawable.p1);
        model1.setCommission(3);
        total = model1.getQuantity() * model1.getPrice();
        model1.setTotal(total);
        ordersList.add(model1);

        ID = UUID.randomUUID().toString();
        OrdersModel model2 = new OrdersModel();
        model2.setID(ID);
        model2.setName("Apple Lightning to USB Cable (1 m)");
        model2.setQuantity(3);
        model2.setPrice(26.84);
        model2.setOrderTime("22/12/2022 11:23:22");
        model2.setImage(R.drawable.p2);
        model2.setCommission(3);
        total = model2.getQuantity() * model2.getPrice();
        model2.setTotal(total);
        ordersList.add(model2);

        ID = UUID.randomUUID().toString();
        OrdersModel model3 = new OrdersModel();
        model3.setID(ID);
        model3.setName("BENGOO G9000 Stereo Gaming Headset for PS4 PC Xbox One PS5 Controller, Noise Cancelling Over Ear Headphones with Mic,");
        model3.setQuantity(2);
        model3.setPrice(19.23);
        model3.setOrderTime("22/12/2022 11:23:22");
        model3.setImage(R.drawable.p3);
        model3.setCommission(3);
        total = model3.getQuantity() * model3.getPrice();
        model3.setTotal(total);
        ordersList.add(model3);

        ID = UUID.randomUUID().toString();
        OrdersModel model4 = new OrdersModel();
        model4.setID(ID);
        model4.setName("Chromecast with Google TV (HD) - Streaming Stick Entertainment on Your TV with Voice Search - Watch Movies, Shows, and Live");
        model4.setQuantity(1);
        model4.setPrice(8.17);
        model4.setOrderTime("22/12/2022 11:23:22");
        model4.setImage(R.drawable.p4);
        model4.setCommission(3);
        total = model4.getQuantity() * model4.getPrice();
        model4.setTotal(total);
        ordersList.add(model4);

        ID = UUID.randomUUID().toString();
        OrdersModel model5 = new OrdersModel();
        model5.setID(ID);
        model5.setName("cooler_master_rgb_hard_gaming_mouse_pad1_-_tejar");
        model5.setQuantity(7);
        model5.setPrice(19.41);
        model5.setOrderTime("22/12/2022 11:23:22");
        model5.setImage(R.drawable.p5);
        model5.setCommission(3);
        total = model5.getQuantity() * model5.getPrice();
        model5.setTotal(total);
        ordersList.add(model5);

        ID = UUID.randomUUID().toString();
        OrdersModel model6 = new OrdersModel();
        model6.setID(ID);
        model6.setName("DJI Mini 3 Pro (DJI RC) Lightweight and Foldable Camera Drone with 4K-60fps Video, 48MP Photo,");
        model6.setQuantity(1);
        model6.setPrice(12.11);
        model6.setOrderTime("22/12/2022 11:23:22");
        model6.setImage(R.drawable.p6);
        model6.setCommission(3);
        total = model6.getQuantity() * model6.getPrice();
        model6.setTotal(total);
        ordersList.add(model6);

        ID = UUID.randomUUID().toString();
        OrdersModel model7 = new OrdersModel();
        model7.setID(ID);
        model7.setName("GoPro HERO11 Black - Waterproof Action Camera with 5.3K60 Ultra HD Video, 27MP Photos, 1-1.9 Image Sensor,");
        model7.setQuantity(2);
        model7.setPrice(14.21);
        model7.setOrderTime("22/12/2022 11:23:22");
        model7.setImage(R.drawable.p7);
        model7.setCommission(3);
        total = model7.getQuantity() * model7.getPrice();
        model7.setTotal(total);
        ordersList.add(model7);

        ID = UUID.randomUUID().toString();
        OrdersModel model8 = new OrdersModel();
        model8.setID(ID);
        model8.setName("Kasa Indoor Pan-Tilt Smart Security Camera, 1080p HD Dog Camera 2.4GHz with Night Vision, Motion Detection");
        model8.setQuantity(3);
        model8.setPrice(11.87);
        model8.setOrderTime("22/12/2022 11:23:22");
        model8.setImage(R.drawable.p8);
        model8.setCommission(3);
        total = model8.getQuantity() * model8.getPrice();
        model8.setTotal(total);
        ordersList.add(model8);

        ID = UUID.randomUUID().toString();
        OrdersModel model9 = new OrdersModel();
        model9.setID(ID);
        model9.setName("philips_272m8cz_27_full_hd_curved_monitor_1-_tejar");
        model9.setQuantity(3);
        model9.setPrice(16.29);
        model9.setOrderTime("22/12/2022 11:23:22");
        model9.setImage(R.drawable.p9);
        model9.setCommission(3);
        total = model9.getQuantity() * model9.getPrice();
        model9.setTotal(total);
        ordersList.add(model9);

        ID = UUID.randomUUID().toString();
        OrdersModel model10 = new OrdersModel();
        model10.setID(ID);
        model10.setName("REMINGTON HAIR STRAIGHTENER MINERAL GLOW ST5408");
        model10.setQuantity(4);
        model10.setPrice(16.33);
        model10.setOrderTime("22/12/2022 11:23:22");
        model10.setImage(R.drawable.p10);
        model10.setCommission(3);
        total = model10.getQuantity() * model10.getPrice();
        model10.setTotal(total);
        ordersList.add(model10);

        ID = UUID.randomUUID().toString();
        OrdersModel model11 = new OrdersModel();
        model11.setID(ID);
        model11.setName("Lenovo 2022 Newest Ideapad 3 Laptop, 15.6 HD Touchscreen, 11th Gen Intel Core i3-1115G4 Processor, 8GB DDR4 RAM");
        model11.setQuantity(1);
        model11.setPrice(252.98);
        model11.setOrderTime("22/12/2022 11:23:22");
        model11.setImage(R.drawable.p11);
        model11.setCommission(3);
        total = model11.getQuantity() * model11.getPrice();
        model11.setTotal(total);
        ordersList.add(model11);

        ID = UUID.randomUUID().toString();
        OrdersModel model12 = new OrdersModel();
        model12.setID(ID);
        model12.setName("KENWOOD BLENDER GLASS 1 MILLCHOP 2L 800W BLP44270SS");
        model12.setQuantity(2);
        model12.setPrice(68.21);
        model12.setOrderTime("22/12/2022 11:23:22");
        model12.setImage(R.drawable.p12);
        model12.setCommission(3);
        total = model12.getQuantity() * model12.getPrice();
        model12.setTotal(total);
        ordersList.add(model12);

        ID = UUID.randomUUID().toString();
        OrdersModel model13 = new OrdersModel();
        model13.setID(ID);
        model13.setName("Logitech MK270 Wireless Keyboard And Mouse Combo For Windows, 2.4 GHz Wireless, Compact Mouse,");
        model13.setQuantity(3);
        model13.setPrice(66.24);
        model13.setOrderTime("22/12/2022 11:23:22");
        model13.setImage(R.drawable.p13);
        model13.setCommission(3);
        total = model13.getQuantity() * model13.getPrice();
        model13.setTotal(total);
        ordersList.add(model13);

        ID = UUID.randomUUID().toString();
        OrdersModel model14 = new OrdersModel();
        model14.setID(ID);
        model14.setName("SAMSUNG Galaxy Watch 4 40mm Smartwatch with ECG Monitor Tracker for Health, Fitness, Running, Sleep Cycles,");
        model14.setQuantity(2);
        model14.setPrice(150.77);
        model14.setOrderTime("22/12/2022 11:23:22");
        model14.setImage(R.drawable.p14);
        model14.setCommission(3);
        total = model14.getQuantity() * model14.getPrice();
        model14.setTotal(total);
        ordersList.add(model14);

        ID = UUID.randomUUID().toString();
        OrdersModel model15 = new OrdersModel();
        model15.setID(ID);
        model15.setName("Sensyne 10 Ring Light with 50 Extendable Tripod Stand, LED Circle Lights with Phone Holder for Live");
        model15.setQuantity(4);
        model15.setPrice(25.14);
        model15.setOrderTime("22/12/2022 11:23:22");
        model15.setImage(R.drawable.p15);
        model15.setCommission(3);
        total = model15.getQuantity() * model15.getPrice();
        model15.setTotal(total);
        ordersList.add(model15);

        ID = UUID.randomUUID().toString();
        OrdersModel model16 = new OrdersModel();
        model16.setID(ID);
        model16.setName("JBL-Flip");
        model16.setQuantity(3);
        model16.setPrice(89.47);
        model16.setOrderTime("22/12/2022 11:23:22");
        model16.setImage(R.drawable.p16);
        model16.setCommission(3);
        total = model16.getQuantity() * model16.getPrice();
        model16.setTotal(total);
        ordersList.add(model16);

        ID = UUID.randomUUID().toString();
        OrdersModel model17 = new OrdersModel();
        model17.setID(ID);
        model17.setName("JBL-Playlist");
        model17.setQuantity(3);
        model17.setPrice(68.76);
        model17.setOrderTime("22/12/2022 11:23:22");
        model17.setImage(R.drawable.p17);
        model17.setCommission(3);
        total = model17.getQuantity() * model17.getPrice();
        model17.setTotal(total);
        ordersList.add(model17);

        ID = UUID.randomUUID().toString();
        OrdersModel model18 = new OrdersModel();
        model18.setID(ID);
        model18.setName("TOZO T6 True Wireless Earbuds Bluetooth 5.3 Headphones Touch Control with Wireless Charging Case IPX8 Waterproof");
        model18.setQuantity(6);
        model18.setPrice(27.88);
        model18.setOrderTime("22/12/2022 11:23:22");
        model18.setImage(R.drawable.p18);
        model18.setCommission(3);
        total = model18.getQuantity() * model18.getPrice();
        model18.setTotal(total);
        ordersList.add(model18);

        ID = UUID.randomUUID().toString();
        OrdersModel model19 = new OrdersModel();
        model19.setID(ID);
        model19.setName("WEST POINT KITCHEN ROBOT 495");
        model19.setQuantity(1);
        model19.setPrice(64.80);
        model19.setOrderTime("22/12/2022 11:23:22");
        model19.setImage(R.drawable.p19);
        model19.setCommission(3);
        total = model19.getQuantity() * model19.getPrice();
        model19.setTotal(total);
        ordersList.add(model19);

        ID =UUID.randomUUID().toString();
        OrdersModel model20 = new OrdersModel();
        model20.setID(ID);
        model20.setName("WYZE Cam v3 with Color Night Vision, Wired 1080p HD Indoor-Outdoor Video Camera, 2-Way Audio, Works with Alexa,");
        model20.setQuantity(1);
        model20.setPrice(55.48);
        model20.setOrderTime("22/12/2022 11:23:22");
        model20.setImage(R.drawable.p20);
        model20.setCommission(3);
        total = model20.getQuantity() * model20.getPrice();
        model20.setTotal(total);
        ordersList.add(model20);


    }
}