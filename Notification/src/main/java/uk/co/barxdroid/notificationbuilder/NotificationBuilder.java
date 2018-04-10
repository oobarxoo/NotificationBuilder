package uk.co.barxdroid.notificationbuilder;

/**
 * Created by Darren Barker on 27/12/2017.
 */

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Permissions;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.keywords.Common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.app.Notification;
import android.support.v4.app.RemoteInput;
import android.app.PendingIntent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Extender;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

@Permissions(values = {"android.permission.VIBRATE"})
@DependsOn(values = {"com.android.support:support-v4", "NotificationBuilder.aar"})
@ShortName("NotificationBuilder")
@Author("BarxDroid")
@Version(3.50F)

// Todo
// Channels API 26
//    - sound

// App Notification Badges/Dots API 26
// Remote Input builder API 21
// setPublicVersion()
// Media Controls

// Check MessagingStyle API 24

// Look up HUD
// Look up Lockscreen


public class NotificationBuilder extends AbsObjectWrapper<Builder>{

    /**
     * Constant Values for use within methods
     */

    public static final int VISIBILITY_PRIVATE = Notification.VISIBILITY_PRIVATE;
    public static final int VISIBILITY_PUBLIC = Notification.VISIBILITY_PUBLIC;
    public static final int VISIBILITY_SECRET = Notification.VISIBILITY_SECRET;

    public static final int PRIORITY_MAX = Notification.PRIORITY_MAX;
    public static final int PRIORITY_HIGH = Notification.PRIORITY_HIGH;
    public static final int PRIORITY_DEFAULT = Notification.PRIORITY_DEFAULT;
    public static final int PRIORITY_LOW = Notification.PRIORITY_LOW;
    public static final int PRIORITY_MIN = Notification.PRIORITY_MIN;

    public static final String CATEGORY_ALARM = Notification.CATEGORY_ALARM;
    public static final String CATEGORY_CALL = Notification.CATEGORY_CALL;
    public static final String CATEGORY_EMAIL = Notification.CATEGORY_EMAIL;
    public static final String CATEGORY_ERROR = Notification.CATEGORY_ERROR;
    public static final String CATEGORY_EVENT = Notification.CATEGORY_EVENT;
    public static final String CATEGORY_MESSAGE = Notification.CATEGORY_MESSAGE;
    public static final String CATEGORY_PROGRESS = Notification.CATEGORY_PROGRESS;
    public static final String CATEGORY_PROMO = Notification.CATEGORY_PROMO;
    public static final String CATEGORY_RECOMMENDATION = Notification.CATEGORY_RECOMMENDATION;
    public static final String CATEGORY_SERVICE = Notification.CATEGORY_SERVICE;
    public static final String CATEGORY_SOCIAL = Notification.CATEGORY_SOCIAL;
    public static final String CATEGORY_STATUS = Notification.CATEGORY_STATUS;
    public static final String CATEGORY_SYSTEM = Notification.CATEGORY_SYSTEM;
    public static final String CATEGORY_TRANSPORT = Notification.CATEGORY_TRANSPORT;

    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final int BADGE_ICON_LARGE = 2;

    private static long nWhen;
    private static boolean setWhen;
    private static int nNumber;
    private static int nDefaults;
    private static int pendingId = 1;
    private static int actionId = 100;
    private static String nTag;
    private static Object nActivity;
    private static Object pActivity;
    private static Intent nIntent;
    private static RemoteViews BigContent = null;

    /**
     * Initializesthe object.
     *
     * ChannelID - Specifies a Channel ID
     * NOTE: A Channel needs to be created first using NotificationChannelBuilder Object.
     */
    public void Initialize(BA ba, String ChannelID) {

        setObject(new NotificationCompat.Builder(ba.context, ChannelID));

        nWhen = System.currentTimeMillis();
        setWhen = true;
        nActivity = null;
        pActivity = null;
        nIntent = null;
        nDefaults = Notification.DEFAULT_ALL;
    }

    //methods

    /**
     * Sets whether the notification will play a sound.
     *
     * Note: Setting to True will over ride any custom sound set.
     *
     * Example:
     * <code>nb.DefaultSound = False</code>
     */
    public void setDefaultSound(boolean v) {
        setValue(v, 1);
    }

