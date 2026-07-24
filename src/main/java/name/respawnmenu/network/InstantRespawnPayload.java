package name.respawnmenu.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record InstantRespawnPayload() implements CustomPacketPayload {
    public static final Type<InstantRespawnPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath("respawn-menu", "instant_respawn"));
    public static final StreamCodec<RegistryFriendlyByteBuf, InstantRespawnPayload> CODEC = StreamCodec.unit(new InstantRespawnPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
