package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren on 09/06/2017.
 */

    import anywheresoftware.b4a.AbsObjectWrapper;
    import anywheresoftware.b4a.BA;
    import anywheresoftware.b4a.BA.Author;
    import anywheresoftware.b4a.BA.DependsOn;
    import anywheresoftware.b4a.BA.ShortName;
    import anywheresoftware.b4a.keywords.Common;

    import android.app.PendingIntent;
    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.support.v4.app.NotificationCompat;
    import android.support.v4.app.NotificationCompat.WearableExtender;
    import android.support.v4.app.NotificationCompat.Builder;
    import android.support.v4.app.RemoteInput;

@ShortName("NotificationWearableExtender")

public class NotificationWearExtender extends AbsObjectWrapper<WearableExtender>{

    public static final int START = 8388611;
    public static final int END = 8388613;
    public static final int TOP = 48;
    public static final int CENTER_VERTICAL = 16;
    public static final int BOTTOM = 80;

    public static final int SIZE_DEFAULT = 0;
    public static final int SIZE_FULL_SCREEN = 5;
    public static final int SIZE_LARGE = 4;
    public static final int SIZE_MEDIUM = 3;
    public static final int SIZE_SMALL = 2;
    public static final int SIZE_XSMALL = 1;

    private static int actionId = 200;

    /**
     * Initializes the object
     */
    public void Initialize(BA ba) {
        setObject(new NotificationCompat.WearableExtender());
    }

    /**
     * Similar to NotificatioBuilder.AddAction, but the Action will show on Wear Devices only.
     * If this method is used the Wear device will NOT display any of the other actions.
     *
     * Tags are named 'Notification_Wear_Action_Tag
     */
    public void AddAction(BA ba, String icon, String title, String tag, Object object) throws ClassNotFoundException {
        AddAction2(ba, icon, title, tag, object, null);
    }