    /**
     * Sets a custom sound to be played on new notification
     *
     * Example:
     * <code>cn.CustomSound = "file:///sdcard/notification/ringer.mp3"</code>
     */

    public void setCustomSound (String path) {
        ((Builder)getObject()).setSound(Uri.parse(path));
    }

    /**
     * Sets whether the notification will show a light.
     *
     * Setting to True will over ride any custom light settings.
     *
     * Example:
     * <code>nb.DefaultLight = True</code>
     */
    public void setDefaultLight(boolean v) {
        setValue(v, 4);
    }

    /**
     * Sets a custom argb value for the notification light, also sets the rates at which it flashes.
     * argb - The color that the devices led will flash.
     * onMs - The time in milliseconds that the led will stay on each blink.
     * offMs - The time in millliseconds that the led will be off after each blink.
     *
     * NOTE: Not all devices will honour these settings, it is dependent on hardware abilities.
     */
    public void setCustomLight(int argb, int onMs, int offMs) {
        setValue(false, 4);
        ((Builder)getObject()).setLights(argb, onMs, offMs);
    }

    /**
     * Sets whether the notification will vibrate the device.
     *
     * Note: Setting to True will over ride any custom vibration settings.
     *
     * Example:
     * <code>nb.Vibrate = True</code>
     */
    public void setDefaultVibrate(boolean v) {
        setValue(v, 2);
    }

    /**
     * Sets a custom vibrate pattern for new notification
     * The Array of values can be as long as you wish.
     * The first Value is the pause before vibration starts, then its ON, OFF, ON, OFF,........
     *
     * Example:
     * <code>
     * Dim nb as NotificationBuilder
     * Dim v() as Long
     *
     * v = Array as Long(0, 100, 200, 300, 400)
     * nb.CustomVibrate(v)
     * </code>
     */
    public void setCustomVibrate(long[] Values) {
        setValue(false, 2);
        ((Builder)getObject()).setVibrate(Values);
    }

    /**
     * One of the predefined notification categories (CATEGORY_* constants) that best describes this Notification.
     */
    public void setCategory(String Category) {
        ((Builder)getObject()).setCategory(Category);
    }

    /**
     * Supply a replacement Notification whose contents should be shown in insecure contexts (i.e. atop the secure lockscreen)
     *
     * API 21+
     */
    public void setPublicVersion(Notification PublicNotification) {
        ((Builder)getObject()).setPublicVersion(PublicNotification);
    }

    /**
     * Sets the progress the notification represents. This may be shown as a progress bar dependent on SDK version.
     *
     * API 14+
     */
    public void setProgress(int Max, int Progress, boolean Indeterminate) {
        ((Builder)getObject()).setProgress(Max, Progress, Indeterminate);
    }

    /**
     * Gets or Sets the number to be shown on the notification.
     * This is useful to represent multiple events in a single notification.
     */
    public int getNumber() {
        return ((Builder)getObject()).build().number;
    }
    public void setNumber(int v) {
        ((Builder)getObject()).setNumber(v);
        nNumber = v;
    }

    /**
     * Sets whether the notification will be cancelled automatically when the user taps it.
     */
    public void setAutoCancel(boolean v) {
        ((Builder)getObject()).setAutoCancel(v);
    }

    /**
     * Sets whether the notification will only play sound / vibrate /show light if the notification is not already showing.
     */
    public void setOnlyAlertOnce(boolean v){
        ((Builder)getObject()).setOnlyAlertOnce(v);
    }

