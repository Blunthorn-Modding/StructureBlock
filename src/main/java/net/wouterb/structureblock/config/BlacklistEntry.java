package net.wouterb.structureblock.config;

import java.util.List;

public class BlacklistEntry {
    public final String structure;
    public final List<String> blocks;

    public BlacklistEntry(String structure, List<String> blocks) {
        this.structure = structure;
        this.blocks = blocks;
    }
}