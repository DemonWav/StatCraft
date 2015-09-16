package wav.demon.StatCraft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import wav.demon.StatCraft.Magic.BucketCode;
import wav.demon.StatCraft.Magic.FishCode;
import wav.demon.StatCraft.Magic.MoveCode;
import wav.demon.StatCraft.Magic.ProjectilesCode;

public class MagicTest {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //           BUCKETS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testBucketWater() {
        assertEquals(BucketCode.WATER, BucketCode.fromCode((byte) 0));
    }

    @Test
    public void testBucketLava() {
        assertEquals(BucketCode.LAVA, BucketCode.fromCode((byte) 1));
    }

    @Test
    public void testBucketMilk() {
        assertEquals(BucketCode.MILK, BucketCode.fromCode((byte) 2));
    }

    @Test
    public void testBucketNull() {
        assertNull(BucketCode.fromCode((byte) 3));
    }

    @Test
    public void testBucketNegative() {
        assertNull(BucketCode.fromCode((byte) -1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //           FISH CAUGHT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testFishCodeFish() {
        assertEquals(FishCode.FISH, FishCode.fromCode((byte) 0));
    }

    @Test
    public void testFishCodeTreasure() {
        assertEquals(FishCode.TREASURE, FishCode.fromCode((byte) 1));
    }

    @Test
    public void testFishCodeJunk() {
        assertEquals(FishCode.JUNK, FishCode.fromCode((byte) 2));
    }

    @Test
    public void testFishNull() {
        assertNull(FishCode.fromCode((byte) 3));
    }

    @Test
    public void testFishNegative() {
        assertNull(FishCode.fromCode((byte) -1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //           MOVEMENT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testMoveWalking() {
        assertEquals(MoveCode.WALKING, MoveCode.fromCode((byte) 0));
    }

    @Test
    public void testMoveCrouching() {
        assertEquals(MoveCode.CROUCHING, MoveCode.fromCode((byte) 1));
    }

    @Test
    public void testMoveSprinting() {
        assertEquals(MoveCode.SPRINTING, MoveCode.fromCode((byte) 2));
    }

    @Test
    public void testMoveSwimming() {
        assertEquals(MoveCode.SWIMMING, MoveCode.fromCode((byte) 3));
    }

    @Test
    public void testMoveFalling() {
        assertEquals(MoveCode.FALLING, MoveCode.fromCode((byte) 4));
    }

    @Test
    public void testMoveClimbing() {
        assertEquals(MoveCode.CLIMBING, MoveCode.fromCode((byte) 5));
    }

    @Test
    public void testMoveFlying() {
        assertEquals(MoveCode.FLYING, MoveCode.fromCode((byte) 6));
    }

    @Test
    public void testMoveDiving() {
        assertEquals(MoveCode.DIVING, MoveCode.fromCode((byte) 7));
    }

    @Test
    public void testMoveMinecart() {
        assertEquals(MoveCode.MINECART, MoveCode.fromCode((byte) 8));

    }

    @Test
    public void testMoveBoat() {
        assertEquals(MoveCode.BOAT, MoveCode.fromCode((byte) 9));
    }

    @Test
    public void testMovePig() {
        assertEquals(MoveCode.PIG, MoveCode.fromCode((byte) 10));
    }

    @Test
    public void testMoveHorse() {
        assertEquals(MoveCode.HORSE, MoveCode.fromCode((byte) 11));
    }

    @Test
    public void testMoveNull() {
        assertNull(MoveCode.fromCode((byte) 12));
    }

    @Test
    public void testMoveNegative() {
        assertNull(MoveCode.fromCode((byte) -1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //           PROJECTILES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testProjectilesNormalArrow() {
        assertEquals(ProjectilesCode.NORMAL_ARROW, ProjectilesCode.fromCode((short) 0));
    }

    @Test
    public void testProjectilesFlamingArrow() {
        assertEquals(ProjectilesCode.FLAMING_ARROW, ProjectilesCode.fromCode((short) 1));
    }

    @Test
    public void testProjectilesEnderPearl() {
        assertEquals(ProjectilesCode.ENDER_PEARL, ProjectilesCode.fromCode((short) 2));
    }

    @Test
    public void testProjectilesUnhatchedEgg() {
        assertEquals(ProjectilesCode.UNHATCHED_EGG, ProjectilesCode.fromCode((short) 3));
    }

    @Test
    public void testProjectilesHatchedEgg() {
        assertEquals(ProjectilesCode.HATCHED_EGG, ProjectilesCode.fromCode((short) 4));
    }

    @Test
    public void testProjectilesSnowball() {
        assertEquals(ProjectilesCode.SNOWBALL, ProjectilesCode.fromCode((short) 5));
    }

    @Test
    public void testProjectilesFourHatchedEgg() {
        assertEquals(ProjectilesCode.FOUR_HATCHED_EGG, ProjectilesCode.fromCode((short) 6));
    }

    @Test
    public void testProjectilesNull() {
        assertNull(ProjectilesCode.fromCode((short) 7));
    }

    @Test
    public void testProjectilesNegative() {
        assertNull(ProjectilesCode.fromCode((short) -1));
    }
}