    /**
     * Similar to AddAction but allows you to pass a RemoteInput object for voice input.
     */
    public void AddAction2(BA ba, String icon, String title, String tag, Object object, RemoteInput.Builder RemoteInput ) throws ClassNotFoundException {
        int ActionIcon = BA.applicationContext.getResources().getIdentifier(icon, "drawable", BA.packageName);
        Intent ActionIntent = Common.getComponentIntent(ba, object);
        if (tag.length() > 0) {
            ActionIntent.putExtra("Notification_Wear_Action_Tag", tag);
        }
        else {
            ActionIntent.putExtra("Notification_Wear_Action_Tag", title);
        }
        PendingIntent ActionPendingIntent = PendingIntent.getActivity(ba.context, actionId++, ActionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(ActionIcon, title, ActionPendingIntent);
        BA.Log("rem = " + RemoteInput);
        if (RemoteInput != null) {
            actionBuilder.addRemoteInput(RemoteInput.build());
        }
        NotificationCompat.Action action = actionBuilder.build();

        ((WearableExtender)getObject()).addAction(action);
    }

    /**
     * Adds an additional page to notifications shown on Wear Device
     *
     * Page - A new notification.
     *
     * Example:
     *
     * <code>
     * Dim Notification1, Notification2 as NotificationBuilder
     * 'code to build notifications
     * ...
     * Notification1.AddPage(Notification2.build())
     * ...
     * </code>
     */
    public void AddPage(BA ba, Builder Page ) {
        ((WearableExtender)getObject()).addPage(Page.build());
    }

    /**
     * Clears any previously added Actions.
     */
    public void ClearActions() {
        ((WearableExtender)getObject()).clearActions();
    }

    /**
     * Clears any previously added Pages.
     */
    public void ClearPages() {
        ((WearableExtender)getObject()).clearPages();
    }

    /**
     * Set or Get the background of the Wear Notification (no matter what the Style)
     */
    public void setBackground(Bitmap Background) {
        ((WearableExtender)getObject()).setBackground(Background);
    }

    public Bitmap getBackground() {
        return ((WearableExtender)getObject()).getBackground();
    }

    /**
     * Sets the action to be clickable with the content of this notification. i.e. When you click the main notification, it will trigger the Action.
     *
     * This action will no longer display separately from this notification's content.
     * For notifications with multiple pages, child pages can also have content actions set,
     * although the list of available actions comes from the main notification and not from the child page's notification.
     *
     * ActionIndex is the ID of the Action to use. ID's start at 0
     */
    public void setContentAction(int ActionIndex) {
        ((WearableExtender)getObject()).setContentAction(ActionIndex);
    }

    /**
     * Sets the Icon that goes with the content of this notification
     *
     * The image file should manually copied to the Objects\res\drawable\ folder and set to read-only.
     * The file name is case sensitive and should not contain the file extension.
     * You can use "icon" to use the applications icon.
     */
    public void setContentIcon(String Icon) {
        ((WearableExtender)getObject()).setContentIcon(BA.applicationContext.getResources().getIdentifier(Icon, "drawable", BA.packageName));
    }

    /**
     * Sets the gravity for the ContentIcon.
     * Supported values START, END
     * Use the built in constants. e.g <code>WearExtender.START</code>
     */
    public void setContentIconGravity(int Gravity) {
        ((WearableExtender)getObject()).setContentIconGravity(Gravity);
    }

    /**
     * Sets whether the content intent is available when the wearable device is not connected to a companion device.
     */
    public void setContentIntentAvailableOffline(Boolean val) {
        ((WearableExtender)getObject()).setContentIntentAvailableOffline(val);
    }

    /**
     * Sets or Gets the custom height for the display of this notifications content.
     *
     * NOTE: This option is only available for custom display notifications created using DisplayIntent
     */
    public void setCustomContentHeight (int Height) {
        ((WearableExtender)getObject()).setCustomContentHeight(Height);
    }

    public int getCustomContentHeight() {
        return ((WearableExtender)getObject()).getCustomContentHeight();
    }

    /**
     * Sets or Gets the custom size preset for the display of this notification out of the available presents.
     * Use the built in constants. e.g. WearExtender1.SIZE_LARGE
     * Possible values - SIZE_DEFAULT, SIZE_FULL_SCREEN, SIZE_LARGE, SIZE_MEDIUM, SIZE_SMALL, SIZE_XSMALL
     */
    public void setCustomSizePreset(int SizePreset) {
        ((WearableExtender)getObject()).setCustomSizePreset(SizePreset);
    }

    public int getCustomSizePreset() {
        return ((WearableExtender)getObject()).getCustomSizePreset();
    }

    /**
     * Googles Docs
     * ------------
     * Set an activity to be displayed while viewing the notification.
     *
     * My Version after doing a little research
     * ----------------------------------------
     * When creating a notification directly from an App on the Wear device, you can use custom layouts. This is done by creating the layout
     * in an activity in the App. Then call this method, passing the layout activity as the Parameter.
     *
     * The activity to launch needs to allow embedding, must be exported and should have an empty task affinity.
     * It is also recommended to use the device default light theme.
     * This is achieved by adding the following to the Manifest Editor
     *
     *[code]
     * SetActivityAttribute (DisplayActivity, android:exported, "true")
     * SetActivityAttribute (DisplayActivity, android:allowEmbedded, "true")
     * SetActivityAttribute (DisplayActivity, android:taskAffinity, "")
     * SetActivityAttribute (DisplayActivity, android:theme, "@android:style/Theme.DeviceDefault.Light")
     * [/code]
     */
    public void setDisplayIntent(BA ba, Object Activity) throws ClassNotFoundException {
        //dIntent  = Activity;
        Intent displayIntent = Common.getComponentIntent(ba, Activity);
        PendingIntent displayPendingIntent = PendingIntent.getActivity(ba.context,  0, displayIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ((WearableExtender)getObject()).setDisplayIntent(displayPendingIntent);
    }

    /**
     * Sets the gravity of the Notification on the device.
     * Supported values BOTTOM, CENTER_VERTICALLY, TOP
     * Use the built in constants. e.g WearExtender.TOP
     */
    public void setGravity(int Gravity) {
        ((WearableExtender)getObject()).setGravity(Gravity);
    }

    /**
     * Hides the Icon if set to true.
     */
    public void setHideIcon(boolean val) {
        ((WearableExtender)getObject()).setHintHideIcon(val);
    }

    /**
     * Only the background image of this notification should be displayed, and other semantic content should be hidden.
     * This method only applies to sub Pages.
     */
    public void setShowBackgroundOnly(boolean val) {
        ((WearableExtender)getObject()).setHintShowBackgroundOnly(val);
    }

    /**
     * Sets whether the scrolling position for the contents of this notification should start at the bottom of the contents
     * instead of the top when the contents are too long to display within the screen. Default is false (start scroll at the top)
     */
    public void setStartScrollBottom(boolean val) {
        ((WearableExtender)getObject()).setStartScrollBottom(val);
    }
}