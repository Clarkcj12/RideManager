package network.palace.ridemanager.handlers;

import lombok.Getter;
import lombok.Setter;
import network.palace.core.Core;
import network.palace.core.player.CPlayer;
import network.palace.ridemanager.RideManager;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.*;

/**
 * Created by Marc on 1/26/17.
 */
public class CarouselRide extends Ride {
    private final int horseRadius1 = 7;
    private final int horseRadius2 = 5;
    private final double riderRadius = 0.395;
    private final double poleAngle = 95;
    private final double poleY;
    private FlatState state = FlatState.LOADING;
    @Getter private Location center;
    @Getter private boolean spawned = false;
    @Getter private List<Horse> horses = new ArrayList<>();
    @Getter @Setter private double speed = 0; //Full speed is 0.2
    @Getter @Setter private double heightSpeed = 0;
    @Getter private boolean started = false;

    public CarouselRide(String name, String displayName, double delay, Location exit, Location center) {
        super(name, displayName, 12, delay, exit);
        this.center = center;
        this.poleY = center.getY() + 2.3;
        loadSurroundingChunks(this.center);
        spawn();
    }

    public void spawn() {
        if (isSpawned()) {
            return;
        }
        World w = this.center.getWorld();
        Location loc1 = getRelativeLocation(0.0, 7.0, this.center);
        Location loc2 = getRelativeLocation(30.0, 7.0, this.center);
        Location loc3 = getRelativeLocation(60.0, 7.0, this.center);
        Location loc4 = getRelativeLocation(90.0, 7.0, this.center);
        Location loc5 = getRelativeLocation(120.0, 7.0, this.center);
        Location loc6 = getRelativeLocation(150.0, 7.0, this.center);
        Location loc7 = getRelativeLocation(180.0, 7.0, this.center);
        Location loc8 = getRelativeLocation(210.0, 7.0, this.center);
        Location loc9 = getRelativeLocation(240.0, 7.0, this.center);
        Location loc10 = getRelativeLocation(270.0, 7.0, this.center);
        Location loc11 = getRelativeLocation(300.0, 7.0, this.center);
        Location loc12 = getRelativeLocation(330.0, 7.0, this.center);

        Location loc13 = getRelativeLocation(15.0, 5.0, this.center);
        Location loc14 = getRelativeLocation(45.0, 5.0, this.center);
        Location loc15 = getRelativeLocation(75.0, 5.0, this.center);
        Location loc16 = getRelativeLocation(105.0, 5.0, this.center);
        Location loc17 = getRelativeLocation(135.0, 5.0, this.center);
        Location loc18 = getRelativeLocation(165.0, 5.0, this.center);
        Location loc19 = getRelativeLocation(195.0, 5.0, this.center);
        Location loc20 = getRelativeLocation(225.0, 5.0, this.center);
        Location loc21 = getRelativeLocation(255.0, 5.0, this.center);
        Location loc22 = getRelativeLocation(285.0, 5.0, this.center);
        Location loc23 = getRelativeLocation(315.0, 5.0, this.center);
        Location loc24 = getRelativeLocation(345.0, 5.0, this.center);

        double d2 = Math.toRadians(30.0);
        double d3 = Math.toRadians(60.0);
        double d4 = Math.toRadians(90.0);
        double d5 = Math.toRadians(120.0);
        double d6 = Math.toRadians(150.0);
        double d7 = Math.toRadians(180.0);
        double d8 = Math.toRadians(210.0);
        double d9 = Math.toRadians(240.0);
        double d10 = Math.toRadians(270.0);
        double d11 = Math.toRadians(300.0);
        double d12 = Math.toRadians(330.0);

        double d13 = Math.toRadians(15.0);
        double d14 = Math.toRadians(45.0);
        double d15 = Math.toRadians(75.0);
        double d16 = Math.toRadians(105.0);
        double d17 = Math.toRadians(135.0);
        double d18 = Math.toRadians(165.0);
        double d19 = Math.toRadians(195.0);
        double d20 = Math.toRadians(225.0);
        double d21 = Math.toRadians(255.0);
        double d22 = Math.toRadians(285.0);
        double d23 = Math.toRadians(315.0);
        double d24 = Math.toRadians(345.0);

        loc1.setYaw(270);
        loc2.setYaw(240);
        loc3.setYaw(210);
        loc4.setYaw(180);
        loc5.setYaw(150);
        loc6.setYaw(120);
        loc7.setYaw(90);
        loc8.setYaw(60);
        loc9.setYaw(30);
        loc10.setYaw(0);
        loc11.setYaw(-30);
        loc12.setYaw(-60);

        loc13.setYaw(255);
        loc14.setYaw(225);
        loc15.setYaw(195);
        loc16.setYaw(165);
        loc17.setYaw(135);
        loc18.setYaw(105);
        loc19.setYaw(75);
        loc20.setYaw(45);
        loc21.setYaw(15);
        loc22.setYaw(-15);
        loc23.setYaw(-45);
        loc24.setYaw(-75);

        ItemStack i1 = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 1);
        ItemStack i2 = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 1);
        ItemStack i3 = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 1);
        ItemStack i4 = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 1);
        ItemStack i5 = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 1);
        ItemStack i6 = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 1);

        ArmorStand a1 = lock(w.spawn(loc1, ArmorStand.class));
        ArmorStand a2 = lock(w.spawn(loc2, ArmorStand.class));
        ArmorStand a3 = lock(w.spawn(loc3, ArmorStand.class));
        ArmorStand a4 = lock(w.spawn(loc4, ArmorStand.class));
        ArmorStand a5 = lock(w.spawn(loc5, ArmorStand.class));
        ArmorStand a6 = lock(w.spawn(loc6, ArmorStand.class));
        ArmorStand a7 = lock(w.spawn(loc7, ArmorStand.class));
        ArmorStand a8 = lock(w.spawn(loc8, ArmorStand.class));
        ArmorStand a9 = lock(w.spawn(loc9, ArmorStand.class));
        ArmorStand a10 = lock(w.spawn(loc10, ArmorStand.class));
        ArmorStand a11 = lock(w.spawn(loc11, ArmorStand.class));
        ArmorStand a12 = lock(w.spawn(loc12, ArmorStand.class));

        ArmorStand a13 = lock(w.spawn(loc13, ArmorStand.class));
        ArmorStand a14 = lock(w.spawn(loc14, ArmorStand.class));
        ArmorStand a15 = lock(w.spawn(loc15, ArmorStand.class));
        ArmorStand a16 = lock(w.spawn(loc16, ArmorStand.class));
        ArmorStand a17 = lock(w.spawn(loc17, ArmorStand.class));
        ArmorStand a18 = lock(w.spawn(loc18, ArmorStand.class));
        ArmorStand a19 = lock(w.spawn(loc19, ArmorStand.class));
        ArmorStand a20 = lock(w.spawn(loc20, ArmorStand.class));
        ArmorStand a21 = lock(w.spawn(loc21, ArmorStand.class));
        ArmorStand a22 = lock(w.spawn(loc22, ArmorStand.class));
        ArmorStand a23 = lock(w.spawn(loc23, ArmorStand.class));
        ArmorStand a24 = lock(w.spawn(loc24, ArmorStand.class));

        a1.setGravity(false);
        a2.setGravity(false);
        a3.setGravity(false);
        a4.setGravity(false);
        a5.setGravity(false);
        a6.setGravity(false);
        a7.setGravity(false);
        a8.setGravity(false);
        a9.setGravity(false);
        a10.setGravity(false);
        a11.setGravity(false);
        a12.setGravity(false);

        a13.setGravity(false);
        a14.setGravity(false);
        a15.setGravity(false);
        a16.setGravity(false);
        a17.setGravity(false);
        a18.setGravity(false);
        a19.setGravity(false);
        a20.setGravity(false);
        a21.setGravity(false);
        a22.setGravity(false);
        a23.setGravity(false);
        a24.setGravity(false);

        a1.setVisible(false);
        a2.setVisible(false);
        a3.setVisible(false);
        a4.setVisible(false);
        a5.setVisible(false);
        a6.setVisible(false);
        a7.setVisible(false);
        a8.setVisible(false);
        a9.setVisible(false);
        a10.setVisible(false);
        a11.setVisible(false);
        a12.setVisible(false);

        a13.setVisible(false);
        a14.setVisible(false);
        a15.setVisible(false);
        a16.setVisible(false);
        a17.setVisible(false);
        a18.setVisible(false);
        a19.setVisible(false);
        a20.setVisible(false);
        a21.setVisible(false);
        a22.setVisible(false);
        a23.setVisible(false);
        a24.setVisible(false);

        a1.setHelmet(i1);
        a2.setHelmet(i2);
        a3.setHelmet(i3);
        a4.setHelmet(i4);
        a5.setHelmet(i5);
        a6.setHelmet(i6);
        a7.setHelmet(i2);
        a8.setHelmet(i2);
        a9.setHelmet(i3);
        a10.setHelmet(i4);
        a11.setHelmet(i5);
        a12.setHelmet(i6);

        a13.setHelmet(i1);
        a14.setHelmet(i2);
        a15.setHelmet(i3);
        a16.setHelmet(i4);
        a17.setHelmet(i5);
        a18.setHelmet(i6);
        a19.setHelmet(i2);
        a20.setHelmet(i2);
        a21.setHelmet(i3);
        a22.setHelmet(i4);
        a23.setHelmet(i5);
        a24.setHelmet(i6);

        EulerAngle pose = new EulerAngle(0, Math.toRadians(90), 0);

        a1.setHeadPose(pose);
        a2.setHeadPose(pose);
        a3.setHeadPose(pose);
        a4.setHeadPose(pose);
        a5.setHeadPose(pose);
        a6.setHeadPose(pose);
        a7.setHeadPose(pose);
        a8.setHeadPose(pose);
        a9.setHeadPose(pose);
        a10.setHeadPose(pose);
        a11.setHeadPose(pose);
        a12.setHeadPose(pose);

        a13.setHeadPose(pose);
        a14.setHeadPose(pose);
        a15.setHeadPose(pose);
        a16.setHeadPose(pose);
        a17.setHeadPose(pose);
        a18.setHeadPose(pose);
        a19.setHeadPose(pose);
        a20.setHeadPose(pose);
        a21.setHeadPose(pose);
        a22.setHeadPose(pose);
        a23.setHeadPose(pose);
        a24.setHeadPose(pose);

