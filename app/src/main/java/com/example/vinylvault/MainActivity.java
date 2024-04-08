package com.example.vinylvault;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.NavigationUI;

import com.example.vinylvault.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    NavController navController;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO: Implement Fab Button
        /**
         * Author: Sierra Riley
         * This on click listener allows the fab button to navigate to a specific fragment
         * when it is on a certain fragment
         */
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDestination currentFragment = navController.getCurrentDestination();
                //If we are on album summary fragment
                if(currentFragment.getId() == R.id.nav_album_summary){
                    Bundle extra = new Bundle();
                    extra.putInt(AddAnAlbumFragment.ACTION_TYPE, AddAnAlbumFragment.CREATE);
                    //Go to the add album fragment in create mode
                    navController.navigate(R.id.nav_add_album, extra);
                }else if(currentFragment.getId() == R.id.nav_search){
                    String url = "https://www.ticketmaster.ca/discover/concerts?landing=c&awtrc=true&c=SEM_TMBRAND_ggl_6619616063_137379093082_ticketmaster&GCID=0&&gad_source=1&gclid=Cj0KCQjwiMmwBhDmARIsABeQ7xSC8NgZSWyUxtCT6yGxUrflbhNZSAuPKrKFMtZPweWV0XqMij0t3JUaAlNiEALw_wcB&gclsrc=aw.ds";
                    Uri webPage = Uri.parse(url);
                    Intent i = new Intent(Intent.ACTION_VIEW, webPage);
                    startActivity(i);
                }else if(currentFragment.getId() == R.id.nav_profile){
                    share(MainActivity.this, screenShot());

                }

            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile,
                R.id.nav_browse,
                R.id.nav_vault,
                R.id.nav_search)
                .build();



        //TODO: Add Animations to RecyclerViews

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.nav_search){
                    binding.fab.setImageResource(R.drawable.ic_baseline_screen_search_desktop_24);
                }

            }
        });


    }

    /**
     * author: Sierra Riley
     *
     * @return bitmap
     */
    private Bitmap screenShot() {
        View root = getWindow().getDecorView().getRootView();
        root.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
        root.setWillNotDraw(false);
        return bitmap;
    }

    /**
     * author: Sierra Riley
     * @param context
     * @param bitmap
     * starts intent
     */
    private void share(Context context, Bitmap bitmap){

        //Make sure screenshot is taking
        Bitmap screenshot = screenShot();
        if (screenshot == null) {
            Toast.makeText(context, "Failed to capture screenshot", Toast.LENGTH_SHORT).show();
            return;
        }

        //Save screenshot
        String path= MediaStore.Images.Media.insertImage(context.getContentResolver(),

                    screenshot,"App Screenshot", null);
        //Check to see if path is empty
        if (path == null) {
            Toast.makeText(context, "Failed to insert image into MediaStore", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uri = Uri.parse(path);

        //Intent -> Share screenshot through app of choice
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("smsto:"));
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_SUBJECT, "Vinyl Vault App");
        i.putExtra(Intent.EXTRA_TEXT, "Hey! Check out my top albums I've listened to so far. If you download the Vinyl Vault app, you can track your albums too! ");
        i.putExtra(Intent.EXTRA_STREAM, uri);

        if (i.resolveActivity(context.getPackageManager()) != null) {
            startActivity(i);
        } else {
            // Handle the case when no activity is found to handle the intent
            Toast.makeText(context, "No app found to handle the intent", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * @author Sierra Riley
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * @author Sierra Riley
     * @param item
     * @return Option Item Selected
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                navController.navigate(R.id.nav_settings);
                break;
            case R.id.action_credits:
                navController.navigate(R.id.nav_credits);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}