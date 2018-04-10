package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren on 09/06/2017.
 */

    import anywheresoftware.b4a.AbsObjectWrapper;
    import anywheresoftware.b4a.BA;
    import anywheresoftware.b4a.BA.ShortName;
    import anywheresoftware.b4a.BA.Author;
    import anywheresoftware.b4a.keywords.Common;

    import android.app.Activity;
    import android.app.PendingIntent;
    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.widget.RemoteViews;

@ShortName("NotificationCustomLayout")

public class NotificationCustomLayout extends AbsObjectWrapper<RemoteViews> {

    //private static int BLANK_LAYOUT_ID = 0;
    private static int clickId = 0;
    public static final int NORMAL_HEIGHT = 64;
    public static final int EXPANDED_HEIGHT = 256;

    /**
     * Initilizes the Object
     *
     * LayoutFile - The name of the .xml file containing the layout (do not add '.xml')
     */
    public void Initialize(String LayoutFile) {
        int LayoutID = BA.applicationContext.getResources().getIdentifier(LayoutFile, "layout", BA.packageName);
        setObject(new RemoteViews(BA.packageName, LayoutID));
    }

    /**
     * !!! NOT WORKING !!!
     *
     * Initializes the object, using a Panel of views for the layout
     *
     */
    public void Initialize2(BA ba, String Panel) {
        //BLANK_LAYOUT_ID = BA.applicationContext.getResources().getIdentifier("blank", "layout", BA.packageName);
        setObject(new RemoteViews(BA.packageName, BA.applicationContext.getResources().getIdentifier(Panel, "id", BA.packageName)));


        //for(int i = 0; i == (Panel.getChildCount() - 1); i++) {

        //}
    }

    /**
     * Returns the CustomLayout object
     */
    public RemoteViews BuildLayout() {
        return ((RemoteViews)getObject());
    }

    /**
     * Enables the Click action for a view in a custom layout.
     *
     * View - The name of the view as defined in the xml layout
     * Module - The activity or Service that will be launched when the view is clicked.
     * Tag - The tag that will be returned as an extra in the starting intent
     *
     * Implements setOnClickPendingIntent
     * Android V3+
     * @throws ClassNotFoundException
     */
    public void setEnableClickEvent(BA ba, String View, Object Module, String Tag) throws ClassNotFoundException {
        Intent clickIntent = Common.getComponentIntent(ba, Module);
        if (Tag.length() > 0) {
            clickIntent.putExtra("Notification_Click_Tag", Tag);
        }
        else {
            clickIntent.putExtra("Notification_Click_Tag", View);
        }

        PendingIntent clickPendingIntent;

        if (Module instanceof Activity) {
            clickPendingIntent = PendingIntent.getActivity(ba.context, clickId++, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            clickPendingIntent = PendingIntent.getService(ba.context, clickId++, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }


        ((RemoteViews)getObject()).setOnClickPendingIntent(BA.applicationContext.getResources().getIdentifier(View, "id", BA.packageName), clickPendingIntent);
    }

    /**
     * Sets the text field in a custom layout
     *
     * TextField - The name of the text element as defined in the xml layout
     * Text - The string that will be assigned to the text element
     */
    public void SetTextField(String TextField, String Text) {
        ((RemoteViews)getObject()).setTextViewText(BA.applicationContext.getResources().getIdentifier(TextField, "id", BA.packageName), Text);
    }

    /**
     * Sets the color of the text in a text field in a custom layout
     *
     * TextField - The name of the text element as defined in the xml layout
     * Color - The color that will be assigned to the text element
     */
    public void SetTextColor(String TextField, int Color) {
        ((RemoteViews)getObject()).setTextColor(BA.applicationContext.getResources().getIdentifier(TextField, "id", BA.packageName), Color);
    }

    /**
     * Sets an image within a custom layout
     *
     * Image - The name of the image element as defined in the xml layout
     * Bitmap - The image to apply
     */
    public void SetImage(String Image, Bitmap Bitmap) {
        ((RemoteViews)getObject()).setImageViewBitmap(BA.applicationContext.getResources().getIdentifier(Image, "id", BA.packageName), Bitmap);
    }

    /**
     * Sets the visibility of a view within the custom layout
     * View - The name of the view as defined in the xml layout
     * Visibility - Pass one of the following Int's
     *
     * 0 - Visible		Visible on screen; the default value.
     * 1 - Invisible	Not displayed, but taken into account during layout (space is left for it).
     * 2 - Gone			Completely hidden, as if the view had not been added.
     */
    public void SetVisibility(String View, int Visibility) {
        ((RemoteViews)getObject()).setViewVisibility(BA.applicationContext.getResources().getIdentifier(View, "id", BA.packageName), Visibility);
    }

    /**
     * Sets the parameters for a progress bar used in a notification
     *
     * ProgressBar - The name of the progress bar as defined in the xml layout
     * MaxProgress - the upper limit of the progress bar. A good value is 100
     * Progress - the current level of progress to be shown. Must be &lt;= maxProgress.
     * Indeterminate - set the progress bar as indeterminate
     */
    public void SetProgress(String ProgressBar, int MaxProgress, int Progress, boolean Indeterminate) {
        ((RemoteViews)getObject()).setProgressBar(BA.applicationContext.getResources().getIdentifier(ProgressBar, "id", BA.packageName), MaxProgress, Progress, Indeterminate);
    }
}