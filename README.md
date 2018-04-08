# DrawerLayout
使用Navigation实现抽屉效果，并设置与Toolbar关联，模拟实现QQ抽屉效果</br>
#### 实现效果如下：
![实现qq抽屉效果](https://github.com/flyingtercel/DrawerLayout/blob/master/app/src/main/res/mipmap-hdpi/ss.png) 

#### DrawerLayout的使用</br>
```
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/drawerLayout"
    tools:context="us.mifeng.drawerlayout02.MainActivity">

    <!--需要两个控件，第一个控件，我们用来标识主视图-->
   <!--主布局-->
    <LinearLayout
        android:id="@+id/main_layout"
        android:layout_width="match_parent"
        android:background="#f0f"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:background="#88f"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"></android.support.v7.widget.Toolbar>
    </LinearLayout>
    <!--抽屉侧滑出来的菜单
        android:layout_gravity="left":指明抽屉是从那边划出来
        (left 左边划出，right 右边划出)
    -->
    <!--代替原来的布局，在控件中添加了新的属性，方法
        app:headerLayout：加载头部布局的
        app:menu:加载条目
        app:itemIconTint：设置条目中图标的颜色
        app:elevation：设置z轴的缩影
        app:itemBackground：设置条目的背景颜色
        app:insetForeground：设置点击时候的颜色变化
        app:itemTextColor：设置字体的颜色
        app:itemTextAppearance：用来设置字体的样式

    -->
    <android.support.design.widget.NavigationView
        android:id="@+id/navigation_view"
        android:layout_width="300dp"
        android:layout_height="match_parent"
        android:background="#ff0"
        android:layout_gravity="left"
        app:headerLayout="@layout/head_layout"
        app:menu="@menu/drawer_menu"
        app:itemIconTint="@color/colorAccent"
        app:elevation="15dp"
        app:itemBackground="@android:color/white"
        app:itemTextColor="#f0f"
        app:insetForeground="#efefef"
        app:itemTextAppearance="@style/TextStyle"/>
```
##### Style中样式的声明：
```
<!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <!--去除标题和Action Bar-->
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>
    <!--navigation的item上文本的样式-->
    <style name="TextStyle" parent="@android:style/Widget">
        <item name="android:textSize">23sp</item>
        <item name="android:textColor">@color/colorText</item>
        <item name="android:fontFamily">宋体</item>
    </style>
</resources>
```
##### 在Activity中设置Home键显示，并设置Home键的点击事件，以及改变Home键的图标
```
 //设置显示home键,并且能够点击
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置显示home键的图标
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);
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
```
###### NavigationView中头部点击事件及NavigationView中itam的点击事件
```
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
                 }
             });
          }
             
```
###### 设置navigationView的menu菜单中的分割线
###### menu中写法如下
```
<group android:id="@+id/one">
        <item android:title="Group1-Item数据">
            <menu>
                <item android:title="Group1-Menu-Item数据1" />
                <item android:title="Group1-Menu-Item数据3" />
            </menu>
        </item>
    </group>
    <group android:id="@+id/two">
        <item android:title="Group1-Item数据">
            <menu>
                <item android:title="Group2-Menu-Item数据1" />
                <item android:title="Group2-Menu-Item数据3" />
            </menu>
        </item>
    </group>
    <group android:id="@+id/three">
        <item android:title="Group3-Item数据">
            <item android:title="Group3-Item数据3" />
        </item>
    </group>
```
###### 设置分割线
```
public static void setNavigationMenuLineStyle(NavigationView navigationView, @ColorInt final int color, final int height){
        try {
            Field fieldByPressenter = navigationView.getClass().getDeclaredField("mPresenter");
            fieldByPressenter.setAccessible(true);
            NavigationMenuPresenter menuPresenter = (NavigationMenuPresenter) fieldByPressenter.get(navigationView);
            Field fieldByMenuView = menuPresenter.getClass().getDeclaredField("mMenuView");
            fieldByMenuView.setAccessible(true);
            final NavigationMenuView mMenuView = (NavigationMenuView) fieldByMenuView.get(menuPresenter);
            mMenuView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                    RecyclerView.ViewHolder viewHolder = mMenuView.getChildViewHolder(view);
                    if (viewHolder != null && "SeparatorViewHolder".equals(viewHolder.getClass().getSimpleName()) && viewHolder.itemView != null) {
                        if (viewHolder.itemView instanceof FrameLayout) {
                            FrameLayout frameLayout = (FrameLayout) viewHolder.itemView;
                            View line = frameLayout.getChildAt(0);
                            line.setBackgroundColor(color);
                            line.getLayoutParams().height = height;
                            line.setLayoutParams(line.getLayoutParams());
                        }
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {

                }
            });

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
```
