package net.juyoh.wetrooms.world.dimension;

import net.juyoh.wetrooms.WetRooms;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public final class CopyWetrooms {

    public static void copyRegionToWorld(MinecraftServer server) throws IOException, URISyntaxException {
        File path = new File(server.getSavePath(WorldSavePath.ROOT).resolve("dimensions/wetrooms/wetroomsdim/region").toString());
        if (!path.exists()){path.mkdirs();}
        String target = server.getSavePath(WorldSavePath.ROOT).resolve("dimensions/wetrooms/wetroomsdim/region").toString();
        System.out.println(target);
        Path TO = Path.of(target);
        Path FROM = Paths.get(CopyWetrooms.class.getResource("/assets/wetrooms/world/wetroomsdim/region").toURI());

        try {
            if (TO.toFile().list().length > 0) {
                WetRooms.LOGGER.info("Not replacing dimension as it has contents.");
            } else {
                FileUtils.copyDirectory(FROM.toFile(), TO.toFile());
                WetRooms.LOGGER.info("Replacing region files in 'wetroomsdim' becuase none were found");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}