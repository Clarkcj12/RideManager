package network.palace.ridemanager.utils;

import org.bukkit.Bukkit;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionUtil {

    private static Method getOnlinePlayersMethod;
    private static boolean getOnlinePlayersUseReflection;

    /**
     * This method uses a regex to get the NMS package part that changes with every update.
     * Example: v1_8_R1
     *
     * @return the NMS package part or null if not found.
     */
    public static String getBukkitVersion() {
        Matcher matcher = Pattern.compile("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer().getClass().getPackage().getName());
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    /**
     * This method uses a regex to get the version of this Minecraft release.
     * Example: 1.8.1
     *
     * @return the version of this release or null if not found.
     */
    public static String getMinecraftVersion() {
        Matcher matcher = Pattern.compile("(\\(MC: )([\\d.]+)(\\))").matcher(Bukkit.getVersion());
        if (matcher.find()) {
            return matcher.group(2);
        } else {
            return null;
        }
    }

    /**
     * @return 1 if reference > comparison, 0 if reference == comparison, -1 if reference < comparison
     */
    private static int compare(String reference, String comparison) throws NumberFormatException {
        String[] referenceSplit = reference.split("\\.");
        String[] comparisonSplit = comparison.split("\\.");

        int longest = Math.max(referenceSplit.length, comparisonSplit.length);

        // Default value is 0
        int[] referenceNumbersArray = new int[longest];
        int[] comparisonNumbersArray = new int[longest];

        for (int i = 0; i < referenceSplit.length; i++) {
            referenceNumbersArray[i] = Integer.parseInt(referenceSplit[i]);
        }

        for (int i = 0; i < comparisonSplit.length; i++) {
            comparisonNumbersArray[i] = Integer.parseInt(comparisonSplit[i]);
        }

        for (int i = 0; i < longest; i++) {
            int diff = referenceNumbersArray[i] - comparisonNumbersArray[i];
            if (diff > 0) {
                return 1;
            } else if (diff < 0) {
                return -1;
            }
        }

        return 0;
    }


    public static boolean isVersionGreaterEqual(String reference, String thanWhat) {
        return compare(reference, thanWhat) >= 0;
    }

    public static boolean isVersionLessEqual(String reference, String thanWhat) {
        return compare(reference, thanWhat) <= 0;
    }

    public static boolean isVersionBetweenEqual(String reference, String lowest, String highest) {
        return isVersionGreaterEqual(reference, lowest) && isVersionLessEqual(reference, highest);
    }


    public static boolean classExists(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

}
