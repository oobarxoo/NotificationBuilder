package uk.co.barxdroid.notificationbuilder;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ShortName;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/**
 * Created by Darren Barker on 27/12/2017.
 */

@ShortName("NotificationChannelBuilder")

public class NotificationChannelBuilder extends AbsObjectWrapper<NotificationChannel> {

    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";

    public static final int IMPORTANCE_HIGH = 3;
    public static final int IMPORTANCE_URGENT = 4;
    public static final int IMPORTANCE_DEFAULT = 2;
    //public static final int IMPORTANCE_MAX = 5; unused
    public static final int IMPORTANCE_LOW = 1;
    public static final int IMPORTANCE_NONE = 0;

    /**
     * Initialize the object
     *
     * NOTE: Channels are implemented API version 26+
     *
     * Parameters:
     * id -
     * name -
     * importance -
     */

    public void Initialize(BA ba, String id, CharSequence name, int importance) {
        setObject(new NotificationChannel(id, name, importance));
    }

    /**
     * Returns if Channels are supported.
     * i.e. API 26+ in use
     */
    public boolean ChannelsSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    /**
     * Gets or Sets whether or not notifications poosted to this channel can bypass the Do Not Disturb mode.
     *
     * Only modifiable before the channel is submitted.
     */
    public boolean getCanBypassDND() {
        return ((NotificationChannel)getObject()).canBypassDnd();
    }

    public void setCanBypassDND(boolean v) {
        ((NotificationChannel)getObject()).setBypassDnd(v);
    }

    /**
     * Returns whether notifications posted to this channel can appear in a Launcher application.
     * Note that badges may be disabled for other reasons.
     */
    public boolean getCanShowBadge() {
        return ((NotificationChannel)getObject()).canShowBadge();
    }

    /**
     * Gets or Sets whether notifications posted to this channel should display notification lights,
     * on devices that support that feature.
     * Only modifiable before the channel is submitted.
     */
    public boolean getEnableLights() {
        return ((NotificationChannel)getObject()).shouldShowLights();
    }

    public void setEnableLights(boolean v) {
        ((NotificationChannel)getObject()).enableLights(v);
    }

    /**
     * Gets or Sets whether notifications posted to this channel should vibrate.
     * The vibration pattern can be set with VibrationPattern().
     * Only modifiable before the channel is submitted.
     */
    public boolean getEnableVibration() {
        return ((NotificationChannel)getObject()).shouldVibrate();
    }

    public void setEnableVibration(boolean v) {
        ((NotificationChannel)getObject()).enableVibration(v);
    }

    /**
     * Gets or Sets the useer visible description of this channel
     *
     * Only modifiable before the channel is submitted
     */
    public String getDescription() {
        return ((NotificationChannel)getObject()).getDescription();
    }

    public void setDescription(String Description) {
        ((NotificationChannel)getObject()).setDescription(Description);
    }

    /**
     * Gets or Sets the group this channel belongs to.
     * This is used only for visually grouping the channels in the UI
     *
     * Only modifiable before channel is submitted
     */
    public String getGroup() {
        return ((NotificationChannel)getObject()).getGroup();
    }

    public void setObject(String Group) {
        ((NotificationChannel)getObject()).setGroup(Group);
    }

    /**
     * Gets the ID of this channel
     */
    public String getID() {
        return ((NotificationChannel)getObject()).getId();
    }

    /**
     * Gets or Sets the user specified importance level for the notifications posted to this channel.
     * Note: Use the built in Constant values
     * e.g NBChannel.IMPORTANCE_LOW
     *
     * Only modifiable before channel submitted
     */
    public int getImportance() {
        return ((NotificationChannel)(getObject())).getImportance();
    }

    public void setImportance(int Importance) {
        ((NotificationChannel)getObject()).setImportance(Importance);
    }

    /**
     * Gets or Sets the notification light color for notifications posted to this channel.
     *
     * Color should be an argb value
     *
     * Only modifiable before the channel is submitted.
     */
    public int getLightColor() {
        return ((NotificationChannel)getObject()).getLightColor();
    }

    public void setLightColor(int Color) {
        ((NotificationChannel)getObject()).setLightColor(Color);
    }

    /**
     * Gets or Sets whether the notifications posted to this channel are shown on the lockscreen in full or redacted form.
     *
     * Only modifiable before the channel is submitted.
     */
    public int getLockscreenVisibility() {
        return ((NotificationChannel)getObject()).getLockscreenVisibility();
    }

    public void setLockscreenVisibility(int value) {
        ((NotificationChannel)getObject()).setLockscreenVisibility(value);
    }

    /**
     * Gets or Sets the notificaiton sound for this channel
     *
     * Only modifiable before the channel is submitted
     */
    //public Uri getSound() {
    //return ((NotificationChannel)getObject()).getSound();
    //}

    //public void setSound(Uri Sound) {
    //((NotificationChannel)getObject()).setSound(Sound);
    //}

    /**
     * Gets or Sets the vibration pattern for notifications posted to this channel.
     * Will be ignored if  vibration is not enabled.
     *
     * Only modifiable before the channel is submitted.
     */
    public long[] getVibrationPattern() {
        return ((NotificationChannel)getObject()).getVibrationPattern();
    }

    public void setVibrationPattern(long[] Pattern) {
        ((NotificationChannel)getObject()).setVibrationPattern(Pattern);
    }

    /**
     *  Submits the Notification Channel
     */
    public void Build(BA ba) {
        NotificationManager mNotificationManager = (NotificationManager) ba.context.getSystemService(ba.context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(((NotificationChannel)getObject()));
    }

    /**
     * Deletes a Channel
     *
     * ChannelID - ID of channel to be deleted.
     */
    public void DeleteChannel(BA ba, String ChannelID) {
        NotificationManager mNotificationManager = (NotificationManager) ba.context.getSystemService(ba.context.NOTIFICATION_SERVICE);
        mNotificationManager.deleteNotificationChannel(ChannelID);
    }

    /**
     * Deletes a Channel Group
     *
     * GroupID - ID of group to be deleted.
     */
    public void DeleteChannelGroup(BA ba, String GroupID) {
        NotificationManager mNotificationManager = (NotificationManager) ba.context.getSystemService(ba.context.NOTIFICATION_SERVICE);
        mNotificationManager.deleteNotificationChannelGroup(GroupID);
    }
}