package network.palace.ridemanager.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import network.palace.core.Core;
import network.palace.core.player.CPlayer;
import network.palace.ridemanager.RideManager;
import network.palace.ridemanager.events.PlayerLeaveRideEvent;
import network.palace.ridemanager.handlers.ride.Ride;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marc on 1/29/17.
 */
public class PacketListener implements Listener {

    public PacketListener() {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        //Shift out of ride vehicle
        manager.addPacketListener(new PacketAdapter(RideManager.getInstance(), ListenerPriority.LOWEST, PacketType.Play.Client.STEER_VEHICLE) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                CPlayer player = Core.getPlayerManager().getPlayer(event.getPlayer());
                if (player == null) return;
                PacketContainer packet = event.getPacket();
                try {
                    Field f = packet.getBooleans().getFields().get(1);
                    f.setAccessible(true);
                    if (!f.getBoolean(packet.getHandle())) {
                        return;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return;
                }
                Ride ride = RideManager.getInstance().getCurrentRide(player);
                if (ride == null) return;
                PlayerLeaveRideEvent leaveRide = new PlayerLeaveRideEvent(player, ride);
                leaveRide.call();
                event.setCancelled(leaveRide.isCancelled());
            }
        });
        //Click to board ride vehicle
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(RideManager.getInstance(), PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                CPlayer player = Core.getPlayerManager().getPlayer(event.getPlayer());
                if (player == null) return;
                int id = event.getPacket().getIntegers().read(0);
                World world = player.getWorld();
                List<Entity> entities = new ArrayList<>(world.getEntities());
                Optional<Entity> opt = entities.stream().filter(en -> en.getEntityId() == id).findFirst();
                if (!opt.isPresent()) return;
                Entity e = opt.get();
                if (!e.getType().equals(EntityType.ARMOR_STAND)) return;
                ArmorStand stand = (ArmorStand) e;
                if (RideManager.getMovementUtil().sitDown(player, stand)) {
                    event.setCancelled(true);
                }
            }
        });
        /*
        manager.addPacketListener(new PacketAdapter(RideManager.getInstance(), PacketType.Play.Client.POSITION, PacketType.Play.Client.POSITION_LOOK) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                CPlayer player = Core.getPlayerManager().getPlayer(event.getPlayer());
                if (player == null) return;
                Ride ride = RideManager.getInstance().getCurrentRide(player);
                Bukkit.broadcastMessage(ride == null ? "yay" : "nay");
                if (ride == null) return;
                if (ride instanceof ArmorStandRide) {
                    event.setCancelled(true);
                    player.teleport(player.getLocation());
                }
            }
        });*/
    }
}
