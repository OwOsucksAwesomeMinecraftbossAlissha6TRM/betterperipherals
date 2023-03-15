package de.srendi.betterperipherals.betterperipherals.miner;

import dan200.computercraft.api.peripheral.IPeripheral;
import de.srendi.betterperipherals.betterperipherals.Registration;
import de.srendi.betterperipherals.betterperipherals.miner.MinerPeripheral;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

public class Miner_TileEntity extends BlockEntity {

    public Miner_TileEntity(BlockPos pos, BlockState state) {
        super(Registration.Miner_TileEntity.get(), pos, state);
    }

    /**
     * Our peripheral, we create a new peripheral for each new tile entity
     */
    protected MinerPeripheral peripheral = new MinerPeripheral(Miner_TileEntity.this);
    private LazyOptional<IPeripheral> peripheralCap;

    /**
     * When a computer modem tries to wrap our block, the modem will call getCapability to receive our peripheral.
     * Then we just simply return a {@link LazyOptional} with our Peripheral
     */
    @Override
    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction direction) {
        if (cap == CAPABILITY_PERIPHERAL) {
            if (peripheralCap == null) {
                peripheralCap = LazyOptional.of(() -> peripheral);
            }
            return peripheralCap.cast();
        }
        return super.getCapability(cap, direction);
    }
}
