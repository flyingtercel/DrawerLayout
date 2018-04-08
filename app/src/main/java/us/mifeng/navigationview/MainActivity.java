package us.mifeng.navigationview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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
    //触摸时候的点的坐标
    private int posX = 0;
    private int posY = 0;
    //移动之后点的坐标
    private int curPosX = 0;
    private int curPosY = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,DrawerActivity.class));
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
        //主视图的事件处理过程
        mian_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //判断手势有没有在这个视图中的水平方向滑动
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //获取点击的坐标
                        posX = (int) event.getX();
                        posY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //如果大于10，标识滑动了
                        curPosX = (int) event.getX();
                        curPosY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //判断在X轴方向滑动的距离是不是大于125，并且判断x轴方向滑动的距离是不是大于y轴滑动的距离
                        if ((curPosX-posX>125) && (curPosX-posX)-(curPosY-posY)>0){
                            //如果满足，则打开抽屉
                            if (!drawerLayout.isDrawerOpen(navigationView)){
                                drawerLayout.openDrawer(navigationView);
                            }
                        }
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