    /**
     * Experimental - Googles' notes:-
     * 'Set the relative priority for this notification.
     * Priority is an indication of how much of the user's valuable attention should be consumed by this notification.
     * Low-priority notifications may be hidden from the user in certain situations, while the user might be interrupted for a higher-priority notification.
     * The system sets a notification's priority based on various factors including the setPriority value.
     * The effect may differ slightly on different platforms.'
     *
     * API 16+
     *
     * As of API 26, this method is deprecated. Instead, you can set a recommended importance level when creating the relevant notification channel.
     */
    public void setPriority(BA ba, int v) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            ((Builder)getObject()).setPriority(v);
        }
        else {
            BA.Log("WARNING - Priority Method deprecated in API 26+");
        }

    }

    /**
     * Sets whether the notification is an 'ongoing event'.
     * The notification will be shown in the ongoing section and will not be cleared.
     */
    public void setOnGoingEvent(boolean v) {
        ((Builder)getObject()).setOngoing(v);
    }

    /**
     * Sets the Small Icon to be displayed.
     * The image file should manually copied to the Objects\res\drawable\ folder and set to read-only.
     * The file name is case sensitive and should not contain the file extension.
     * You can use "icon" to use the applications icon.
     */
    public void setSmallIcon(String Icon){
        ((Builder)getObject()).setSmallIcon(BA.applicationContext.getResources().getIdentifier(Icon, "drawable", BA.packageName));
    }

    /**
     * Similar to .SmallIcon but takes a 'Level' parameter for when a LevelListDrawable is used.
     */
    public void setSmallIcon2(String Icon, int Level) {
        ((Builder)getObject()).setSmallIcon(BA.applicationContext.getResources().getIdentifier(Icon, "drawable", BA.packageName), Level);
    }

    /**
     * Add a large icon to the notification (and the ticker on some devices).
     * In the platform template, this image will be shown on the left of the notification view in place of the small icon (which will move to the right side).
     */
    public void setLargeIcon(Bitmap Icon) {
        ((Builder)getObject()).setLargeIcon(Icon);
    }

    /**
     * Sets the text in the Title field.
     */
    public void setContentTitle(String Title) {
        ((Builder)getObject()).setContentTitle(Title);
    }

    /**
     * Set the text in the Text field.
     */
    public void setContentText(String Text) {
        ((Builder)getObject()).setContentText(Text);
    }

    /**
     * Set the Channel ID for this notification. If not called, 'Default' will be used.
     */
    public void setChannelID(String ChannelID) { ((Builder)getObject()).setChannelId(ChannelID); }

    /**
     * Set this notification to be part of a group of notifications sharing the same key.
     * Grouped notifications may display in a cluster or stack on devices which support such rendering.
     * (Generally the GroupSummary will display on the handheld and the Group items will display on a Wearable)
     *
     * To make this notification the summary for its group, also call
     *     <code>nb.GroupSummary = True</code>
     * A sort order can be specified by using
     *     <code>nb.SortKey(Key)</code>
     */
    public void setGroup(String GroupKey) {
        ((Builder)getObject()).setGroup(GroupKey);
    }

    /**
     * Set this notification to be the group summary for a group of notifications.
     * Grouped notifications may display in a cluster or stack on devices which support such rendering.
     * (Generally the GroupSummary will display on the handheld and the Group items will display on a Wearable)
     *
     * The GroupSummary notification will not show on the notification stack on Wearables, but will show as the only notification on handheld devices.
     * See URL <link>Click|https://developer.android.com/training/wearables/notifications/stacks.html</link>
     *
     * Requires a group key also be set using
     *     <code>nb.Group(GroupKey)</code>
     */
    public void setGroupSummary(boolean isGroupSummary) {
        ((Builder)getObject()).setGroupSummary(isGroupSummary);
    }

    /**
     * Sets whether or not this notification is only relevant to the current device.
     * Some notifications can be bridged to other devices for remote display. I.E Wear Devices
     */
    public void setLocalOnly(boolean val) {
        ((Builder)getObject()).setLocalOnly(val);
    }

    /**
     * Set a sort key that orders this notification among other notifications from the same package.
     * This can be useful if an external sort was already applied and an app would like to preserve this.
     * Notifications will be sorted lexicographically using this value.
     *
     * This sort key can also be used to order members of a notification group.
     */
    public void setSortKey(String SortKey) {
        ((Builder)getObject()).setSortKey(SortKey);
    }



    /**
     * Set the third line of text in the platform notification template.
     * Don't use if you're also using setProgress(); they occupy the same location in the standard template.
     * If the platform does not provide large-format notifications, this method has no effect. The third line of text only appears in expanded view.
     *
     *  API 16+
     */
    public void setSubText(String Text) {
        ((Builder)getObject()).setSubText(Text);
    }

    /**
     * Sets the text in the right-hand side of the notification.
     */
    public void setContentInfo(String Text) {
        ((Builder)getObject()).setContentInfo(Text);
    }

    /**
     * If set the timestamp place on the notification will be used as a stopwatch. It will automatically update the minutes and seconds since .When()
     *
     *  API 16+
     */
    public void setUsesChrono(boolean v) {
        ((Builder)getObject()).setUsesChronometer(v);
    }

    /**
     * Sets the Ticker Text which shows along side the status bar icon of new Notifications.
     */
    public void setTicker(String Ticker) {
        ((Builder)getObject()).setTicker(Ticker);
    }

    /**
     * Sets the time stamp that shows on the notification. This should indicate the time the event occurred.
     * Default is the current time.
     * Setting to 0 will reset to current time.
     */
    public void setWhen(Long value) {
        nWhen = (value == 0) ? System.currentTimeMillis() : value;
    }

    /**
     * Sets whether the time stamp is shown on the notification.
     * Default is 'true'
     */
    public void setShowTime(boolean value){
        setWhen = value;
    }

    /**
     * Sets the activity that will be launched when the Notification is tapped.
     *
     * Use <code>nb.Intent</code> to use your own calling intent
     */
    public void setActivity(BA ba, Object Activity) {
        nIntent = null;
        nActivity = Activity;
    }

    /**
     * Sets the Intent that will be called when the Notification is tapped.
     *
     * Use <code>nb.Activity</code> to call an activity in your project.
     */
    public void setIntent(BA ba, Intent intent) {
        nActivity = null;
        nIntent = intent;
    }

    /**
     * Sets a Parent Activity to add to the 'Back Stack'. If this method is used then once you have entered
     * an Activity from your notification, pressing back will return to the Parent Activity, rather than exiting the App.
     *
     * API 11+
     */

    public void setParentActivity(BA ba, Object Activity) {
        pActivity = Activity;
    }

    /**
     * Experimental - Setting a DeleteActivity will (should) show the activity when the user clears the notification manually.
     *
     * API 11+
     */
    public void setDeleteActivity(BA ba, Object Activity) throws ClassNotFoundException {
        Intent deleteIntent = Common.getComponentIntent(ba, Activity);
        PendingIntent dpi = PendingIntent.getActivity(ba.context, 0, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ((Builder)getObject()).setDeleteIntent(dpi);
    }

    /**
     * Experimental - Setting a DeleteService will (should) show the service when the user clears the notification manually.
     *
     * A{PI 11+
     */
    public void setDeleteService(BA ba, Object Service) throws ClassNotFoundException {
        Intent deleteIntent = Common.getComponentIntent(ba, Service);
        PendingIntent dpi = PendingIntent.getService(ba.context, 0, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ((Builder)getObject()).setDeleteIntent(dpi);
    }

    /**
     * Adds an Action to the notification.
     *
     * Actions are buttons displayed on the notification that can allow users to do something without starting the app. e.g Call back a missed call, pause a sound track, etc
     *
     * The icon file should manually copied to the Objects\res\drawable\ folder and set to read-only.
     * The file name is case sensitive and should not contain the file extension.
     * If tag is set to "" then it will default to the passed title.
     *
     * tags are named 'Notification_Action_Tag'
     *
     * Use AddAction2 to pass a Service module instead of an Activity.
     *
     * MAXIMUM of 3 Actions can be added to each notification.
     *
     * API 16+
     */
    public void AddAction(BA ba, String icon, String title, String tag, Object Activity) throws ClassNotFoundException {
        int ActionIcon = BA.applicationContext.getResources().getIdentifier(icon, "drawable", BA.packageName);
        Intent actionIntent = Common.getComponentIntent(ba, Activity);
        if (tag.length() > 0) {
            actionIntent.putExtra("Notification_Action_Tag", tag);
            BA.Log("Notification_Action_Tag set to " + tag);
        }
        else {
            actionIntent.putExtra("Notification_Action_Tag", title);
            BA.Log("Notification_Action_Tag set to " + title);
        }
        PendingIntent ActionPendingIntent = PendingIntent.getActivity(ba.context, actionId++, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ((Builder)getObject()).addAction(ActionIcon, title, ActionPendingIntent);
    }

    /**
     * Similar to AddAction, but you pass a Service Module rather than an Activity Module.
     */
    public void AddAction2(BA ba, String icon, String title, String tag, Object Service) throws ClassNotFoundException {
        int ActionIcon = BA.applicationContext.getResources().getIdentifier(icon, "drawable", BA.packageName);
        Intent actionIntent = Common.getComponentIntent(ba, Service);
        if (tag.length() > 0) {
            actionIntent.putExtra("Notification_Action_Tag", tag);
            BA.Log("Notification_Action_Tag set to " + tag);
        }
        else {
            actionIntent.putExtra("Notification_Action_Tag", title);
            BA.Log("Notification_Action_Tag set to - " + title);
        }
        PendingIntent ActionPendingIntent = PendingIntent.getService(ba.context, actionId++, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        ((Builder)getObject()).addAction(ActionIcon, title, ActionPendingIntent);
    }

    /**
     * Adds a remote input action to the notification
     *
     * API 24+
     */
    public void AddRemoteInputAction(BA ba, String icon, String title, String tag, Object Service, RemoteInput remoteInput) throws ClassNotFoundException {
        // Get icon resource from String
        int ActionIcon = BA.applicationContext.getResources().getIdentifier(icon, "drawable", BA.packageName);

        // Create Intent for remote action
        Intent remoteIntent = Common.getComponentIntent(ba, Service);
        if (tag.length() > 0) {
            remoteIntent.putExtra("Notification_Remote_Input_Tag", tag);
            BA.Log("Notification_Remote_Input_Tag set to - " + tag);
        } else {
            remoteIntent.putExtra("Notification_Remote_Input_Tag", title);
            BA.Log("Notification_Remote_Input_Tag set to - " + title);
        }

        // Create pending intent for reply action
        PendingIntent replyPendingIntent = PendingIntent.getService(BA.applicationContext, actionId++, remoteIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Attach RemoteInput to a new Action
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(ActionIcon, title, replyPendingIntent)
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();

        // Add the action to the Notification
        ((Builder)getObject()).addAction(action);
    }

    /**
     * Sets the icn type shown on badges in API 26+
     */
    public void setBadgeIconType(int IconType) {
        ((Builder)getObject()).setBadgeIconType(IconType);
    }

    /**
     * The Tag is a string that can be extracted later on Activity_Resume.
     * This can be used to determine which notification has been clicked by the user when multiple notifications exist.
     *
     * Example of extracting the Tag:
     * <code>
     * Sub Activity_Resume
     *   Dim in as Intent
     *   Dim intentExtra as String
     *
     *   in = Activity.GetStartingIntent
     *   If in.HasExtra("Notification_Tag") Then
     *     intentExtra = in.GetExtra("Notification_Tag")
     *   End If
     * End Sub
     * </code>
     */
    public void setTag(String Tag)
    {
        nTag = Tag;
    }

    /**
     * Add an extended style to the notification.
     *
     * Must be passed a style object:-
     *     NotificationInboxStyle
     *     NotificationBigTextStyle
     *     NotificationBigPictureStyle
     *     NotificationMediaStyle
     *
     * API 16+
     */
    public void SetStyle(NotificationCompat.Style style) {
        ((Builder)getObject()).setStyle(style);
    }

    /**
     * Sets a custom layout for the notification.
     *
     * Pass a NotificationCustomLayout object.
     *
     * If the height is explicitly set, it should be 64dp
     *
     * API 11+
     *
     * If you are targetting API 24+ you can use
     * <code>nb.CustomContentView</code>
     *For some enhancments
     */
    public void setCustomLayout(RemoteViews NotificationCustomLayout) {

        //ToDo - Which one??

        ((Builder)getObject()).setContent(NotificationCustomLayout);
        ((Builder)getObject()).setCustomContentView(NotificationCustomLayout);
    }

    /**
     *
     */
    public void setCustomContentView(RemoteViews NotificationCustomLayout, boolean Decorated) {
        ((Builder)getObject()).setCustomContentView(NotificationCustomLayout);
        if(Decorated) {
            ((Builder)getObject()).setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        }
    }

    /**
     * Sets a custom layout for an expanded notification
     *
     * Pass a NotificationCustomLayout object
     *
     * If the height is explicitly set, it should be 256dp
     *
     * API 16+
     */
    public void setBigCustomLayout(RemoteViews NotificationBigCustomLayout) {
        BigContent = NotificationBigCustomLayout;
    }

    /**
     * Set the visibility of the notification.
     *
     * Options:
     * 		VISIBILITY_PRIVATE - The standard Android notification behaviour. Icon / Ticker text are always shown. The content is only shown if the device is unlocked by the appropriate user/
     * 		VISIBILITY_PUBLIC - Shown at any time.
     * 		VISIBILITY_SECRET - Even supresses the Icon / Ticker until  the device is unlocked
     */
    public void setVisibility(int Visibility) {
        ((Builder)getObject()).setVisibility(Visibility);
    }


    /**
     * Applies an Extender to the Notification.
     *
     * i.e NotificationWearableExtender is used to extend the notification with functions only available to Wearable devices.
     */
    public void Extend(Extender NotificationWearableExtender) {
        ((Builder)getObject()).extend(NotificationWearableExtender);
    }

    /**
     * Gets the Notification Object. Useful for things like Foreground Services
     */
    public Notification GetNotification(BA ba) throws ClassNotFoundException {
        return PrepareNotification(ba);
    }

    /**
     * Displays the notification.
     * id - The notification id. This id can be used later to update the notification (by calling Notify again with the same id),
     * or to cancel the notification
     */
    public void Notify(BA ba, int id) throws ClassNotFoundException {

        //NotificationManager mNotificationManager = (NotificationManager) ba.context.getSystemService(Context.NOTIFICATION_SERVICE);
        //mNotificationManager.notify(id, PrepareNotification(ba));

        NotificationManagerCompat nm = NotificationManagerCompat.from(ba.context);
        nm.notify(id, PrepareNotification(ba));
    }

    /**
     * Cancels the notification with the given id
     */
    public void Cancel(BA ba, int id) {

        //NotificationManager mNotificationManager = (NotificationManager) ba.context.getSystemService(Context.NOTIFICATION_SERVICE);
        //mNotificationManager.cancel(id);
        NotificationManagerCompat nm = NotificationManagerCompat.from(ba.context);
        nm.cancel(id);
    }

    /**
     * Returns current API Version.
     * Useful for adding features that require a certain version of Android
     */
    public int getAPIVersion() {
        return Build.VERSION.SDK_INT;
    }

    //Helper subs

    private Notification PrepareNotification(BA ba) throws ClassNotFoundException {
        if (setWhen) {
            ((Builder)getObject()).setWhen(nWhen);
        }
        else {
            ((Builder)getObject()).setWhen(0);
        }

        ((Builder)getObject()).setDefaults(nDefaults);
        Intent resultIntent;
        if (nIntent != null) {
            resultIntent = nIntent;
        }
        else {
            resultIntent = Common.getComponentIntent(ba, nActivity);
        }
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ba.context);

        if (nTag != null) {
            resultIntent.putExtra("Notification_Tag", nTag);
        }

        if (pActivity != null) {
            Intent parentIntent = Common.getComponentIntent(ba, pActivity);
            if (nTag != null) {
                parentIntent.putExtra("Notification_Tag", nTag);
                //resultIntent.removeExtra("Notification_Tag");
            }
            stackBuilder.addNextIntent(parentIntent);
        }

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent((nTag == null) ? 0 : pendingId++, PendingIntent.FLAG_UPDATE_CURRENT);
        ((Builder)getObject()).setContentIntent(resultPendingIntent);

        Notification n = ((Builder)getObject()).build();
        n.number = nNumber;
        if (BigContent != null) {
            n.bigContentView = BigContent;
        }
        return n;
    }

    private void setValue(boolean v, int Default) {
        if (v)
            nDefaults |= Default;
        else
            nDefaults &= (Default ^ 0xFFFFFFFF);
    }
}