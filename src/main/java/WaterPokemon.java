/**
 * Our specialty WaterPokemon that inherits from our Pokemon class.
 */
public class WaterPokemon extends Pokemon {

    /**
     * The value we will be setting our specialty probability to.
     */
    final double specProb = 0.2;
    /**
     * We now have a specialty attack for the water pokemon! Hydro cannon attack!
     */
    public String specialtyAttack;

    /**
     * The probability that we execute the specialty attack.
     */
    public double specialtyProbability;

    /**
     * Our WaterPokemon constructor.
     * Constructs a new WaterPokemon with the Pokemon Type of water.
     * WaterPokemon's specialty attack is HYDRO CANNON
     *
     */
    public WaterPokemon() {
        pokeType = PokemonType.WATER;
        specialtyAttack = "HYDRO CANNON";
        specialtyProbability = specProb;
    }
    /**
     * We have to modify our attack slightly to account for our new specialty attacks.
     * We will first have a normal attack that we're used to with our normal Pokemon.
     * Then, if the normal attack wasn't already fatal, we check to see if the Pokemon can
     * execute a fatal specialty attack to immediately win the game.
     * <p>
     * Requirements for a specialty attack: <br>
     *  - The normal attack wasn't already fatal <br>
     *  - The type of the opponent pokemon is not WATER <br>
     *  - The probability of the specialty attack is greater than
     *    the next random value <br>
     *  - We should print out the specialty attack used <br>
     *  - Opponent's HP goes to 0 <br>
     *  - Returns true that the opponent has been defeated and game is over <br>
     *
     * Hint: Math.random() is static, or you can use the random import <br>
     *
     * Example of a specialty attack hitting:
     * Bubba is attacking Pikachu <br>
     * Bubba rolls an attack bonus of 6 <br>
     * Pikachu rolls a defense bonus of 13 <br>
     * The attack missed! <br>
     *
     * Bubba executes a specialty attack... HYDRO CANNON!!! <br>
     * Pikachu has been defeated! <br>
     *
     *
     * @param opponent the Pokemon to attack
     * @return whether or not the game has ended
     *
     * Implement this.
     */
    public boolean attack(final Pokemon opponent) {
        /*
         * Get the attack and defense bonuses.
         */
        int attackBonus = d20.roll();
        int defenseBonus = d20.roll();

        /*
         * Roll the damage dice and compute total damage.
         */
        int damage1 = d6.roll();
        int damage2 = d6.roll();
        int damage3 = d6.roll();
        int totalDamage = damage1 + damage2 + damage3;

        System.out.println(this.getName() + " is attacking " + opponent.getName());
        System.out.println(this.getName() + " rolls an attack bonus of " + attackBonus);
        System.out.println(opponent.getName() + " rolls a defense bonus of " + defenseBonus);


        /*
         * Did our attack hit?
         */
        if ((getAttackLevel() + attackBonus) > (opponent.getDefenseLevel() + defenseBonus)) {
            System.out.println("The attack hits dealing 3-D6 damage!");
            System.out.println("The rolls are " + damage1 + ", " + damage2 + ", " + "and "
                    + damage3 + " totaling: " + totalDamage + " damage!");

            /*
             * Does opponent have hit points left?
             */
            if ((opponent.getHitPoints() - totalDamage) > 0) {
                System.out.println(opponent.getName() + " has "
                        + (opponent.getHitPoints() - totalDamage) + " hit points");
            } else {
                System.out.println(opponent.getName() + " has been defeated!");
            }
            /*
             * Set the opponents hitPoints appropriately.
             */
            opponent.setHitPoints(opponent.getHitPoints() - totalDamage);
        } else {
            System.out.println("The attack missed!");
        }
        System.out.println(" ");
        if (opponent.getHitPoints() < 1 && !opponent.getClass().equals(this.getClass()) && specProb > Math.random()) {
            System.out.println(specialtyAttack);
            opponent.setHitPoints(0);
            return true;
        }
        return (opponent.getHitPoints() < 1);
    }
}