//        a3.setHeadPose(a3.getHeadPose().add(0.0, Math.toRadians(90), 0.0));
//        a4.setHeadPose(a4.getHeadPose().add(0.0, Math.toRadians(90), 0.0));
//        a5.setHeadPose(a5.getHeadPose().add(0.0, d9, 0.0));
//        a6.setHeadPose(a6.getHeadPose().add(0.0, d8, 0.0));
//        a7.setHeadPose(a7.getHeadPose().add(0.0, d7, 0.0));
//        a8.setHeadPose(a8.getHeadPose().add(0.0, d6, 0.0));
//        a9.setHeadPose(a9.getHeadPose().add(0.0, d5, 0.0));
//        a10.setHeadPose(a10.getHeadPose().add(0.0, d4, 0.0));
//        a11.setHeadPose(a11.getHeadPose().add(0.0, d3, 0.0));
//        a12.setHeadPose(a12.getHeadPose().add(0.0, d2, 0.0));
//
//        a13.setHeadPose(a13.getHeadPose().add(0.0, d24, 0.0));
//        a14.setHeadPose(a14.getHeadPose().add(0.0, d23, 0.0));
//        a15.setHeadPose(a15.getHeadPose().add(0.0, d22, 0.0));
//        a16.setHeadPose(a16.getHeadPose().add(0.0, d21, 0.0));
//        a17.setHeadPose(a17.getHeadPose().add(0.0, d20, 0.0));
//        a18.setHeadPose(a18.getHeadPose().add(0.0, d19, 0.0));
//        a19.setHeadPose(a19.getHeadPose().add(0.0, d18, 0.0));
//        a20.setHeadPose(a20.getHeadPose().add(0.0, d17, 0.0));
//        a21.setHeadPose(a21.getHeadPose().add(0.0, d16, 0.0));
//        a22.setHeadPose(a22.getHeadPose().add(0.0, d15, 0.0));
//        a23.setHeadPose(a23.getHeadPose().add(0.0, d14, 0.0));
//        a24.setHeadPose(a24.getHeadPose().add(0.0, d13, 0.0));

        Horse h1 = new Horse(a1, 0.0, false);
        Horse h2 = new Horse(a2, 30.0, false);
        Horse h3 = new Horse(a3, 60.0, false);
        Horse h4 = new Horse(a4, 90.0, false);
        Horse h5 = new Horse(a5, 120.0, false);
        Horse h6 = new Horse(a6, 150.0, false);
        Horse h7 = new Horse(a7, 180.0, false);
        Horse h8 = new Horse(a8, 210.0, false);
        Horse h9 = new Horse(a9, 240.0, false);
        Horse h10 = new Horse(a10, 270.0, false);
        Horse h11 = new Horse(a11, 300.0, false);
        Horse h12 = new Horse(a12, 330.0, false);

        Horse h13 = new Horse(a13, 15.0, true);
        Horse h14 = new Horse(a14, 45.0, true);
        Horse h15 = new Horse(a15, 75.0, true);
        Horse h16 = new Horse(a16, 105.0, true);
        Horse h17 = new Horse(a17, 135.0, true);
        Horse h18 = new Horse(a18, 165.0, true);
        Horse h19 = new Horse(a19, 195.0, true);
        Horse h20 = new Horse(a20, 225.0, true);
        Horse h21 = new Horse(a21, 255.0, true);
        Horse h22 = new Horse(a22, 285.0, true);
        Horse h23 = new Horse(a23, 315.0, true);
        Horse h24 = new Horse(a24, 345.0, true);

        this.horses = new LinkedList<>(Arrays.asList(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21, h22, h23, h24));
        this.spawned = true;
    }

    @Override
    public void start() {
        List<UUID> queue = getQueue();
        List<UUID> riding = new ArrayList<>();
        if (queue.size() < getRiders()) {
            riding.addAll(queue);
            queue.clear();
        } else {
            for (int i = 0; i < getRiders(); i++) {
                riding.add(queue.get(0));
                queue.remove(0);
            }
        }
        List<Player> riders = new ArrayList<>();
        for (UUID uuid : riding) {
            Player tp = Bukkit.getPlayer(uuid);
            if (tp == null) {
                continue;
            }
            if (!getOnRide().contains(tp.getUniqueId())) riders.add(tp);
        }
        int hc = 1;
        Horse h = getHorse(hc);
        for (Player tp : riders) {
            h.addPassenger(tp);
            tp.sendMessage(ChatColor.GREEN + "Ride starting in 3 seconds!");
            getOnRide().add(tp.getUniqueId());
            h = getHorse(hc++);
        }
        state = FlatState.RUNNING;
        int taskID = Bukkit.getScheduler().runTaskTimer(RideManager.getInstance(), new Runnable() {
            int time = 0;

            @Override
            public void run() {
                switch (time) {
                    case 0:
                        speed = 1.5;
                        heightSpeed = 0.2;
//                        heightSpeed = 0.025;
                        break;
                    case 1:
                        speed = 1;
//                        heightSpeed = 0.035;
                        break;
                    case 2:
                        speed = 0.9;
//                        heightSpeed = 0.045;
                        break;
                    case 3:
                        speed = 0.8;
//                        heightSpeed = 0.055;
                        break;
                    case 4:
                        speed = 0.7;
//                        heightSpeed = 0.065;
                        break;
                    case 5:
                        speed = 0.6;
//                        heightSpeed = 0.085;
                        break;
                    case 6:
                        speed = 0.5;
//                        heightSpeed = 0.11;
                        break;
                    case 7:
                        speed = 0.4;
//                        heightSpeed = 0.13;
                        break;
                    case 8:
                        speed = 0.3;
//                        heightSpeed = 0.16;
                        break;
                    case 9:
                        speed = 0.2;
//                        heightSpeed = 0.2;
                        break;
                    case 53:
                        speed = 0.3;
                        break;
                    case 54:
                        speed = 0.4;
                        break;
                    case 55:
                        speed = 0.5;
                        break;
                    case 56:
                        speed = 0.6;
                        break;
                    case 57:
                        speed = 0.7;
                        break;
                    case 58:
                        speed = 0.8;
                        break;
                    case 59:
                        speed = 0.9;
                        break;
                    case 60:
                        speed = 1;
                        break;
                    case 61:
                        speed = 1.5;
                        break;
                    case 63:
                        speed = 2;
                        break;
                    case 64:
                        speed = 0;
                        heightSpeed = 0;
                        break;
                    case 67:
                        ejectPlayers();
                        state = FlatState.LOADING;
                        break;
                }
                time++;
            }
        }, 60L, 20L).getTaskId();
        Bukkit.getScheduler().runTaskLater(RideManager.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().cancelTask(taskID);
            }
        }, 1400L);
    }

    @Override
    public boolean handleEject(CPlayer player) {
        for (Horse c : getHorses()) {
            CPlayer p = c.getPassenger();
            if (p != null && p.getUniqueId().equals(player.getUniqueId())) {
                getOnRide().remove(player.getUniqueId());
                c.eject();
                p.sendMessage(ChatColor.GREEN + "You were ejected from the ride!");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean sitDown(CPlayer player, ArmorStand stand) {
        if (!state.equals(FlatState.LOADING) || getOnRide().size() >= 18 || getOnRide().contains(player.getUniqueId())) {
            return false;
        }
        UUID uuid = stand.getUniqueId();
        for (Horse c : getHorses()) {
            Optional<ArmorStand> s = c.getStand();
            if (!s.isPresent()) continue;
            if (s.get().getUniqueId().equals(uuid)) {
                c.addPassenger(player.getBukkitPlayer());
                getOnRide().add(player.getUniqueId());
            }
        }
        return false;
    }

    @Override
    public void move() {
        if (!isSpawned() || speed == 0) {
            return;
        }
        double tableChange = 360 / (speed * 20 * 60);
        for (Horse c : getHorses()) {
            double a = (c.getAngle() + tableChange) % 360;
            c.setAngle(a);
            Optional<ArmorStand> stand = c.getStand();
            if (!stand.isPresent()) {
                continue;
            }
            ArmorStand s = stand.get();
            final float old = s.getLocation().getYaw();
            Location n;
            if (c.isInside()) {
                n = getRelativeLocation(a, horseRadius2, center);
            } else {
                n = getRelativeLocation(a, horseRadius1, center);
            }
            double height = getHeight(c.getTicks(), c.isPositive());
            n.setY(height);
            n.setYaw((float) (old - tableChange));
//            s.setHeadPose(s.getHeadPose().add(0, -head, 0));
            Location p = n.clone();
            p.setY(poleY);
            teleport(c.getPole(), getRelativeLocation(a + poleAngle, riderRadius, p));
            teleport(s, n);
//            c.getPole().setHeadPose(c.getPole().getHeadPose().add(0, Math.toRadians(-tableChange), 0));
        }
    }

    @Override
    public void despawn() {
        if (!isSpawned()) {
            return;
        }
        spawned = false;
        for (Horse h : getHorses()) {
            h.despawn();
        }
    }

    public double getHeight(double ticks, boolean positive) {
        double time = ticks / 20;
        double h = 0.5 * Math.sin(0.5 * Math.PI * time * heightSpeed);
        if (!positive) {
            h *= -1;
        }
        return h + center.getY();
    }

    private Horse getHorse(int i) {
        return horses.get(i - 1);
    }

    private void ejectPlayers() {
        for (Horse c : getHorses()) {
            c.eject();
        }
        getOnRide().clear();
    }

    private class Horse {
        private UUID stand;
        @Getter private ArmorStand pole;
        @Getter @Setter private double angle;
        private double ticks;
        @Getter private boolean positive;
        @Getter private boolean inside;

        public Horse(ArmorStand stand, double angle, boolean inside) {
            this.stand = stand.getUniqueId();
            this.angle = angle;
            if (inside) {
                this.positive = (angle % 60) == 15;
            } else {
                this.positive = (angle % 60) == 0;
            }
            this.inside = inside;
            Location loc = getRelativeLocation(angle + poleAngle, riderRadius, stand.getLocation());
            loc.setY(poleY);
            this.pole = lock(stand.getWorld().spawn(loc, ArmorStand.class));
            pole.setGravity(false);
            pole.setVisible(false);
            pole.setHelmet(new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 2));
            pole.setHeadPose(pole.getHeadPose().setY(stand.getHeadPose().getY()));
        }

        public Optional<ArmorStand> getStand() {
            return center.getWorld().getEntitiesByClass(ArmorStand.class).stream().filter(a -> a.getUniqueId().equals(stand)).findFirst();
        }

        public double getTicks() {
            return ticks++;
        }

        public void addPassenger(Player player) {
            Optional<ArmorStand> s = getStand();
            if (!s.isPresent()) {
                return;
            }
            ArmorStand stand = s.get();
            if (!stand.getPassengers().isEmpty()) {
                return;
            }
            stand.addPassenger(player);
        }

        public CPlayer getPassenger() {
            Optional<ArmorStand> s = getStand();
            if (!s.isPresent()) {
                return null;
            }
            ArmorStand stand = s.get();
            if (stand == null || stand.getPassengers() == null || stand.getPassengers().isEmpty()) {
                return null;
            }
            Entity pass = stand.getPassengers().get(0);
            if (!(pass instanceof Player)) {
                return null;
            }
            return Core.getPlayerManager().getPlayer((Player) pass);
        }

        public void eject() {
            Optional<ArmorStand> s = getStand();
            if (!s.isPresent()) {
                return;
            }
            ArmorStand stand = s.get();
            CPlayer passenger = getPassenger();
            if (passenger != null) {
                final Location playerLoc = passenger.getLocation();
                stand.removePassenger(passenger.getBukkitPlayer());
                Location loc = getExit();
                if (state.equals(FlatState.LOADING)) {
                    loc = stand.getLocation().add(0, 1, 0);
                    loc.setYaw(playerLoc.getYaw());
                    loc.setPitch(playerLoc.getPitch());
                }
                passenger.teleport(loc);
                getOnRide().remove(passenger.getUniqueId());
            }
        }

        public void despawn() {
            eject();
            Optional<ArmorStand> s = getStand();
            s.ifPresent(Entity::remove);
            pole.remove();
        }
    }
}
