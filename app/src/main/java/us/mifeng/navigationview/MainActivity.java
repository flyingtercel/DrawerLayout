package us.mifeng.navigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout mian_layout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();
        //设置显示home键,并且能够点击
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置显示home键的图标
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);
    }

    private void findview() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mian_layout = (LinearLayout) findViewById(R.id.main_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //在Toolbar上添加视图，并设置视图的宽度和高度，以及显示的文本
        toolbar.addView(LayoutInflater.from(this).inflate(R.layout.toolbar_view,null), WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置Toolbar与Drawerlayout关联
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                //上下文
                this,
                //DrawerLayout对象
                drawerLayout,
                //ToolBar对象
                toolbar,
                //文本内容
                R.string.drawer_open,
                R.string.drawer_close){
            //设置滑动时候，主视图的动作
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                int drawerViewWidth = drawerView.getWidth();
                mian_layout.setTranslationX(drawerViewWidth*slideOffset);
            }
        };
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        //headView就是头部的布局，指的是head_layout布局
        View headerView = navigationView.getHeaderView(0);
        final ImageView head_img = (ImageView) headerView.findViewById(R.id.head_img);
        //head_img的点击事件
        head_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                head_img.setImageResource(R.mipmap.ic_launcher);
            }
        });
        //设置navigationView的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //写条目的点击事件
                switch (item.getItemId()){
                    case R.id.add_menu:
                        showTaost("我是添加的");
                        break;
                    case R.id.delete_menu:
                        showTaost("我是删除的");
                        break;
                    case R.id.update_menu:

                        break;
                    case R.id.query_menu:

                        break;
                    case R.id.heihei_menu:

                        break;
                    case R.id.hehe_menu:

                        break;
                }




                return true;
            }
        });
    }
    //返回键的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //判断抽屉有没有打开，如果没有打开，则打开
                if (!drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.openDrawer(navigationView);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showTaost(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
