
<a href="https://www.ramotion.com/agency/app-development/?utm_source=gthb&utm_medium=repo&utm_campaign=expanding-collection-android"><img src="https://github.com/Ramotion/folding-cell/blob/master/header.png"></a>

<a href="https://github.com/Ramotion/expanding-collection-android">
<img align="left" src="https://github.com/Ramotion/expanding-collection-android/blob/master/preview.gif" width="480" height="360" /></a>

<p><h1 align="left">EXPANDING COLLECTION</h1></p>

<h4>ExpandingCollection is a material design card peek/pop controller</h4>

<p><h6>Stay tuned for the latest updates:</h6>
<a href="https://goo.gl/rPFpid" >

[
![Github](https://img.shields.io/badge/follow-github-black.svg)](https://github.com/upendra-bajpai)
[![Twitter](https://img.shields.io/badge/Twitter-@Upendra-blue.svg?style=flat)](https://twitter.com/BajpaiUpendra)
[![Generic badge](https://img.shields.io/badge/visit-medium-black.svg)](https://medium.com/@supernovaplazma)
[
![Generic badge](https://img.shields.io/badge/file-issue-black.svg)](https://github.com/upendra-bajpai/expanding-collection-network)
## Requirements
​
- Android 4.0 IceCreamSandwich (API lvl 14) or greater
- Your favorite IDE

## Installation
​
maven repo:
Add Jitpack in project gradle
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Gradle:
```groovy
implementation 'com.github.upendra-bajpai:expanding-collection-network:1.2.0'
```
```
SBT:
```scala
libraryDependencies += "com.github.upendra-bajpai" % "expanding-collection-network" % "1.2.0"	
```
```
Maven:
```xml
<dependency>
	    <groupId>com.github.upendra-bajpai</groupId>
	    <artifactId>expanding-collection-network</artifactId>
	    <version>1.2.0</version>
	</dependency>
```
```

## Basic usage
 ​
1. Add a background switcher element `ECBackgroundSwitcherView` and a main pager element `ECPagerView` to your layout. `ECPagerView` should always have `match_parent` width and `wrap_content` height. You can adjust the vertical position yourself using **alignment/gravity** or **top margin**. `ECBackgroundSwitcherView` is the dynamic background switcher, so you probably want it to be as big as its parent.

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <com.ramotion.expandingcollection.ECBackgroundSwitcherView
        android:id="@+id/ec_bg_switcher_element"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
        
    <com.ramotion.expandingcollection.ECPagerView
        android:id="@+id/ec_pager_element"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>
        
</RelativeLayout>
```

2. Tune `ECPagerView`: setup size of card in collapsed state and height of header in expanded state.  

```xml
<com.ramotion.expandingcollection.ECPagerView xmlns:ec="http://schemas.android.com/apk/res-auto"
    android:id="@+id/ec_pager_element"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_centerInParent="true"
    ec:cardHeaderHeightExpanded="150dp"
    ec:cardHeight="200dp"
    ec:cardWidth="250dp" />
```

3. Expanded card contains two parts: a header part with a background (initially visible when card is collapsed) and a ListView element as content (visible only when card is expanded), so you need an xml layout for the list items.

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    
    <TextView xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/list_item_text"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_gravity="center_vertical|center_horizontal"
        android:background="@color/colorPrimary"
        android:textAlignment="center" />
        
</FrameLayout>
```

4. Also, you need to implement a custom list adapter for the list items by extending the parametrized `com.ramotion.expandingcollection.ECCardContentListItemAdapter.java` class,  where `T` is type of datasource object for list items inside the card. In the example below, `T` is just a string object. It's a pretty straightforward implementation with a common view holder pattern. 

```java
public class CardListItemAdapter extends ECCardContentListItemAdapter<String> {

    public CardListItemAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, R.layout.list_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rowView = inflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.itemText = (TextView) rowView.findViewById(R.id.list_item_text);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        String item = getItem(position);
        if (item != null) {
            viewHolder.itemText.setText(item);
        }
        return rowView;
    }

    static class ViewHolder {
        TextView itemText;
    }

}
```
5. Your data class must implement the `com.ramotion.expandingcollection.ECCardData.java` interface where `T` is type of datasource object for list items inside the card.

```java
public class CardDataImpl implements ECCardData<String> {

    private String cardTitle;
    private Integer mainBackgroundResource;
    private Integer headBackgroundResource;
    private List<String> listItems;

    @Override
    public Integer getMainBackgroundResource() {
        return mainBackgroundResource;
    }

    @Override
    public Integer getHeadBackgroundResource() {
        return headBackgroundResource;
    }

    @Override
    public List<String> getListItems() {
        return listItems;
    }
}
```

6. Almost done! The last thing we need to do is provide our dataset to a pager element through a pager adapter. It's just an implementation of the abstract class `com.ramotion.expandingcollection.ECPagerViewAdapter.java` with one abstract method, so it can be easily implemented inside your activity.  

```java
public class MainActivity extends Activity {

   private ECPagerView ecPagerView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       // Get pager from layout
       ecPagerView = (ECPagerView) findViewById(R.id.ec_pager_element);

       // Generate example dataset
       List<ECCardData> dataset = CardDataImpl.generateExampleData();

       // Implement pager adapter and attach it to pager view
       ecPagerView.setPagerViewAdapter(new ECPagerViewAdapter(getApplicationContext(), dataset) {
           @Override
           public void instantiateCard(LayoutInflater inflaterService, ViewGroup head, ListView list, ECCardData data) {
               // Data object for current card
               CardDataImpl cardData = (CardDataImpl) data;

               // Set adapter and items to current card content list
               list.setAdapter(new CardListItemAdapter(getApplicationContext(), cardData.getListItems()));
               // Also some visual tuning can be done here
               list.setBackgroundColor(Color.WHITE);

               // Here we can create elements for head view or inflate layout from xml using inflater service
               TextView cardTitle = new TextView(getApplicationContext());
               cardTitle.setText(cardData.getCardTitle());
               cardTitle.setTextSize(COMPLEX_UNIT_DIP, 20);
               FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
               layoutParams.gravity = Gravity.CENTER;
               head.addView(cardTitle, layoutParams);

               // Card toggling by click on head element
               head.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(final View v) {
                       ecPagerView.toggle();
                   }
               });
           }
       });

       // Add background switcher to pager view
       ecPagerView.setBackgroundSwitcherView((ECBackgroundSwitcherView) findViewById(R.id.ec_bg_switcher_element));

   }

   // Card collapse on back pressed
   @Override
   public void onBackPressed() {
       if (!ecPagerView.collapse())
           super.onBackPressed();
   }

}
```

Samples are coming soon.

<br>


## 📄 License

Expanding Collection Android is released under the MIT license.
See [LICENSE](./LICENSE) for details.

This library is a part of a <a href="https://github.com/Ramotion/android-ui-animation-components-and-libraries"><b>selection of our best UI open-source projects</b></a>

If you use the open-source library in your project, please make sure to credit and backlink to www.ramotion.com



