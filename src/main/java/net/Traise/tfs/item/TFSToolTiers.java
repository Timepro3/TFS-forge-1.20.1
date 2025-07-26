package net.Traise.tfs.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class TFSToolTiers {
    public static final Tier ROCK = new Tier() {
                @Override
                public int getLevel() {
                    return 0;
                }

                @Override
                public int getUses() {
                    return 70;
                }

                @Override
                public float getSpeed() {
                    return 2.5F;
                }

                @Override
                public float getAttackDamageBonus() {
                    return 0.5F;
                }

                @Override
                public int getEnchantmentValue() {
                    return 12;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(TFSItems.ROCK.get());
                }
    };

    public static final Tier BRONZE = new Tier() {
        @Override
        public int getLevel() {
            return 1;
        }

        @Override
        public int getUses() {
            return 170;
        }

        @Override
        public float getSpeed() {
            return 4.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 1.0F;
        }

        @Override
        public int getEnchantmentValue() {
            return 9;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(TFSItems.BRONZE_INGOT.get());
        }
    };

    public static final Tier SILVER = new Tier() {
        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public int getUses() {
            return 200;
        }

        @Override
        public float getSpeed() {
            return 5.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 1.5F;
        }

        @Override
        public int getEnchantmentValue() {
            return 17;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(TFSItems.SILVER_INGOT.get());
        }
    };

    public static final Tier STEEL = new Tier() {
        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public int getUses() {
            return 500;
        }

        @Override
        public float getSpeed() {
            return 7.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 2.5F;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(TFSItems.STEEL_INGOT.get());
        }
    };

}
