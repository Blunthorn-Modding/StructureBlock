package net.wouterb.structureblock.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.wouterb.structureblock.StructureBlock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class LockedStructures {
    public final String[] breaking_and_placing;
    public final String[] breaking;
    public final String[] placement;
    public final List<BlacklistEntry> blacklist;

    public LockedStructures(){
        breaking_and_placing = new String[]{};
        breaking = new String[]{};
        placement = new String[]{};
        blacklist = Collections.emptyList();
    }

    public LockedStructures(String[] breaking, String[] placement, String[] breaking_and_placing, List<BlacklistEntry> blacklist) {
        this.breaking_and_placing = breaking_and_placing;
        this.breaking = breaking;
        this.placement = placement;
        this.blacklist = blacklist;
    }

    public static LockedStructures generateDefaultLockedStructures() {
        File defaultValuesFile = ModConfigManager.getLockedStructuresFile();
        try {
            Files.createFile(defaultValuesFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LockedStructures lockedStructures = new LockedStructures();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(lockedStructures);
        try (FileWriter writer = new FileWriter(defaultValuesFile.toString())) {
            writer.write(json);
        } catch (IOException e) {
            StructureBlock.LOGGER.error(e.toString());
        }

        return lockedStructures;
    }
}