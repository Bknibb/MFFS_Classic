# Agent Context: MFFS Classic (Minecraft Mod)

This project is a Minecraft mod titled **Modular Force Field System (MFFS) Classic**

## Project Stack
- **Minecraft Version:** 26.1.2
- **Mod Loader:** NeoForge 26.1.2.82
- **Java Version:** 25 (Microsoft OpenJDK recommended for consistency with Minecraft Launcher)
- **Build System:** Gradle (Kotlin DSL)
- **Mod ID:** `mffs`
- **Main Package:** `dev.su5ed.mffs`

## Commands
### Build & Development
- `./gradlew build`: Full build of the project.
- `./gradlew assemble`: Compile and package the mod jar.
- `./gradlew runClient`: Launch Minecraft client with the mod loaded.
- `./gradlew runServer`: Launch Minecraft server with the mod loaded.
- `./gradlew runData`: Run data generation to update JSON assets and data.

### Testing & Verification
- `./gradlew test`: Run unit tests (if any).
- `./gradlew downloadAssets prepareClientRun`: Ensure local environment is ready for client testing.

## Project Structure
- `src/main/java/dev/su5ed/mffs/`: Core Java source code.
    - `setup/`: Registry classes (Blocks, Items, Menus, etc.) using `DeferredRegister`.
    - `block/` / `item/`: Implementation of game objects.
    - `network/`: Networking code (Custom payloads, Packet handlers).
    - `datagen/`: Data generation providers.
    - `MFFSMod.java`: Main entry point.
- `src/main/resources/`: Static assets and metadata.
    - `META-INF/neoforge.mods.toml`: Mod configuration and metadata.
    - `assets/mffs/`: Textures, models, and lang files (mostly generated).
    - `data/mffs/`: Recipes, loot tables, and tags (mostly generated).
- `src/generated/resources/`: Output directory for data generation.

## Development Guidelines

### Registry & Object Initialization
- Use `DeferredRegister` for all registry entries (Blocks, Items, Fluids, etc.).
- Initialization is handled in `dev.su5ed.mffs.setup.Mod*` classes.
- Ensure `init(bus)` is called in the `MFFSMod` constructor.

### Data Generation (Datagen)
- **Prefer datagen over manual JSON editing.**
- Most assets (models, blockstates) and data (recipes, loot tables) are managed via `dev.su5ed.mffs.datagen`.
- After changing block/item properties or adding new ones, run `./gradlew runData`.

### Networking
- Uses NeoForge's `CustomPacketPayload` system.
- Packets are registered in `dev.su5ed.mffs.network.Network`.
- Packet handling is split between `mainThreadHandler` and `ClientPacketHandler`.

### Code Style & Patterns
- Follow existing patterns in the codebase.
- Use `MFFSMod.location("path")` for creating `ResourceLocation` instances.
- Use `BaseBlockEntity` as the parent class for block entities requiring common MFFS logic.

## Common Pitfalls
- **Sides:** Always be mindful of Client vs. Server code. Use `@EventBusSubscriber(value = Dist.CLIENT)` for client-only event handlers.
- **Resource Names:** Always use the `mffs` namespace.
- **NeoForge API:** This project uses NeoForge, not legacy Forge. Refer to NeoForge documentation for 1.21.2.
